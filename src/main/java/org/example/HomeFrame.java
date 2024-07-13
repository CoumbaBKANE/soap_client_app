import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class HomeFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTable usersTable;

    public HomeFrame() {
        setTitle("Bienvenue sur l'interface d'administration des utilisateurs");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation du CardLayout pour gérer les différentes vues
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panneau pour l'ajout d'utilisateur
        JPanel addUserPanel = createAddUserPanel();
        cardPanel.add(addUserPanel, "addUser");

        // Panneau pour la liste des utilisateurs
        JPanel listUsersPanel = createListUsersPanel();
        cardPanel.add(listUsersPanel, "listUsers");

        // Panneau pour la modification d'utilisateur
        JPanel modifyUserPanel = createModifyUserPanel();
        cardPanel.add(modifyUserPanel, "modifyUser");

        // Panneau pour la suppression d'utilisateur
        JPanel deleteUserPanel = createDeleteUserPanel();
        cardPanel.add(deleteUserPanel, "deleteUser");

        // Ajout du panel de cartes à la fenêtre principale
        add(cardPanel, BorderLayout.CENTER);

        // Création du menu de navigation
        JPanel navigationPanel = createNavigationPanel();
        add(navigationPanel, BorderLayout.WEST);

        // Affichage initial du panneau d'ajout d'utilisateur
        cardLayout.show(cardPanel, "addUser");
    }

    private JPanel createAddUserPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        // Création de la bordure encadrant le formulaire
        Border border = BorderFactory.createEtchedBorder();
        panel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        panel.setPreferredSize(new Dimension(300, 200)); // Taille préférée du panneau

        // Label et champ de texte pour le login
        JLabel loginLabel = new JLabel("Login:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(loginLabel, constraints);

        JTextField loginField = new JTextField(20);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(loginField, constraints);

        // Espace entre les champs login et mot de passe
        constraints.gridy = 1;
        panel.add(Box.createVerticalStrut(10), constraints);

        // Label et champ de texte pour le mot de passe
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, constraints);

        // Espace entre le champ mot de passe et le bouton
        constraints.gridy = 3;
        panel.add(Box.createVerticalStrut(20), constraints);

        // Bouton d'ajout d'utilisateur
        JButton addButton = new JButton("Add User");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, constraints);

        addButton.addActionListener(e -> addUser(loginField.getText(), new String(passwordField.getPassword())));

        return panel;
    }

    private JPanel createListUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Données fictives d'exemple pour les utilisateurs
        List<String> usernames = new ArrayList<>();
        usernames.add("JohnDoe");
        usernames.add("JaneSmith");
        usernames.add("RobertJohnson");

        // Noms des colonnes pour la table des utilisateurs
        String[] columnNames = {"Users"};

        // Création du modèle de table pour les utilisateurs
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (String username : usernames) {
            tableModel.addRow(new Object[]{username});
        }

        JTable usersTable = new JTable(tableModel);
        usersTable.setEnabled(false); // Rend la table en lecture seule
        JScrollPane scrollPane = new JScrollPane(usersTable);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createModifyUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Bouton de modification d'utilisateur
        JButton modifyButton = new JButton("Modify Selected User");
        modifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(modifyButton, BorderLayout.NORTH);

        // Gestion de la modification d'utilisateur
        modifyButton.addActionListener(e -> modifyUser());

        // Tableau des utilisateurs (simulé ici, normalement récupéré de la JTable listUsersPanel)
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{"JohnDoe", "password1"});
        userData.add(new String[]{"JaneSmith", "password2"});
        userData.add(new String[]{"RobertJohnson", "password3"});

        // Noms des colonnes pour la table des utilisateurs
        String[] columnNames = {"Login", "Password"};

        // Création du modèle de table pour les utilisateurs
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (String[] user : userData) {
            tableModel.addRow(user);
        }

        JTable usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createDeleteUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Bouton de suppression d'utilisateur
        JButton deleteButton = new JButton("Delete Selected User");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(deleteButton, BorderLayout.NORTH);

        // Gestion de la suppression d'utilisateur
        deleteButton.addActionListener(e -> deleteUser());

        // Tableau des utilisateurs (simulé ici, normalement récupéré de la JTable listUsersPanel)
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{"JohnDoe", "password1"});
        userData.add(new String[]{"JaneSmith", "password2"});
        userData.add(new String[]{"RobertJohnson", "password3"});

        // Noms des colonnes pour la table des utilisateurs
        String[] columnNames = {"Login", "Password"};

        // Création du modèle de table pour les utilisateurs
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (String[] user : userData) {
            tableModel.addRow(user);
        }

        JTable usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Boutons de navigation pour chaque fonctionnalité avec espacement
        JButton addButton = createNavigationButton("Add User", "addUser");
        panel.add(addButton);

        JButton listButton = createNavigationButton("List Users", "listUsers");
        panel.add(Box.createVerticalStrut(10));
        panel.add(listButton);

        JButton modifyButton = createNavigationButton("Modify User", "modifyUser");
        panel.add(Box.createVerticalStrut(10));
        panel.add(modifyButton);

        JButton deleteButton = createNavigationButton("Delete User", "deleteUser");
        panel.add(Box.createVerticalStrut(10));
        panel.add(deleteButton);

        // Barre de séparation
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JButton createNavigationButton(String buttonText, String panelName) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(150, 30)); // Taille fixe pour tous les boutons
        button.addActionListener(e -> cardLayout.show(cardPanel, panelName));
        return button;
    }

    private void addUser(String login, String password) {
        DefaultTableModel tableModel = (DefaultTableModel) usersTable.getModel();
        tableModel.addRow(new Object[]{login, password});
    }

    private void modifyUser() {
        DefaultTableModel tableModel = (DefaultTableModel) usersTable.getModel();
        int[] selectedRows = usersTable.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select a user to modify.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Parcourir les lignes sélectionnées pour la modification
        for (int row : selectedRows) {
            String login = (String) tableModel.getValueAt(row, 0);
            String password = (String) tableModel.getValueAt(row, 1);

            // Simuler l'affichage des données modifiées
            JOptionPane.showMessageDialog(this, "Modified User:\nLogin: " + login + "\nPassword: " + password, "User Modified", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteUser() {
        DefaultTableModel tableModel = (DefaultTableModel) usersTable.getModel();
        int[] selectedRows = usersTable.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Supprimer les utilisateurs sélectionnés
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            tableModel.removeRow(selectedRows[i]);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeFrame().setVisible(true));
    }
}
