package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {


        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized Array Deque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        lld1.addFirst(9);
        lld1.addFirst(8);
        lld1.addFirst(7);
        lld1.addFirst(6);
        lld1.addFirst(5);
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);

        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());
        int x = lld1.removeLast();
        int x2 = lld1.removeLast();
        int x3 = lld1.removeLast();
        int x4 = lld1.removeLast();
        int x5 = lld1.removeLast();
        int x6 = lld1.removeLast();
        int x7 = lld1.removeLast();
        int x8 = lld1.removeLast();
        int x9 = lld1.removeLast();
        int x10 = lld1.removeLast();

        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
        int x11 = lld1.removeLast();
        int x12 = lld1.removeLast();
        int x13 = lld1.removeLast();
        int x14 = lld1.removeLast();
        int x15 = lld1.removeLast();
        int x16 = lld1.removeLast();

        // test for the coherence of add last and add first
        lld1.addLast(12);
        int y = lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
        assertEquals(10, x);
        assertEquals(9, x2);
        assertEquals(8, x3);
        assertEquals(7, x4);
        assertEquals(6, x5);
        assertEquals(5, x6);
        assertEquals(4, x7);
        assertEquals(3, x8);
        assertEquals(2, x9);
        assertEquals(1, x10);
        assertEquals(2, x11);
        assertEquals(3, x12);
        assertEquals(4, x13);
        assertEquals(5, x14);
        assertEquals(6, x15);
        assertEquals(7, x16);
        assertEquals(12, y);
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

        assertEquals("string", s);
        assertEquals(3.14159, d, 0.0001);
        assertEquals(true, b);
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void forIterTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);

        // iteration
        for (int i : lld1) {
            System.out.println(i);
        }

    }

    @Test
    public void equalTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);

        ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>();
        lld2.addFirst(10);
        lld2.addFirst(20);
        lld2.addFirst(30);

        System.out.println(lld1.equals(lld2));
        System.out.println(lld1.equals(null));
        System.out.println(lld1.equals("lld2"));
    }

    @Test
    public void iterTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);
        lld1.addFirst(40);
        lld1.addFirst(50);
        lld1.addFirst(60);
        lld1.addFirst(70);
        lld1.addFirst(80);
        lld1.addFirst(90);

        //iterator
        Iterator<Integer> iter = lld1.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }

        //iteration
        for (int i : lld1) {
            System.out.println(i);
        }
    }

}
