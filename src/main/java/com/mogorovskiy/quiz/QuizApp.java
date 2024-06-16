package com.mogorovskiy.quiz;

import com.mogorovskiy.dao.CardDao;
import com.mogorovskiy.dao.DeckDao;
import com.mogorovskiy.dao.impl.CardMultipleChoiceDaoImpl;
import com.mogorovskiy.dao.impl.CardTranslationDaoImpl;
import com.mogorovskiy.dao.impl.DeckDaoImpl;
import com.mogorovskiy.model.Deck;
import com.mogorovskiy.model.card.CardMultipleChoice;
import com.mogorovskiy.model.card.CardTranslation;
import com.mogorovskiy.quiz.executor.QuizExecutor;
import com.mogorovskiy.quiz.executor.impl.QuizExecutorMultipleChoice;
import com.mogorovskiy.quiz.executor.impl.QuizExecutorTranslation;
import com.mogorovskiy.quiz.service.DeckPicker;
import com.mogorovskiy.quiz.service.DeckService;
import com.mogorovskiy.quiz.service.impl.CardServiceImpl;
import com.mogorovskiy.quiz.service.impl.DeckPickerImpl;
import com.mogorovskiy.quiz.service.impl.DeckServiceImpl;

import java.util.List;
import java.util.Scanner;

public class QuizApp {

    private final DeckDao deckDao;
    private final CardDao cardDao;
    private final Scanner scanner;
    private final DeckPicker deckPicker;
    private QuizExecutor quizExecutor;
    private CardMultipleChoiceDaoImpl cardMC = new CardMultipleChoiceDaoImpl();
    private DeckService deckService;
    private CardTranslationDaoImpl cardT = new CardTranslationDaoImpl();


    public QuizApp() {
        this.deckDao = new DeckDaoImpl();
        this.cardDao = new CardMultipleChoiceDaoImpl();
        this.scanner = new Scanner(System.in);
        this.deckPicker = new DeckPickerImpl(deckDao);
        this.quizExecutor = quizExecutor;
        this.deckService = new DeckServiceImpl(deckDao);
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
                case 1 -> deckService.createDeck();
                case 2 -> new CardServiceImpl(deckDao, cardDao, scanner).addCardsToDeck();   //TODO
                case 3 -> play();
                case 4 -> {
                    System.out.println("Exiting QuizApp. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private void play() {
        System.out.println("Choose deck ID to start: (may take a few seconds)");
        Deck deck = deckPicker.pickDeck();

        Long deckId = deck.getId();
        System.out.println("Choose mode: (1 - Multiple Choice, 2 - Translation)");
        int mode = scanner.nextInt();

        QuizExecutor quizExecutor;

        if (mode == 1) {
            quizExecutor = new QuizExecutorMultipleChoice();
            List<CardMultipleChoice> cardsMC = cardMC.getCardsByDeckId(deckId);

            quizExecutor.execute(scanner, cardsMC);
        } else if (mode == 2) {
            quizExecutor = new QuizExecutorTranslation();
            List<CardTranslation> cardsT = cardT.getCardsByDeckId(deckId);

            quizExecutor.execute(scanner, cardsT);
        }
    }
}
