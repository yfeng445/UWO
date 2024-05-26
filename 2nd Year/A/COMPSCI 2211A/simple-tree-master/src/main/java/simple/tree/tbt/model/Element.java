package simple.tree.tbt.model;

/**
 * @Author: Q-WHai
 * @Date: Created in 10:22 2019/04/08
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
