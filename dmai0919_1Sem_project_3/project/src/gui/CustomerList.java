package gui;

import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final CustomerController custctrl = new CustomerController();
    private JList<Customer> customers = new JList<>();
    private DefaultListModel<Customer> listRepresentation;

    public CustomerList() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
        }
        {
            JScrollPane scrollPane = new JScrollPane();
            contentPanel.add(scrollPane);

            customers = new JList<>();
            scrollPane.setViewportView(customers);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRemove = new JButton("Remove");
                buttonPane.add(btnRemove);
                btnRemove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeCustomer();
                    }
                });
            }
            {
                JButton btnShowDetails = new JButton("Show Details");
                buttonPane.add(btnShowDetails);
                btnShowDetails.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showUpdatedDetails();
                    }
                });
            }
            {
                JButton btnCancel = new JButton("Cancel");
                btnCancel.setActionCommand("Cancel");
                buttonPane.add(btnCancel);
                btnCancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        goBack();
                    }
                });

            }
        }
        init();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CustomerList dialog = new CustomerList();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises the dialog and lists all customers
     */
    private void init() {
        listRepresentation = new DefaultListModel<>();

        List<Customer> dataList = custctrl.getAll();
        for (Customer c : dataList) {
            listRepresentation.addElement(c);
        }
        customers.setModel(listRepresentation);

        customers.setCellRenderer(new AnotherListCell());
    }

    /**
     * Closes the dialog
     */
    private void goBack() {
        this.setVisible(false);
    }

    /**
     * Removes a selected customer
     */
    private void removeCustomer() {
        int index = customers.getSelectedIndex();
        Customer cc = customers.getSelectedValue();
        if (index >= 0 && index < listRepresentation.getSize()) {
            listRepresentation.remove(index);
            custctrl.deleteCustomer(cc);
        }
    }

    /**
     * Updates the list
     */
    private void showUpdatedDetails() {
        Customer cc = customers.getSelectedValue();
        int index = customers.getSelectedIndex();

        CustomerView dialog = new CustomerView(cc);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

        listRepresentation.set(index, cc);

    }

}
