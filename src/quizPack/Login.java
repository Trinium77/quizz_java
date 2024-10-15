package quizPack;

import Utils.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Login extends JFrame {

    private TextFieldUsername usernameField;
    private TextFieldUsername universiteField;
    private JPasswordField passwordField;
    private JLabel universiteLabel;

    public Login() {

        JPanel mainJPanel = getMainJPanel();

        addHeading(mainJPanel);
        addUtilisateur(mainJPanel);
        addNom(mainJPanel);
        addPass(mainJPanel);
        addLogo(mainJPanel);
        addSeparator(mainJPanel);
        addUsernameTextField(mainJPanel);
        addPasswordTextField(mainJPanel);
        addUniversite(mainJPanel);
        addUniversiteTextField(mainJPanel);
        addLoginButton(mainJPanel);
        addAuthor(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
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

    private void addHeading(JPanel panel1) {
        JLabel heading = new JLabel("Connexion");
        heading.setBounds(470, 40, 400, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        panel1.add(heading);
    }

    private void addUtilisateur(JPanel panel1) {
        JLabel welcomeLabel = new JLabel("Application quizz en Java");
        welcomeLabel.setBounds(470, 100, 300, 20);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(30, 144, 254));
        panel1.add(welcomeLabel);
    }

    private void addNom(JPanel panel1) {
        JLabel name = new JLabel("Nom ");
        name.setBounds(470, 150, 300, 44);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 32));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addPass(JPanel panel1) {
        JLabel name = new JLabel("Pass ");
        name.setBounds(470, 210, 300, 44);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 32));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addUniversite(JPanel panel1) {
        universiteLabel = new JLabel("Université ");
        universiteLabel.setBounds(470, 270, 300, 44);
        universiteLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 32));
        universiteLabel.setForeground(new Color(103, 112, 120));
        universiteLabel.setVisible(false);
        panel1.add(universiteLabel);
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

    private void addUsernameTextField(JPanel panel1) {
        usernameField = new TextFieldUsername();  // Correction
        usernameField.setBounds(650, 150, 250, 44);
        panel1.add(usernameField);
    }

    private void addUniversiteTextField(JPanel panel1) {
        universiteField = new TextFieldUsername();
        universiteField.setBounds(650, 270, 250, 44);
        universiteField.setEnabled(false);
        universiteField.setVisible(false);
        panel1.add(universiteField);
    }

    private void addPasswordTextField(JPanel panel1) {
        passwordField = new TextFieldPassword();
        passwordField.setBounds(650, 210, 250, 44);
        panel1.add(passwordField);
    }

    private void addLoginButton(JPanel panel1) {
        final Color[] loginButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel loginButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);
                int w = getWidth() - getInsets().left - getInsets().right;
                int h = getHeight() - getInsets().top - getInsets().bottom;
                g2.setColor(loginButtonColors[0]);
                g2.fillRoundRect(getInsets().left, getInsets().top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_LOGIN)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(loginButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_LOGIN, x2, y2);
            }
        };

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loginEventHandler();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                loginButtonColors[1] = UIUtils.OFFWHITE;
                loginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                loginButtonColors[1] = Color.white;
                loginButton.repaint();
            }
        });

        loginButton.setBounds(650, 337, 250, 44);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(loginButton);
    }

    private void loginEventHandler() {
        String utilisateur = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if(Objects.equals(utilisateur, "admin") && password.equals("admin")) {
            new quizPack.Admin();
            this.dispose();
        } else {


            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userQuizz", "root", "");
                String query1 = "SELECT * FROM user WHERE nom=?";
                PreparedStatement pst = con.prepareStatement(query1);
                pst.setString(1, utilisateur);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("pass");
                    if (Objects.equals(storedPassword, password)) {
                        int score = rs.getInt("score");
                        String universite = rs.getString("universite");
                        showMessageDialog(null, "Connexion réussie\nNom: " + utilisateur + "\nUniversité: " + universite + "\nScore: " + score);
                        new quizPack.Parametrage(0, utilisateur, universite);
                        this.dispose();
                    } else {
                        showMessageDialog(null, "Mot de passe incorrect.");
                    }
                } else {
                    if (!universiteField.isVisible()) {
                        int registerChoice = JOptionPane.showConfirmDialog(null, "Utilisateur non trouvé. Voulez-vous vous inscrire ?");
                        if (registerChoice == JOptionPane.YES_OPTION) {
                            universiteField.setEnabled(true);
                            universiteField.setVisible(true);
                            universiteLabel.setVisible(true);
                            showMessageDialog(null, "Veuillez remplir votre université, puis cliquez à nouveau sur 'Connexion' pour terminer l'inscription.");
                        }
                    } else {
                        String universite = universiteField.getText();

                        if (universite.isEmpty()) {
                            showMessageDialog(null, "Veuillez saisir une université pour terminer l'inscription.");
                        } else {
                            String query2 = "INSERT INTO user (nom, pass, universite, score) VALUES (?, ?, ?, ?)";
                            PreparedStatement pst2 = con.prepareStatement(query2);
                            pst2.setString(1, utilisateur);
                            pst2.setString(2, password);
                            pst2.setString(3, universite);
                            pst2.setInt(4, 0);
                            pst2.executeUpdate();

                            showMessageDialog(null, "Inscription réussie !");
                            this.dispose();
                            new quizPack.Parametrage(0, utilisateur, universite);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}