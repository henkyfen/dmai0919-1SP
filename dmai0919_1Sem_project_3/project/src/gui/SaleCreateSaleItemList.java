package gui;

import exception.StorageException;
import model.SaleItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaleCreateSaleItemList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final JTextField amount;
    private final JTextField barCode;
    private final SaleCreateMenu saleCreateMenu;
    private JList<SaleItem> list = new JList<>();
    private DefaultListModel<SaleItem> listRepresentation;

    /**
     * Create the dialog.
     */
    public SaleCreateSaleItemList(SaleCreateMenu menu) {
        this.saleCreateMenu = menu;
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
            SaleCreateSaleItemList dialog = new SaleCreateSaleItemList(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        listRepresentation = new DefaultListModel<>();

        List<SaleItem> dataList = SaleCreateMenu.salesController.getSale().getItems();

        for (SaleItem item : dataList) {
            listRepresentation.addElement(item);
        }
        list.setModel(listRepresentation);

        list.setCellRenderer(new AnotherListCell());
    }

    private void goBack() {
        this.setVisible(false);
        this.saleCreateMenu.updateInfo();
    }

    private void removeItem() {
        int index = list.getSelectedIndex();
        SaleItem item = list.getSelectedValue();
        if (index != -1) {
            try {
                try {
                    int amount = Integer.parseInt(this.amount.getText());
                    System.out.println(amount);
                    SaleCreateMenu.salesController.removeProduct(item.getProduct().getBarCode(), amount);
                    init();
                } catch (NumberFormatException e) {
                    SaleCreateMenu.salesController.removeProduct(
                            item.getProduct().getBarCode(),
                            item.getAmount()
                    );
                    init();
                }
            } catch (StorageException e) {
                Message message = new Message(e.getMessage());
                message.setVisible(true);
            }
        }
    }

    private void addItem() {
        int index = list.getSelectedIndex();
        SaleItem item = list.getSelectedValue();
        if (index == -1) {
            try {
                String barCode = this.barCode.getText();
                int amount = Integer.parseInt(this.amount.getText());
                SaleCreateMenu.salesController.findProduct(barCode, amount);
            } catch (StorageException e) {
                Message message = new Message(e.getMessage());
                message.setVisible(true);
            } catch (NumberFormatException ignored) {

            }
        } else {
            try {
                String barCode = item.getProduct().getBarCode();
                int amount = Integer.parseInt(this.amount.getText());
                SaleCreateMenu.salesController.findProduct(barCode, amount);
            } catch (StorageException e) {
                Message message = new Message(e.getMessage());
                message.setVisible(true);
            } catch (NumberFormatException ignored) {

            }
        }
        init();
    }
}
