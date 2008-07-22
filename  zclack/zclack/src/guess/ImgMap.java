package guess;

import java.util.HashMap;
import java.util.Map;

import clicks.iclick;

public class ImgMap {

	Map<Integer, Map<Integer, iclick>> imap = new HashMap<Integer, Map<Integer, iclick>>();

	public void put(int slice, int avgcolor, iclick click) {
		slice = slice / 10;
		avgcolor = avgcolor / 100000;
		if (!imap.containsKey(slice)) {
			imap.put(slice, new HashMap<Integer, iclick>());
		}
		imap.get(slice).put(avgcolor, click);

	}
	public iclick get(int slice, int avgcolor){
		slice = slice / 10;
		avgcolor = avgcolor / 100000;
		Map<Integer, iclick> map = imap.get(slice);
		if(map == null)
			return null;
		return map.get(avgcolor);
	}
}
