package gui;

import controller.EmployeeController;
import model.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeMenu extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final EmployeeController empCtrl = new EmployeeController();
    private JList<Employee> employeeMenu = new JList<>();
    private DefaultListModel<Employee> listRepresentation;

    /**
     * Create the dialog.
     */
    public EmployeeMenu() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            {
                JScrollPane scrollPane = new JScrollPane();
                contentPanel.add(scrollPane);

                employeeMenu = new JList<>();
                scrollPane.setViewportView(employeeMenu);
            }

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnUpdate = new JButton("Update Employee");
                buttonPane.add(btnUpdate);
                btnUpdate.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showUpdatedDetails();
                    }
                });
            }
            {
                JButton btnDelete = new JButton("Delete Employee");
                buttonPane.add(btnDelete);
                {
                    JButton btnAdd = new JButton("Add Employee");
                    buttonPane.add(btnAdd);
                    btnAdd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            addEmployee();
                        }
                    });

                }
                btnDelete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeEmployee();
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
            EmployeeMenu dialog = new EmployeeMenu();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises the dialog and loads/lists all employees
     */
    private void init() {
        listRepresentation = new DefaultListModel<>();

        List<Employee> dataList = empCtrl.getAll();
        for (Employee e : dataList) {
            listRepresentation.addElement(e);
        }
        employeeMenu.setModel(listRepresentation);

        employeeMenu.setCellRenderer(new AnotherListCell());
    }

    private void removeEmployee() {
        int index = employeeMenu.getSelectedIndex();
        Employee e = employeeMenu.getSelectedValue();
        if (index >= 0 && index < listRepresentation.getSize()) {
            listRepresentation.remove(index);
            empCtrl.removeEmployee(e);
        }
    }

    private void addEmployee() {

        EmployeeView dialog = new EmployeeView(this, null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

    }


    private void showUpdatedDetails() {
        Employee e = employeeMenu.getSelectedValue();
        int index = employeeMenu.getSelectedIndex();

        EmployeeView dialog = new EmployeeView(this, e);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

        listRepresentation.set(index, e);

    }

    public void updateList() {
        init();
    }

}
