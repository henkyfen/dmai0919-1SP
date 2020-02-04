package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public CustomerMenu() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnItems = new JMenu("Customers");
        menuBar.add(mnItems);

        JMenuItem mntmListCustomer = new JMenuItem("List Customers");
        mntmListCustomer.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnItems.add(mntmListCustomer);
        mntmListCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCustomerList();
            }
        });

        JMenuItem mntmAddCustomer = new JMenuItem("Add Customer");
        mntmAddCustomer.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnItems.add(mntmAddCustomer);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        mntmAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCustomerView();
            }
        });

        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAddCustomer.setBounds(127, 161, 157, 21);
        contentPane.add(btnAddCustomer);
        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCustomerView();
            }
        });

        JButton btnListCustomers = new JButton("List Customers");
        btnListCustomers.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnListCustomers.setBounds(127, 193, 157, 21);
        contentPane.add(btnListCustomers);
        btnListCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCustomerList();
            }
        });
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CustomerMenu dialog = new CustomerMenu();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToCustomerView() {
        CustomerView customerView = new CustomerView(null);
        customerView.setVisible(true);
    }

    private void goToCustomerList() {
        CustomerList customerList = new CustomerList();
        customerList.setVisible(true);
    }
}