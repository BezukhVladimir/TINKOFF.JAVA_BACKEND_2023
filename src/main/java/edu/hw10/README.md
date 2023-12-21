# Домашнее задание №10
## Задание 1
Часто при разработке для тестирования требуются случайные данные. В этом задании мы будем писать генератор объектов на основе рефлексии.

Пример вызова может выглядеть следующим образом:

    RandomObjectGenerator rog = ...;
    
    var myClass = rog.nextObject(MyClass.class, "create");
    var myRecord = rog.nextObject(MyRecord.class);
Полезные ссылки:
* https://docs.oracle.com/en/java/javase/21/docs//api/java.base/java/lang/reflect/Proxy.html

Реализуйте поддержку генерации record и POJO. Поддерживать создание объектов нужно только через конструкторы или фабричный метод (если такой есть, например, MyClass#create).

Реализовывать поддержку списков, словарей и других сложных структур не требуется, но нужно написать код так, чтобы добавить новый тип было не очень сложно.

После этого создайте несколько аннотаций:
* @NotNull
* @Min(value)
* @Max(value)

Сделайте так, чтобы ваш генератор учитывал эти аннотации, если они присутствуют на полях конструктора или фабричного метода.

## Задание 2
Реализуйте кэширующих прокси для возвращаемых значений:

    public interface FibCalculator {
    @Cache(persist = true)
        public long fib(int number);
    }
    
    FibCalculator c = ...;
    FibCalculator proxy = CacheProxy.create(c, c.getClass());

У аннотации @Cache есть опциональный параметр persist, который сохраняет результаты на диск, например, во временный каталог.

Интерфейс FibCalculator приведён для примера, CacheProxy должен уметь работать и с другими интерфейсами.
