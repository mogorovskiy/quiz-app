package com.mogorovskiy.quiz;

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
import com.mogorovskiy.quiz.service.CardService;
import com.mogorovskiy.quiz.service.DeckPicker;
import com.mogorovskiy.quiz.service.DeckService;
import com.mogorovskiy.quiz.service.impl.CardServiceImpl;
import com.mogorovskiy.quiz.service.impl.DeckPickerImpl;
import com.mogorovskiy.quiz.service.impl.DeckServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class QuizApp {

    private final CardService cardService;
    private final Scanner scanner;
    private final DeckPicker deckPicker;
    private final DeckService deckService;
    private final CardMultipleChoiceDaoImpl cardMC;
    private final CardTranslationDaoImpl cardT;
    private final QuizExecutor quizExecutorMC;
    private final QuizExecutor quizExecutorT;

    public QuizApp() {
        DeckDao deckDao = new DeckDaoImpl();

        this.scanner = new Scanner(System.in);
        this.deckPicker = new DeckPickerImpl(deckDao);
        this.deckService = new DeckServiceImpl(deckDao);
        this.cardService = new CardServiceImpl(deckDao, scanner);
        this.cardMC = new CardMultipleChoiceDaoImpl();
        this.cardT = new CardTranslationDaoImpl();
        this.quizExecutorMC = new QuizExecutorMultipleChoice();
        this.quizExecutorT = new QuizExecutorTranslation();
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
                case 2 -> cardService.addCardsToDeck();
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
        Optional<Deck> deckOptional = deckPicker.pickDeck();

        if (deckOptional.isEmpty()) {
            System.out.println("No deck found with the given ID.");
            return;
        }

        Deck deck = deckOptional.get();
        Long deckId = deck.getId();

        List<CardMultipleChoice> cardsMC = cardMC.getCardsByDeckId(deckId);
        List<CardTranslation> cardsT = cardT.getCardsByDeckId(deckId);

        quizExecutorMC.execute(scanner, cardsMC);
        quizExecutorT.execute(scanner, cardsT);
    }
}
