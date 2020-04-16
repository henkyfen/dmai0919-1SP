package gui;

import controller.EmployeeController;
import model.Employee;
import model.Manager;
import model.SalesAssistant;
import model.WarehouseWorker;
import tui.ContentGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    public static Employee loggedInEmployee;

    private final JPanel contentPanel = new JPanel();
    private final EmployeeController employeeController;
    private final JTextField username;
    private final JPasswordField password;

    /**
     * Create the dialog.
     */
    public Login() {
        ContentGenerator.generate();
        this.employeeController = new EmployeeController();
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 2};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 2};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel usernameLabel = new JLabel("Username:");
            GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
            gbc_usernameLabel.anchor = GridBagConstraints.EAST;
            gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
            gbc_usernameLabel.gridx = 0;
            gbc_usernameLabel.gridy = 0;
            contentPanel.add(usernameLabel, gbc_usernameLabel);
        }
        {
            username = new JTextField();
            GridBagConstraints gbc_username = new GridBagConstraints();
            gbc_username.insets = new Insets(0, 0, 5, 0);
            gbc_username.fill = GridBagConstraints.HORIZONTAL;
            gbc_username.gridx = 1;
            gbc_username.gridy = 0;
            contentPanel.add(username, gbc_username);
            username.setColumns(10);
        }
        {
            JLabel passwordLabel = new JLabel("Password:");
            GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
            gbc_passwordLabel.anchor = GridBagConstraints.EAST;
            gbc_passwordLabel.insets = new Insets(0, 0, 0, 5);
            gbc_passwordLabel.gridx = 0;
            gbc_passwordLabel.gridy = 1;
            contentPanel.add(passwordLabel, gbc_passwordLabel);
        }
        {
            password = new JPasswordField();
            GridBagConstraints gbc_password = new GridBagConstraints();
            gbc_password.fill = GridBagConstraints.HORIZONTAL;
            gbc_password.gridx = 1;
            gbc_password.gridy = 1;
            contentPanel.add(password, gbc_password);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton loginButton = new JButton("Login");
                buttonPane.add(loginButton);
                getRootPane().setDefaultButton(loginButton);
                loginButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        logIn();
                    }
                });
            }
            {
                JButton quitButton = new JButton("Quit");
                buttonPane.add(quitButton);
                quitButton.addActionListener(new ActionListener() {
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
            Login dialog = new Login();
            dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs the employee in if the username+password matches. Loads the appropriate menu based on the role of the employee
     */
    private void logIn() {
        String username = this.username.getText();
        String password = new String(this.password.getPassword());
        Employee employee = employeeController.findEmployee(username, password);
        if (employee != null) {
            loggedInEmployee = employee;
            if (employee instanceof SalesAssistant) {
                SaleMenu menu = new SaleMenu();
                menu.setVisible(true);
            } else if (employee instanceof Manager) {
                ManagerMenu menu = new ManagerMenu();
                menu.setVisible(true);
            } else if (employee instanceof WarehouseWorker) {
            	WarehouseMenu menu = new WarehouseMenu();
            	menu.setVisible(true);
            }
        } else {
            Message message = new Message("Wrong username and/or password");
            message.setVisible(true);
        }
    }
}
