package VIEW.PANELS;

import DAO.UserDAO;
import MODEL.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final JTextField txtName, txtEmail;
    private final JButton btnAdd, btnDelete;
    private final UserDAO userDAO;

    public UserPanel() {
        this.userDAO = new UserDAO();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Usuarios"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(15);
        formPanel.add(txtName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        formPanel.add(txtEmail, gbc);

        JPanel btnBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Añadir");
        btnDelete = new JButton("Eliminar");
        btnBox.add(btnDelete);
        btnBox.add(btnAdd);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(btnBox, gbc);
        add(formPanel, BorderLayout.WEST);

        String[] cols = {"ID", "Nombre", "Email"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnAdd.addActionListener(e -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            if (!name.isEmpty() && !email.isEmpty()) {
                User u = new User();
                u.setName(name);
                u.setEmail(email);
                userDAO.saveOrUpdate(u);
                txtName.setText("");
                txtEmail.setText("");
                cargar();
            }
        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                User u = new User();
                u.setId((Integer) model.getValueAt(row, 0));
                userDAO.delete(u);
                cargar();
            }
        });
        cargar();
    }

    private void cargar() {
        model.setRowCount(0);
        List<User> lista = userDAO.findAll();
        for (User u : lista) model.addRow(new Object[]{u.getId(), u.getName(), u.getEmail()});
    }
}