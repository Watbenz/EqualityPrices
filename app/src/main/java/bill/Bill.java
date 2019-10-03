package bill;

import java.util.ArrayList;

public class Bill extends Expense {
    private ArrayList<Expense> list;
    private double total;

    public Bill() {
        super("Bill");
    }

    public Bill(String name) {
        super(name);
    }

    private void init(String name) {
        this.list = new ArrayList<>();
        this.total = 0;
    }

    public void addExpense(Expense e) {
        list.add(e);
        total += e.getCost();
    }

    public void delete(int i) {
        Expense tmp = list.get(i);
        log.append("delete ");
        list.remove(tmp);
        total -= tmp.getCost();
    }

    public Expense get(int i) {
        return list.get(i);
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Expense> getList() {
        return list;
    }

    public double getTotal() {
        return total;
    }
}
