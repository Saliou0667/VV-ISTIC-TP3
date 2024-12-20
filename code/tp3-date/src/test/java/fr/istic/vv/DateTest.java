package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // Tests sur le constructeur
    @Test
    void testValidDate() {
        Date d = new Date(1, 1, 2020);
        assertEquals(1, d.getDay());
        assertEquals(1, d.getMonth());
        assertEquals(2020, d.getYear());
    }

    @Test
    void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(31, 11, 2020));
    }

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(29, 2, 2020)); // année bissextile
        assertFalse(Date.isValidDate(29, 2, 2021));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertFalse(Date.isLeapYear(2019));
    }

    @Test
    void testNextDate() {
        Date d = new Date(31, 12, 2020);
        Date next = d.nextDate();
        assertEquals(1, next.getDay());
        assertEquals(1, next.getMonth());
        assertEquals(2021, next.getYear());
    }

    @Test
    void testPreviousDate() {
        Date d = new Date(1, 1, 2021);
        Date prev = d.previousDate();
        assertEquals(31, prev.getDay());
        assertEquals(12, prev.getMonth());
        assertEquals(2020, prev.getYear());
    }

    @Test
    void testCompareTo() {
        Date d1 = new Date(1, 1, 2020);
        Date d2 = new Date(2, 1, 2020);
        Date d3 = new Date(1, 1, 2020);

        assertTrue(d1.compareTo(d2) < 0);
        assertTrue(d2.compareTo(d1) > 0);
        assertEquals(0, d1.compareTo(d3));

        assertThrows(NullPointerException.class, () -> d1.compareTo(null));
    }

}

