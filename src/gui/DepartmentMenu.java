package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentMenu extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public DepartmentMenu() {

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

        JMenu mnItems = new JMenu("Items");
        menuBar.add(mnItems);

        JMenuItem mntmListProduct = new JMenuItem("List Department");
        mntmListProduct.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnItems.add(mntmListProduct);
        mntmListProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToDepartmentList();
            }
        });

        JMenuItem mntmAddProduct = new JMenuItem("Add Department");
        mntmAddProduct.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnItems.add(mntmAddProduct);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        mntmAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToDepartmentView();
            }
        });

        JButton btnAddProduct = new JButton("Add Department");
        btnAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAddProduct.setBounds(127, 161, 157, 21);
        contentPane.add(btnAddProduct);
        btnAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToDepartmentView();
            }
        });

        JButton btnNewButton = new JButton("List Departments");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton.setBounds(127, 193, 157, 21);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToDepartmentList();
            }
        });
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DepartmentMenu dialog = new DepartmentMenu();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToDepartmentView() {
        DepartmentView departmentView = new DepartmentView();
        departmentView.setVisible(true);
    }

    private void goToDepartmentList() {
        DepartmentList DepartmentList = new DepartmentList();
        DepartmentList.setVisible(true);
    }

}
