package gui;

import controller.SalesController;
import exception.StorageException;
import model.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleCreateMenu extends JFrame {

    public static SalesController salesController;

    private final JPanel contentPane;
    private final JLabel lblCustomerName;
    private final JLabel lblProductsAmout;

    /**
     * Create the frame.
     */
    public SaleCreateMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnCustomer = new JButton("Customer");
        btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCustomer.setBounds(127, 97, 157, 21);
        contentPane.add(btnCustomer);
        btnCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customer();
            }
        });

        JButton btnScanProduct = new JButton("Products");
        btnScanProduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnScanProduct.setBounds(127, 130, 157, 21);
        contentPane.add(btnScanProduct);
        btnScanProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scanProduct();
            }
        });

        JButton btnFinishSale = new JButton("Finish Sale");
        btnFinishSale.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnFinishSale.setBounds(127, 163, 157, 21);
        contentPane.add(btnFinishSale);
        btnFinishSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finishSale();
            }
        });

        JButton btnFinishOrder = new JButton("Finish Order");
        btnFinishOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnFinishOrder.setBounds(127, 196, 157, 21);
        contentPane.add(btnFinishOrder);
        btnFinishOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finishOrder();
            }
        });

        JButton btnCancelSale = new JButton("Cancel Sale");
        btnCancelSale.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCancelSale.setBounds(127, 229, 157, 21);
        contentPane.add(btnCancelSale);

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
        btnCancelSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelSale();
            }
        });

        salesController = new SalesController();
        salesController.createSale(Login.loggedInEmployee);
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
        SaleCreateCustomer menu = new SaleCreateCustomer(this);
        menu.setVisible(true);
    }

    private void scanProduct() {
        SaleCreateSaleItemList menu = new SaleCreateSaleItemList(this);
        menu.setVisible(true);
    }

    private void finishSale() {
        try {
            salesController.saleFinished(false);
            this.dispose();
        } catch (StorageException e) {
            Message message = new Message(e.getMessage());
            message.setVisible(true);
        }
    }

    private void finishOrder() {
        try {
            salesController.saleFinished(true);
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
        Customer customer = salesController.getSale().getCustomer();
        if (customer != null) {
            this.lblCustomerName.setText(customer.getName() + " ( " + customer.getCardCode() + " )");
        }

        int amount = salesController.getSale().getItems().size();

        this.lblProductsAmout.setText(amount == 0 ? "none" : "" + amount);
    }
}
