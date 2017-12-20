package com.example.demo.component;

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

    public String getLhs() {
		return lhs;
	}



	public void setLhs(String lhs) {
		this.lhs = lhs;
	}



	public RHS getRhs() {
		return rhs;
	}

	public void setRhs(RHS rhs) {
		this.rhs = rhs;
	}



	public int getI() {
		return i;
	}



	public void setI(int i) {
		this.i = i;
	}



	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
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
