package gui;

import controller.WarehouseController;
import exception.StorageException;
import model.ProductType;
import model.WarehouseOrderItem;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WarehouseOrderMenu extends JDialog {

    private WarehouseController controller;
    private final JPanel contentPanel = new JPanel();
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
	private JList<WarehouseOrderItem> list;
	private DefaultListModel<WarehouseOrderItem> listRepresentation;
	private JTextField barcode;
	private JTextField amount;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            WarehouseOrderMenu dialog = new WarehouseOrderMenu(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the dialog.
     */
    public WarehouseOrderMenu(WarehouseController controller) {
		this.controller = controller;
    	
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            btnNewButton_1 = new JButton("Remove Item");
            btnNewButton_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                	removeItem();
                }
            });
            {
                btnNewButton = new JButton("Accept Item");
                btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addItem();
					}
				});
            }
        }
        {
            btnNewButton_2 = new JButton("Finish Accepting");
            btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finishOrder();
				}
			});
        }

        JScrollPane scrollPane = new JScrollPane();
        
        JLabel lblBarcode = new JLabel("Barcode:");
        
        barcode = new JTextField();
        barcode.setColumns(10);
        
        JLabel lblAmount = new JLabel("Amount:");
        
        amount = new JTextField();
        amount.setColumns(10);
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
        	gl_contentPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPanel.createSequentialGroup()
        					.addGap(18)
        					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnNewButton_2)
        						.addComponent(btnNewButton_1)
        						.addComponent(btnNewButton)))
        				.addGroup(gl_contentPanel.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_contentPanel.createSequentialGroup()
        							.addComponent(lblAmount)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_contentPanel.createSequentialGroup()
        							.addComponent(lblBarcode)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(barcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
        			.addGap(55))
        );
        gl_contentPanel.setVerticalGroup(
        	gl_contentPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        				.addGroup(gl_contentPanel.createSequentialGroup()
        					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblBarcode)
        						.addComponent(barcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(16)
        					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblAmount)
        						.addComponent(amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
        					.addComponent(btnNewButton)
        					.addGap(18)
        					.addComponent(btnNewButton_1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnNewButton_2)
        					.addContainerGap())))
        );

        list = new JList<WarehouseOrderItem>();
        list.setToolTipText("");
        scrollPane.setViewportView(list);
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
        }
        init();
    }
    
    private void init() {    	
    	listRepresentation = new DefaultListModel<>();

        List<WarehouseOrderItem> dataList = controller.getOrder().getOrderItems();
        for (WarehouseOrderItem item : dataList) {
            listRepresentation.addElement(item);
        }
        list.setModel(listRepresentation);

        list.setCellRenderer(new AnotherListCell());	
    }
    
    private void addItem() {
    	String barcode = this.barcode.getText();
    	int amount = Integer.parseInt(this.amount.getText());
    	try {
			this.controller.acceptItem(barcode, amount);
			init();
		} catch (StorageException e) {
			Message message = new Message(e.getMessage());
			message.setVisible(true);
		}
    }
    
    private void removeItem() {
    	int index = list.getSelectedIndex();
        WarehouseOrderItem item = list.getSelectedValue();
        if (index != -1) {
            listRepresentation.remove(index);
            try {
				controller.removeItem(item.getProduct().getBarCode());
				init();
			} catch (StorageException e) {
				Message message = new Message(e.getMessage());
				message.setVisible(true);
			}
        }
    }
    
    private void finishOrder() {
    	try {
			if (this.controller.finishAccepting()) {
				this.dispose();
			} else {
				Message message = new Message("Cannot finish accepting");
				message.setVisible(true);
			}
		} catch (StorageException e) {
			Message message = new Message(e.getMessage());
			message.setVisible(true);
		}
    }
}
