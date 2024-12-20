package fr.istic.vv;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    @Test
    void testEmptyHeapPop() {

        BinaryHeap<Integer> heap = new BinaryHeap<>((Comparator<Integer>) Comparator.naturalOrder());

        assertThrows(NoSuchElementException.class, heap::pop);
    }

    @Test
    void testEmptyHeapPeek() {
        BinaryHeap<Integer> heap = new BinaryHeap<>((Comparator<Integer>) Comparator.naturalOrder());

        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    void testPushAndPopSingleElement() {
        BinaryHeap<Integer> heap = new BinaryHeap<>((Comparator<Integer>) Comparator.naturalOrder());

        heap.push(10);
        assertEquals(1, heap.count());
        assertEquals(10, heap.pop());
        assertEquals(0, heap.count());
    }

    @Test
    void testHeapOrdering() {

        BinaryHeap<Integer> heap = new BinaryHeap<>((Comparator<Integer>) Comparator.naturalOrder());

        heap.push(5);
        heap.push(3);
        heap.push(4);
        heap.push(10);
        heap.push(1);

        // Le plus petit est 1
        assertEquals(1, heap.pop());

        assertEquals(3, heap.pop());

        assertEquals(4, heap.pop());

        assertEquals(5, heap.pop());

        assertEquals(10, heap.pop());
    }

    @Test
    void testPeekDoesNotRemove() {
        BinaryHeap<Integer> heap = new BinaryHeap<>((Comparator<Integer>) Comparator.naturalOrder());

        heap.push(2);

        heap.push(1);

        assertEquals(1, heap.peek());
        assertEquals(2, heap.count());
    }

}
