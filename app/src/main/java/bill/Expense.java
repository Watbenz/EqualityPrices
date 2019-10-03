package bill;

import android.annotation.SuppressLint;

import java.util.IllegalFormatException;

public class Expense {
    private String name;
    private double cost;
    private double originalCost;
    StringBuilder log;
    public static final int PERCENT = 1;
    public static final int VALUE = 2;

    public Expense(String name) {
        init(name, 0);
    }

    public Expense(String name, double cost) {
        init(name, cost);
    }

    private void init(String name, double cost) {
        this.originalCost = cost;
        this.cost = cost;
        this.log = new StringBuilder();
    }

    private void manage(String type, double val, boolean isDiscount) {
        if (isDiscount)  { val = -val; }

        switch (type) {
            case "percent":
                this.cost = cost * (1 + (val / 100.0));
                break;
            case "value":
                this.cost = cost + val;
                break;
        }
    }

    public void changeValue(double val, int type, boolean isDiscount) throws IllegalAccessException {
        double oldCost = cost;
        switch (type) {
            case PERCENT:
                if (val < 0 || val > 100) {
                    throw new IllegalAccessException("Percent must be in range 0 - 100");
                }
                manage("percent", val, isDiscount);
                break;

            case VALUE:
                if (val < 0 || val > this.cost) {
                    throw new IllegalAccessException("Value must be in range 0 - " + cost + " (cost)");
                }
                manage("value", val, isDiscount);
                break;
        }
        @SuppressLint("DefaultLocale")
        String l = String.format("%s %.2f%s from %.2f to %.2f\n", (isDiscount? "discount": "charge"), val, (type == PERCENT? "%":" Baht"), oldCost, cost);
        this.log.append(l);
    }

    public void reset() {
        this.cost = this.originalCost;
        this.log = new StringBuilder();
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public StringBuilder getLog() {
        return log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
