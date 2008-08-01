package click;

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

import main.Buttons;
import main.InvisibleButtons;

import clicks.DoubleClick;
import clicks.Drag;
import clicks.Left;
import clicks.None;
import clicks.Repeat;
import clicks.Right;
import clicks.iclick;

public class click {

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
				long timepassed = last
						- Calendar.getInstance().getTimeInMillis();
				last = Calendar.getInstance().getTimeInMillis();
				long l = -timepassed / 10;

				final Point b = MouseInfo.getPointerInfo().getLocation();
				
				

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();
				if (b.equals(c)) {
					if (!hasClicked && count < interval) {
						if (l < interval / 2)
							count += l;
						else
							System.out.println(l);
					}

				} else {
					count = 0;
					hasClicked = false;
					has50Checked = false;
				}
				
				bu.runButtons(count);
				a.delay(10);
				
				if (count > interval - 1 && !hasClicked) {

					// bu.setLocation(b.x, b.y);

					hasClicked = true;
					bu.ic.execute(a, bu);
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
