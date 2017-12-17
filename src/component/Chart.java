package component;

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

    State getState(int i) {
        if (i < 0 || i >= chart.size()) {
            return null;
        }
        return (State) chart.get(i);
    }

    public int size() {
        return chart.size();
    }
}
