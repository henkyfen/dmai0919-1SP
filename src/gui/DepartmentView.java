package gui;

import controller.DepartmentController;
import model.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class DepartmentView extends JDialog {
    private final JTextField name;
    private LinkedList<Department> department;
    private JLabel lblProductView;

    /**
     * Create the dialog.
     */
    public DepartmentView() {
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

        lblProductView = new JLabel("New Deparmtent");
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

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DepartmentView dialog = new DepartmentView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void okClicked() {

        String name = this.name.getText();

        DepartmentController controller = new DepartmentController();
        controller.createDepartment(name);

        this.setVisible(false);
        this.dispose();
    }


}
