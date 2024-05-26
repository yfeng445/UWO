package simple.tree.avl.model;

/**
 * AVL树中失衡状态枚举
 */
public enum RotateType {

    LL("左左失衡"),
    LR("左右失衡"),
    RR("右右失衡"),
    RL("右左失衡"),
    Balance("平衡状态");

    private String desc;

    private RotateType(String _desc) {
        desc = _desc;
    }

    public String getDesc() {
        return desc;
    }
}
