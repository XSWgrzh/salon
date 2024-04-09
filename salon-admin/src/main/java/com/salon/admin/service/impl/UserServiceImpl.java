package com.salon.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.UserListQry;
import com.salon.admin.DTO.UserProfileDTO;
import com.salon.admin.mapper.MenusMapper;
import com.salon.admin.mapper.RoleUserMapper;
import com.salon.admin.mapper.UserMapper;
import com.salon.admin.service.UserService;
import com.salon.common.core.VO.UserVO;
import com.salon.common.core.exception.GlobalException;
import com.salon.common.core.model.admin.SysRoleUser;
import com.salon.common.core.model.admin.SysUser;
import com.salon.common.core.utils.ConvertUtil;
import com.salon.common.core.utils.IdGenerator;
import com.salon.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.service.impl
 * @Project：salon
 * @name：UserServiceImpl
 * @Date：2024/2/20 16:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenusMapper menusMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserProfileDTO getProfile() {
        SysUser sysUser = this.userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, UserUtil.getUserName()));
        UserProfileDTO userProfileDTO = ConvertUtil.sourceToTarget(sysUser, UserProfileDTO.class);
        List<String> permissionsList = menusMapper.getPermissions();
        userProfileDTO.setPermissionList(permissionsList);
        return userProfileDTO;
    }

    @Override
    public Page<UserVO> findList(UserListQry qry) {
        Page<SysUser> sysUserPage = this.userMapper.selectPage(new Page<>(qry.getPageNum(), qry.getPageNum()),
                new LambdaQueryWrapper<SysUser>().like(SysUser::getUsername, qry.getUsername()));
        Page<UserVO> userVOPage = new Page<>(qry.getPageNum(), qry.getPageSize());
        userVOPage.setTotal(sysUserPage.getTotal());
        userVOPage.setRecords(ConvertUtil.sourceToTarget(sysUserPage.getRecords(), UserVO.class));
        return userVOPage;
    }

    @Override
    public UserVO findById(Long id) {
        SysUser sysUser = this.userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getId, SysUser::getUsername, SysUser::getStatus).eq(SysUser::getId, id));
        UserVO userVO = ConvertUtil.sourceToTarget(sysUser, UserVO.class);
        List<SysRoleUser> sysRoleUsers = this.roleUserMapper.selectList(new LambdaQueryWrapper<SysRoleUser>().eq(SysRoleUser::getUserId, id));
        userVO.setRoleIds(sysRoleUsers.stream().map(item -> item.getRoleId()).collect(Collectors.toList()));
        return userVO;
    }

    @Override
    public Boolean removeUser(String[] ids) {
        int count = this.userMapper.deleteBatchIds(Arrays.asList(ids));
        return count > 0;
    }

    @Transactional
    @Override
    public Boolean createUser(UserVO userVO) {
        Long count = this.userMapper.selectCount(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, userVO.getUsername()));
        if (count > 0) {
            throw new GlobalException("用户名已存在，请重新输入");
        }
        SysUser sysUser = ConvertUtil.sourceToTarget(userVO, SysUser.class);
        sysUser.setId(IdGenerator.defaultSnowflakeId());
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        int insert = this.userMapper.insert(sysUser);

        this.addRoleUser(userVO.getRoleIds(), userVO.getId());
        return insert > 0;
    }

    @Transactional
    @Override
    public Boolean modifyUser(UserVO userVO) {
        SysUser sysUser = ConvertUtil.sourceToTarget(userVO, SysUser.class);
        sysUser.setUpdateTime(LocalDateTime.now());
        int update = this.userMapper.updateById(sysUser);
        this.roleUserMapper.delete(new LambdaQueryWrapper<SysRoleUser>().eq(SysRoleUser::getUserId, userVO.getId()));
        this.addRoleUser(userVO.getRoleIds(), userVO.getId());
        return update > 0;
    }

    private void addRoleUser(List<String> roleIdList, String userId) {
        List<SysRoleUser> sysRoleUserList = roleIdList.stream().map(item ->
                new SysRoleUser(userId, item)
        ).collect(Collectors.toList());
        this.roleUserMapper.insertList(sysRoleUserList);
    }

    @Override
    public Boolean resetPassword(String id, String password) {
        int update = this.userMapper.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, id).set(SysUser::getPassword, passwordEncoder.encode(password)));
        return update > 0;
    }

    @Override
    public void modifyStatus(String id, Integer status) {
        int update = this.userMapper.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, id).set(SysUser::getStatus, status));
    }
}