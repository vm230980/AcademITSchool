package ru.academitschool.mochalov.lambdas_main;

import ru.academitschool.mochalov.lambdas_person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Ivan", 14),
                new Person("Marina", 22),
                new Person("Petr", 34),
                new Person("Sergey", 12),
                new Person("Marina", 43),
                new Person("Vlad", 25),
                new Person("Ivan", 16)
        );

        List<String> uniqueNames = people.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> underageLambdasPeople = people.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        OptionalDouble underagePeopleAverageAge = underageLambdasPeople.stream()
                .mapToDouble(Person::getAge)
                .average();

        if (underagePeopleAverageAge.isPresent()) {
            System.out.println("Средний возраст несовершеннолетних: " + underagePeopleAverageAge.getAsDouble());
        } else {
            System.out.println("Нет значения типа double");
        }

        Map<String, Double> averageAgesForNames = people.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Ассоциативный массив (Ключ: Группировка по именам. Значение: Средний возраст для людей с таким именем):");
        System.out.println(averageAgesForNames);

        List<String> certainAgePeopleNames = people.stream()
                .filter(x -> x.getAge() >= 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("Список людей в возрасте от 20 до 45 в порядке убывания возраста:" + certainAgePeopleNames);
    }
}