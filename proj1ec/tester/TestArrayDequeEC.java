package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.lang.reflect.Array;

public class TestArrayDequeEC {

    private String errorMessage(String[] s, int j) {
        String errMes = "";
        for (int oind = j - 2; oind <= j; oind += 1) {
            if (oind < 0) {
                continue;
            }
            errMes = errMes + '\n' + s[oind];
        }
        return errMes;
    }

    public String[] checkSize(ArrayDequeSolution L, StudentArrayDeque B, String[] errMessages, int j) {
        int corrSize = L.size();
        int stuSize = B.size();
        errMessages[j] = "size()";
        String errmess = errorMessage(errMessages, j);
        assertEquals(errmess, corrSize, stuSize);
        return errMessages;
    }
    @Test
    public void randomisedTest() {
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
                output[j] = "addLast(" + randVal + ")";
                j += 1;
            } else if (operationNumber == 1) {
                // size
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                B.addFirst(randVal);
                output[j] = "addFirst(" + randVal + ")";
                j += 1;
            } else if (operationNumber == 2 & L.size() > 0) {
                // remove last
                Integer x = L.removeLast();
                Integer bX = B.removeLast();
                output[j] = "removeLast()";
                String errmes = errorMessage(output, j);
                assertEquals(errmes, x, bX);
                j += 1;
            } else if (operationNumber == 3 & L.size() > 0) {
                // remove first
                Integer x = L.removeFirst();
                Integer bX = B.removeFirst();
                output[j] = "removeFirst()";
                String errmes = errorMessage(output, j);
                assertEquals(errmes, x, bX);
                j += 1;
            }
        }
    }

    public void main(String[] args) {
        randomisedTest();
    }
}
