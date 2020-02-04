package gui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehouseMenu extends JDialog {


    private final JPanel contentPanel = new JPanel();
    private JButton btnNewButton_1;
    private JButton btnNewButton;

    /**
     * Create the dialog.
     */
    public WarehouseMenu() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            btnNewButton_1 = new JButton("Accept Order");
            btnNewButton_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    acceptOrder();
                }
            });
        }
        {
            btnNewButton = new JButton("List Warehouse Orders");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                	listOrders();
                }
            });
        }
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGap(179)
                                .addComponent(btnNewButton_1)
                                .addContainerGap(140, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
                                .addContainerGap(153, Short.MAX_VALUE)
                                .addComponent(btnNewButton)
                                .addGap(110))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGap(160)
                                .addComponent(btnNewButton_1)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnNewButton)
                                .addGap(26))
        );
        contentPanel.setLayout(gl_contentPanel);

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            WarehouseMenu dialog = new WarehouseMenu();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acceptOrder() {
        WarehouseOrderInit init = new WarehouseOrderInit();
        init.setVisible(true);
    }
    
    private void listOrders() {
    	WarehouseOrderList list = new WarehouseOrderList();
    	list.setVisible(true);
    }
}
