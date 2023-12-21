# Домашнее задание №3

## 1. Шифр Атбаш

##### Постановка задачи:

Шифр Атбаша — это метод шифрования, при котором каждая буква слова заменяется на свою "зеркальную" букву в алфавите:
A <=> Z; B <=> Y; C <=> X и т.д.

Создайте функцию, которая принимает строку и применяет к ней шифр.

Замечания:

* используется латинский алфавит
* регистр букв нужно сохранить
* символы не латинского алфавита нужно писать как есть

Примеры (https://www.boxentriq.com/code-breaking/atbash-cipher):

    atbash("Hello world!") -> "Svool dliow!"
    atbash("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler") -> "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"

## 2. Кластеризация скобок

##### Постановка задачи:

Напишите функцию, которая группирует строку в кластеры, заключенные в скобки. Каждый кластер должен быть
сбалансированным.

Примеры:

    clusterize("()()()") -> ["()", "()", "()"]
    clusterize("((()))") -> ["((()))"]
    clusterize("((()))(())()()(()())") -> ["((()))", "(())", "()", "()", "(()())"]
    clusterize("((())())(()(()()))") -> ["((())())", "(()(()()))"]

## 3. Частота слов

##### Постановка задачи:

На вход подаётся список объектов одного типа. Верните частотный словарь этого списка.

Примеры:

    freqDict(["a", "bb", "a", "bb"]) → {"bb": 2, "a": 2}
    freqDict(["this", "and", "that", "and"]) → {"that": 1, "and": 2, "this": 1}
    freqDict(["код", "код", "код", "bug"]) → {"код": 3, "bug": 1}
    freqDict([1, 1, 2, 2]) → {1: 2, 2: 2}

## 4. Римские цифры

##### Постановка задачи:

Создать функцию, которая принимает арабское число и преобразует его в римское.

Примеры:

    convertToRoman(2) ➞ "II"
    convertToRoman(12) ➞ "XII"
    convertToRoman(16) ➞ "XVI"

## 5. Список контактов

##### Постановка задачи:

Напишите функцию сортировки, которая принимает массив имен, сортирует их по фамилии по возрастанию/убыванию (ASC/DESC) и
возвращает новый массив контактов с заданной сортировкой.

Замечания:

* если фамилия отсутствует, то нужно использовать имя
* возвращать нужно не строки, а объекты

Примеры:

    parseContacts([ "John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes" ], "ASC") 
    ->
    [ "Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke" ]
    // Aquinas (A) < Descartes (D) < Hume (H) < Locke (L)
    
    parseContacts(["Paul Erdos", "Leonhard Euler", "Carl Gauss"], "DESC")
    ->
    ["Carl Gauss", "Leonhard Euler", "Paul Erdos"]
    // Gauss (G) > Erdos (ER) > Euler (EU)
    
    parseContacts([], "DESC") ➞ []
    parseContacts(null, "DESC") ➞ []

## 6. Биржа

##### Постановка задачи:

Реализуйте класс:

    interface StockMarket {
        /** Добавить акцию */
        void add(Stock stock);
        /** Удалить акцию */
        void remove(Stock stock);
        /** Самая дорогая акция */
        Stock mostValuableStock();
    }

Внутри реализации используйте PriorityQueue для сортировки акций по цене.

## 7. Дерево и null

##### Постановка задачи:

Начиная с Java 8 в классе TreeMap нельзя использовать null в качестве ключа.

При этом, можно передать Comparator который будет обрабатывать null. Напишите и продемонстрируйте такой Comparator.

В результате у вас должен работать следующий код:

    TreeMap<String, String> tree = ...;
    tree.add(null, "test");

    assertThat(tree.contains(null)).isTrue();

## 8. Обратный итератор

##### Постановка задачи:

Реализуйте Iterator<T>, который принимает коллекцию, но при этом двигается "назад".

То есть new BackwardIterator<>(List.of(1,2,3)) должен сначала вернуть 3, потом 2, а потом 1.
