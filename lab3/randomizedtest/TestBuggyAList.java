package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public static void testThreeAddThreeRemove() {
        BuggyAList<Integer> abugg = new BuggyAList<>();
        abugg.addLast(4);
        abugg.addLast(5);
        abugg.addLast(6);

        AListNoResizing<Integer> acorr = new AListNoResizing<>();
        acorr.addLast(4);
        acorr.addLast(5);
        acorr.addLast(6);

        for (int i = 0; i < acorr.size(); i++) {
            int last = abugg.removeLast();
            int expected = acorr.removeLast();
            assertEquals(expected, last);
        }
    }

    @Test
    public static void randomisedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int Bsize = B.size();
                assertEquals(size,Bsize);
            } else if (L.size() != 0) {
                if (operationNumber == 2){
                    int x = L.removeLast();
                    int Bx = B.removeLast();
                    assertEquals(x,Bx);
                } else if (operationNumber == 3){
                    int x = L.getLast();
                    int Bx = B.getLast();
                    assertEquals(x,Bx);
                }
            }
        }
    }

    public static void main(String[] args) {
        //testThreeAddThreeRemove();
        randomisedTest();
    }
}
