package gui;

import controller.LeaseController;
import exception.StorageException;
import model.Lease;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LeaseList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final LeaseController leaseController = new LeaseController();
    private JList<Lease> list = new JList<>();
    private DefaultListModel<Lease> listRepresentation;
    private Lease lease;

    /**
     * Create the dialog.
     */
    public LeaseList() {
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
                JButton btnRemove = new JButton("Remove");
                buttonPane.add(btnRemove);
                btnRemove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeLease();
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

        List<Lease> dataList = leaseController.getLeases();
        System.out.println(dataList.size());
        for (Lease lease : dataList) {
            listRepresentation.addElement(lease);
        }
        list.setModel(listRepresentation);

        list.setCellRenderer(new AnotherListCell());
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void removeLease() {
        int index = list.getSelectedIndex();
        Lease lease = list.getSelectedValue();
        if (index >= 0 && index < listRepresentation.getSize()) {
            int leaseId = lease.getID();
            listRepresentation.remove(index);
            try {
                leaseController.returnLease(leaseId);
            } catch (StorageException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void showUpdatedDetails() {
        Lease lease = list.getSelectedValue();
        int index = list.getSelectedIndex();
        if (index != -1) {
        	LeaseView dialog = new LeaseView(lease);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }

}
