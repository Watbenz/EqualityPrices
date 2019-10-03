package com.example.myapplication;

import org.junit.Test;

import bill.Expense;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void expenseCheck() {
        Expense expense = new Expense("expense", 200);
        try {
            expense.changeValue(15, Expense.PERCENT, true);
            expense.changeValue(15, Expense.PERCENT, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(expense.getCost());
        System.out.println(expense.getLog());
    }
}