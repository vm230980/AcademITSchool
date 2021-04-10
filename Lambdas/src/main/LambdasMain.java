package main;

import ru.academitschool.mochalov.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 14),
                new Person("Marina", 22),
                new Person("Petr", 34),
                new Person("Sergey", 12),
                new Person("Marina", 43),
                new Person("Vlad", 25),
                new Person("Ivan", 16)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> underagePersons = persons.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        double underagePersonsAverageAge = underagePersons.stream()
                .mapToDouble(Person::getAge)
                .reduce(0, Double::sum) / underagePersons.size();

        System.out.println("Средний возраст несовершеннолетних: " + underagePersonsAverageAge);

        Map<String, Double> groupedByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        System.out.println("Ассоциативный массив (Ключ: Группировка по именам. Значение: Средний возраст для людей с таким именем):");
        System.out.println(groupedByName);

        List<String> certainAgeNames = persons.stream()
                .filter(x -> x.getAge() > 20 && x.getAge() < 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("Список людей в возрасте от 20 до 45 в порядке убывания возраста:" + certainAgeNames);
    }
}