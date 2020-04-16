package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleMenu extends JDialog {

    private final JPanel contentPane;

    /**
     * Create the frame.
     */
    public SaleMenu() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnCreateSale = new JButton("Create Sale");
        btnCreateSale.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCreateSale.setBounds(127, 126, 157, 21);
        contentPane.add(btnCreateSale);
        btnCreateSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createSale();
            }
        });

        JButton btnCreateLease = new JButton("Create Lease");
        btnCreateLease.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnCreateLease.setBounds(127, 156, 157, 21);
        contentPane.add(btnCreateLease);
        btnCreateLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createLease();
            }
        });

        JButton btnShowSales = new JButton("Show Sales");
        btnShowSales.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnShowSales.setBounds(127, 186, 157, 21);
        contentPane.add(btnShowSales);
        btnShowSales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listSales();
            }
        });


        JButton btnShowLeases = new JButton("Show Leases");
        btnShowLeases.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnShowLeases.setBounds(127, 216, 157, 21);
        contentPane.add(btnShowLeases);
        btnShowLeases.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listLeases();
            }
        });
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaleMenu dialog = new SaleMenu();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createSale() {
        SaleCreateMenu menu = new SaleCreateMenu();
        menu.setVisible(true);
    }

    private void createLease() {
        LeaseCreateMenu menu = new LeaseCreateMenu();
        menu.setVisible(true);
    }

    private void listLeases() {
        LeaseList list = new LeaseList();
        list.setVisible(true);
    }


    private void listSales() {
        SaleList list = new SaleList();
        list.setVisible(true);
    }

}
