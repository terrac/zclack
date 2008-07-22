package clicks;

import java.awt.event.InputEvent;

import main.Buttons;
import main.click;

public class Drag implements iclick {
	/*
	 * (non-Javadoc)
	 * 
	 * @see iclick#execute(java.awt.Robot)
	 */
	

	public void execute(java.awt.Robot a, Buttons b, click click) {

			a.mousePress(InputEvent.BUTTON1_MASK);
			a.delay(4000);
			a.mouseRelease(InputEvent.BUTTON1_MASK);
			Left.setDefault(b);
	}
}
