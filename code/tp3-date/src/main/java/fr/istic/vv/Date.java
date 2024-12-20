package fr.istic.vv;

import java.time.LocalDate;
import java.util.Objects;

class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        // On va s'appuyer  sur LocalDate pour la validité
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean isLeapYear(int year) {
        return java.time.Year.isLeap(year);
    }

    public Date nextDate() {
        LocalDate current = LocalDate.of(year, month, day);
        LocalDate next = current.plusDays(1);
        return new Date(next.getDayOfMonth(), next.getMonthValue(), next.getYear());
    }

    public Date previousDate() {
        LocalDate current = LocalDate.of(year, month, day);
        LocalDate prev = current.minusDays(1);
        return new Date(prev.getDayOfMonth(), prev.getMonthValue(), prev.getYear());
    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other, "Other date must not be null");

        LocalDate thisDate = LocalDate.of(year, month, day);
        LocalDate otherDate = LocalDate.of(other.year, other.month, other.day);
        return thisDate.compareTo(otherDate);
    }

    // Méthodes pour les tests
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }

}
