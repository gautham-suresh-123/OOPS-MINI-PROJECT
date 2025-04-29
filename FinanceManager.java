package project;
import java.util.ArrayList;

class Transaction {
    private double amount;
    private String date;
    private String description;

    public Transaction(double amount, String date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void displayDetails() {
        System.out.println("Transaction: " + amount + ", Description: " + description + ", Date: " + date);
    }
}

class Income extends Transaction {
    private String source;

    public Income(double amount, String date, String source) {
        super(amount, date, source);
        this.source = source;
    }

    @Override
    public void displayDetails() {
        System.out.println("Income: " + getAmount() + ", Source: " + source + ", Date: " + getDate());
    }
}

class Expense extends Transaction {
    private String category;

    public Expense(double amount, String date, String category) {
        super(amount, date, category);
        this.category = category;
    }

    @Override
    public void displayDetails() {
        System.out.println("Expense: " + getAmount() + ", Category: " + category + ", Date: " + getDate());
    }
}

public class FinanceManager {
    private ArrayList<Transaction> transactions;
    private double balance;
    private double budget;

    public FinanceManager(double budget) {
        transactions = new ArrayList<>();
        this.budget = budget;
        this.balance = 0.0;
    }

    public void addIncome(double amount, String date, String source) {
        Income income = new Income(amount, date, source);
        transactions.add(income);
        balance += amount;
    }

    public void addExpense(double amount, String date, String category) {
        Expense expense = new Expense(amount, date, category);
        transactions.add(expense);
        balance -= amount;
        checkBudget();
    }

    public double displayBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    private void checkBudget() {
        double totalExpenses = 0;
        for (Transaction t : transactions) {
            if (t instanceof Expense) {
                totalExpenses += t.getAmount();
            }
        }
        if (totalExpenses > budget * 0.8) {
            System.out.println("Warning: Expenses exceed 80% of budget!");
        }
    }
}
