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

public class pointclick {

	private static final int tsize = 1;

	ClickMatch clickMatch = new ClickMatch();

	Map<String, iclick> buttonMap = new HashMap<String, iclick>();
	{
		buttonMap.put(Left.class.getSimpleName(), new Left());
		buttonMap.put(None.class.getSimpleName(), new None());
		buttonMap.put(DoubleClick.class.getSimpleName(), new DoubleClick());
		buttonMap.put(Right.class.getSimpleName(), new Right());
		buttonMap.put(Repeat.class.getSimpleName(), new Repeat());
		buttonMap.put(Drag.class.getSimpleName(), new Drag());
	}

	public static void main(String[] args) {
		new pointclick().stuff();
	}

	//
	Point c = null;

	public int count = 0;

	public boolean hasClicked = false;

	public boolean has50Checked = false;

	protected void stuff() {

		final Buttons bu = new InvisibleButtons();
		bu.setupButtons(buttonMap);
		bu.setVisible(true);

		List<Double> list = new ArrayList();
		List<Point> plist = new ArrayList();

		int clicount = 0;
		try {
			final Robot a = new Robot();

			Point last = Point
					.convert(MouseInfo.getPointerInfo().getLocation());
			int tcount = 0;
			while (true) {

				final Point b = Point.convert(MouseInfo.getPointerInfo()
						.getLocation());
				a.delay(10);

				if (b.equals(last)) {
					tcount++;
					if (tcount > 100) {
						System.out.println(tcount);
						tcount = 0;
						plist.clear();
						plist.add(b);
					}
					count++;
					if (count > 50&&plist.size() > 0) {

						Point point = plist.get(0);

						if (within(point.x, b.x, 20)
								&& within(point.y, b.y, 20)) {

							System.out.println(" " + plist.size());
							plist.clear();

						}
						count = 0;
						plist.add(b);
						
					}
					

				} else {
					tcount = 0;
				}
				int i = (b.x - last.x);
				if (i == 0)
					list.add(10.0);
				else {
					double e = (double) (b.y - last.y) / i;

					list.add(e);
				}
				if (Calendar.getInstance().getTime().getMinutes() == 50) {
					System.exit(0);
				}

				last = b;
			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean within(int x1, int xf, int w) {

		int abs = Math.abs(xf - x1);
		// System.out.print("a" + abs);
		return abs < w;
	}

}
