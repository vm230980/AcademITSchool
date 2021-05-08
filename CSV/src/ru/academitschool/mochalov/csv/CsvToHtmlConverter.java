package ru.academitschool.mochalov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvToHtmlConverter {
    public static String getSpecialCharacterReplaced(char currentSymbol) {
        if (currentSymbol == '<') {
            return "&lt;";
        }

        if (currentSymbol == '>') {
            return "&gt;";
        }

        if (currentSymbol == '&') {
            return "&amp;";
        }

        return Character.toString(currentSymbol);
    }

    public static void convertCsvToHtml(String pathToCsv, String pathToHtml) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(pathToCsv));
             PrintWriter writer = new PrintWriter(pathToHtml)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println(" <head>");
            writer.println("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            writer.println("    <title>Таблица, полученная из .csv</title>");
            writer.println("    <style>");
            writer.println("   table {");
            writer.println("    width: 50%; /* Ширина таблицы */");
            writer.println("    border: 4px single black; /* Рамка вокруг таблицы */");
            writer.println("    border-collapse: collapse; /* Отображать только одинарные линии */");
            writer.println("   }");
            writer.println("   th {");
            writer.println("    text-align: left; /* Выравнивание по левому краю */");
            writer.println("    background: #ccc; /* Цвет фона ячеек */");
            writer.println("    padding: 5px; /* Поля вокруг содержимого ячеек */");
            writer.println("    border: 1px solid black; /* Граница вокруг ячеек */");
            writer.println("   }");
            writer.println("   td {");
            writer.println("    padding: 5px; /* Поля вокруг содержимого ячеек */");
            writer.println("    text-align: center; /* Выравнивание по центру */");
            writer.println("    border: 1px solid black; /* Граница вокруг ячеек */");
            writer.println("   }");
            writer.println("  </style>");
            writer.println(" </head>");
            writer.println(" <body>");
            writer.println("<table>");

            String currentString;

            while (scanner.hasNextLine()) {
                currentString = scanner.nextLine();

                if (currentString.length() == 0) {
                    continue;
                }

                writer.print("\t<tr>" + System.lineSeparator() + "\t\t");

                boolean isCellStart = true;
                boolean isThisInQuotes = false;

                for (int i = 0; i < currentString.length(); i++) {
                    if (!isThisInQuotes && currentString.charAt(i) == '"') {
                        isThisInQuotes = true;
                    }

                    if (!isThisInQuotes && currentString.charAt(i) == ',') {
                        if (i == 0 ||  currentString.charAt(i - 1) == ',') {
                            writer.print("<td></td>");
                        }

                       if (i == currentString.length() - 1)  {
                           writer.print("<td></td>");
                       }

                        continue;
                    }

                    if (!isThisInQuotes) {
                        if (isCellStart) {
                            writer.print("<td>");
                            isCellStart = false;
                        }

                        if (i >= currentString.length() - 1 || currentString.charAt(i + 1) == ',') {
                            writer.print(getSpecialCharacterReplaced(currentString.charAt(i)) + "</td>");
                            isCellStart = true;
                        } else {
                            writer.print(getSpecialCharacterReplaced(currentString.charAt(i)));
                        }
                    } else {
                        if (isCellStart) {
                            writer.print("<td>");
                            isCellStart = false;

                            if (currentString.length() == 1 || i >= currentString.length() - 1) {
                                writer.print("<br/>");
                                currentString = scanner.nextLine();
                                i = -1;
                                continue;
                            }

                            continue;
                        }

                        if (i >= currentString.length() - 1 && currentString.charAt(i) != '"') {
                            writer.print(getSpecialCharacterReplaced(currentString.charAt(i)));

                            do {
                                writer.print("<br/>");
                                currentString = scanner.nextLine();
                            } while (currentString.length() == 0);

                            i = -1;
                        } else if (i >= currentString.length() - 1 && currentString.charAt(i) == '"') {
                            writer.print("</td>");
                            isCellStart = true;
                            isThisInQuotes = false;
                        } else if (currentString.charAt(i) == '"' && currentString.charAt(i + 1) == ',') {
                            writer.print("</td>");
                            isCellStart = true;
                            isThisInQuotes = false;
                        } else if (currentString.charAt(i) == '"' && currentString.charAt(i + 1) == '"') {
                            writer.print("\"");
                            if (i + 2 == currentString.length()) {
                                writer.print("<br/>");
                                currentString = scanner.nextLine();
                                i = -1;
                            } else {
                                i++;
                            }
                        } else {
                            writer.print(getSpecialCharacterReplaced(currentString.charAt(i)));
                        }
                    }
                }

                writer.println(System.lineSeparator() + "\t</tr>");
            }

            writer.println("</table>");
            writer.println(" </body>");
            writer.print("</html>");
        }
    }
}