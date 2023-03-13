package deque;
import afu.org.checkerframework.checker.oigj.qual.O;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {

    private class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
    private IntComparator c = new IntComparator();
    private StringComparator s = new StringComparator();

    @Test
    public void NullArrayTest() {

        MaxArrayDeque<Integer> intMaxArray = new MaxArrayDeque<Integer>(c);
        Object largest = intMaxArray.max();
        Object largest2 = intMaxArray.max(c);
        assertEquals(null, largest);
        assertEquals(null, largest2);
    }

    @Test
    public void IntArrayTest() {
        MaxArrayDeque<Integer> intMaxArray = new MaxArrayDeque<Integer>(c);
        intMaxArray.addFirst(10);
        intMaxArray.addLast(23);
        intMaxArray.addFirst(23);
        Object largest = intMaxArray.max();
        Object largest2 = intMaxArray.max(c);
        assertEquals(23, largest);
        assertEquals(23, largest2);
    }

    @Test
    public void StringArrayTest() {
        MaxArrayDeque<String> stringMaxArray = new MaxArrayDeque<String>(s);
        stringMaxArray.addFirst("Bob");
        stringMaxArray.addFirst("Dec");
        stringMaxArray.addFirst("Caffee");
        Object largest = stringMaxArray.max();
        Object largest2 = stringMaxArray.max(s);
        assertEquals("Dec", largest);
        assertEquals("Dec", largest2);
    }

}
