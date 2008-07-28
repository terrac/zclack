package main;

import guess.ClickMatch;
import guess.ImgMap;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
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

import clicks.DoubleClick;
import clicks.Drag;
import clicks.Left;
import clicks.None;
import clicks.Repeat;
import clicks.Right;
import clicks.iclick;

public class roundclick {

	public static final int interval = 70;

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
		long last = Calendar.getInstance().getTimeInMillis();

		final Buttons bu = new InvisibleButtons();
		bu.setupButtons(buttonMap);
		bu.setVisible(true);

		List<Integer> xlist = new ArrayList<Integer>();
		List<Integer> ylist = new ArrayList<Integer>();

		try {
			final Robot a = new Robot();

			int delay = 0;
			while (true) {

				final Point b = MouseInfo.getPointerInfo().getLocation();

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();
				ylist.add(y1);
				xlist.add(x1);

				a.delay(50);
				int size = ylist.size();
				if (size > 5) {
					int xf = xlist.get(size);
					int yf = ylist.get(size);

					if ((within(x1, xf) && within(y1, yf))) {

						int y = 0;
						int x = 0;
						for (int i = 0; i < size; i++) {
							y += ylist.get(i);
							x += xlist.get(i);
						}
						y = y / size;
						x = x / xlist.size();
						
						

					}
					ylist.remove(0);
					xlist.remove(0);
				}

			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean within(int x1, int xf) {
		return xf + 5 > x1 && xf - 5 < x1;
	}

}
