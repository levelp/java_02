/**
 * Точка
 */
class Point {
    /**
     * Координаты точки x, y
     */
    private double x, y;

    /**
     * Конструктор
     *
     * @param x x-координата точки
     * @param y y-координата точки
     */
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
