package com.mogorovskiy.quiz.executor;

import java.util.List;
import java.util.Scanner;

public interface QuizExecutor <T> {
    void execute(Scanner scanner, List<T> cards);
}
