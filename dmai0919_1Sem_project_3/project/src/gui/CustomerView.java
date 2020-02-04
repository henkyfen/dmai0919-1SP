package gui;

import controller.CustomerController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerView extends JDialog {
    private final JTextField name;
    private final JTextField phoneNumber;
    private final JTextField email;
    private final JTextField address;
    private final JTextField cardCode;
    //\TODO CustomerGroup
    private Customer customer;
    private JLabel lblCustomerView;

    /**
     * Create the dialog.
     */
    public CustomerView(Customer c) {
        setBounds(100, 100, 450, 317);
        getContentPane().setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 232, 436, 31);
            buttonPane.setBackground(Color.LIGHT_GRAY);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        okClicked();
                    }
                });
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        goBack();
                    }
                });
            }
        }

        lblCustomerView = new JLabel("New Customer");
        lblCustomerView.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCustomerView.setBounds(10, 10, 132, 24);
        getContentPane().add(lblCustomerView);

        JLabel lblCode = new JLabel("Card code: ");
        lblCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCode.setBounds(70, 60, 120, 16);
        getContentPane().add(lblCode);

        cardCode = new JTextField();
        cardCode.setBounds(172, 60, 116, 22);
        cardCode.setColumns(10);
        getContentPane().add(cardCode);


        JLabel lblName = new JLabel("Name: ");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblName.setBounds(70, 130, 120, 16);
        getContentPane().add(lblName);

        name = new JTextField();
        name.setBounds(172, 95, 116, 22);
        getContentPane().add(name);
        name.setColumns(10);


        JLabel lblPhone = new JLabel("Phone number: ");
        lblPhone.setBounds(70, 165, 120, 16);
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        getContentPane().add(lblPhone);

        JLabel lblEmail = new JLabel("Email address: ");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEmail.setBounds(70, 200, 120, 16);
        getContentPane().add(lblEmail);

        JLabel lblAddress = new JLabel("Address: ");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblAddress.setBounds(70, 96, 120, 16);
        getContentPane().add(lblAddress);

        phoneNumber = new JTextField();
        phoneNumber.setBounds(172, 130, 116, 22);
        getContentPane().add(phoneNumber);
        phoneNumber.setColumns(10);

        email = new JTextField();
        email.setBounds(172, 165, 116, 22);
        getContentPane().add(email);
        email.setColumns(10);

        address = new JTextField();
        address.setBounds(172, 200, 116, 22);
        getContentPane().add(address);
        address.setColumns(10);

        init(c);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            CustomerView dialog = new CustomerView(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises the dialog
     * @param c the customer whose details are to be displayed (if null, it's a new customer dialog)
     */
    private void init(Customer c) {
        this.customer = c;
        if (c != null) {
            fillFields();
        }
    }

    /**
     * Fills the textfields with the details of the customer
     */
    private void fillFields() {
        lblCustomerView.setText("Edit customer");
        this.name.setText("" + customer.getName());
        this.phoneNumber.setText("" + customer.getPhoneNumber());
        this.email.setText("" + customer.getEmail());
        this.address.setText("" + customer.getAddress());
        this.cardCode.setText("" + customer.getCardCode());
    }

    private void goBack() {
        this.setVisible(false);
    }

    /**
     * If the init() method got no Customer passed, it creates a new customer via the controller. Else it updates
     * the details of the customer. After the data is saved, it closes and disposes of the dialog.
     */
    private void okClicked() {
        String name = this.name.getText();
        String phoneNumber = this.phoneNumber.getText();
        String email = this.email.getText();
        String address = this.address.getText();
        String cardCode = this.cardCode.getText();
        CustomerController cctrl = new CustomerController();
        if (customer != null) {
            cctrl.updateCustomer(name, phoneNumber, email, address, cardCode);
        } else {
            cctrl.createCustomer(name, phoneNumber, email, address, cardCode);
        }
        this.setVisible(false);
        this.dispose();
    }
}
