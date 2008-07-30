package clicks;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.InputEvent;

import main.Buttons;

public class Right implements iclick {
	/*
	 * (non-Javadoc)
	 * 
	 * @see iclick#execute(java.awt.Robot)
	 */
	public void execute(java.awt.Robot a, Buttons b) {
		a.mousePress(InputEvent.BUTTON3_MASK);
		a.delay(50);
		a.mouseRelease(InputEvent.BUTTON3_MASK);
		Left.setDefault(b);
	}
}
