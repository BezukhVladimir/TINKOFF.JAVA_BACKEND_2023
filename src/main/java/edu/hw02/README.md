# Домашнее задание №2

## 1. Калькулятор выражений

##### Постановка задачи:

Напишите иерархию классов для вычисления математических выражений.

    public sealed interface Expr {
        double evaluate();

        public record Constant implements Expr {}
        public record Negate implements Expr {}
        public record Exponent implements Expr {}
        public record Addition implements Expr {}
        public record Multiplication implements Expr {}
    }

Разбор строковых представлений выражений не требуется, достаточно чтобы работал код вида

    var two = new Constant(2);
    var four = new Constant(4);
    var negOne = new Negate(new Constant(1));
    var sumTwoFour = new Addition(two, four);
    var mult = new Multiplication(sumTwoFour, negOne);
    var exp = new Exponent(mult, 2);
    var res = new Addition(exp, new Constant(1));

    System.out.println(res + " = " + res.evaluate());

## 2. Квадрат и прямоугольник

##### Предметная область:

Что может быть проще наследования... Думают все начинающие программисты.

На практике оказывается, что применение наследования очень ограничено и в реальности почти всегда лучше использовать
композицию или относительно "глупые" sealed-интерфейсы (ADT).

Чтобы продемонстрировать утверждение выше, попробуем разрешить классический парадокс прямоугольника и квадрата.

Допустим, у вас есть классы Rectangle и Square с методами setWidth, setHeight и area:

    public class Rectangle {  
        private int width;  
        private int height;
    
        void setWidth(int width) {  
            this.width = width;  
        }  
      
        void setHeight(int height) {  
            this.height = height;  
        }  
      
        double area() {  
            return width * height;  
        }  
    }
    
    public class Square extends Rectangle {  
        @Override  
        void setWidth(int width) {  
            super.setHeight(width);  
            super.setWidth(width);  
        }
    
        @Override  
        void setHeight(int height) {  
            super.setHeight(height);  
            super.setWidth(height);  
        }  
    }

И есть следующий тест:

    static Arguments[] rectangles() {  
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };  
    }
    
    @ParameterizedTest  
    @MethodSource("rectangles")  
    void rectangleArea(Rectangle rect) {  
        rect.setWidth(20);  
        rect.setHeight(10);
    
        assertThat(rect.area()).isEqualTo(200.0);  
    }

Если вы запустите этот код, то вы увидите, что один из тестов падает.

Проблема этого кода заключается в нарушении принципа подстановки Лисков:

    если можно написать хоть какой-то осмысленный код, в котором замена объекта базового класса на объекта класса потомка, его сломает, то тогда не стоит их друг от друга-то наследовать.
    Мы должны расширять поведение базового класса в потомках, а не существенным образом изменять его. Функции, которые используют базовый класс, должны иметь возможность использовать объекты подклассов, не зная об этом.

К сожалению проблемы с LSP встречаются постоянно даже в крупных проектах, например, в JDK:

    static Arguments[] lists() {  
        return new Arguments[]{  
            Arguments.of(new ArrayList<Integer>()),  
            Arguments.of(Collections.unmodifiableList(new ArrayList<Integer>()))  
        };  
    }
    
    @ParameterizedTest  
    @MethodSource("lists")  
    void addElement(List<Integer> list) {  
        list.add(1);
    
        assertThat(list).hasSize(1).containsExactly(1);  
    }

##### Постановка задачи

Найдите решение проблемы, удовлетворяющее следующим ограничениям:

- Не нарушается математическая логика, то есть квадрат является прямоугольником с точки зрения типизации
- При этом не нарушается принцип подстановки
- Все так же присутствует понятие высоты и ширины, а также операция вычисления площади
- Реализация класса Rectangle не должна предполагать существование класса Square, иными словами, не нарушен принцип
  открытости-закрытости

Если всё-таки не получится придумать решение самостоятельно, то воспользуйтесь подсказками ниже:

- подсказка 1: методы setWidth/setHeight могут что-то возвращать
- подсказка 2: может пригодиться ключевое слово final

## 3. Удаленный сервер

##### Предметная область:

Программист Иван хочет выполнять часто используемые консольные команды на удаленном сервере из Java-программы.

В распоряжении Ивана следующий набор интерфейсов:

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }
    
    public interface ConnectionManager {
        Connection getConnection();
    }
    
    public class ConnectionException extends RuntimeException {}
    
    public final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public void updatePackages() {
	        tryExecute("apt update && apt upgrade -y");
        }

	    void tryExecute(String command) { ... }
    }

Пояснение:

- работа с сервером происходит через Connection, у которого есть метод execute
- чтобы получить соединение, используется ConnectionManager
- при выполнении команды может возникнуть исключение ConnectionException

##### Постановка задачи

Помогите Ивану и реализуйте:

- 2 типа соединений: StableConnection / FaultyConnection, стабильное соединение работает всегда, проблемное соединение
  иногда бросает ConnectionException
- DefaultConnectionManager, который с некоторой вероятностью возвращает проблемное соединение
- FaultyConnectionManager, который всегда возвращает проблемное соединение
- Метод tryExecute, который должен попытаться выполнить переданную команду maxAttempts раз
- Если tryExecute не смог выполнить команду (превышено количество попыток), то нужно вернуть ConnectionException, при
  этом сохранив оригинальное исключение в параметре cause

Обратите внимание, что Connection требуется закрывать (интерфейс AutoCloseable).

## 4. Кто вызвал функцию?

##### Постановка задачи:

Напишите статическую функцию callingInfo, которая возвращает

    public record CallingInfo(String className, String methodName) {}

Для получения доступа к стеку вызова используйте метод Throwable#getStackTrace.
