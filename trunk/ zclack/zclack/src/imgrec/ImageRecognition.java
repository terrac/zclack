package imgrec;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class ImageRecognition {

	public static void main(String[] args) {
		BufferedImage totalPicture = null;
		try {

			Robot robot = new Robot();

			if (totalPicture == null) {
				Dimension a = Toolkit.getDefaultToolkit().getScreenSize();

				BufferedImage bImg = robot.createScreenCapture(new Rectangle(
						a.width, a.height));
				obj[][] objMap = new obj[a.width][a.height];
				obj o = new obj();
				Tree<obj> t = new Tree<obj>(o);
				o.setColor(bImg.getRGB(0, 0));
				for (int i = 1; i < bImg.getWidth(); i += 1) {
					for (int j = 1; j < bImg.getHeight(); j += 1) {

						if (objMap[i][j] != null) {
							continue;
						}

						objMap[i][j] = o;
						if (withinrgb(bImg.getRGB(i, j), o.getRGB())) {

						} else {

							subcut(bImg, objMap, new obj(bImg.getRGB(i, j)), t,
									i, j);
						}

					}
				}

				totalPicture = new BufferedImage(a.width, a.height,
						BufferedImage.TYPE_INT_RGB);
				int x = 0, y = 0;
				for (obj[] objs : objMap) {
					for (obj obj : objs) {
						totalPicture.setRGB(x, y, obj.getRGB());
						x++;
					}
					y++;
					x = 0;
				}
				Canvas canv = new Canvas();
				canv.getGraphics().drawImage(totalPicture, 0, 0, null);
			}
			BufferedImage mousePicture = robot
					.createScreenCapture(new Rectangle(200, 200));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean withinrgb(int rgbx, int rgby) {
		Color x = new Color(rgbx);
		Color y = new Color(rgby);
		return within(x.getBlue(), y.getBlue())
				&& within(x.getRed(), y.getRed())
				&& within(x.getGreen(), y.getGreen());
	}

	public static void subcut(BufferedImage bImg, obj[][] objMap, obj o,
			Tree t, int i, int j) {
		for (int x = i; x < bImg.getWidth(); x--) {
			if (withinrgb(bImg.getRGB(x, j), o.getRGB())) {
				objMap[j][x] = o;
			}

		}
		for (int x = i; x < bImg.getWidth(); x++) {
			for (int y = j; y < bImg.getHeight(); y++) {
				if (withinrgb(bImg.getRGB(x, y), o.getRGB())) {
					objMap[y][x] = o;
				}
			}
		}
		t.addBranch(new Tree(o));
	}

	private static boolean within(int x1, int xf) {

		return Math.abs(xf - x1) < 10;
	}
}
