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
			
			if(totalPicture == null){
				Dimension a =Toolkit.getDefaultToolkit().getScreenSize();
				
				BufferedImage bImg = robot.createScreenCapture(new Rectangle(a.width,a.height));
				totalPicture = new BufferedImage(bImg.getWidth()/10,bImg.getHeight()/10,BufferedImage.TYPE_INT_ARGB);
				
//				for (int i = 0; i < bImg.getWidth(); i +=10) {
//					for (int j = 0; j < bImg.getHeight(); j +=10) {
//						Set<Integer> set = new HashSet<Integer>();
//						
//						for (int k = 0; k < 10; k++) {
//							for (int index = 0; index < 10; index++) {
//								int l = j+index;
//								int l2 = i+k;
//								if(l2 > bImg.getWidth()||l > bImg.getHeight()){
//									continue;
//								}
//								//should average by r g b seperately probably
//								avg = bImg.getRGB(l2, l);
//								Color x,y;
//								if(within(x.getBlue(), y.getBlue())
//										&&within(x.getRed(), y.getRed())
//										&&within(x.getGreen(), y.getGreen())){
//										
//									
//								}
//								
//								
//							}
//						}
//						totalPicture.setRGB(i, j, avg);
//					}
//				}
				
//				Canvas canv = new Canvas();
//				canv.getGraphics().drawImage(totalPicture,0,0,null);
			}
			BufferedImage mousePicture =robot.createScreenCapture(new Rectangle(200,200));
			
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static boolean within(int x1, int xf) {
		
		return Math.abs(xf - x1)<10;
	}
}
