package gui;

import controller.LeaseController;
import model.Lease;
import model.LeaseProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LeaseView extends JDialog {

    private final JLabel leaseId;
    private final JLabel employee;
    private final JLabel leased;
    private final JLabel date;
    private final JLabel customer;
    private final JLabel price;
    private final JLabel leaseTime;
    private final JPanel contentPanel = new JPanel();
    private Lease lease;
    private JList<LeaseProduct> list = new JList<>();
    private DefaultListModel<LeaseProduct> listRepresentation;
    private JLabel lblLeaseView;

    /**
     * Create the dialog.
     */
    public LeaseView(Lease lease) {
        setBounds(100, 100, 506, 373);
        getContentPane().setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 295, 488, 31);
            buttonPane.setBackground(Color.LIGHT_GRAY);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane);

            {
                JScrollPane scrollPane = new JScrollPane();
                contentPanel.add(scrollPane);

                list = new JList<>();
                scrollPane.setViewportView(list);
            }
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        goBack();
                    }
                });
            }
        }

        lblLeaseView = new JLabel("New lease");
        lblLeaseView.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblLeaseView.setBounds(10, 10, 132, 24);
        getContentPane().add(lblLeaseView);

        JLabel lblCustomer = new JLabel("Customer: ");
        lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCustomer.setBounds(10, 51, 72, 13);
        getContentPane().add(lblCustomer);

        customer = new JLabel();
        customer.setBounds(94, 45, 116, 24);
        getContentPane().add(customer);

        JLabel lblEmployee = new JLabel("Employee: ");
        lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEmployee.setBounds(10, 120, 72, 13);
        getContentPane().add(lblEmployee);

        leaseId = new JLabel();
        leaseId.setBounds(94, 82, 116, 20);
        getContentPane().add(leaseId);

        JLabel lblLeased = new JLabel("Is leased: ");
        lblLeased.setBounds(10, 157, 72, 16);
        getContentPane().add(lblLeased);

        JLabel lblDate = new JLabel("Created: ");
        lblDate.setBounds(10, 192, 56, 16);
        getContentPane().add(lblDate);

        JLabel lblLeaseId = new JLabel("Lease ID: ");
        lblLeaseId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblLeaseId.setBounds(10, 77, 72, 16);
        getContentPane().add(lblLeaseId);

        employee = new JLabel();
        employee.setBounds(94, 115, 116, 22);
        getContentPane().add(employee);

        leased = new JLabel();
        leased.setBounds(94, 154, 116, 22);
        getContentPane().add(leased);

        date = new JLabel();
        date.setBounds(94, 189, 116, 22);
        getContentPane().add(date);

        price = new JLabel();
        price.setBounds(94, 220, 116, 22);
        getContentPane().add(price);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(10, 221, 56, 16);
        getContentPane().add(lblPrice);

        leaseTime = new JLabel();
        leaseTime.setBounds(94, 260, 116, 22);
        getContentPane().add(leaseTime);

        JLabel lblLeaseTime = new JLabel("Lease time:");
        lblLeaseTime.setBounds(10, 263, 72, 16);
        getContentPane().add(lblLeaseTime);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(220, 12, 253, 270);
        getContentPane().add(scrollPane);

        scrollPane.setViewportView(list);

        init(lease);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SaleView dialog = new SaleView(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(Lease lease) {
        this.lease = lease;
        if (lease != null) {
            fillFields();
            listRepresentation = new DefaultListModel<>();
            LeaseController lCtr = new LeaseController();
            List<LeaseProduct> dataList = lease.getLeaseProducts();

            for (LeaseProduct lp : dataList) {
                listRepresentation.addElement(lp);
            }
            list.setModel(listRepresentation);
            list.setCellRenderer(new AnotherListCell());
        }
    }

    private void fillFields() {
        lblLeaseView.setText("Lease");
        this.customer.setText("" + lease.getCustomer().getName());
        this.employee.setText("" + lease.getEmployee().getName());
        this.leaseId.setText("" + lease.getID());
        this.price.setText("" + lease.getPrice());
        this.date.setText("" + lease.getCreated());
        this.leaseTime.setText("" + lease.getLeaseTime());
        this.leased.setText("" + lease.getLeasedString());
    }

    private void goBack() {
        this.setVisible(false);
    }
}
