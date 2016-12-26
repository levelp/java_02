import geometry.Point;

public class EqualsDemo {

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        System.out.println("p1==p2 = " + (p1 == p2));
        System.out.println("p1.equals(p2) = " + p1.equals(p2));
    }
}
