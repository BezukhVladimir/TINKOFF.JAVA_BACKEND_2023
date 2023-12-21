# Домашнее задание №6
## Задание 1
Реализуйте класс DiskMap, который представляет собой ассоциативный массив, хранящий пары ключ-значение на жестком диске. Класс должен реализовывать интерфейс Map<String, String>.

Ключи и значения должны быть сохранены на жестком диске в файле в формате "ключ:значение". Класс должен поддерживать сохранение и загрузку из файла на диске.

## Задание 2
При копировании файла в ту же папку в Проводнике Windows создается его копия, копия автоматически получает новое имя. Воспроизведём это поведение.

Напишите функцию cloneFile(Path path), которая создает копию файла с новым именем.

Например, файл называется Tinkoff Bank Biggest Secret.txt. Тогда новые имена файлов должны выглядеть следующим образом:

    Tinkoff Bank Biggest Secret.txt
    Tinkoff Bank Biggest Secret — копия.txt
    Tinkoff Bank Biggest Secret — копия (2).txt
    Tinkoff Bank Biggest Secret — копия (3).txt

## Задание 3
Класс Files предоставляет три статических метода для запроса всех записей в каталоге:

    newDirectoryStream(Path dir)
    newDirectoryStream(Path dir, String glob)
    newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter)

Результатом всегда является DirectoryStream<Path>. Первый метод не фильтрует результаты, второй метод позволяет использовать glob-строку, например *.txt, а третий метод позволяет использовать произвольный фильтр.

java.nio.file.DirectoryStream.Filter<T> - это интерфейс-предикат, который должны реализовывать фильтры. В JDK объявлен интерфейс, но нет реализаций.

Напишите реализации для DirectoryStream.Filter, которые проверяют:
* атрибуты (например, readable, writable)
* размер файла
* расширения файлов
* имя файла с помощью регулярных выражений
* магические начальные идентификаторы

Итоговый API нужно сделать цепочечным, т.е. должна быть возможность объединить все фильтры в один:

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {
        // TODO
    }
    
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
    
    
    DirectoryStream.Filter<Path> filter = regularFile
        .and(readable)
        .and(largerThan(100_000))
        .and(magicNumber(0x89, 'P', 'N', 'G'))
        .and(globMatches("*.png"))
        .and(regexContains("[-]"));
    
    try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
        entries.forEach(System.out::println);
    }

## Задание 4
В этом задании мы будем делать композицию OutputStream'ов, стрелка -> указывает, кто куда пишет:

    PrintWriter -> OutputStreamWriter -> BufferedOutputStream -> CheckedOutputStream -> file OutputStream.

При построении цепочек такого типа всегда следует начинать с самого нижнего уровня:
1. Создайте файл (Files.new*(...)) и получите из него OutputStream
2. Добавьте к нему CheckedOutputStream для проверки записи при помощи контрольной суммы
3. Для буферизации данных добавьте BufferedOutputStream
4. Чтобы не работать с сырыми байтами добавьте OutputStreamWriter, и включите поддержку UTF-8.
5. Добавьте финальный PrintWriter и запишите в файл текст: Programming is learned by writing programs. ― Brian Kernighan

Не забудьте закрыть ресурсы с помощью try-with-resources.

## Задание 5
Hacker News - это сайт с актуальными обсуждениями технологических тенденций. Доступ к статьям осуществляется через веб-сервис, а документацию можно найти на сайте https://github.com/HackerNews/API.

Нас интересуют 2 endpoint'а:
1. https://hacker-news.firebaseio.com/v0/topstories.json: возвращает JSON-массив с идентификаторами наиболее обсуждаемых статей.
2. https://hacker-news.firebaseio.com/v0/item/37570037.json: возвращает JSON-объект, содержащий сообщение с идентификатором 37570037.

Создайте класс HackerNews.

Реализуйте метод long[] hackerNewsTopStories(), который будет
* делать HTTP-запрос при помощи HttpClient к https://hacker-news.firebaseio.com/v0/topstories.json
* конвертировать возвращаемый JSON в long[]

В общем случае для чтения JSON используются специальные парсеры, но т.к. структура массива очень простая, то можем обойтись без них. В реальном приложении, конечно ,мы бы использовали библиотеку, например, Jackson.

В случае ошибки ввода-вывода должен быть возвращен пустой массив.

Напишите метод String news(long id), который возвращает название новости.

Для получения имени новости используйте регулярное выражение.

Пример:

    System.out.println(Arrays.toString(hackerNewsTopStories()));
    String newsTitle = news(37570037);
    System.out.println(newsTitle);

## Задание 6
Напишите программу, которая сканирует порты и определяет заняты они или нет.

Для этого нужно зарегистрировать ServerSocket и DatagramSocket на всех TCP/UDP-портах от 0 до 49151.

В случае успеха порт свободен, в противном случае он занят.

Дополнительно выведите информацию о потенциальном приложении, которое использует этот порт, список известных портов.

Выберите несколько, не нужно брать всё, например, https://www.tutorialspoint.com/50-common-ports-you-should-know.

Пример вывода программы:

    Протокол  Порт   Сервис
    TCP       135    EPMAP
    UDP       137    Служба имен NetBIOS
    UDP       138    Служба датаграмм NetBIOS
    TCP       139    Служба сеансов NetBIOS
    TCP       445    Microsoft-DS Active Directory
    TCP       843    Adobe Flash
    UDP       1900   Simple Service Discovery Protocol (SSDP)
    UDP       3702   Динамическое обнаружение веб-служб
    TCP       5040   
    UDP       5050   
    UDP       5353   Многоадресный DNS
    UDP       5355   Link-Local Multicast Name Resolution (LLMNR)
    TCP       5939   
    TCP       6463   
    TCP       6942   
    TCP       17500  Dropbox
    UDP       17500  Dropbox
    TCP       17600
    TCP       27017  MongoDB
    UDP       42420
