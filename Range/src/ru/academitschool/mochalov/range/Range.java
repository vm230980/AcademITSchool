package ru.academitschool.mochalov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }

    public static String getRangesArrayString(Range[] rangesArray) {
        if (rangesArray.length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (Range r : rangesArray) {
            stringBuilder.append(r).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
        return stringBuilder.toString();
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (from >= range.to || range.from >= to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (range.from > to || from > range.to) {
            return new Range[]{
                    new Range(Math.min(from, range.from), Math.min(to, range.to)),
                    new Range(Math.max(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.to || range.from >= to) {
            return new Range[]{new Range(from, to)};
        }

        if (range.from <= from && range.to >= to) {
            return new Range[]{};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from >= range.from) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{new Range(from, range.from)};
    }
}