# Проект 5: быстрая рефлексия

Представим, что мы пишем программу для сериализации/десериализации данных, например, из POJO в JSON.

Другими словами, мы хотим чтобы класс

    public record Student(String name, String surname) { }

автоматически конвертировался в

    {
        "name": "...",
        "surname": "..."
    }

Конвертер такого типа нужно писать максимально общим, потому что мы можем не знать, что нам приходит на вход, из-за полиморфных коллекций, в которых происходит стирание типов, т.е. метод должен всегда принимать Object:

    JsonMapper mapper = ...;
    Object o = new Student("Alexander","Biryukov");
    var json = mapper.convert(o);

Тратить ресурсы на сканирование объекта, поиск полей и т.п. каждый раз очень дорого, поэтому конвертеры кэшируют такую информацию.

Но даже с кэшированием рефлексия не будет такой же быстрой, как если бы мы написали код руками, потому что java.lang.reflect.Method перед каждым вызовом будет проверять права на вызов (см справочную информацию).

Поэтому в Java появилось несколько альтернативных API для вызова методов через рефлексию.

Справочная информация
Цикл статей про рефлексию:

1. https://blogs.oracle.com/javamagazine/post/java-reflection-introduction
2. https://blogs.oracle.com/javamagazine/post/java-reflection-performance
3. https://blogs.oracle.com/javamagazine/post/java-reflection-method-handles

Полезные ссылки:
* https://github.com/openjdk/jmh/tree/master/jmh-samples/src/main/java/org/openjdk/jmh/samples
* https://habr.com/ru/articles/318418/
* https://habr.com/ru/companies/haulmont/articles/431922/
* https://habr.com/ru/companies/haulmont/articles/432418/


### Оффтоп: Альтернатива рефлексии

Альтернативным способом является кодогенерация. Например, так сделано во фреймворке Kora от Тинькофф.

Плюсы:
* не нужно тратить время в runtime на анализ классов (рефлексия)
* максимальная производительность

Минусы:
* требуется как-то встроить кодогенерацию
* не поддерживаются типы из сторонних библиотек

### Задание
В этом задании вам потребуется реализовать и сравнить производительность 4 способов обращения к методу Student#name() (или любого другого класса/интерфейса):

1. Прямой доступ
2. java.lang.reflect.Method
3. java.lang.invoke.MethodHandles
4. java.lang.invoke.LambdaMetafactory

Скорость обращения к полю обычно изменяется в наносекундах и сильно зависит от текущего состояния операционной системы, поэтому такие замеры делаются при помощи специальной библиотеки под названием JMH -- Java Microbenchmark Harness.

Ваше задание состоит в том, чтобы написать набор JMH-тестов для каждого сценария и сравнить, насколько медленнее работает каждый из способов по сравнению с прямым доступом.

Помимо кода в MR приложите финальную таблицу JMH-тестов (в описание).

##### На что обратить внимание
1. Вся подготовительная работа (получение Method, вызовы LambdaMetafactory и т.п.) должны производиться в @Setup-методе (!), это важно, потому что мы замеряем производительность вызова.
2. Не забудьте про Blackhole-параметр и вызов consume. Без него оптимизирующий компилятор JVM может посчитать код "мертвым" и вы получите некорректные данные.
3. Для получения финальных результатов нужно оставить компьютер без фоновой нагрузки и увеличить время выполнения бенчмарка до пары минут

##### Как подключить JMH
    <properties>
        <jmh.version>1.37</jmh.version>
    </properties>

    <dependencies>
        <!-- JMH -->
       <dependency>
          <groupId>org.openjdk.jmh</groupId>
          <artifactId>jmh-core</artifactId>
          <version>${jmh.version}</version>
       </dependency>
       <dependency>
          <groupId>org.openjdk.jmh</groupId>
          <artifactId>jmh-generator-annprocess</artifactId>
          <version>${jmh.version}</version>
          <scope>provided</scope>
       </dependency>
    </dependencies>

##### Пример теста
    import java.lang.reflect.InvocationTargetException;
    import java.lang.reflect.Method;
    import java.util.concurrent.TimeUnit;
    import org.openjdk.jmh.annotations.Benchmark;
    import org.openjdk.jmh.annotations.Mode;
    import org.openjdk.jmh.annotations.Scope;
    import org.openjdk.jmh.annotations.Setup;
    import org.openjdk.jmh.annotations.State;
    import org.openjdk.jmh.infra.Blackhole;
    import org.openjdk.jmh.runner.Runner;
    import org.openjdk.jmh.runner.RunnerException;
    import org.openjdk.jmh.runner.options.Options;
    import org.openjdk.jmh.runner.options.OptionsBuilder;
    import org.openjdk.jmh.runner.options.TimeValue;
    
    @State(Scope.Thread)
    public class ReflectionBenchmark {
        public static void main(String[] args) throws RunnerException {
            Options options = new OptionsBuilder()
                    .include(ReflectionBenchmark.class.getSimpleName())
                    .shouldFailOnError(true)
                    .shouldDoGC(true)
                    .mode(Mode.AverageTime)
                    .timeUnit(TimeUnit.NANOSECONDS)
                    .forks(1)
                    .warmupForks(1)
                    .warmupIterations(1)
                    .warmupTime(TimeValue.seconds(5))
                    .measurementIterations(1)
                    .measurementTime(TimeValue.seconds(5))
                    .build();

            new Runner(options).run();
        }

        record Student(String name, String surname) {
        }
    
        private Student student;
        private Method method;
    
        @Setup
        public void setup() throws NoSuchMethodException {
            student = new Student("Alexander", "Biryukov");
            method = ...;
        }
    
        @Benchmark
        public void directAccess(Blackhole bh) {
            String name = student.name();
            bh.consume(name);
        }
    
        @Benchmark
        public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
            // TODO
        }
    }
