package quizPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertQuestions {

    private Connection con;

    private void initializeDatabaseConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userQuizz", "root", "");
            System.out.println("Connexion établie avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertQuestionsIntoDatabase() {
        String query = "INSERT INTO question (question, reponse, opt1, opt2, opt3, domaine, niveau) VALUES (?, ?, ?, ?, ?, ?, ?)";


        final String[][] questions = {
                /*
                {"OS veut dire ...", "Operating System", "Operation et Synthese", "Optimisation du Systeme", "Operating System", "Informatique", "Débutant"},
                {"Quel langage est utilisé pour créer des applications Android ?", "Java", "Java", "HTML", "PHP", "Informatique", "Débutant"},
                {"Lequel de ces algorithmes est utilisé pour le chiffrement ?", "AES", "AES", "LIFO", "None", "Informatique", "Professionnel"},
                {"Que signifie 'IDE' dans le développement logiciel ?", "Integrated Development Environment", "Interactive Data Environment", "Integrated Development Environment", "None", "Informatique", "Professionnel"},
                {"Quel terme décrit une attaque visant à submerger un système avec du trafic ?", "DDoS", "Phishing", "Malware", "DDoS", "Informatique", "Débutant"},
                {"Quel est le rôle d'un pare-feu ?", "Filtrer le trafic réseau", "Analyser les fichiers", "Filtrer le trafic réseau", "Monitorer l'utilisation du CPU", "Informatique", "Débutant"},
                {"Qu'est-ce qu'une 'vulnérabilité zéro-day' ?", "Une faille non corrigée et inconnue", "Un virus qui se propage rapidement", "Une technique de phishing", "Une faille non corrigée et inconnue", "Informatique", "Professionnel"},
                {"Dans une architecture Web3, qu'est-ce qui est souvent utilisé pour garantir la sécurité des données ?", "Cryptographie", "Compilateur", "Cloud computing", "Cryptographie", "Informatique", "Professionnel"},
                {"Lequel de ces outils est utilisé pour le déploiement de conteneurs ?", "Docker", "Git", "Docker", "VS Code", "Informatique", "Professionnel"},
                {"Quelle commande Linux permet de lister les fichiers dans un répertoire ?", "ls", "cd", "ls", "touch", "Informatique", "Débutant"},
                {"Lequel des nombres suivants est un nombre premier ?", "17", "18", "20", "17", "Mathématique", "Débutant"},
                {"Quel est le résultat de 5 + 3 * 2 ?", "11", "16", "13", "11", "Mathématique", "Débutant"},
                {"Quelle est la dérivée de la fonction f(x) = x^2 ?", "2x", "x^3", "x", "2x", "Mathématique", "Professionnel"},
                {"Quel est le périmètre d'un cercle de rayon r ?", "2πr", "πr^2", "r^2", "2πr", "Mathématique", "Débutant"},
                {"Dans un triangle rectangle, le carré de l'hypoténuse est égal à la somme des carrés des ...", "deux autres côtés", "angles", "longueurs des côtés adjacents", "deux autres côtés", "Mathématique", "Expert"},
                {"Quelle est la solution de l'équation 2x + 3 = 7 ?", "x = 2", "x = 5", "x = 1", "x = 2", "Mathématique", "Professionnel"},
                {"Combien y a-t-il de degrés dans un angle droit ?", "90", "180", "45", "90", "Mathématique", "Débutant"},
                {"Quel est le résultat de l'intégrale de 1/x dx ?", "ln|x| + C", "x^2/2 + C", "1/x + C", "ln|x| + C", "Mathématique", "Professionnel"},
                {"Que représente le discriminant dans une équation quadratique ?", "Il détermine le nombre de solutions réelles", "Il calcule la pente de la parabole", "Il trouve le sommet de la parabole", "Il détermine le nombre de solutions réelles", "Mathématique", "Expert"},
                {"Combien de côtés a un hexagone ?", "6", "8", "5", "6", "Mathématique", "Professionnel"},
                {"Qu'est-ce que la matrice identité ?", "Une matrice carrée avec des 1 sur la diagonale principale et des 0 ailleurs", "Une matrice où tous les éléments sont égaux à 1", "Une matrice où tous les éléments sont égaux à 0", "Une matrice carrée avec des 1 sur la diagonale principale et des 0 ailleurs", "Mathématique", "Professionnel"},
                {"Quel est le résultat de l'opération vectorielle u × v ?", "Produit vectoriel", "Produit scalaire", "Produit cartésien", "Produit vectoriel", "Mathématique", "Expert"},
                {"Quelle est la valeur de π (pi) approximativement ?", "3.14", "3.41", "4.13", "3.14", "Mathématique", "Expert"},
                {"Comment appelle-t-on un polygone à 10 côtés ?", "Décagone", "Hexagone", "Pentagone", "Décagone", "Mathématique", "Débutant"},
                {"Quel est le résultat de la somme des angles d'un triangle ?", "180 degrés", "360 degrés", "90 degrés", "180 degrés", "Mathématique", "Expert"},
                {"En quelle année a eu lieu la Révolution française ?", "1789", "1776", "1815", "1789", "Histoire", "Débutant"},
                {"Qui était l'empereur de France pendant les guerres napoléoniennes ?", "Napoléon Bonaparte", "Louis XIV", "Charlemagne", "Napoléon Bonaparte", "Histoire", "Débutant"},
                {"Qui a découvert l'Amérique en 1492 ?", "Christophe Colomb", "Vasco de Gama", "Ferdinand Magellan", "Christophe Colomb", "Histoire", "Débutant"},
                {"Quand a eu lieu l'indépendance des États-Unis ?", "1776", "1789", "1812", "1776", "Histoire", "Débutant"},
                {"Qui a mené la lutte contre l'apartheid en Afrique du Sud ?", "Nelson Mandela", "Desmond Tutu", "Robert Mugabe", "Nelson Mandela", "Histoire", "Débutant"},
                {"Quel traité a mis fin à la Première Guerre mondiale ?", "Traité de Versailles", "Traité de Brest-Litovsk", "Traité de Trianon", "Traité de Versailles", "Histoire", "Professionnel"},
                {"Quel événement a marqué le début de la Seconde Guerre mondiale ?", "L'invasion de la Pologne", "L'attaque de Pearl Harbor", "La bataille de Stalingrad", "L'invasion de la Pologne", "Histoire", "Professionnel"},
                {"Quel roi de France a été surnommé 'le Roi Soleil' ?", "Louis XIV", "Louis XVI", "Charles V", "Louis XIV", "Histoire", "Professionnel"},
                {"Quelle guerre a opposé l'Union soviétique et les États-Unis sans affrontement direct ?", "La guerre froide", "La guerre de Corée", "La guerre du Vietnam", "La guerre froide", "Histoire", "Professionnel"},
                {"Quelle femme est célèbre pour son rôle dans la guerre de Cent Ans en France ?", "Jeanne d'Arc", "Catherine de Médicis", "Marie-Antoinette", "Jeanne d'Arc", "Histoire", "Professionnel"},
                {"Quel empire était dirigé par Gengis Khan ?", "Empire Mongol", "Empire Ottoman", "Empire Perse", "Empire Mongol", "Histoire", "Expert"},
                {"Quel est le premier homme à avoir marché sur la Lune ?", "Neil Armstrong", "Buzz Aldrin", "Youri Gagarine", "Neil Armstrong", "Histoire", "Expert"},
                {"En quelle année le mur de Berlin est-il tombé ?", "1989", "1991", "1975", "1989", "Histoire", "Expert"},
                {"Quel pharaon a fait construire les grandes pyramides de Gizeh ?", "Khéops", "Ramsès II", "Toutankhamon", "Khéops", "Histoire", "Expert"},
                {"Quel traité a mis fin à la guerre d'Algérie ?", "Les accords d'Évian", "Le traité de Versailles", "Le traité de Trianon", "Les accords d'Évian", "Histoire", "Expert"},
                {"Quelle est la formule chimique de l'eau ?", "H2O", "O2", "CO2", "H2O", "Science", "Débutant"},
                {"Quel est l'organe principal de la circulation sanguine ?", "Cœur", "Poumon", "Foie", "Cœur", "Science", "Débutant"},
                {"Quelle est la vitesse de la lumière dans le vide ?", "299 792 458 m/s", "300 000 km/s", "150 000 km/s", "299 792 458 m/s", "Science", "Débutant"},
                {"Quel gaz constitue 78 % de l'atmosphère terrestre ?", "Azote", "Oxygène", "Carbone", "Azote", "Science", "Débutant"},
                {"Quel est l'élément chimique avec le symbole 'Fe' ?", "Fer", "Fluor", "Fermium", "Fer", "Science", "Débutant"},
                {"Quelle partie de la cellule contient l'ADN ?", "Noyau", "Cytoplasme", "Membrane", "Noyau", "Science", "Professionnel"},
                {"Quel est l'unité de mesure de la force ?", "Newton", "Joule", "Pascal", "Newton", "Science", "Professionnel"},
                {"Quel est l'organe responsable de la respiration ?", "Poumon", "Cœur", "Foie", "Poumon", "Science", "Professionnel"},
                {"Quel est l'organe principal du système nerveux ?", "Cerveau", "Moelle épinière", "Nerfs", "Cerveau", "Science", "Professionnel"},
                {"Quelle est la première loi de Newton ?", "Un corps au repos reste au repos", "La force égale la masse multipliée par l'accélération", "Pour chaque action, il y a une réaction égale et opposée", "Un corps au repos reste au repos", "Science", "Professionnel"},
                {"Quel est le processus par lequel les plantes produisent leur nourriture ?", "Photosynthèse", "Respiration", "Transpiration", "Photosynthèse", "Science", "Expert"},
                {"Quel est le point de fusion du glace ?", "0 °C", "-32 °C", "100 °C", "0 °C", "Science", "Expert"},
                {"Quel est l'organe de la vue chez l'homme ?", "Œil", "Oreille", "Peau", "Œil", "Science", "Expert"},
                {"Quel type de roche se forme par refroidissement du magma ?", "Roche ignée", "Roche sédimentaire", "Roche métamorphique", "Roche ignée", "Science", "Expert"},
                {"Quel est le symbole chimique de l'or ?", "Au", "Ag", "Fe", "Au", "Science", "Expert"},
                {"Quel est l'organe responsable de la digestion ?", "Estomac", "Foie", "Intestin", "Estomac", "Science", "Expert"}*/
        };

        try (PreparedStatement pst = con.prepareStatement(query)) {
            for (String[] questionData : questions) {
                pst.setString(1, questionData[0]); // Question
                pst.setString(2, questionData[1]); // Réponse correcte
                pst.setString(3, questionData[2]); // Option 1
                pst.setString(4, questionData[3]); // Option 2
                pst.setString(5, questionData[4]); // Option 3
                pst.setString(6, questionData[5]); // Domaine
                pst.setString(7, questionData[6]); // Niveau

                pst.executeUpdate();
            }
            System.out.println("Insertion des questions réussie.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InsertQuestions iq = new InsertQuestions();
        iq.initializeDatabaseConnection();
        iq.insertQuestionsIntoDatabase();
    }
}


