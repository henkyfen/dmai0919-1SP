package gui;

import controller.DepartmentController;
import model.Department;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DepartmentList extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JList<Department> departmentList = new JList<>();
    private DefaultListModel<Department> listRepresentation;

    public DepartmentList() {
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

            departmentList = new JList<>();
            scrollPane.setViewportView(departmentList);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

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

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DepartmentList dialog = new DepartmentList();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goBack() {
        this.setVisible(false);
    }

    /**
     * Initialises the dialog and lists the existing departments.
     */
    private void init() {
        listRepresentation = new DefaultListModel<>();
        DepartmentController controller = new DepartmentController();

        List<Department> dataList = controller.getDepartments();
        for (Department p : dataList) {
            listRepresentation.addElement(p);
        }
        departmentList.setModel(listRepresentation);

        departmentList.setCellRenderer(new AnotherListCell());
    }


}
