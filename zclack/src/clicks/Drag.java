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
	boolean drag = true;

	public void execute(java.awt.Robot a, Buttons b, click click) {
		if (drag)
			a.mousePress(InputEvent.BUTTON1_MASK);
		else
			a.mouseRelease(InputEvent.BUTTON1_MASK);
		drag = !drag;
	}
}
