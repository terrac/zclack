package clicks;

import java.awt.event.InputEvent;

import main.Buttons;
import main.click;

public class DoubleClick implements iclick {
	/*
	 * (non-Javadoc)
	 * 
	 * @see iclick#execute(java.awt.Robot)
	 */
	public void execute(java.awt.Robot a, Buttons b, click click) {
		a.mousePress(InputEvent.BUTTON1_MASK);
		a.delay(50);
		a.mouseRelease(InputEvent.BUTTON1_MASK);
		a.mousePress(InputEvent.BUTTON1_MASK);
		a.delay(50);
		a.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
