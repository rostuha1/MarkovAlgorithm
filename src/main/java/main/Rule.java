package main;

public class Rule {
    private String left;
    private String right;

    public Rule(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }
    public String getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " → " + right;
    }
}
