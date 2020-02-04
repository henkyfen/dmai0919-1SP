package gui;

import controller.ProductController;
import model.ProductType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final ProductController prodCtrl = new ProductController();
    private JList<ProductType> productList = new JList<>();
    private DefaultListModel<ProductType> listRepresentation;

    public ProductList() {
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

            productList = new JList<>();
            scrollPane.setViewportView(productList);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRemove = new JButton("Remove");
                buttonPane.add(btnRemove);
                btnRemove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeProduct();
                    }
                });
            }
            {
                JButton btnShowDetails = new JButton("Show Details");
                buttonPane.add(btnShowDetails);
                btnShowDetails.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showUpdatedDetails();
                    }
                });
            }
            {
                JButton btnCancel = new JButton("Cancel");
                btnCancel.setActionCommand("Cancel");
                buttonPane.add(btnCancel);
                btnCancel.addActionListener(new ActionListener() {
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
            ProductList dialog = new ProductList();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        listRepresentation = new DefaultListModel<>();

        List<ProductType> dataList = prodCtrl.getAll();
        for (ProductType p : dataList) {
            listRepresentation.addElement(p);
        }
        productList.setModel(listRepresentation);

        productList.setCellRenderer(new AnotherListCell());
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void removeProduct() {
        int index = productList.getSelectedIndex();
        ProductType pp = productList.getSelectedValue();
        if (index >= 0 && index < listRepresentation.getSize()) {
            listRepresentation.remove(index);
            prodCtrl.deleteProduct(pp);
        }
    }

    private void showUpdatedDetails() {
        ProductType pp = productList.getSelectedValue();
        int index = productList.getSelectedIndex();

        ProductView dialog = new ProductView(pp);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

        listRepresentation.set(index, pp);

    }

}
