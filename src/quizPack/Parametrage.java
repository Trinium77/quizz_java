package quizPack;

import Utils.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class Parametrage extends JFrame {

    int score, question;
    String utilisateur, universite, niveau, domaine;

    private ComboBoxField comboNiveau, comboDomaine, comboQuestion;

    public Parametrage(int score, String utilisateur, String universite) {

        this.utilisateur = utilisateur;
        this.score = score;
        this.universite = universite;

        JPanel mainJPanel = getMainJPanel();

        addHeading(mainJPanel);
        addUtilisateur(mainJPanel);
        addLogo(mainJPanel);
        addSeparator(mainJPanel);
        addNiveau(mainJPanel);
        addDomain(mainJPanel);
        addNombreQuestion(mainJPanel);
        addComboNiveau(mainJPanel);
        addComboDomaine(mainJPanel);
        addStartButton(mainJPanel);
        addAuthor(mainJPanel);
        addQuestion(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        Toaster toaster = new Toaster(mainJPanel);
        toaster.warn("Connexion Réussie");
    }

    private JPanel getMainJPanel() {
        Dimension size = new Dimension(1000, 500);
        setTitle("Parametrage du Quiz");

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
        JLabel heading = new JLabel("Parametrage");
        heading.setBounds(470, 40, 400, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        panel1.add(heading);
    }

    private void addUtilisateur(JPanel panel1) {
        JLabel welcomeLabel = new JLabel("Bienvenue, Mr. " + utilisateur);
        welcomeLabel.setBounds(470, 100, 300, 20);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(30, 144, 254));
        panel1.add(welcomeLabel);
    }

    private void addNiveau(JPanel panel1) {
        JLabel name = new JLabel("Niveau ");
        name.setBounds(470, 149, 300, 44);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 32));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addDomain(JPanel panel1) {
        JLabel name = new JLabel("Domaine ");
        name.setBounds(470, 208, 300, 44);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 32));
        name.setForeground(new Color(103, 112, 120));
        panel1.add(name);
    }

    private void addQuestion(JPanel panel1) {
        JLabel name = new JLabel("Question ");
        name.setBounds(470, 267, 300, 44);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 32));
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
        name.setBounds(635, 450, 300, 15);
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

    private void addComboNiveau(JPanel panel1) {
        comboNiveau = new ComboBoxField();
        comboNiveau.addItem("Débutant");
        comboNiveau.addItem("Professionnel");
        comboNiveau.addItem("Expert");

        comboNiveau.setBounds(640, 149, 250, 44);
        comboNiveau.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                comboNiveau.setForeground(Color.white);
                comboNiveau.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboNiveau.setForeground(Color.white);
                comboNiveau.setBorderColor(UIUtils.COLOR_BACKGROUND);
            }
        });

        panel1.add(comboNiveau);
    }

    private void addComboDomaine(JPanel panel1) {
        comboDomaine = new ComboBoxField();
        comboDomaine.addItem("Informatique");
        comboDomaine.addItem("Mathématique");
        comboDomaine.addItem("Science");
        comboDomaine.addItem("Histoire");

        comboDomaine.setBounds(640, 208, 250, 44);
        comboDomaine.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                comboDomaine.setForeground(Color.white);
                comboDomaine.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboDomaine.setForeground(Color.white);
                comboDomaine.setBorderColor(UIUtils.COLOR_BACKGROUND);
            }
        });

        panel1.add(comboDomaine);
    }

    private void addNombreQuestion(JPanel panel1) {
        comboQuestion = new ComboBoxField();
        comboQuestion.addItem("2");
        comboQuestion.addItem("3");
        comboQuestion.addItem("5");

        comboQuestion.setBounds(640, 267, 250, 44);
        comboQuestion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                comboQuestion.setForeground(Color.white);
                comboQuestion.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboQuestion.setForeground(Color.white);
                comboQuestion.setBorderColor(UIUtils.COLOR_BACKGROUND);
            }
        });

        panel1.add(comboQuestion);
    }

    private void addStartButton(JPanel panel1) {
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
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_START)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(loginButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_START, x2, y2);
            }
        };

        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                loginButtonColors[0] = Color.white;
                loginButtonColors[1] = UIUtils.COLOR_INTERACTIVE;
                loginButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                loginButtonColors[1] = Color.white;
                loginButton.repaint();
                loginEventHandler();
            }
        });

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(640, 380, 250, 44);
        panel1.add(loginButton);
    }

    private void loginEventHandler() {

        niveau = Objects.requireNonNull(comboNiveau.getSelectedItem()).toString();
        domaine = Objects.requireNonNull(comboDomaine.getSelectedItem()).toString();
        question = Integer.parseInt((String) Objects.requireNonNull(comboQuestion.getSelectedItem()));


        new Examen(utilisateur, universite, niveau, domaine, question, score);
        this.dispose();
    }
}
