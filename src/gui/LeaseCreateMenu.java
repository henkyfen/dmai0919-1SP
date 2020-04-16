package gui;

import controller.LeaseController;
import exception.StorageException;
import model.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaseCreateMenu extends JDialog {
    public static LeaseController leaseController;

    private final JPanel contentPane;
    private final JLabel lblCustomerName;
    private final JLabel lblProductsAmout;

    /**
     * Create the frame.
     */
    public LeaseCreateMenu() {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnCustomer = new JButton("Customer");
        btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCustomer.setBounds(127, 126, 157, 21);
        contentPane.add(btnCustomer);
        btnCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customer();
            }
        });

        JButton btnScanProduct = new JButton("Products");
        btnScanProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnScanProduct.setBounds(127, 156, 157, 21);
        contentPane.add(btnScanProduct);
        btnScanProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scanProduct();
            }
        });

        JButton btnFinishLease = new JButton("Finish Lease");
        btnFinishLease.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnFinishLease.setBounds(127, 186, 157, 21);
        contentPane.add(btnFinishLease);
        btnFinishLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finishLease();
            }
        });

        JButton btnCancelLease = new JButton("Cancel Lease");
        btnCancelLease.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCancelLease.setBounds(127, 216, 157, 21);
        contentPane.add(btnCancelLease);

        JLabel lblCustomer = new JLabel("Customer:");
        lblCustomer.setBounds(127, 0, 107, 15);
        contentPane.add(lblCustomer);

        lblCustomerName = new JLabel("Anonymous");
        lblCustomerName.setBounds(209, 0, 229, 15);
        contentPane.add(lblCustomerName);

        JLabel lblProducts = new JLabel("Products:");
        lblProducts.setBounds(127, 12, 70, 15);
        contentPane.add(lblProducts);

        lblProductsAmout = new JLabel("none");
        lblProductsAmout.setBounds(209, 12, 70, 15);
        contentPane.add(lblProductsAmout);
        btnCancelLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelSale();
            }
        });

        leaseController = new LeaseController();
        leaseController.createLease(Login.loggedInEmployee);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaleCreateMenu frame = new SaleCreateMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void customer() {
        LeaseCreateCustomer menu = new LeaseCreateCustomer(this);
        menu.setVisible(true);
    }

    private void scanProduct() {
        LeaseCreateLeaseItemList menu = new LeaseCreateLeaseItemList(this);
        menu.setVisible(true);
    }

    private void finishLease() {
        try {
            leaseController.leaseFinished();
            this.dispose();
        } catch (StorageException e) {
            Message message = new Message(e.getMessage());
            message.setVisible(true);
        }
    }

    private void cancelSale() {
        this.dispose();
    }

    public void updateInfo() {
        Customer customer = leaseController.getLease().getCustomer();
        if (customer != null) {
            this.lblCustomerName.setText(customer.getName() + " ( " + customer.getCardCode() + " )");
        }

        int amount = leaseController.getLease().getLeaseProducts().size();

        this.lblProductsAmout.setText(amount == 0 ? "none" : "" + amount);
    }
}
