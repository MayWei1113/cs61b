package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    private static String errorMessage(String[] s, int j) {
        String errMes = "\n";
        for (int oind = j - 2; oind <= j; oind += 1) {
            if (oind < 0) {
                continue;
            }
            errMes = errMes + s[oind] + '\n';
        }
        return errMes;
    }
    @Test
    public static void randomisedTest() {
        ArrayDequeSolution<Integer> L = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> B = new StudentArrayDeque<>();

        int N = 5000;
        String[] output = new String[N];
        int j = 0;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                int size = L.size();
                int bSize = B.size();
                output[j] = "addLast(" + randVal + ")";
                String errmes = errorMessage(output, j);
                j = j + 1;
                assertEquals(errmes, size, bSize);
            } else if (operationNumber == 1) {
                // size
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                B.addFirst(randVal);
                int size = L.size();
                int bSize = B.size();
                output[j] = "addFirst(" + randVal + ")";
                String errmes = errorMessage(output, j);
                j = j + 1;
                assertEquals(errmes, size, bSize);
            } else if (operationNumber == 2 & L.size() > 0) {
                // remove last
                Integer x = L.removeLast();
                Integer bX = B.removeLast();
                output[j] = "removeLast()";
                String errmes = errorMessage(output, j);
                j = j + 1;
                assertEquals(errmes, x, bX);
            } else if (operationNumber == 3 & L.size() > 0) {
                // remove first
                Integer x = L.removeFirst();
                Integer bX = B.removeFirst();
                output[j] = "removeFirst()";
                String errmes = errorMessage(output, j);
                j = j + 1;
                assertEquals(errmes, x, bX);
            }
        }
    }

    public static void main(String[] args) {
        randomisedTest();
    }
}
