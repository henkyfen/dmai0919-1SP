package gui;

import exception.StorageException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleCreateCustomer extends JDialog {

    private final SaleCreateMenu saleCreateMenu;

    private final JPanel contentPanel = new JPanel();
    private final JTextField cardCode;

    /**
     * Create the dialog.
     */
    public SaleCreateCustomer(SaleCreateMenu saleCreateMenu) {
        this.saleCreateMenu = saleCreateMenu;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
        {
            JLabel lblCustomerCardCode = new JLabel("Customer Card Code:");
            contentPanel.add(lblCustomerCardCode);
        }
        {
            cardCode = new JTextField();
            contentPanel.add(cardCode);
            cardCode.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onOk();
                    }
                });
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
            }
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SaleCreateCustomer dialog = new SaleCreateCustomer(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onOk() {
        String code = cardCode.getText();
        try {
            SaleCreateMenu.salesController.findCustomer(code);
            saleCreateMenu.updateInfo();
            this.dispose();
        } catch (StorageException e) {
            Message message = new Message(e.getMessage());
            message.setVisible(true);
        }
    }

}
