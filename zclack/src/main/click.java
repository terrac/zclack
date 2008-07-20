package main;

import guess.ImgPattern;

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
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;

import clicks.Double;
import clicks.Drag;
import clicks.Left;
import clicks.Repeat;
import clicks.Right;
import clicks.iclick;

public class click {
	Map<String, iclick> buttonMap = new HashMap<String, iclick>();
	{
		buttonMap.put(Left.class.getSimpleName(), new Left());
		buttonMap.put(Double.class.getSimpleName(), new Double());
		buttonMap.put(Right.class.getSimpleName(), new Right());
		buttonMap.put(Repeat.class.getSimpleName(), new Repeat());
		buttonMap.put(Drag.class.getSimpleName(), new Drag());
	}

	public static void main(String[] args) {
		new click().stuff();
	}
//
	Point c = null;
	public	int count = 0;
	public boolean hasClicked = false;
	public boolean has50Checked = false;
	protected void stuff() {
		long last = Calendar.getInstance().getTimeInMillis();
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final Buttons bu = new Buttons();
		bu.setLocation(screenSize.width-120,screenSize.height - 300);
		bu.setAlwaysOnTop(true);
		bu.setVisible(true);

		final MouseListener ml = new java.awt.event.MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent e) {
				JButton f = (JButton) e.getSource();
				String c = f.getText();
				System.out.println(c);
				// posMap.put(key, buttonMap.get(c));
				bu.ic = buttonMap.get(c);
				// a.mouseMove(b.x, b.y);
				// buttonMap.get(c).execute(a);
				// bu.setVisible(false);
				// bu.removeMouseListener(this);
				bu.getCurrent().setText(c);
			}
		};

		bu.addlistener(ml);
		bu.show();
		try {
			final Robot a = new Robot();

			
			
			int delay = 0;
			while (true) {
				final Point b = MouseInfo.getPointerInfo().getLocation();

				int x1 = (int) b.getX();
				int y1 = (int) b.getY();
				//System.out.println(b.equals(c)+" "+hasClicked);
				if (b.equals(c)) {
					if (!hasClicked && count < 71){
						long timepassed =Calendar.getInstance().getTimeInMillis()-last ;
						last = Calendar.getInstance().getTimeInMillis();
						count += timepassed / 10;
					}
						
				} else {
					last = Calendar.getInstance().getTimeInMillis();
					count = 0;
					hasClicked = false;
					has50Checked = false;
				}
				if (y1 > 875 && x1 > 1375) {
					a.delay(10000);
				}
				a.delay(10);
				int w = 100;
				bu.getTime().setText("" + count);
				
				if (count > 70 && !hasClicked) {
		

					// bu.setLocation(b.x, b.y);

					hasClicked = true;
					bu.ic.execute(a, bu, this);
				}
				end(b);

			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void clickifmatch(final Buttons bu, final Robot a, BufferedImage y) {

		int hi = Integer.MIN_VALUE;
		int lo = Integer.MAX_VALUE;
		Set z = new HashSet(); 
		for (int i = 0; i < y.getHeight(); i++) {
			for (int j = 0; j < y.getWidth(); j++) {
				int first = y.getRGB(i, j);
				if( hi < first)
					hi = first;
				if ( lo > first)
					lo = first;
				z.add(first);
				
//				ImgPattern imgPattern = posMap.get(first);
//				if(imgPattern != null&&imgPattern.execute(y)){
//					imgPattern.toClick.execute(a, bu, this);
//					System.out.println(posMap);
//					
//					return;
//					
//				}
			}
		}
		System.out.println(z);
		System.out.println(z.size());
		
	}

	protected void end(final Point b) {
		c = b;
	}

	Map<Integer,ImgPattern> posMap = new HashMap<Integer, ImgPattern>();

}
