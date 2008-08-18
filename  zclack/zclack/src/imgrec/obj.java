package imgrec;

import java.awt.Color;

public class obj {
	public obj() {
		// TODO Auto-generated constructor stub
	}

	public obj(int rgb) {
		setColor(rgb);
	}

	int height;

	int weight;

	shape shape;

	Color color;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public shape getShape() {
		return shape;
	}

	public void setShape(shape shape) {
		this.shape = shape;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRGB() {
		return color.getRGB();
	}

	public void setColor(int rgb) {
		this.color = new Color(rgb);
	}

}
