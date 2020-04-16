package gui;

import exception.StorageException;
import model.LeaseProduct;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LeaseCreateLeaseItemList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final JTextField amount;
    private final JTextField barCode;
    private final LeaseCreateMenu leaseCreateMenu;
    private JList<LeaseProduct> list = new JList<>();
    private DefaultListModel<LeaseProduct> listRepresentation;

    /**
     * Create the dialog.
     */
    public LeaseCreateLeaseItemList(LeaseCreateMenu menu) {
        this.leaseCreateMenu = menu;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(12, 12, 282, 204);
            contentPanel.add(scrollPane);

            list = new JList<>();
            scrollPane.setRowHeaderView(list);
        }

        JLabel lblBarCode = new JLabel("Bar Code");
        lblBarCode.setBounds(341, 12, 70, 15);
        contentPanel.add(lblBarCode);

        barCode = new JTextField();
        barCode.setBounds(324, 39, 114, 19);
        contentPanel.add(barCode);
        barCode.setColumns(10);
        {
            JLabel lblAmount = new JLabel("Amount");
            lblAmount.setBounds(341, 153, 70, 15);
            contentPanel.add(lblAmount);
        }

        amount = new JTextField();
        amount.setBounds(324, 180, 114, 19);
        contentPanel.add(amount);
        amount.setColumns(10);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRemove = new JButton("Remove");
                buttonPane.add(btnRemove);
                btnRemove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeItem();
                    }
                });
            }
            {
                JButton btnShowDetails = new JButton("Add");
                buttonPane.add(btnShowDetails);
                btnShowDetails.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addItem();
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
            LeaseCreateLeaseItemList dialog = new LeaseCreateLeaseItemList(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        listRepresentation = new DefaultListModel<>();
        List<LeaseProduct> dataList = LeaseCreateMenu.leaseController.getLease().getLeaseProducts();
        System.out.println(dataList.size());
        for (LeaseProduct item : dataList) {
            listRepresentation.addElement(item);
        }
        list.setModel(listRepresentation);

        list.setCellRenderer(new AnotherListCell());
    }

    private void goBack() {
        this.setVisible(false);
        this.leaseCreateMenu.updateInfo();
    }

    private void removeItem() {
        int index = list.getSelectedIndex();
        LeaseProduct item = list.getSelectedValue();
        if (index >= 0 && index < listRepresentation.getSize()) {
            listRepresentation.remove(index);
            try {
                LeaseCreateMenu.leaseController.removeLeaseProduct(item.getLeasedCode());
                init();
            } catch (NumberFormatException ignored) {

            }
        }
    }

    private void addItem() {
        try {
            String barCode = this.barCode.getText();
            LeaseCreateMenu.leaseController.findLeaseProduct(barCode);
        } catch (StorageException e) {
            Message message = new Message(e.getMessage());
            message.setVisible(true);
        } catch (NumberFormatException ignored) {

        }
        init();
    }

}
