package main;

public class Point extends java.awt.Point {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x + " " + y;
	}
	public static Point convert(java.awt.Point a){
		Point b = new Point();
		b.x = a.x;
		b.y = a.y;
		return b;
	}
}
