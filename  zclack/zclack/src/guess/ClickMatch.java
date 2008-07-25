package guess;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import main.Buttons;
import main.click;
import clicks.iclick;

public class ClickMatch {
	public  iclick clickifmatch(BufferedImage y) {

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
		iclick iclick = map.get(slice, avgcolor);
		if (iclick != null) {
			System.out.println(iclick);
			Buttons.getGuess().setText(iclick.getClass().getSimpleName());
			
			return iclick;
		}
		return null;
	}

	public int checkDiff() {
		int diff = 0;
		for ( BufferedImage a : optimizationMap.keySet()){
			iclick b =clickifmatch(a);
			if(!optimizationMap.get(a).equals(b)){
				diff++;
			}
		}
		return diff;
	}
	
	ImgMap map = new ImgMap();

	public void put(int slice, int avgcolor, iclick click, BufferedImage y) {
		map.put(slice, avgcolor, click);
		optimizationMap.put(y, click);
	}
	Map<BufferedImage,iclick> optimizationMap = new HashMap<BufferedImage, iclick>();
}
