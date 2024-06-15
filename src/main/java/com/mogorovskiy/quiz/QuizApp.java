package com.mogorovskiy.quiz;

import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.util.DatabaseUtil;
import com.mogorovskiy.dao.impl.CardMultipleChoiceDaoImpl;
import com.mogorovskiy.dao.impl.DeckDaoImpl;
import com.mogorovskiy.quiz.service.DeckPicker;
import com.mogorovskiy.quiz.executor.QuizExecutor;
import com.mogorovskiy.quiz.executor.impl.PlayMultipleChoice;
import com.mogorovskiy.quiz.service.impl.CardServiceImpl;
import com.mogorovskiy.quiz.service.impl.DeckServiceImpl;

import java.util.Scanner;

public class QuizApp {

    private final DeckDao deckDao;
    private final com.mogorovskiy.dao.CardMultipleChoiceDao cardDao;
    private final DatabaseUtil dbHelper;
    private final Scanner scanner;
    private DeckPicker deckPicker;
    private QuizExecutor playExecutor;

    public QuizApp() {
        this.deckDao = new DeckDaoImpl();
        this.cardDao = new CardMultipleChoiceDaoImpl();
        this.scanner = new Scanner(System.in);
        this.dbHelper = new DatabaseUtil();
        this.deckPicker = deckPicker;
        this.playExecutor = playExecutor;
    }

    public void start() {
        System.out.println("Welcome to QuizApp!");

        while (true) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Create Deck");
            System.out.println("2. Add Cards to Deck");
            System.out.println("3. Play!");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> new DeckServiceImpl(deckDao).createDeck(scanner);
                case 2 -> new CardServiceImpl(deckDao, cardDao).addCardsToDeck(scanner);
                case 3 -> play(scanner);
                case 4 -> {
                    System.out.println("Exiting QuizApp. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private void play(Scanner scanner) {
        DeckPicker deckPicker = new DeckPicker(new DeckDao());
        QuizExecutor playExecutor = new PlayMultipleChoice();
        QuizApp quizApp = new QuizApp(deckPicker, playExecutor);
        quizApp.start();
    }
}
