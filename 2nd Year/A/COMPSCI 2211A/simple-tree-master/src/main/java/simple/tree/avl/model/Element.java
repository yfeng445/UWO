package simple.tree.avl.model;

/**
 * @Description TODO
 * @Author      Q-WHai
 * @Date        2019/4/5 12:55
 */
public class Element {
    private int val;
    public Element(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.format("[%d]", getVal());
    }
}
