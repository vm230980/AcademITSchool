package ru.academitschool.mochalov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVToHTMLConverter {
    public static void convertCSVToHTML(String PathToCSV, String PathToHTML) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(PathToCSV));
             PrintWriter writer = new PrintWriter(PathToHTML)) {

            writer.println(HTMLHeaders.addHTMLStartTags());
            writer.println("<table>");

            String currentString;

            while (scanner.hasNextLine()) {
                currentString = scanner.nextLine();

                if (currentString.contains("<")) {
                    currentString = currentString.replace("<", "&lt");
                } else if (currentString.contains(">")) {
                    currentString = currentString.replace(">", "&gt");
                } else if (currentString.contains("&")) {
                    currentString = currentString.replace("&", "&amp");
                }

                if (currentString.length() == 0) {
                    continue;
                } else if (!currentString.contains("\"")) {
                    currentString = "<td>" + currentString.replace(",", "</td><td>") + "</td>";
                } else {
                    StringBuilder stringBuilder = new StringBuilder();

                    boolean isCellStart = true;
                    boolean isThisInQuotes = false;
                    char doubleQuotes = (char) 34;  // " = (char) 34
                    char comma = (char) 44;         // , = (char) 44

                    for (int i = 0; i < currentString.length(); i++) {
                        if (!isThisInQuotes && currentString.charAt(i) == doubleQuotes) {
                            isThisInQuotes = true;
                        }

                        if (!isThisInQuotes && currentString.charAt(i) == comma && (i == 0 || i >= currentString.length() - 1 || currentString.charAt(i + 1) == comma)) {
                            stringBuilder.append("<td></td>");
                            continue;
                        }

                        if (!isThisInQuotes && currentString.charAt(i) == comma) {
                            continue;
                        }

                        if (!isThisInQuotes) {
                            if (isCellStart) {
                                stringBuilder.append("<td>");
                                isCellStart = false;
                            }

                            if (i >= currentString.length() - 1) {
                                stringBuilder.append(currentString.charAt(i)).append("</td>");
                                isCellStart = true;
                            } else if (currentString.charAt(i + 1) == comma) {
                                stringBuilder.append(currentString.charAt(i)).append("</td>");
                                isCellStart = true;
                            } else {
                                stringBuilder.append(currentString.charAt(i));
                            }
                        } else {
                            if (isCellStart) {
                                stringBuilder.append("<td>");
                                isCellStart = false;

                                if (currentString.length() == 1 || i >= currentString.length() - 1) {
                                    stringBuilder.append("<br/>");
                                    currentString = scanner.nextLine();
                                    i = -1;
                                    continue;
                                }

                                continue;
                            }

                            if (i >= currentString.length() - 1 && currentString.charAt(i) != doubleQuotes) {
                                stringBuilder.append(currentString.charAt(i));

                                do {
                                    stringBuilder.append("<br/>");
                                    currentString = scanner.nextLine();
                                } while (currentString.length() == 0);

                                i = -1;
                            } else if (i >= currentString.length() - 1 && currentString.charAt(i) == doubleQuotes) {
                                stringBuilder.append("</td>");

                                isCellStart = true;
                                isThisInQuotes = false;
                            } else if (currentString.charAt(i) == doubleQuotes && currentString.charAt(i + 1) == comma) {
                                stringBuilder.append("</td>");

                                isCellStart = true;
                                isThisInQuotes = false;
                            } else if (currentString.charAt(i) == doubleQuotes && currentString.charAt(i + 1) == doubleQuotes) {
                                stringBuilder.append("\"");

                                if (i + 2 == currentString.length()) {
                                    stringBuilder.append("<br/>");
                                    currentString = scanner.nextLine();
                                    i = -1;
                                } else {
                                    i++;
                                }
                            } else {
                                stringBuilder.append(currentString.charAt(i));
                            }
                        }
                    }

                    currentString = stringBuilder.toString();
                }

                writer.print("\t<tr>\n\t\t");
                writer.print(currentString);
                writer.println("\n\t</tr>");
            }

            writer.println("</table>");
            writer.print(HTMLHeaders.addHTMLEndTags());
        }
    }
}