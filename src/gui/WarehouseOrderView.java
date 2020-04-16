package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.LeaseController;
import controller.WarehouseController;
import model.Lease;
import model.LeaseProduct;
import model.WarehouseOrder;
import model.WarehouseOrderItem;

public class WarehouseOrderView extends JDialog {

    private final JLabel worker;
    private final JLabel date;
    private final JLabel price;
    private final JPanel contentPanel = new JPanel();
    private WarehouseOrder order;
    private JList<WarehouseOrderItem> list = new JList<>();
    private DefaultListModel<WarehouseOrderItem> listRepresentation;
    private JLabel lblWarehouseOrdedView;

    /**
     * Create the dialog.
     */
    public WarehouseOrderView(WarehouseOrder order) {
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

        lblWarehouseOrdedView = new JLabel("New Warehouse Order");
        lblWarehouseOrdedView.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblWarehouseOrdedView.setBounds(10, 10, 200, 24);
        getContentPane().add(lblWarehouseOrdedView);


        JLabel lblWorker = new JLabel("Worker: ");
        lblWorker.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblWorker.setBounds(10, 68, 72, 13);
        getContentPane().add(lblWorker);


        JLabel lblDate = new JLabel("Created: ");
        lblDate.setBounds(10, 131, 56, 16);
        getContentPane().add(lblDate);

        worker= new JLabel();
        worker.setBounds(94, 68, 116, 22);
        getContentPane().add(worker);

        date = new JLabel();
        date.setBounds(94, 131, 116, 22);
        getContentPane().add(date);

        price = new JLabel();
        price.setBounds(94, 195, 116, 22);
        getContentPane().add(price);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(10, 195, 56, 16);
        getContentPane().add(lblPrice);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(220, 12, 253, 270);
        getContentPane().add(scrollPane);

        scrollPane.setViewportView(list);

        init(order);
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

    private void init(WarehouseOrder order) {
        this.order = order;
        if (order != null) {
            fillFields();
            listRepresentation = new DefaultListModel<>();
            WarehouseController wCtr = new WarehouseController();
            List<WarehouseOrderItem> dataList = order.getOrderItems();

            for (WarehouseOrderItem w: dataList) {
                listRepresentation.addElement(w);
            }
            list.setModel(listRepresentation);
            list.setCellRenderer(new AnotherListCell());
        }
    }

    private void fillFields() {
        lblWarehouseOrdedView.setText("Warehouse Order");
        this.worker.setText("" + order.getWorker().getName());
        this.price.setText("" + order.getPrice());
        this.date.setText("" + order.getDate());
    }

    private void goBack() {
        this.setVisible(false);
    }
}
