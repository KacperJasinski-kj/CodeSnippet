package VIEW;

import VIEW.PANELS.*;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("CodeSnippet Manager - Sistema Integrado Hibernate");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña de Snippets vacía/básica por si la vuelves a programar
        tabbedPane.addTab("Fragmentos (Snippets)", new SnippetPanel());
        tabbedPane.addTab("Categorías", new CategoryPanel());
        tabbedPane.addTab("Lenguajes", new LanguagePanel());
        tabbedPane.addTab("Etiquetas (Tags)", new TagPanel());
        tabbedPane.addTab("Usuarios", new UserPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

}