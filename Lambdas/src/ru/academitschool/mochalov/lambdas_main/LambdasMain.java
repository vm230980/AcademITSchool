package ru.academitschool.mochalov.lambdas_main;

import ru.academitschool.mochalov.lambdas_person.LambdasPerson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class LambdasMain {
    public static void main(String[] args) {
        List<LambdasPerson> lambdasPeople = Arrays.asList(
                new LambdasPerson("Ivan", 14),
                new LambdasPerson("Marina", 22),
                new LambdasPerson("Petr", 34),
                new LambdasPerson("Sergey", 12),
                new LambdasPerson("Marina", 43),
                new LambdasPerson("Vlad", 25),
                new LambdasPerson("Ivan", 16)
        );

        List<String> uniqueNames = lambdasPeople.stream()
                .map(LambdasPerson::getName)
                .distinct()
                .collect(Collectors.toList());

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        List<LambdasPerson> underageLambdasPeople = lambdasPeople.stream()
                .filter(x -> x.getAge() < 18)
                .collect(Collectors.toList());

        OptionalDouble underagePersonsAverageAge = underageLambdasPeople.stream()
                .mapToDouble(LambdasPerson::getAge)
                .average();

        if (underagePersonsAverageAge.isPresent()) {
            System.out.println("Средний возраст несовершеннолетних: " + underagePersonsAverageAge.getAsDouble());
        } else {
            System.out.println("Нет значения типа double");
        }

        Map<String, Double> namesAndAverageAges = lambdasPeople.stream()
                .collect(Collectors.groupingBy(LambdasPerson::getName, Collectors.averagingInt(LambdasPerson::getAge)));

        System.out.println("Ассоциативный массив (Ключ: Группировка по именам. Значение: Средний возраст для людей с таким именем):");
        System.out.println(namesAndAverageAges);

        List<String> certainAgeNames = lambdasPeople.stream()
                .filter(x -> x.getAge() > 20 && x.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(LambdasPerson::getName)
                .collect(Collectors.toList());

        System.out.println("Список людей в возрасте от 20 до 45 в порядке убывания возраста:" + certainAgeNames);
    }
}