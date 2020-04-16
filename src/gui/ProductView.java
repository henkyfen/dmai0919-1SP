package gui;

import controller.ProductController;
import model.ProductType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView extends JDialog {
    private final JTextField description;
    private final JTextField price;
    private final JTextField barcode;
    private final JTextField amount;
    private final JTextField name;
    private ProductType productType;
    private JLabel lblProductView;

    /**
     * Create the dialog.
     */
    public ProductView(ProductType p) {
        setBounds(100, 100, 450, 317);
        getContentPane().setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 232, 436, 31);
            buttonPane.setBackground(Color.LIGHT_GRAY);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane);
            {
                JButton okButton = new JButton("Save");
                okButton.setActionCommand("Save");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        okClicked();
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

        lblProductView = new JLabel("New Product");
        lblProductView.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblProductView.setBounds(10, 10, 132, 24);
        getContentPane().add(lblProductView);

        JLabel lblName = new JLabel("Name: ");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblName.setBounds(70, 60, 72, 16);
        getContentPane().add(lblName);

        name = new JTextField();
        name.setBounds(148, 60, 116, 22);
        getContentPane().add(name);
        name.setColumns(10);

        JLabel lblPrice = new JLabel("Price: ");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPrice.setBounds(70, 130, 72, 16);
        getContentPane().add(lblPrice);

        description = new JTextField();
        description.setBounds(148, 95, 116, 22);
        getContentPane().add(description);
        description.setColumns(10);

        JLabel lblBarcode = new JLabel("Barcode: ");
        lblBarcode.setBounds(70, 165, 72, 16);
        getContentPane().add(lblBarcode);

        JLabel lblAmount = new JLabel("Amount: ");
        lblAmount.setBounds(70, 200, 72, 16);
        getContentPane().add(lblAmount);

        JLabel lblDescription = new JLabel("Description: ");
        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDescription.setBounds(70, 96, 72, 16);
        getContentPane().add(lblDescription);

        price = new JTextField();
        price.setBounds(148, 130, 116, 22);
        getContentPane().add(price);
        price.setColumns(10);

        barcode = new JTextField();
        barcode.setBounds(148, 165, 116, 22);
        getContentPane().add(barcode);
        barcode.setColumns(10);

        amount = new JTextField();
        amount.setBounds(148, 200, 116, 22);
        getContentPane().add(amount);
        amount.setColumns(10);

        init(p);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ProductView dialog = new ProductView(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(ProductType p) {
        this.productType = p;
        if (p != null) {
            fillFields();
        }
    }

    private void fillFields() {
        lblProductView.setText("Edit product");
        this.name.setText("" + productType.getName());
        this.description.setText("" + productType.getPrice());
        this.amount.setText("" + productType.getAmount());
        this.barcode.setText("" + productType.getBarCode());
        this.price.setText("" + productType.getPrice());
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void okClicked() {
        try {
            String name = this.name.getText();
            double price = Double.parseDouble(this.price.getText());
            String description = this.description.getText();
            String barCode = this.barcode.getText();
            int amount = Integer.parseInt(this.amount.getText());
            ProductController pctrl = new ProductController();
            if (productType != null) {
                pctrl.updateProduct(name, description, price, productType.getBarCode(), amount);
            } else {
                pctrl.createProduct(name, description, price, barCode, amount);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Number for price and amount, please enter number");
        }
        this.setVisible(false);
        this.dispose();
    }
}
