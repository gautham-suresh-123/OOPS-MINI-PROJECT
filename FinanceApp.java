package project;
import javax.swing.*;
import java.awt.*;

public class FinanceApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private final FinanceManager manager;

    public FinanceApp() {
        String budgetInput = JOptionPane.showInputDialog(this, "Enter your monthly budget:");
        double budget = 0;
        try {
            budget = Double.parseDouble(budgetInput);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid budget. Defaulting to 0.");
        }

        manager = new FinanceManager(budget);

        setTitle("Personal Finance Manager");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton incomeBtn = new JButton("Add Income");
        JButton expenseBtn = new JButton("Add Expense");
        JButton balanceBtn = new JButton("View Balance");
        JButton transactionsBtn = new JButton("View Transactions");
        JButton exitBtn = new JButton("Exit");

        add(incomeBtn);
        add(expenseBtn);
        add(balanceBtn);
        add(transactionsBtn);
        add(exitBtn);

        incomeBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter income amount:"));
                String source = JOptionPane.showInputDialog(this, "Enter income source:");
                String date = JOptionPane.showInputDialog(this, "Enter date (YYYY-MM-DD):");
                manager.addIncome(amount, date, source);
                JOptionPane.showMessageDialog(this, "Income added.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        expenseBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter expense amount:"));
                String category = JOptionPane.showInputDialog(this, "Enter expense category:");
                String date = JOptionPane.showInputDialog(this, "Enter date (YYYY-MM-DD):");
                manager.addExpense(amount, date, category);
                JOptionPane.showMessageDialog(this, "Expense added.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        balanceBtn.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Current Balance: " + manager.displayBalance())
        );

        transactionsBtn.addActionListener(e -> {
            JFrame frame = new JFrame("Transactions");
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(this);
            JTextArea area = new JTextArea();
            area.setEditable(false);
            for (Transaction t : manager.getTransactions()) {
                area.append(t.getClass().getSimpleName() + ": " +
                            t.getAmount() + ", Description: " +
                            t.getDescription() + ", Date: " +
                            t.getDate() + "\n");
            }
            frame.add(new JScrollPane(area));
            frame.setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceApp::new);
    }
}
