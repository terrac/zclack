package guess;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import clicks.iclick;

public class ImgPattern {

	List<Integer> height = new ArrayList<Integer>();
	List<Integer> width = new ArrayList<Integer>();
	List<Integer> color = new ArrayList<Integer>();
	public boolean execute(BufferedImage a){
		for (int i = 0; i < color.size(); i++) {
			if(width.get(i) >a.getWidth()|| height.get(i)> a.getHeight()){
				return false;
			}
			if(a.getRGB(width.get(i), height.get(i)) != color.get(i)){
				return false;
			}
		}
		
		return true;
	}
	public iclick toClick; 
}
