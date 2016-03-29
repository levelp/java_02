package geometry;

/**
 * Отрезок
 */
public class Segment {
    Point a, b;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Segment))
            return false;

        Segment s = (Segment) o;

        return (s.a.equals(a) && s.b.equals(b)) ||
                (s.a.equals(b) && s.b.equals(a));
    }
}
