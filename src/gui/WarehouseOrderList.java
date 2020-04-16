package gui;

import controller.SalesController;
import controller.WarehouseController;
import model.Sale;
import model.WarehouseOrder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WarehouseOrderList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final WarehouseController controller = new WarehouseController();
    private JList<WarehouseOrder> list = new JList<>();
    private DefaultListModel<WarehouseOrder> listRepresentation;

    /**
     * Create the dialog.
     */
    public WarehouseOrderList() {
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

            list = new JList<WarehouseOrder>();
            scrollPane.setViewportView(list);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
            WarehouseOrderList dialog = new WarehouseOrderList();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        listRepresentation = new DefaultListModel<>();

        List<WarehouseOrder> dataList = controller.getOrders();
        
        for (WarehouseOrder order : dataList) {
            listRepresentation.addElement(order);
        }
        list.setModel(listRepresentation);

        list.setCellRenderer(new AnotherListCell());
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void showDetails() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            WarehouseOrder order = list.getSelectedValue();
			
			  WarehouseOrderView dialog = new WarehouseOrderView(order);
			  dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			  dialog.setVisible(true);
			 
        }


    }

}
