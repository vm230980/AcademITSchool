package ru.academitschool.mochalov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvToHtmlConverter {
    public static String getSpecialCharacterReplaced(char currentSymbol) {
        char lessThan = (char) 60;
        char greaterThan = (char) 62;
        char ampersand = (char) 38;

        if (currentSymbol == lessThan) {
            return "&lt;";
        }

        if (currentSymbol == greaterThan) {
            return "&gt;";
        }

        if (currentSymbol == ampersand) {
            return "&amp;";
        }

        return Character.toString(currentSymbol);
    }

    public static void convertCsvToHtml(String PathToCsv, String PathToHtml) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(PathToCsv));
             PrintWriter writer = new PrintWriter(PathToHtml)) {

            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println(" <head>");
            writer.println("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            writer.println("    <title>Таблица, полученная из .csv</title>");
            writer.println("    <style>");
            writer.println("   table {");
            writer.println("    width: 100%; /* Ширина таблицы */");
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
            writer.println("    border: 1px solid black; /* Граница вокруг ячеек */");
            writer.println("   }");
            writer.println("  </style>");
            writer.println(" </head>");
            writer.println(" <body>");
            writer.println("<table>");

            String currentString;

            while (scanner.hasNextLine()) {
                currentString = scanner.nextLine();

                writer.print("\t<tr>" + System.lineSeparator() + "\t\t");

                if (currentString.length() == 0) {
                    continue;
                }

                boolean isCellStart = true;
                boolean isThisInQuotes = false;
                char doubleQuotes = (char) 34;
                char comma = (char) 44;

                for (int i = 0; i < currentString.length(); i++) {
                    if (!isThisInQuotes && currentString.charAt(i) == doubleQuotes) {
                        isThisInQuotes = true;
                    }

                    if (!isThisInQuotes && currentString.charAt(i) == comma && (i == 0 || i >= currentString.length() - 1 || currentString.charAt(i + 1) == comma)) {
                        writer.print("<td></td>");
                        continue;
                    }

                    if (!isThisInQuotes && currentString.charAt(i) == comma) {
                        continue;
                    }

                    if (!isThisInQuotes) {
                        if (isCellStart) {
                            writer.print("<td>");
                            isCellStart = false;
                        }

                        if (i >= currentString.length() - 1 || currentString.charAt(i + 1) == comma) {
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

                        if (i >= currentString.length() - 1 && currentString.charAt(i) != doubleQuotes) {
                            writer.print(getSpecialCharacterReplaced(currentString.charAt(i)));

                            do {
                                writer.print("<br/>");
                                currentString = scanner.nextLine();
                            } while (currentString.length() == 0);

                            i = -1;
                        } else if (i >= currentString.length() - 1 && currentString.charAt(i) == doubleQuotes) {
                            writer.print("</td>");

                            isCellStart = true;
                            isThisInQuotes = false;
                        } else if (currentString.charAt(i) == doubleQuotes && currentString.charAt(i + 1) == comma) {
                            writer.print("</td>");

                            isCellStart = true;
                            isThisInQuotes = false;
                        } else if (currentString.charAt(i) == doubleQuotes && currentString.charAt(i + 1) == doubleQuotes) {
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