package ru.academitschool.mochalov.csv;

public class HTMLHeaders {
    public static String addHTMLStartTags() {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \n" +
                "  \"\">\n" +
                "<html>\n" +
                " <head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <title>Таблица, полученная из .csv</title>\n" +
                "    <style>\n" +
                "   table { \n" +
                "    width: 100%; /* Ширина таблицы */\n" +
                "    border: 4px single black; /* Рамка вокруг таблицы */\n" +
                "    border-collapse: collapse; /* Отображать только одинарные линии */\n" +
                "   }\n" +
                "   th { \n" +
                "    text-align: left; /* Выравнивание по левому краю */\n" +
                "    background: #ccc; /* Цвет фона ячеек */\n" +
                "    padding: 5px; /* Поля вокруг содержимого ячеек */\n" +
                "    border: 1px solid black; /* Граница вокруг ячеек */\n" +
                "   }\n" +
                "   td { \n" +
                "    padding: 5px; /* Поля вокруг содержимого ячеек */\n" +
                "    border: 1px solid black; /* Граница вокруг ячеек */\n" +
                "   }\n" +
                "  </style>\n" +
                " </head>\n" +
                " <body>";
    }

    public static String addHTMLEndTags() {
        return " </body>\n" +
                "</html>";
    }
}
