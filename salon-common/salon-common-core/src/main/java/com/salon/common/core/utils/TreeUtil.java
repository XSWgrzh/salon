package com.salon.common.core.utils;

import com.salon.common.core.exception.GlobalException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.utils
 * @Project：salon
 * @name：TreeUtil
 * @Date：2024/1/17 16:06
 */
public class TreeUtil {

    private static String COMMA = ",";

    public static <T> TreeNode<T> rootRootNode(String name) {
        return new TreeNode<>("0", name, null, "0", new ArrayList<>(0));
    }

    public static <T> TreeNode<T> rootRootNode() {
        return rootRootNode("根节点");
    }

    public static <T extends TreeNode<T>> T buildTreeNode(List<T> treeNodes, Class<T> clazz) {
        return buildTreeNode(treeNodes, ConvertUtil.sourceToTarget(rootRootNode(), clazz));
    }

    public static <T extends TreeNode<T>> T buildTreeNode(List<T> treeNodes, T rootNode) {
        if (null == rootNode) {
            throw new GlobalException("请构造根节点");
        }
        List<T> nodes = new ArrayList<>(treeNodes);
        nodes.add(rootNode);
        // list转map
        Map<String, T> nodeMap = new LinkedHashMap<>(nodes.size());
        for (T node : nodes) {
            nodeMap.put(node.getId(), node);
        }
        for (T treeNo : nodes) {
            T parent = nodeMap.get(treeNo.getPid());
            if (parent != null && treeNo.getPid().equals(parent.getId())) {
                treeNo.setPath(parent.getPath() + COMMA + treeNo.getId());
                parent.getChildren().add(treeNo);
            }
        }
        return rootNode;
    }

    public static <T extends TreeNode<T>> List<T> buildTreeNode(List<T> treeNodes) {
        List<T> nodes = new ArrayList<>(treeNodes.size());
        // list转map
        Map<String, T> nodeMap = new LinkedHashMap<>(treeNodes.size());
        for (T node : treeNodes) {
            nodeMap.put(node.getId(), node);
        }
        for (T treeNo : treeNodes) {
            T parent = nodeMap.get(treeNo.getPid());
            if (parent != null && treeNo.getPid().equals(parent.getId())) {
                treeNo.setPath(parent.getPath() + COMMA + treeNo.getId());
                parent.getChildren().add(treeNo);
                continue;
            }
            nodes.add(treeNo);
        }
        return nodes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "TreeNode", description = "树节点")
    public static class TreeNode<T> {

        @Schema(name = "id", description = "ID")
        private String id;

        @Schema(name = "name", description = "名称")
        private String name;

        @Schema(name = "pid", description = "父ID")
        private String pid;

        @Schema(name = "path", description = "节点")
        private String path;

        @Schema(name = "children", description = "子节点")
        private List<T> children = new ArrayList<>(0);

    }

}
