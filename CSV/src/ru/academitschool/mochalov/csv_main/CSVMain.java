package ru.academitschool.mochalov.csv_main;

import ru.academitschool.mochalov.csv.CsvToHtmlConverter;

import java.io.FileNotFoundException;

public class CSVMain {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.printf("Введено неверное количество аргументов - %d. Количество аргументов должно быть равно 2." + System.lineSeparator(), args.length);
            System.out.println("В качестве первого аргумента нужно указать путь к файлу \".csv\", в качестве второго аргумента нужно указать путь к файлу \".html\"");
            return;
        }

        try {
            CsvToHtmlConverter.convertCsvToHtml(args[0], args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден или пути содержат ошибки");
        }
    }
}