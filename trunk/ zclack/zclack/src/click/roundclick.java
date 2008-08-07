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

public class roundclick {

	private static final int interval = 80;

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
		new roundclick().stuff();
	}

	//
	Point c = null;

	public int count = 0;

	public boolean hasClicked = false;

	protected void stuff() {

		final Buttons bu = new InvisibleButtons();
		bu.setupButtons(buttonMap);
		bu.setVisible(true);
		// bu.setAlwaysOnTop(false);
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
			long tlast = Calendar.getInstance().getTimeInMillis();
			while (true) {
				long timepassed = tlast
						- Calendar.getInstance().getTimeInMillis();
				tlast = Calendar.getInstance().getTimeInMillis();
				long l = -timepassed / 10;

				int minutes = Calendar.getInstance().getTime().getMinutes();
				// if (within(minutes, 55) < 5) {
				// bu.getCurrent().setText("Stop");
				//
				// continue;
				// }
				final Point b = Point.convert(MouseInfo.getPointerInfo()
						.getLocation());

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();

				if (!b.equals(last)) {
					
					list.add(b);
					hasClicked = false;
					count = 0;

				} else {
					if (count < interval) {
						if (l < interval / 2)
							count += l;
						else
							System.out.println(l);
					}
				}
				//System.out.println(count);
				a.delay(10);

				if (count > interval-1) {
					count = 0;

					if (!none.isNone && !hasClicked) {
						if (!within(lastClick, b, 2)) {
							buttonMap.get("Left").execute(a, null);
						}
						hasClicked = true;
						lastClick = b;
					}
					// System.out.println();
				}

				// System.out.println(size);
				if (count == 10) {
					list.clear();
				}
				int size = list.size();
				if (size >= tsize && count > 5) {
					int xf = list.get(0).x;
					int yf = list.get(0).y;

					String pattern = "";
					int d = within(x1, xf);
					int e = within(y1, yf);
					// System.out.println(d+" "+e);
					// System.out.print(y1+" "+x1+"-");
					// System.out.println(yf+" "+xf);
					if (d < 20 && e < 20) {
						System.out.println("within");
						System.out.println(hcount);

						int y = 0;
						int x = 0;
						for (Point point : list) {
							y += point.y;
							x += point.x;
						}

						int highestx = 0;
						int lowestx = Integer.MAX_VALUE;

						int highesty = 0;
						int lowesty = Integer.MAX_VALUE;
						y = y / size;
						x = x / size;
						String slast = "";
						String total = "";
						for (Point point : list) {
							if (highestx < point.x) {
								highestx = point.x;
							}
							if (lowestx > point.x) {
								lowestx = point.x;
							}
							if (highesty < point.y) {
								highesty = point.y;
							}
							if (lowesty > point.y) {
								lowesty = point.y;
							}
							pattern = "";
							if (point.y > y) {
								pattern += "D";
							} else if (point.y < y) {
								pattern += "U";
							} else {
								pattern += "M";
							}
							if (point.x > x) {
								pattern += "L";
							} else if (point.x < x) {
								pattern += "R";
							} else {
								pattern += "M";
							}
							if (!slast.equals(pattern)) {
								total += pattern;
							}

							slast = pattern;
						}

						int i = highestx - lowestx;
						int ylen = highesty - lowesty;
						// System.out.println(list);
						System.out.println(i);
						// System.out.println(x + " " + y);
						System.out.println(total);
						// if (pattern.startsWith("URULDL")) {
						// buttonMap.get("Left").execute(a, null);
						// System.out.println("aoeu");
						// }
						// if (pattern.startsWith("ULDLDR")) {
						// buttonMap.get("Right").execute(a, null);
						// }
						iclick iclick = null;
						;

						if (i > 20) {
							if (list.get(5).x < list.get(0).x) {
								System.out.println("double");
								iclick = buttonMap.get("DoubleClick");

							} else {
								iclick = buttonMap.get("Right");
							}
						} else {
							if (list.get(5).y < list.get(0).y) {
								System.out.println("none");
								// buttonMap.get("None").execute(a, null);
							} else {
								iclick = buttonMap.get("Drag");
								System.out.println("drag");
							}
						}
						// a.delay(200);
						a.mouseMove(list.get(0).x, list.get(0).y);
						if (iclick != null && ylen > 20)
							iclick.execute(a, null);

						hasClicked = true;
						list.clear();

					}
					// list.remove(0);
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
