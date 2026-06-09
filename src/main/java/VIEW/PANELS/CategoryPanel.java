package VIEW.PANELS;

import DAO.CategoryDAO;
import MODEL.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CategoryPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final JTextField txtName;
    private final JButton btnAdd, btnDelete;
    private final CategoryDAO categoryDAO;

    public CategoryPanel() {
        this.categoryDAO = new CategoryDAO();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Categorías"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(15);
        formPanel.add(txtName, gbc);

        JPanel btnBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Añadir");
        btnDelete = new JButton("Eliminar");
        btnBox.add(btnDelete);
        btnBox.add(btnAdd);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(btnBox, gbc);
        add(formPanel, BorderLayout.WEST);

        String[] cols = {"ID", "Nombre"};
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
            if (!name.isEmpty()) {
                Category c = new Category();
                c.setName(name);
                categoryDAO.saveOrUpdate(c);
                txtName.setText("");
                cargar();
            }
        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Category c = new Category();
                c.setId((Integer) model.getValueAt(row, 0));
                categoryDAO.delete(c);
                cargar();
            }
        });
        cargar();
    }

    private void cargar() {
        model.setRowCount(0);
        List<Category> lista = categoryDAO.findAll();
        for (Category c : lista) model.addRow(new Object[]{c.getId(), c.getName()});
    }
}