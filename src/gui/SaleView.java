package gui;

import controller.SalesController;
import model.Sale;
import model.SaleItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleView extends JDialog {
    private final JLabel picked;
    private final JLabel customer;
    private final JLabel created;
    private final JLabel employee;
    private final JLabel price;
    private final JLabel id;
    private final JPanel contentPanel = new JPanel();
    private Sale sale;
    private DefaultListModel<SaleItem> listRepresentation;
    private SalesController salesController = new SalesController();
    private JList<SaleItem> list;
    private JLabel lblProductView;

    /**
     * Create the dialog.
     */
    public SaleView(Sale sale) {
        setBounds(100, 100, 500, 390);
        getContentPane().setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 307, 474, 33);
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
                        goBack();
                    }
                });
            }
        }
        lblProductView = new JLabel("New sale");
        lblProductView.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblProductView.setBounds(10, 10, 132, 24);
        getContentPane().add(lblProductView);

        JLabel lblCustomer = new JLabel("Customer: ");
        lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCustomer.setBounds(30, 45, 68, 24);
        getContentPane().add(lblCustomer);

        customer = new JLabel();
        customer.setBounds(110, 47, 116, 24);
        getContentPane().add(customer);

        JLabel lblEmployee = new JLabel("Employee:");
        lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEmployee.setBounds(30, 84, 96, 16);
        getContentPane().add(lblEmployee);

        employee = new JLabel();
        employee.setFont(new Font("Tahoma", Font.PLAIN, 13));
        employee.setBounds(110, 84, 116, 20);
        getContentPane().add(employee);

        JLabel lblId = new JLabel("ID: ");
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblId.setBounds(30, 113, 68, 13);
        getContentPane().add(lblId);

        id = new JLabel();
        id.setBounds(110, 113, 116, 22);
        getContentPane().add(id);

        JLabel lblPrice = new JLabel("Price: ");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPrice.setBounds(30, 139, 56, 27);
        getContentPane().add(lblPrice);

        price = new JLabel();
        price.setBounds(110, 148, 116, 22);
        getContentPane().add(price);

        JLabel lblCreated = new JLabel("Created: ");
        lblCreated.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCreated.setBounds(30, 179, 72, 16);
        getContentPane().add(lblCreated);

        created = new JLabel();
        created.setBounds(110, 183, 116, 22);
        getContentPane().add(created);


        JLabel lblPicked = new JLabel("Picked:");
        lblPicked.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPicked.setBounds(30, 224, 52, 14);
        getContentPane().add(lblPicked);

        picked = new JLabel();
        picked.setBounds(110, 218, 116, 20);
        getContentPane().add(picked);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(223, 31, 216, 264);
        getContentPane().add(scrollPane);

        list = new JList<SaleItem>();
        scrollPane.setViewportView(list);

        init(sale);
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

    private void init(Sale sale) {
        this.sale = sale;
        if (sale != null) {
            fillFields();
            listRepresentation = new DefaultListModel<>();

            for (SaleItem saleItem : sale.getItems()) {
                listRepresentation.addElement(saleItem);
            }
            list.setModel(listRepresentation);

            list.setCellRenderer(new AnotherListCell());
        }

    }

    private void fillFields() {
        lblProductView.setText("Sale");
        this.customer.setText("" + sale.getCustomer().getName());
        this.employee.setText("" + sale.getEmployee().getName());
        this.id.setText("" + sale.getId());
        this.price.setText("" + sale.getTotalPrice());
        this.created.setText("" + sale.getCreated());
        this.picked.setText("" + sale.getOrderPicked());

    }

    private void goBack() {
        this.setVisible(false);
    }
}
