package com.salon.common.core.VO;

import com.salon.common.core.utils.TreeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.VO
 * @Project：salon
 * @name：MenuVO
 * @Date：2024/1/16 16:10
 */
@Data
public class MenuVO extends TreeUtil.TreeNode implements Serializable {


    @Schema(name = "icon", description = "图标")
    private String icon;

    @Schema(name = "type", description = "类型 0菜单 1按钮")
    private Integer type;

    @Schema(name = "sort", description = "排序")
    private Integer sort;

    @Schema(name = "url", description = "路径")
    private String url;

    @Schema(name = "permission", description = "权限标识")
    private String permission;

    @Schema(name = "visible", description = "状态 0显示 1隐藏")
    private Integer visible;

}
