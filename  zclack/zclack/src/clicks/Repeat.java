package clicks;

import java.awt.event.InputEvent;

import main.Buttons;

public class Repeat implements iclick {
	/* (non-Javadoc)
	 * @see iclick#execute(java.awt.Robot)
	 */
	public void execute(java.awt.Robot a, Buttons b) {
		a.mousePress(InputEvent.BUTTON1_MASK);
		a.delay(50);
			a.mouseRelease(InputEvent.BUTTON1_MASK);
//			click.hasClicked = false;
//			click.count = 0;
	}
}
