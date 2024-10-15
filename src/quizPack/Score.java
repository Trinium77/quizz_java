package quizPack;

import Utils.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Score extends JFrame {

    private final String utilisateur;
    private final String universite;
    private final int score;
    private int scoreTotal;

    public Score(String utilisateur, String universite, String niveau, String domaine, int score) {
        this.utilisateur = utilisateur;
        this.score = score;
        this.universite = universite;

        scoreTotal = recupererScoreTotal(utilisateur) + score;

        mettreAJourScoreTotal(utilisateur, scoreTotal);

        JPanel mainJPanel = getMainJPanel();
        addHeading(mainJPanel);
        addUtilisateur(mainJPanel);
        addUniversite(mainJPanel);
        addNom(mainJPanel);
        addLogo(mainJPanel);
        addSeparator(mainJPanel);
        addScore(mainJPanel);
        addTotal(mainJPanel);
        addLoginButton(mainJPanel);
        addAuthor(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
    }

    private int recupererScoreTotal(String utilisateur) {
        int scoreBase = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userQuizz", "root", "");
            String query = "SELECT score FROM user WHERE nom = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, utilisateur);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                scoreBase = rs.getInt("score");
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreBase;
    }

    private void mettreAJourScoreTotal(String utilisateur, int scoreTotal) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userQuizz", "root", "");
            String query = "UPDATE user SET score = ? WHERE nom = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, scoreTotal);
            pst.setString(2, utilisateur);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JPanel getMainJPanel() {
        Dimension size = new Dimension(1000, 500);
        setTitle("Présentation du Quizz");

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
        JLabel heading = new JLabel("Score");
        heading.setBounds(470, 40, 400, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        panel1.add(heading);
    }

    private void addUtilisateur(JPanel panel1) {
        JLabel welcomeLabel = new JLabel("Fin de l'évaluation");
        welcomeLabel.setBounds(470, 100, 300, 20);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(30, 144, 254));
        panel1.add(welcomeLabel);
    }

    private void addNom(JPanel panel1) {
        JLabel name = new JLabel("Nom: " + utilisateur);
        name.setBounds(470, 160, 450, 30);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 22));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addUniversite(JPanel panel1) {
        JLabel name = new JLabel("Université: " + universite);
        name.setBounds(470, 200, 450, 30);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 22));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addScore(JPanel panel1) {

        JLabel name = new JLabel("Score: " + score);
        name.setBounds(470, 240, 450, 30);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 22));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addTotal(JPanel panel1) {

        JLabel name = new JLabel("Score Total: " + scoreTotal);
        name.setBounds(470, 280, 450, 30);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 22));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
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


    private void addLoginButton(JPanel panel1) {
        final Color[] loginButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel loginButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(loginButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_RESTART)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(loginButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_RESTART, x2, y2);
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

        loginButton.setBackground(UIUtils.COLOR_BACKGROUND);
        loginButton.setBounds(650, 337, 250, 44);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(loginButton);
    }

    private void loginEventHandler() {

        new quizPack.Parametrage(score, utilisateur, universite);
        this.dispose();

    }

}