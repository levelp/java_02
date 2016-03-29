package geometry;

/// Object, toString. Сравнение объектов: equals, hashCode. Контракт между equals и hashCode
/// ----------------------------------------------------------------------------------------
/// o1.equals(o2) == true  =>  o1.hashCode() == o2.hashCode()
/// Точка
public class Point {
    private static final double DELTA = 1e-15;
    // Координаты
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        if (!(o instanceof Point))
            return false;

        Point point = (Point) o;
        return Math.abs(x - point.x) < DELTA &&
                Math.abs(y - point.y) < DELTA;
    }

    /// При вычислении hashCode должны участвовать те же поля что и в equals
    @Override
    public int hashCode() {
        // http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
        return Double.hashCode(x) + Double.hashCode(y);
        // Wrong: return 1;
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ')';
    }
}
