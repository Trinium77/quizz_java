package quizPack;

import Utils.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Admin extends JFrame {

    private Connection con;
    private JTextArea textArea;
    private JButton searchButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;

    public Admin() {
        JPanel mainJPanel = getMainJPanel();

        addHeading(mainJPanel);
        addUtilisateur(mainJPanel);
        addLogo(mainJPanel);
        addSeparator(mainJPanel);
        addDeconnexionButton(mainJPanel);
        addAuthor(mainJPanel);
        addRecheche(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        initializeDatabaseConnection();

        Toaster toaster = new Toaster(mainJPanel);
        toaster.warn("Connexion Réussie");
    }

    private JPanel getMainJPanel() {
        Dimension size = new Dimension(1000, 500);
        setTitle("Connexion du Quiz");

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

        MouseAdapter ma = new MouseAdapter() {
            int lastX, lastY;

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                lastX = x;
                lastY = y;
            }
        };

        panel1.addMouseListener(ma);
        panel1.addMouseMotionListener(ma);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        return panel1;
    }


    private void addRecheche(JPanel panel1) {


        textArea = new JTextArea();

        textArea.setRows(5);
        textArea.setColumns(50);
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        textArea.setBorder(border);

        textArea.setBounds(470, 160, 480, 170);
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setForeground(Color.BLACK);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textArea.setFocusable(true);
        textArea.requestFocus();
        panel1.add(textArea);


        searchButton = new JButton("Rechercher");
        searchButton.setBounds(470, 350, 480, 30);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBackground(UIUtils.COLOR_INTERACTIVE);
        panel1.add(searchButton);

        searchButton.addActionListener(e -> {
            String questionToSearch = textArea.getText();
            searchQuestion(questionToSearch);
        });

        addButton = new JButton("Ajouter");
        addButton.setBounds(470, 350, 480, 30);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBackground(UIUtils.COLOR_INTERACTIVE);
        addButton.setVisible(false);
        panel1.add(addButton);

        addButton.addActionListener(e -> addQuestion());

        modifyButton = new JButton("Modifier");
        modifyButton.setBounds(600, 350, 150, 30);
        modifyButton.setVisible(false);
        panel1.add(modifyButton);

        modifyButton.addActionListener(e -> modifyQuestion());

        deleteButton = new JButton("Supprimer");
        deleteButton.setBounds(760, 350, 150, 30);
        deleteButton.setVisible(false);
        panel1.add(deleteButton);

        deleteButton.addActionListener(e -> deleteQuestion());
    }

    private void searchQuestion(String questionToSearch) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM question WHERE question = ?");
            stmt.setString(1, questionToSearch);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                addButton.setVisible(false);
                searchButton.setVisible(false);
                modifyButton.setVisible(true);
                deleteButton.setVisible(true);
            } else {

                modifyButton.setVisible(false);
                deleteButton.setVisible(false);
                searchButton.setVisible(false);
                addButton.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addQuestion() {
        try {
            String[] newData = textArea.getText().split(",");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO question (question, reponse, opt1, opt2, opt3, domaine, niveau) VALUES (?, ?, ?, ?, ?, ?, ?)");
            for (int i = 0; i < newData.length; i++) {
                stmt.setString(i + 1, newData[i].trim());
            }
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Question ajoutée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifyQuestion() {
        try {
            String[] updatedData = textArea.getText().split(",");
            PreparedStatement stmt = con.prepareStatement("UPDATE question SET reponse = ?, opt1 = ?, opt2 = ?, opt3 = ?, domaine = ?, niveau = ? WHERE question = ?");
            for (int i = 0; i < updatedData.length - 1; i++) {
                stmt.setString(i + 1, updatedData[i + 1].trim());
            }
            stmt.setString(7, updatedData[0].trim());  // La question elle-même
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Question modifiée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteQuestion() {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM question WHERE question = ?");
            stmt.setString(1, textArea.getText().trim());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Question supprimée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDeconnexionButton(JPanel panel1) {

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setBounds(800, 100, 150, 30);
        logoutButton.setBackground(UIUtils.COLOR_INTERACTIVE);
        panel1.add(logoutButton);

        logoutButton.addActionListener(e -> {
            new Login();
            this.dispose();
        });
    }

    private void initializeDatabaseConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userQuizz", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void addHeading(JPanel panel1) {

        JLabel heading = new JLabel("Administration");
        heading.setBounds(470, 40, 400, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        panel1.add(heading);
    }

    private void addUtilisateur(JPanel panel1) {

        JLabel welcomeLabel = new JLabel("Optimisation du quizz!");
        welcomeLabel.setBounds(470, 100, 300, 20);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(30, 144, 254));
        panel1.add(welcomeLabel);
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        panel1.add(separator1);
        separator1.setBounds(410, 80, 2, 340);
    }

    private void addAuthor(JPanel panel1) {

        JLabel name = new JLabel("Code by Ella DABIRE and Victor DAH");
        name.setBounds(640, 450, 300, 15);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addLogo(JPanel panel1) {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Profil1.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(-20, 0, 420, 500);
        panel1.add(image);
    }
}
