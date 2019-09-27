package bill;

import java.util.ArrayList;

class Expense {
    private double cost;
    private final double originalCost;
    private ArrayList<String> hist;
    private int histPtr;

    public Expense(double cost) {
        this.cost = cost;
        this.originalCost = cost;
        this.hist = new ArrayList<>();
        this.histPtr = 0;
    }

    private void manage(String type, double cost, double val) {
        switch (type) {
            case "percent":
                this.cost = cost * ((val / 100.0) + 1);
                break;
            case "value":
                this.cost = cost - val;
                break;
        }
    }

    private void writeLog(String event, String oldVal, String type, String value) {
        if (histPtr != hist.size()) {
            hist.subList(histPtr + 1, hist.size()).clear();
        }
        hist.add(event + " " + oldVal + " " + type + " " + value);
    }

    public boolean discount(String type, double discountVal) {
        String temp = String.valueOf(cost);
        switch (type) {
            case "percent":
                if (discountVal < 0 || discountVal > 100) {
                    return false;
                }
                manage("percent", cost, discountVal);
                break;

            case "value":
                if (discountVal < 0 || discountVal > this.cost) {
                    return false;
                }
                manage("value", cost, discountVal);
                break;
        }
        writeLog("-", temp, type, String.valueOf(discountVal));
        return true;
    }

    public void undo() {
        if (hist.size() <= 0) return;
        String lastAction = hist.get(histPtr);
        String[] actions = lastAction.split(" ");
        this.cost = Double.parseDouble(actions[1]);
        histPtr--;
    }

    public void redo() {
        if (histPtr >= hist.size()) return;
        histPtr++;
        String nextAction = hist.get(histPtr);
        String[] actions = nextAction.split(" ");
        this.cost = Double.parseDouble(actions[1]);
    }

    public void clearHist() {
        this.cost = this.originalCost;
        hist.clear();
    }

    public double getCost() {
        return cost;
    }

    public double getOriginalCost() {
        return originalCost;
    }
}
