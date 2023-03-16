package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;
    int maxDex = 0;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }
    

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        for (int i = 0; i < this.size(); i += 1) {
            T a = this.get(i);
            T b = this.get(maxDex);
            int cmp = comp.compare(a, b);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return this.get(maxDex);

    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        for (int i = 0; i < this.size(); i += 1) {
            T a = this.get(i);
            T b = this.get(maxDex);
            int cmp = c.compare(a, b);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return this.get(maxDex);
    }
}
