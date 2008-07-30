package clicks;

import java.awt.event.InputEvent;

import main.Buttons;

public class None implements iclick {
	/* (non-Javadoc)
	 * @see iclick#execute(java.awt.Robot)
	 */
	public void execute(java.awt.Robot a, Buttons b) {
		a.delay(5000);
		Left.setDefault(b);
	}
}
