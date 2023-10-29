package edu.hw4;

import edu.hw4.validator.AnimalValidator;
import edu.hw4.validator.errors.ValidationError;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    public static List<Animal> getSortedAnimalsByHeightAscending(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> getTopKHeaviestAnimals(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    static Map<Animal.Type, Long> getNumberOfAnimalsByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    /**
     * Determines the most common sex among a list of animals.
     *
     * @param animals The list of animals to analyze.
     * @return The most common sex (Sex.M or Sex.F),
     *     or null if the number of males and females is equal.
     */
    static Animal.Sex getMostCommonSex(List<Animal> animals) {
        Map<Animal.Sex, Long> sexCountMap = animals.stream()
            .collect(Collectors.groupingBy(
                Animal::sex,
                Collectors.counting()));

        long maleCount   = sexCountMap.getOrDefault(Animal.Sex.M, 0L);
        long femaleCount = sexCountMap.getOrDefault(Animal.Sex.F, 0L);

        if (maleCount == femaleCount) {
            return null;
        }

        return maleCount > femaleCount
            ? Animal.Sex.M
            : Animal.Sex.F;
    }

    static Map<Animal.Type, Animal> getHeaviestAnimalPerType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                    optional -> optional.orElse(null)
                )
            ));
    }

    public static Animal getKthOldestAnimal(List<Animal> animals, int k) {
        if (k <= 0 || k > animals.size()) {
            return null;
        }

        List<Animal> sortedAnimals = animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList();

        return sortedAnimals.get(k - 1); // Вернуть K-ое самое старое животное
    }

    public static Animal getHeaviestAnimalBelowHeight(List<Animal> animals, int maxHeight) {
        return animals.stream()
            .filter(animal -> animal.height() < maxHeight)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static int getSumOfAllPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAnimalsWithMismatchedAgeAndPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public static List<Animal> getBitingAnimalsWithHeightOver100(List<Animal> animals) {
        final int minHeight = 100;

        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > minHeight)
            .toList();
    }

    public static long getNumberOfAnimalsWithWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> getAnimalsWithNamesMoreThanTwoWords(List<Animal> animals) {
        Pattern pattern = Pattern.compile("\\s+");

        return animals.stream()
            .filter(animal -> pattern.split(animal.name()).length > 2)
            .toList();
    }

    public static boolean hasDogTallerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    static Map<Animal.Type, Integer> getSumWeightByTypeInAgeRange(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> k <= animal.age() && animal.age() <= l)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    public static List<Animal> getSortedAnimalsByTypeSexNameAscending(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static boolean doSpidersBiteMoreThanDogs(List<Animal> animals) {
        long bitingSpiders = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        long bitingDogs = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        return bitingSpiders > bitingDogs;
    }

    public static Animal getHeaviestFishInLists(List<List<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, List<ValidationError>> getValidationErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidator::getValidationErrors
            ));
    }

    public static Map<String, String> getValidationErrorMessages(List<Animal> animals) {
        Map<String, List<ValidationError>> errors = getValidationErrors(animals);

        return errors.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(Throwable::getMessage)
                    .collect(Collectors.joining(", "))
            ));
    }
}
