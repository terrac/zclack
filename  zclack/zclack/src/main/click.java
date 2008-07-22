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

public class click {

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
		new click().stuff();
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


		try {
			final Robot a = new Robot();

			int delay = 0;
			while (true) {
				final Point b = MouseInfo.getPointerInfo().getLocation();

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();
				// System.out.println(b.equals(c)+" "+hasClicked);
				if (b.equals(c)) {
					if (!hasClicked && count < 70) {
						long timepassed = last
								- Calendar.getInstance().getTimeInMillis();
						last = Calendar.getInstance().getTimeInMillis();
						count -= timepassed / 10;
					}

				} else {
					count = 0;
					hasClicked = false;
					has50Checked = false;
				}
				if (y1 > 875 && x1 > 1375) {
					a.delay(10000);
				}
				bu.runButtons(count);
				a.delay(10);
				int w = 100;
				bu.getTime().setText("" + count);
				BufferedImage y = a.createScreenCapture(new Rectangle(b.x, b.y,
						w, w));
				if (count > 50 && !has50Checked) {
					has50Checked = true;

					iclick clickifmatch = clickMatch.clickifmatch(y);
					if (clickifmatch != null)
						clickifmatch.execute(a, bu, this);

				}
				if (count > 70 && !hasClicked) {

					// bu.setLocation(b.x, b.y);

					hasClicked = true;
					bu.ic.execute(a, bu, this);
					int hi = Integer.MIN_VALUE;
					int lo = Integer.MAX_VALUE;
					long coltot = 0;
					int colsize = 0;
					for (int i = 0; i < y.getHeight(); i++) {
						for (int j = 0; j < y.getWidth(); j++) {
							int color = y.getRGB(i, j);
							if (hi < color)
								hi = color;
							if (lo > color)
								lo = color;
							coltot += color;
							colsize++;
						}
					}

					int slice = hi - lo;
					int avgcolor = (int) ((long) coltot / colsize);
					clickMatch.put(slice, avgcolor, bu.ic, y);
					System.out.println(slice + " " + avgcolor);
				}
				end(b);

			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void end(final Point b) {
		c = b;
	}

}