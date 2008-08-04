package click;

import guess.ClickMatch;
import guess.ImgMap;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;

import main.Buttons;
import main.InvisibleButtons;
import main.Point;

import clicks.DoubleClick;
import clicks.Drag;
import clicks.Left;
import clicks.None;
import clicks.Repeat;
import clicks.Right;
import clicks.iclick;

public class speedyclick {

	private static final int tsize = 50;

	ClickMatch clickMatch = new ClickMatch();
	None none = new None();
	Map<String, iclick> buttonMap = new HashMap<String, iclick>();
	{
		buttonMap.put(Left.class.getSimpleName(), new Left());

		buttonMap.put(None.class.getSimpleName(), none);
		buttonMap.put(DoubleClick.class.getSimpleName(), new DoubleClick());
		buttonMap.put(Right.class.getSimpleName(), new Right());
		buttonMap.put(Repeat.class.getSimpleName(), new Repeat());
		buttonMap.put(Drag.class.getSimpleName(), new Drag());
	}

	public static void main(String[] args) {
		new speedyclick().stuff();
	}

	//
	Point c = null;

	public int count = 0;

	public boolean hasClicked = false;

	protected void stuff() {

		final Buttons bu = new InvisibleButtons();
		bu.setupButtons(buttonMap);
		bu.setVisible(true);
		bu.setAlwaysOnTop(false);
		List<Point> list = new ArrayList();
		List<String> plist = new ArrayList();
		int clicount = 0;
		try {
			final Robot a = new Robot();

			Point last = Point
					.convert(MouseInfo.getPointerInfo().getLocation());
			Point lastClick = Point.convert(MouseInfo.getPointerInfo()
					.getLocation());
			int hcount = 0;
			while (true) {

				final Point b = Point.convert(MouseInfo.getPointerInfo()
						.getLocation());

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();

				if (!b.equals(last)) {

					list.add(b);
					hasClicked = false;
					count = 0;
				} else {
					count++;
				}

				a.delay(10);

				if (count > 100) {
					list.clear();
					count = 0;

					if (!none.isNone && !hasClicked) {
						if (!within(lastClick, b, 10)) {
							buttonMap.get("Left").execute(a, null);
						}
						hasClicked = true;
						lastClick = b;
					}
					// System.out.println();
				}
				int size = list.size();
				// System.out.println(size);
				if (size >= tsize) {

					int xf = list.get(0).x;
					int yf = list.get(0).y;
					int d = within(x1, xf);
					int e = within(y1, yf);
					if (d < 30 && e < 30 && within(b, list.get(tsize - 1), 30)) {
						System.out.println("within");
						buttonMap.get("Right").execute(a, null);
					}

					list.remove(0);
				}

				last = b;
			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int within(int x1, int xf) {

		return Math.abs(xf - x1);
	}

	private boolean within(Point a, Point b, int c) {

		return Math.abs(a.x - b.x) < c && Math.abs(a.y - b.y) < c;
	}
}
