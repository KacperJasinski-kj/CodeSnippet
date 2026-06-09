package VIEW.PANELS;

import DAO.LanguageDAO;
import MODEL.Language;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LanguagePanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final JTextField txtName;
    private final JButton btnAdd, btnDelete;
    private final LanguageDAO languageDAO;

    public LanguagePanel() {
        this.languageDAO = new LanguageDAO();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Lenguajes"));
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

        String[] cols = {"ID", "Lenguaje"};
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
                Language l = new Language();
                l.setName(name);
                languageDAO.saveOrUpdate(l);
                txtName.setText("");
                cargar();
            }
        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Language l = new Language();
                l.setId((Integer) model.getValueAt(row, 0));
                languageDAO.delete(l);
                cargar();
            }
        });
        cargar();
    }

    private void cargar() {
        model.setRowCount(0);
        List<Language> lista = languageDAO.findAll();
        for (Language l : lista) model.addRow(new Object[]{l.getId(), l.getName()});
    }
}