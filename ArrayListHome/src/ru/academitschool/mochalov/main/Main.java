package ru.academitschool.mochalov.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> readLinesFromFileToArrayList(String fileName) {
        ArrayList<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + fileName + "  не найден.");
        }

        return strings;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutMatches(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>(list.size());

        for (Integer item : list) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = readLinesFromFileToArrayList("strings.txt");
        System.out.println("Строки, прочитанные из файла: " + strings);

        ArrayList<Integer> integerArrayList1 = new ArrayList<>(Arrays.asList(1, 2, 8, 4, 5, 6, 3, 4, 9, 10, 12));
        System.out.println("Список целых чисел 1: " + integerArrayList1);
        deleteEvenNumbers(integerArrayList1);
        System.out.println("Список целых 1 чисел после удаления четных чисел: " + integerArrayList1);

        ArrayList<Integer> integerArrayList2 = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 5, 5, 3, 4, 9, 2, 1, 0, 0));
        System.out.println("Список целых чисел 2: " + integerArrayList2);

        ArrayList<Integer> integerArrayList3 = getListWithoutMatches(integerArrayList2);
        System.out.println("Список целых чисел 2 после удаления одинаковых элементов: " + integerArrayList3);
    }
}