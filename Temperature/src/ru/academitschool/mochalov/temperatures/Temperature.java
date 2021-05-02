package ru.academitschool.mochalov.temperatures;

public interface Temperature {
    String getName();

    double getCelsius (double valueThis);

    double getThis (double celsius);
}