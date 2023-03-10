package flik;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilkTest {

    @Test
    public static void testSamenumber() {
        int x = 128;
        int y = 128;
        boolean expected = true;
        boolean acturals = Flik.isSameNumber(x, y);

        assertEquals(expected, acturals);
    }

    @Test
    public static void testMultiNumber() {
        int N = 5000;
        boolean expected = true;
        for (int i = 0; i < N; i ++) {
            int x = i;
            int y = i;
            System.out.println(i);
            assertEquals(expected, Flik.isSameNumber(x, y));
        }
    }

    public static void main(String[] args) {
        // testMultiNumber();
        testSamenumber();
    }

}
