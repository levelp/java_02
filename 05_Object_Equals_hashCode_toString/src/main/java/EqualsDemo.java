/**
 * Сравнение объектов
 */
public class EqualsDemo {

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        System.out.println("p1==p2 = " + (p1 == p2));
        System.out.println("p1.equals(p2) = " + p1.equals(p2));
    }

    static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!o.getClass().equals(Point.class))
                return false;
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
    }
}
