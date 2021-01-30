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

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (this.from >= range.to || range.from >= this.to) { // !isInside(range.from) && !range.isInside(this.from)
            return null;
        } else if (this.from > range.from) {
            return new Range(this.from, Math.min(this.to, range.to));
        } else {
            return new Range(range.from, Math.min(this.to, range.to));
        }
    }

    public Range[] getUnion(Range range) {
        if (range.from > this.to) {
            Range range1 = new Range(this.from, this.to);
            Range range2 = new Range(range.from, range.to);
            return new Range[]{range1, range2};
        } else if (this.from > range.to) {
            Range range1 = new Range(range.from, range.to);
            Range range2 = new Range(this.from, this.to);
            return new Range[]{range1, range2};
        } else if (this.from > range.from) {
            Range unionRange = new Range(range.from, Math.max(this.to, range.to));
            return new Range[]{unionRange};
        } else {
            Range unionRange = new Range(this.from, Math.max(this.to, range.to));
            return new Range[]{unionRange};
        }
    }

    public Range[] getSubtraction(Range range) {
        if (this.from > range.to || range.from > this.to) {
            return new Range[]{this};
        } else if (range.from < this.from && range.to > this.to) {
            return new Range[]{null};
        } else if (this.from < range.from && this.to > range.to) {
            Range range1 = new Range(this.from, range.from);
            Range range2 = new Range(range.to, this.to);
            return new Range[]{range1, range2};
        } else if (range.from > this.from) {
            Range subtractionRange = new Range(this.from, range.from);
            return new Range[]{subtractionRange};
        } else {
            Range subtractionRange = new Range(this.to, range.to);
            return new Range[]{subtractionRange};
        }
    }
}