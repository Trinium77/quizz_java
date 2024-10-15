package quizPack;

import Utils.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Examen extends JFrame implements ActionListener {

    int score;
    String utilisateur;
    String universite, niveau, domaine;
    int nombreQuestion;

    JLabel qno, timerLabel;
    JTextArea question;
    JRadioButton opt1, opt2, opt3;
    ButtonGroup groupoptions;
    JButton next, submit;

    private ArrayList<Question> questions = new ArrayList<>();
    private int count = 0;
    private int totalQuestions;
    private Timer timer;
    private int remainingTime = 120;
    private Connection con;

    public Examen(String utilisateur, String universite, String niveau, String domaine, int nombreQuestion, int score) {
        this.utilisateur = utilisateur;
        this.score = score;
        this.universite = universite;
        this.niveau = niveau;
        this.domaine = domaine;
        this.nombreQuestion = nombreQuestion;

        JPanel mainJPanel = getMainJPanel();
        addHeading(mainJPanel);
        addUtilisateur(mainJPanel);
        addSeparator(mainJPanel);
        addLogo(mainJPanel);
        addAuthor(mainJPanel);

        initializeDatabaseConnection();
        initializeQuestionsAndAnswers();
        setupQuizUI();

        if (totalQuestions > 0) {
            start(count);
            this.add(mainJPanel);
            this.pack();
            this.setVisible(true);
            this.toFront();
            startTimer();
        } else {
            JOptionPane.showMessageDialog(this, "Aucune question disponible pour ce niveau et domaine.", "Erreur", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void initializeDatabaseConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userQuizz", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeQuestionsAndAnswers() {
        try {
            String query1 = "SELECT question, opt1, opt2, opt3, reponse FROM question WHERE domaine=? AND niveau=? LIMIT ?";
            PreparedStatement pst = con.prepareStatement(query1);
            pst.setString(1, domaine);
            pst.setString(2, niveau);
            pst.setInt(3, nombreQuestion);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String questionText = rs.getString("question");
                String option1 = rs.getString("opt1");
                String option2 = rs.getString("opt2");
                String option3 = rs.getString("opt3");
                String correctAnswer = rs.getString("reponse");

                questions.add(new Question(questionText, correctAnswer, option1, option2, option3, domaine, niveau));
            }

            totalQuestions = questions.size();

            System.out.println("Total questions loaded: " + totalQuestions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupQuizUI() {
        setBounds(450, 120, 570, 500);
        getContentPane().setBackground(Color.WHITE);

        timerLabel = new JLabel("Time left: " + remainingTime + " seconds");
        timerLabel.setBounds(700, 60, 300, 20);
        timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timerLabel.setForeground(Color.WHITE);
        add(timerLabel);

        qno = new JLabel();
        qno.setBounds(440, 120, 500, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JTextArea();
        question.setBounds(440, 160, 500, 60);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        question.setForeground(Color.WHITE);
        question.setWrapStyleWord(true);
        question.setLineWrap(true);
        question.setOpaque(false);
        question.setEditable(false);
        question.setFocusable(false);
        add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(440, 240, 500, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(440, 280, 500, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(440, 320, 500, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);

        next = new JButton("Suivant");
        next.setBounds(440, 380, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        submit = new JButton("Fin");
        submit.setBounds(740, 380, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        submit.setEnabled(false);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            remainingTime--;
            timerLabel.setText("Time left: " + remainingTime + " seconds");
            if (remainingTime <= 0) {
                timer.stop();
                handleSubmit();
            }
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            handleNext();
        } else if (ae.getSource() == submit) {
            timer.stop();
            handleSubmit();
        }
    }

    private void handleNext() {
        if (groupoptions.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une réponse.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String userAnswer = groupoptions.getSelection().getActionCommand();
            questions.get(count).setUserAnswer(userAnswer);
        }

        if (count == totalQuestions - 1) {
            next.setEnabled(false);
            submit.setEnabled(true);
        }

        count++;
        if (count < totalQuestions) {
            start(count);
        }
    }

    private void handleSubmit() {
        for (Question q : questions) {
            if (q.getUserAnswer().equals(q.getCorrectAnswer())) {
                score += 10;
            }
        }
        setVisible(false);
        new Score(utilisateur, universite, niveau, domaine, score);
    }

    public void start(int count) {
        qno.setText((count + 1) + ". ");
        question.setText(questions.get(count).getQuestionText());
        opt1.setText(questions.get(count).getOption1());
        opt1.setActionCommand(questions.get(count).getOption1());
        opt2.setText(questions.get(count).getOption2());
        opt2.setActionCommand(questions.get(count).getOption2());
        opt3.setText(questions.get(count).getOption3());
        opt3.setActionCommand(questions.get(count).getOption3());
        groupoptions.clearSelection();
    }

    private JPanel getMainJPanel() {
        Dimension size = new Dimension(1000, 500);
        setTitle("Examen du Quizz");

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
        JLabel heading = new JLabel("Examen");
        heading.setBounds(440, 50, 400, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        panel1.add(heading);
    }

    private void addUtilisateur(JPanel panel1) {
        JLabel welcomeLabel = new JLabel("Bonne chance Mr. " + utilisateur);
        welcomeLabel.setBounds(440, 110, 300, 20);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(30, 144, 254));
        panel1.add(welcomeLabel);
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setBounds(410, 80, 2, 340);
        panel1.add(separator1);
    }
    private void addAuthor(JPanel panel1) {

        JLabel name = new JLabel("Code by Ella DABIRE and Victor DAH");
        name.setBounds(595, 450, 300, 15);
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

