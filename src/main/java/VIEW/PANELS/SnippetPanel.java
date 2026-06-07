package VIEW.PANELS;

import DAO.SnippetDAO;
import MODEL.Snippet;
import MODEL.Tag;
import VIEW.MainWindow;
import VIEW.DIALOGS.SnippetDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class SnippetPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel model;
    private final JTextField txtSearch;
    private final JButton btnSearch, btnAdd, btnEdit, btnDelete;
    private final SnippetDAO snippetDAO;

    public SnippetPanel() {
        this.snippetDAO = new SnippetDAO();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Barra Superior de Búsqueda ---
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Buscador"));
        txtSearch = new JTextField();
        btnSearch = new JButton("Buscar por Título");
        searchPanel.add(txtSearch, BorderLayout.CENTER);
        searchPanel.add(btnSearch, BorderLayout.EAST);
        add(searchPanel, BorderLayout.NORTH);

        // --- Tabla Central de Visualización ---
        String[] columns = {"ID", "Título", "Lenguaje", "Categoría", "Usuario", "Etiquetas"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Barra Inferior de Acciones CRUD ---
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Nuevo Snippet");
        btnEdit = new JButton("Modificar");
        btnDelete = new JButton("Eliminar");
        actionPanel.add(btnDelete);
        actionPanel.add(btnEdit);
        actionPanel.add(btnAdd);
        add(actionPanel, BorderLayout.SOUTH);

        // --- Listeners de Eventos ---
        btnSearch.addActionListener(e -> buscarSnippets());
        btnAdd.addActionListener(e -> abrirDialogoNuevo());
        btnEdit.addActionListener(e -> abrirDialogoModificar());
        btnDelete.addActionListener(e -> eliminarSnippet());

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int row = table.getSelectedRow();

                    if (row == -1) {
                        return;
                    }

                    Integer id = (Integer) model.getValueAt(row, 0);

                    Snippet snippet = snippetDAO.findAll()
                            .stream()
                            .filter(s -> s.getId().equals(id))
                            .findFirst()
                            .orElse(null);

                    if (snippet != null) {

                        JTextArea area = new JTextArea(
                                snippet.getSourceCode()
                        );

                        area.setEditable(false);
                        area.setCaretPosition(0);

                        JScrollPane scroll = new JScrollPane(area);
                        scroll.setPreferredSize(new Dimension(700, 400));

                        JOptionPane.showMessageDialog(
                                SnippetPanel.this,
                                scroll,
                                snippet.getTitle(),
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });

        cargarTodos();
    }



    private void cargarTodos() {
        model.setRowCount(0);
        List<Snippet> lista = snippetDAO.findAll();
        for (Snippet s : lista) {
            agregarFila(s);
        }
    }

    private void buscarSnippets() {
        String query = txtSearch.getText().trim();
        if (query.isEmpty()) {
            cargarTodos();
        } else {
            model.setRowCount(0);
            List<Snippet> filtrados = snippetDAO.searchByTitle(query);
            for (Snippet s : filtrados) {
                agregarFila(s);
            }
        }
    }

    private void agregarFila(Snippet s) {
        String langName = (s.getLanguage() != null) ? s.getLanguage().getName() : "N/A";
        String catName = (s.getCategory() != null) ? s.getCategory().getName() : "N/A";
        String userName = (s.getUser() != null) ? s.getUser().getName() : "N/A";
        String tagsCSV = s.getTags().stream().map(Tag::getName).collect(Collectors.joining(", "));

        model.addRow(new Object[]{
                s.getId(),
                s.getTitle(),
                langName,
                catName,
                userName,
                tagsCSV
        });
    }

    private void abrirDialogoNuevo() {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        SnippetDialog dialog = new SnippetDialog(parentFrame, "Crear Nuevo Snippet", null);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Snippet nuevo = dialog.getSnippetFromForm();
            snippetDAO.saveOrUpdate(nuevo);
            cargarTodos();
        }
    }

    private void abrirDialogoModificar() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un snippet de la lista.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer id = (Integer) model.getValueAt(selectedRow, 0);
        List<Snippet> todos = snippetDAO.findAll();
        Snippet actual = todos.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);

        if (actual != null) {
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            SnippetDialog dialog = new SnippetDialog(parentFrame, "Modificar Snippet", actual);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                Snippet modificado = dialog.getSnippetFromForm();
                snippetDAO.saveOrUpdate(modificado);
                cargarTodos();
            }
        }
    }

    private void eliminarSnippet() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona el elemento que deseas eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este fragmento de código?", "Confirmar baja", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Integer id = (Integer) model.getValueAt(selectedRow, 0);
            Snippet s = new Snippet();
            s.setId(id);
            snippetDAO.delete(s);
            cargarTodos();
        }
    }
}