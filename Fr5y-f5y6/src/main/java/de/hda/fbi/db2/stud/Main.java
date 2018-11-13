package de.hda.fbi.db2.stud;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import de.hda.fbi.db2.stud.entity.Answer;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Player;
import de.hda.fbi.db2.stud.entity.Question;
import de.hda.fbi.db2.tools.CsvDataReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Main Class.
 * @version 0.1.1
 * @since 0.1.0
 * @author A. Hofmann
 * @author B.-A. Mokroß
 */
public class Main {
    /**
     * Main Method and Entry-Point.
     * @param args Command-Line Arguments.
     */

    public static void main(String[] args) {

        // Connect
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultPU");

        EntityManager em = null;                    // Session
        EntityTransaction tx = null;                // Transaction



        try {
            em = emf.createEntityManager();         // Open Session ************************ [EM]
            tx = em.getTransaction();               // i.e. OneToOne Session-Transaction-Coupling
            tx.begin();                             // Open Transaction -------------------- [TX]

            // ObjectLists
            final List<Integer> idList = new ArrayList<>();
            final List<Question> questionList = new ArrayList<>();
            final List<Category> categoryList = new ArrayList<>();
            final List<Answer> answerList = new ArrayList<>();

            //HashMaps
            HashMap<Integer, Question> hmapQuestion = new HashMap<>();
            HashMap<Integer, Category> hmapCategory = new HashMap<>();
            HashMap<Integer, Answer[]> hmapAnswer = new HashMap<>();

            // Read default csv
            final List<String[]> defaultCsvLines = CsvDataReader.read();
            
            // Read (if available) additional csv-files and default csv-file
            List<String> availableFiles = CsvDataReader.getAvailableFiles();
            for (String availableFile: availableFiles){
                final List<String[]> additionalCsvLines = CsvDataReader.read(availableFile);
            }
            for (int i = 1; i < defaultCsvLines.size(); i++) {

                // Create Objects and push into List
                idList.add(Integer.parseInt(defaultCsvLines.get(i)[0]));
                Question quest = new Question(defaultCsvLines.get(i)[1],
                                            Integer.parseInt(defaultCsvLines.get(i)[6]));
                questionList.add(quest);
                hmapQuestion.put(Integer.parseInt(defaultCsvLines.get(i)[0]), quest);

                // Category
                Category cat = new Category(defaultCsvLines.get(i)[7]);
                int checkCat = checkCategory(cat, categoryList);
                if (checkCat < 0) {
                    categoryList.add(cat);
                } else {
                    cat = categoryList.get(checkCat);
                }

                Answer answer1 = new Answer(defaultCsvLines.get(i)[2]);
                Answer answer2 = new Answer(defaultCsvLines.get(i)[3]);
                Answer answer3 = new Answer(defaultCsvLines.get(i)[4]);
                Answer answer4 = new Answer(defaultCsvLines.get(i)[5]);
                int checkans = checkAnswer(answer1, answerList);
                if (checkans < 0){
                    answerList.add(answer1);
                } else {
                    answer1 = answerList.get(checkans);
                }
                checkans = checkAnswer(answer2, answerList);
                if (checkans < 0){
                    answerList.add(answer2);
                } else {
                    answer2 = answerList.get(checkans);
                }
                checkans = checkAnswer(answer3, answerList);
                if (checkans < 0){
                    answerList.add(answer3);
                } else {
                    answer3 = answerList.get(checkans);
                }
                checkans = checkAnswer(answer4, answerList);
                if (checkans < 0){
                    answerList.add(answer4);
                } else {
                    answer4 = answerList.get(checkans);
                }
                Answer[] answerArray = {answer1, answer2, answer3, answer4};
                // put to HashMap
                hmapCategory.put(Integer.parseInt(defaultCsvLines.get(i)[0]), cat);
                hmapAnswer.put(Integer.parseInt(defaultCsvLines.get(i)[0]), answerArray);
            }
            // Praktikum 1 output
            for (int i = 0; i < idList.size(); i++){
                System.out.print("Question " + ( i + 1 ) + ": ");
                System.out.println(hmapQuestion.get(idList.get(i)).getQuestion());
                System.out.print("\tCategory: ");
                System.out.println(hmapCategory.get(idList.get(i)).getNameOfCategory());
                System.out.println("\tAnswers:");
                for (int j = 0; j < 4; j++) {
                    System.out.print("\t\tAnswer " + ( j + 1 ) + ": ");
                    System.out.println(hmapAnswer.get(idList.get(i))[j].getAnswer());
                }
                System.out.print("\tCorrect answer: ");
                System.out.println(hmapQuestion.get(idList.get(i)).getSolution());
            }

            System.out.println("Number of Questions: " + questionList.size());
            System.out.println("Number of Categories: " + categoryList.size());
            System.out.println("Number of Answers: " + answerList.size());


            for (int i = 0; i < categoryList.size(); i++) {
                Category category1;
                category1 = categoryList.get(i);
                em.persist(category1);
            }
            for (int i = 0; i < questionList.size(); i++) {
                Question question1;
                question1 = questionList.get(i);
                em.persist(question1);
            }
            for (int i = 0; i < answerList.size(); i++) {
                em.persist(answerList.get(i));
            }

            tx.commit();
        } catch (URISyntaxException use) {
            System.out.println(use);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        emf.close();
    }

    public static int checkCategory(Category cat, List<Category> categoryList){
        for (int i = 0; i < categoryList.size(); i++) {
            if (cat.getNameOfCategory().contentEquals(categoryList.get(i).getNameOfCategory())){
                return i;
            }
        }
        return -1;
    }
    public static int checkAnswer(Answer ans, List<Answer> ansList){
        for (int i = 0; i < ansList.size(); i++) {
            if (ans.getAnswer().contentEquals(ansList.get(i).getAnswer())) {
                return i;
            }
        }
        return -1;
    }

    public String getGreeting() {
        return "app should have a greeting";
    }
}
