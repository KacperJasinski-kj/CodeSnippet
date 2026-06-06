package VIEW.DIALOGS;

import DAO.CategoryDAO;
import DAO.LanguageDAO;
import DAO.UserDAO;
import DAO.TagDAO;
import MODEL.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnippetDialog extends JDialog {
    private final JTextField txtTitle;
    private final JTextArea txtDescription, txtSourceCode, txtTags;
    private final JComboBox<UserItem> comboUser;
    private final JComboBox<LanguageItem> comboLanguage;
    private final JComboBox<CategoryItem> comboCategory;
    private boolean confirmed = false;
    private Snippet snippet;

    public SnippetDialog(Frame parent, String dialogTitle, Snippet snippetToEdit) {
        super(parent, dialogTitle, true);
        this.snippet = snippetToEdit;
        setSize(650, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // --- Panel de Formulario Superior ---
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Título del Fragmento:"));
        txtTitle = new JTextField();
        formPanel.add(txtTitle);

        formPanel.add(new JLabel("Autor / Usuario:"));
        comboUser = new JComboBox<>();
        formPanel.add(comboUser);

        formPanel.add(new JLabel("Lenguaje:"));
        comboLanguage = new JComboBox<>();
        formPanel.add(comboLanguage);

        formPanel.add(new JLabel("Categoría:"));
        comboCategory = new JComboBox<>();
        formPanel.add(comboCategory);

        add(formPanel, BorderLayout.NORTH);

        // --- Panel Central (Áreas de Texto Grandes) ---
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        txtDescription = new JTextArea();
        txtDescription.setBorder(BorderFactory.createTitledBorder("Descripción / Comentarios"));
        centerPanel.add(new JScrollPane(txtDescription));

        txtSourceCode = new JTextArea();
        txtSourceCode.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtSourceCode.setBorder(BorderFactory.createTitledBorder("Código Fuente (Source Code)"));
        centerPanel.add(new JScrollPane(txtSourceCode));

        txtTags = new JTextArea();
        txtTags.setBorder(BorderFactory.createTitledBorder("Etiquetas (Separadas por comas, ej: java, loops, web)"));
        centerPanel.add(new JScrollPane(txtTags));

        add(centerPanel, BorderLayout.CENTER);

        // --- Panel Inferior de Botones ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Guardar Datos");
        JButton btnCancel = new JButton("Cancelar");
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);
        add(buttonPanel, BorderLayout.SOUTH);

        // 1. Rellenar los JComboBoxes desde la Base de Datos usando Hibernate
        cargarCombos();

        // 2. Si es una MODIFICACIÓN, rellenar los campos con los datos del objeto existente
        if (snippet != null) {
            mapearObjetoAlFormulario();
        }

        // --- Listeners de Eventos ---
        btnSave.addActionListener(e -> {
            if (txtTitle.getText().trim().isEmpty() || txtSourceCode.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El Título y el Código Fuente son obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            confirmed = true;
            setVisible(false);
        });

        btnCancel.addActionListener(e -> setVisible(false));
    }

    private void cargarCombos() {
        new UserDAO().findAll().forEach(u -> comboUser.addItem(new UserItem(u)));
        new LanguageDAO().findAll().forEach(l -> comboLanguage.addItem(new LanguageItem(l)));
        new CategoryDAO().findAll().forEach(c -> comboCategory.addItem(new CategoryItem(c)));
    }

    private void mapearObjetoAlFormulario() {
        txtTitle.setText(snippet.getTitle());
        txtDescription.setText(snippet.getDescription());
        txtSourceCode.setText(snippet.getSourceCode());

        // Seleccionar los elementos correspondos en los comboboxes según los IDs
        if (snippet.getUser() != null) {
            for (int i = 0; i < comboUser.getItemCount(); i++) {
                if (comboUser.getItemAt(i).user.getId().equals(snippet.getUser().getId())) {
                    comboUser.setSelectedIndex(i); break;
                }
            }
        }
        if (snippet.getLanguage() != null) {
            for (int i = 0; i < comboLanguage.getItemCount(); i++) {
                if (comboLanguage.getItemAt(i).language.getId().equals(snippet.getLanguage().getId())) {
                    comboLanguage.setSelectedIndex(i); break;
                }
            }
        }
        if (snippet.getCategory() != null) {
            for (int i = 0; i < comboCategory.getItemCount(); i++) {
                if (comboCategory.getItemAt(i).category.getId().equals(snippet.getCategory().getId())) {
                    comboCategory.setSelectedIndex(i); break;
                }
            }
        }

        // Convertir la lista de objetos Tag en un String separado por comas para el cuadro de texto
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < snippet.getTags().size(); i++) {
            sb.append(snippet.getTags().get(i).getName());
            if (i < snippet.getTags().size() - 1) {
                sb.append(", ");
            }
        }
        txtTags.setText(sb.toString());
    }

    public Snippet getSnippetFromForm() {
        if (snippet == null) {
            snippet = new Snippet();
        }
        snippet.setTitle(txtTitle.getText().trim());
        snippet.setDescription(txtDescription.getText().trim());
        snippet.setSourceCode(txtSourceCode.getText().trim());

        // Asignar los objetos seleccionados en los comboboxes
        if (comboUser.getSelectedItem() != null) snippet.setUser(((UserItem) comboUser.getSelectedItem()).user);
        if (comboLanguage.getSelectedItem() != null) snippet.setLanguage(((LanguageItem) comboLanguage.getSelectedItem()).language);
        if (comboCategory.getSelectedItem() != null) snippet.setCategory(((CategoryItem) comboCategory.getSelectedItem()).category);

        // --- Lógica ManyToMany para procesar las etiquetas escritas ---
        List<Tag> listaTags = new ArrayList<>();
        TagDAO tagDAO = new TagDAO();
        List<Tag> tagsExistentes = tagDAO.findAll();
        String[] nombresTags = txtTags.getText().split(",");

        for (String nt : nombresTags) {
            String limpio = nt.trim();
            if (!limpio.isEmpty()) {
                // Si la etiqueta ya existe en la base de datos, la reutilizamos para no duplicar nombres
                Tag tagBD = tagsExistentes.stream()
                        .filter(t -> t.getName().equalsIgnoreCase(limpio))
                        .findFirst().orElse(null);

                // Si es nueva, la creamos y guardamos al instante en la base de datos
                if (tagBD == null) {
                    tagBD = new Tag();
                    tagBD.setName(limpio);
                    tagDAO.saveOrUpdate(tagBD);
                }
                listaTags.add(tagBD);
            }
        }
        snippet.setTags(listaTags);
        return snippet;
    }

    public boolean isConfirmed() { return confirmed; }

    // --- Clases Wrappers internas para que los JComboBox muestren el 'name' en pantalla ---
    private static class UserItem {
        User user;
        UserItem(User u) { this.user = u; }
        @Override public String toString() { return user.getName(); }
    }

    private static class LanguageItem {
        Language language;
        LanguageItem(Language l) { this.language = l; }
        @Override public String toString() { return language.getName(); }
    }

    private static class CategoryItem {
        Category category;
        CategoryItem(Category c) { this.category = c; }
        @Override public String toString() { return category.getName(); }
    }
}