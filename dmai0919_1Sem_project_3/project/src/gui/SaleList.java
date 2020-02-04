package gui;

import controller.SalesController;
import model.Sale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaleList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final SalesController salesController = new SalesController();
    private JList<Sale> list = new JList<>();
    private DefaultListModel<Sale> listRepresentation;

    /**
     * Create the dialog.
     */
    public SaleList() {
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

            list = new JList<>();
            scrollPane.setViewportView(list);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRemove = new JButton("Return");
                buttonPane.add(btnRemove);
                btnRemove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        returnSale();
                    }
                });
            }
            {
                JButton btnShowDetails = new JButton("Show Details");
                buttonPane.add(btnShowDetails);
                btnShowDetails.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showDetails();
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
        init();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SaleList dialog = new SaleList();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        listRepresentation = new DefaultListModel<>();

        List<Sale> dataList = salesController.getSales();
        
        for (Sale sale : dataList) {
            listRepresentation.addElement(sale);
        }
        list.setModel(listRepresentation);

        list.setCellRenderer(new AnotherListCell());
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void returnSale() {
        int index = list.getSelectedIndex();
        Sale sale = list.getSelectedValue();
        if (index >= 0 && index < listRepresentation.getSize()) {
            listRepresentation.remove(index);
            salesController.returnSale(sale);
        }
    }

    private void showDetails() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            Sale sale = list.getSelectedValue();
            SaleView dialog = new SaleView(sale);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }


    }

}
