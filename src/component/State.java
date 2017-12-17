package component;

public class State {

    private String lhs;
    private RHS rhs;
    private int i, j;

    public State(String lhs, RHS rhs, int i, int j) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.i = i;
        this.j = j;
    }

    public String getLHS() {
        return lhs;
    }

    public RHS getRHS() {
        return rhs;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getPriorToDot() {
        return rhs.getPriorToDot();
    }

    public String getAfterDot() {
        return rhs.getAfterDot();
    }

    public boolean isDotLast() {
        return rhs.isDotLast();
    }
}
