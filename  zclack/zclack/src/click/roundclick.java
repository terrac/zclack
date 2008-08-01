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

	private static final int tsize = 50;

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
		new roundclick().stuff();
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

		List<Point> list = new ArrayList();
		List<String> plist = new ArrayList();
		int clicount = 0;
		try {
			final Robot a = new Robot();

			Point last = Point
					.convert(MouseInfo.getPointerInfo().getLocation());
			int hcount = 0;
			while (true) {

				final Point b = Point.convert(MouseInfo.getPointerInfo()
						.getLocation());

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();

				if (!b.equals(last)) {

					list.add(b);
				} else {
					count++;
				}

				a.delay(10);
				
				if (count > 30) {
					list.clear();
					count = 0;
					//System.out.println();
				}
				int size = list.size();
				//System.out.println(size);
				if (size >= tsize) {
					int xf = list.get(0).x;
					int yf = list.get(0).y;

					String pattern = "";
					int d = within(x1, xf) ;
					int e = within(y1, yf);
					//System.out.println(d+" "+e);
					//System.out.print(y1+" "+x1+"-");
					//System.out.println(yf+" "+xf);
					if (d<10&&e<10) {
						System.out.println("within");
						System.out.println(hcount);

						int y = 0;
						int x = 0;
						for (Point point : list) {
							y += point.y;
							x += point.x;
						}

						y = y / size;
						x = x / size;
						String slast = "";
						String total = "";
						for (Point point : list) {
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

						
//						System.out.println(list);
//						System.out.println(x + " " + y);
//						System.out.println(total);
						if (pattern.startsWith("URULDL")) {
							buttonMap.get("Left").execute(a, null);
							System.out.println("aoeu");
						}
						if (pattern.startsWith("ULDLDR")) {
							buttonMap.get("Right").execute(a, null);
						}
						//buttonMap.get("Right").execute(a, null);
						list.clear();
						plist.add(total);
						System.out.println(plist);
					}
					//list.remove(0);
				}

				last = b;
			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int within(int x1, int xf) {
		
		return Math.abs(xf-x1);
	}

}
