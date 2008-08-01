package clicks;

import java.awt.event.InputEvent;

import main.Buttons;

public class Drag implements iclick {
	/*
	 * (non-Javadoc)
	 * 
	 * @see iclick#execute(java.awt.Robot)
	 */

	boolean isDrag = false;;

	public void execute(java.awt.Robot a, Buttons b) {
		isDrag = !isDrag;
		if (isDrag) {
			a.mousePress(InputEvent.BUTTON1_MASK);
		} else {
			a.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		
	}
}
