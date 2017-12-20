package com.example.demo.component;

import java.util.Vector;

public class Chart {

    Vector<State> chart;

    public Chart() {
        chart = new Vector<State>();
    }

    void addState(State s) {
        if (!chart.contains(s)) {
            chart.add(s);
        }
    }

    public State getState(int i) {
        if (i < 0 || i >= chart.size()) {
            return null;
        }
        return (State) chart.get(i);
    }

    public int size() {
        return chart.size();
    }

	public Vector<State> getChart() {
		return chart;
	}

	public void setChart(Vector<State> chart) {
		this.chart = chart;
	}
    
    
}
