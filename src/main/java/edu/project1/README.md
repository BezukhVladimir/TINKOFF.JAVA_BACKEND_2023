# Проект №1: Виселица

Требуется написать консольную версию игры Виселица.

Слово загадывается случайным образом из заранее заданного словаря, в словаре достаточно разместить всего несколько слов
для демонстрации.

Также в игре должна быть возможность сдаться не дожидаясь конца игры, например, спец. команда или поддержка Ctrl+D (
достаточно реализовать что-то одно).

Пример протокола общения программы с пользователем
Вы можете использовать любой промт, формат вывода и т.п.

Пример выигрыша:

    > Guess a letter:
    < a
    > Missed, mistake 1 out of 5.
    >
    > The word: *****
    >
    > Guess a letter:
    < b
    > Missed, mistake 2 out of 5.
    >
    > The word: *****
    >
    > Guess a letter:
    < e
    > Hit!
    >
    > The word: *e***
    >
    > Guess a letter:
    < o
    > Hit!
    >
    > The word: *e**o
    >
    > Guess a letter:
    < l
    > Hit!
    >
    > The word: *ello
    >
    > Guess a letter:
    < h
    > Hit!
    >
    > The word: hello
    >
    > You won!

Пример проигрыша:

    > Guess a letter:
    < x
    > Missed, mistake 1 out of 5.
    >
    > The word: ******
    >
    > Guess a letter:
    < y
    > Missed, mistake 2 out of 5.
    >
    > The word: ******
    >
    > Guess a letter:
    < z
    > Missed, mistake 3 out of 5.
    >
    > The word: ******
    >
    > Guess a letter:
    < n
    > Hit!
    >
    > The word: **n*n*
    >
    > Guess a letter:
    < m
    > Missed, mistake 4 out of 5.
    >
    > The word: **n*n*
    >
    > Guess a letter:
    < o
    > Missed, mistake 5 out of 5.
    >
    > The word: **n*n*
    >
    > You lost!

Примеры тестов:

- Игра не запускается, если загадываемое слово имеет некорректную длину
- После превышения заданного количества попыток игра всегда возвращает поражение
- Проверка, что состояние игры корректно изменяется (при угадывании/не угадывании)
- Проверка, что при отгадывании ввод строки длиной больше чем 1 (опечатка) приводит к повторному вводу, без изменения
  состояния

##### Оценка:

    Всего за проект можно получить 6 баллов.

Если возникают проблемы с организацией структуры кода, можете использовать следующий набор интерфейсов и классов, но
лучше попробуйте сначала сами.

Пример иерархии:

    import org.jetbrains.annotations.NotNull;
    
    interface Dictionary {
        @NotNull String randomWord();
    }
    
    class Session {
        private final String answer;
        private final char[] userAnswer;
        private final int maxAttempts;
        private int attempts;
        
        @NotNull GuessResult guess(char guess);
        @NotNull GuessResult giveUp();
    }
    
    sealed interface GuessResult {
        char[] state();
        int attempt();
        int maxAttempts();
        @NotNull String message();
        
        record Defeat(...) implements GuessResult {}
        record Win(...) implements GuessResult {}
        record SuccessfulGuess(...) implements GuessResult {}
        record FailedGuess(...) implements GuessResult {}
    }
    
    
    class ConsoleHangman {
        public void run() {
            while (...) {
            // ...
            }
        }
    
        private GuessResult tryGuess(Session session, String input) {}
    
        private void printState(GuessResult guess) {}
    }
