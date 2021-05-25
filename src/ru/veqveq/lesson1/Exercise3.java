package ru.veqveq.lesson1;

import java.util.Arrays;
import java.util.List;

public class Exercise3 {
    public static void main(String[] args) {
        Point base = new Point(0, 0);
        Shape movedTriangle = new Triangle(base, new Point(0, 7), new Point(6, 0));

        List<Shape> shapes = Arrays.asList(
                new Square(base, 3),
                new Circle(base, 6),
                movedTriangle
        );
        shapes.forEach(s -> System.out.println(s.toString()));

        movedTriangle.move(new Point(10, 10));
        System.out.println(movedTriangle);
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("[%d: %d]", x, y);
    }
}

class Line {
    private Point point1;
    private Point point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public double getLength() {
        int dX = point2.getX() - point1.getX();
        int dY = point2.getY() - point1.getY();
        return Math.hypot(dX, dY);
    }

    public static double getAngle(Line l1, Line l2) {
        double scalar = (l2.getPoint2().getX() - l2.getPoint1().getX())
                * (l1.getPoint2().getX() - l1.getPoint1().getX())
                + (l2.getPoint2().getY() - l2.getPoint1().getY())
                * (l1.getPoint2().getY() - l1.getPoint1().getY());
        double cos = scalar / (l1.getLength() * l2.getLength());
        return Math.acos(cos);
    }
}

abstract class Shape {

    private Point basicVertex;

    public Shape(Point basicPoint) {
        this.basicVertex = basicPoint;
    }

    public void move(Point newPoint) {
        this.basicVertex = newPoint;
    }

    public Point getBasicVertex() {
        return basicVertex;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    @Override
    public String toString() {
        return String.format("Shape: %s%n" +
                        "Basic Vertex: %s%n" +
                        "S: %f%n" +
                        "P: %f%n",
                getClass().getSimpleName(),
                basicVertex.toString(),
                getArea(), getPerimeter());
    }
}

class Square extends Shape {
    private double edge;

    public Square(Point basicPoint, double edge) {
        super(basicPoint);
        this.edge = edge;
    }

    @Override
    public double getArea() {
        return edge * edge;
    }

    @Override
    public double getPerimeter() {
        return edge * 4;
    }

    public double getEdge() {
        return edge;
    }

    public void setEdge(double edge) {
        this.edge = edge;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(Point basicPoint, double radius) {
        super(basicPoint);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

class Triangle extends Shape {
    private Point vertex2;
    private Point vertex3;
    private List<Line> edges;

    public Triangle(Point basicPoint, Point vertex2, Point vertex3) {
        super(basicPoint);
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        generateEdges();
    }

    @Override
    public double getArea() {
        double base = edges.get(0).getLength();
        double height = edges.get(2).getLength() * Math.sin(Line.getAngle(edges.get(0), edges.get(2)));
        return base * height / 2;
    }

    @Override
    public double getPerimeter() {
        return edges.stream()
                .mapToDouble(Line::getLength)
                .sum();
    }

    @Override
    public void move(Point newPoint) {
        this.vertex2 = new Point(newPoint.getX() + vertex2.getX(), newPoint.getY() + vertex2.getY());
        this.vertex3 = new Point(newPoint.getX() + vertex3.getX(), newPoint.getY() + vertex3.getY());
        super.move(newPoint);
        generateEdges();
    }


    public Point getVertex2() {
        return vertex2;
    }

    public void setVertex2(Point vertex2) {
        this.vertex2 = vertex2;
        generateEdges();
    }

    public Point getVertex3() {
        return vertex3;
    }

    public void setVertex3(Point vertex3) {
        this.vertex3 = vertex3;
        generateEdges();
    }

    private void generateEdges() {
        this.edges = Arrays.asList(
                new Line(getBasicVertex(), vertex2),
                new Line(vertex2, vertex3),
                new Line(vertex3, getBasicVertex()));
    }
}