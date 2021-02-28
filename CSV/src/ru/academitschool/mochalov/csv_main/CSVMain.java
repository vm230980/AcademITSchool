package ru.academitschool.mochalov.csv_main;

import ru.academitschool.mochalov.csv.CSVToHTMLConverter;

import java.io.FileNotFoundException;

public class CSVMain {
    public static void main(String[] args) throws FileNotFoundException {
        CSVToHTMLConverter.convertCSVToHTML(args[0], args[1]);
    }
}