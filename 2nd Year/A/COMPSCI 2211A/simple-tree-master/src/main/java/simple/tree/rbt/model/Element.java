package simple.tree.rbt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 15:45 2019/04/09
 */
public class Element {

    private int val;

    public Element(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return String.format("[%d]", getVal());
    }
}
