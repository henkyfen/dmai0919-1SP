package gui;

import controller.DepartmentController;
import controller.EmployeeController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends JDialog {

    private final JTextField name;
    private final JTextField login;
    private final JTextField password;
    private final JTextField phoneNumber;
    private final EmployeeMenu employeeMenu;
    private final JTextField salary;
    private final JComboBox typeBox;
    private final JPanel contentPanel = new JPanel();
    private Employee employee;
    private JLabel lblEmployeeView;
    private final DepartmentController departmentController = new DepartmentController();
	private JList<Department> list;
	private DefaultListModel<Department> listRepresentation;
	
	/**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            EmployeeView dialog = new EmployeeView(null, null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the dialog.
     */
    public EmployeeView(EmployeeMenu menu, Employee e) {
        this.employeeMenu = menu;
        setBounds(100, 100, 469, 405);
        getContentPane().setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(21, 325, 436, 31);
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

        lblEmployeeView = new JLabel("New Employee");
        lblEmployeeView.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEmployeeView.setBounds(10, 10, 132, 24);
        getContentPane().add(lblEmployeeView);

        JLabel lblName = new JLabel("Name: ");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblName.setBounds(70, 60, 100, 16);
        getContentPane().add(lblName);

        name = new JTextField();
        name.setBounds(176, 60, 160, 22);
        getContentPane().add(name);
        name.setColumns(10);

        JLabel lblLogin = new JLabel("Login: ");
        lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblLogin.setBounds(70, 130, 100, 16);
        getContentPane().add(lblLogin);

        password = new JTextField();
        password.setBounds(176, 95, 160, 22);
        getContentPane().add(password);
        password.setColumns(10);

        JLabel lblPhone = new JLabel("Phone: ");
        lblPhone.setBounds(70, 165, 100, 16);
        getContentPane().add(lblPhone);

        JLabel lblSalary = new JLabel("Salary: ");
        lblSalary.setBounds(70, 200, 100, 16);
        getContentPane().add(lblSalary);

        JLabel lblPassword = new JLabel("Password: ");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPassword.setBounds(70, 96, 100, 16);
        getContentPane().add(lblPassword);

        JLabel lblType = new JLabel("Employee type:");
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblType.setBounds(70, 235, 100, 16);
        getContentPane().add(lblType);

        login = new JTextField();
        login.setBounds(176, 130, 160, 22);
        getContentPane().add(login);
        login.setColumns(10);

        phoneNumber = new JTextField();
        phoneNumber.setBounds(176, 165, 160, 22);
        getContentPane().add(phoneNumber);
        phoneNumber.setColumns(10);

        salary = new JTextField();
        salary.setBounds(176, 200, 160, 22);
        getContentPane().add(salary);
        salary.setColumns(10);

        String[] types = {"-- Please choose -- ", "Sales assistant", "Salesman", "Warehouse worker", "Officer", "Manager"};
        typeBox = new JComboBox(types);
        typeBox.setSelectedIndex(0);
        typeBox.setBounds(176, 235, 160, 22);
        getContentPane().add(typeBox);
        
        JLabel lblDepartments = new JLabel("Department:");
        lblDepartments.setBounds(70, 263, 100, 16);
        getContentPane().add(lblDepartments);
        
        
        list = new JList<Department>();
        list.setBounds(176, 263, 160, 53);
        getContentPane().add(list);

        init(e);
    }

    private void init(Employee e) {
        this.employee = e;
        if (e != null) {
            fillFields();
        }
    }

    private void fillFields() {
        lblEmployeeView.setText("Edit employee");
        this.name.setText("" + employee.getName());
        this.password.setText("" + employee.getPassword());
        this.salary.setText("" + employee.getSalary());
        this.phoneNumber.setText("" + employee.getPhoneNumber());
        this.login.setText("" + employee.getLogin());
        if (employee instanceof SalesAssistant) this.typeBox.setSelectedIndex(1);
        if (employee instanceof SalesMan) this.typeBox.setSelectedIndex(2);
        if (employee instanceof WarehouseWorker) this.typeBox.setSelectedIndex(3);
        if (employee instanceof Officer) this.typeBox.setSelectedIndex(4);
        if (employee instanceof Manager) this.typeBox.setSelectedIndex(5);
        
        listRepresentation = new DefaultListModel<>();
        
        
        for (Department department : departmentController.getDepartments()) {
            listRepresentation.addElement(department);
        }
        list.setModel(listRepresentation);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setCellRenderer(new AnotherListCell());
        
        List<Department> empDepartments = employee.getDepartments();
        
        int index = 0;
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (Department department : departmentController.getDepartments()) {
        	if (empDepartments.contains(department)) {
        		indexList.add(index);
        	}
            index++;
        }
        int [] indexes = new int[indexList.size()];
        for (int i = 0; i < indexes.length; i++) {
			indexes[i] = indexList.get(i);
		}
        
        list.setSelectedIndices(indexes);
        
        typeBox.setEnabled(false);
        login.setEnabled(false);
    }

    private void goBack() {
        this.setVisible(false);
    }

    private void okClicked() {
        try {
            String name = this.name.getText();
            String login = this.login.getText();
            String password = this.password.getText();
            String phoneNumber = this.phoneNumber.getText();
            double salary = Double.parseDouble(this.salary.getText());

            EmployeeController empctrl = new EmployeeController();
            if (employee != null) {
                empctrl.updateEmployee(name, login, password, phoneNumber, salary);
            } else if (typeBox.getSelectedIndex() != 0) {
                this.employee = empctrl.createEmployee(typeBox.getSelectedIndex(), name, login, password, phoneNumber, salary);
            } else throw new IllegalArgumentException("ERROR! You have to select the type of the employee!");
            
            employee.setDepartments(list.getSelectedValuesList());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Number for salary, please enter number");
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(null, iae.getMessage());
        }
        this.employeeMenu.updateList();
        this.setVisible(false);
        this.dispose();
    }
}
