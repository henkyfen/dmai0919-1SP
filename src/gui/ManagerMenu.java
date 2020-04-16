package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenu extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public ManagerMenu() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnProductMenu = new JButton("Product menu");
        btnProductMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnProductMenu.setBounds(127, 161, 157, 21);
        contentPane.add(btnProductMenu);
        btnProductMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToProductMenu();
            }
        });

        JButton btnEmployeeMenu = new JButton("Employee menu");
        btnEmployeeMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnEmployeeMenu.setBounds(127, 193, 157, 21);
        contentPane.add(btnEmployeeMenu);
        btnEmployeeMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToEmployeeMenu();
            }
        });

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ManagerMenu dialog = new ManagerMenu();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToProductMenu() {
        ProductMenu productMenu = new ProductMenu();
        productMenu.setVisible(true);
    }

    private void goToEmployeeMenu() {
        EmployeeMenu employeeMenu = new EmployeeMenu();
        employeeMenu.setVisible(true);
    }

}
