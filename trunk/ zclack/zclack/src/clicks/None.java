package clicks;

import java.awt.event.InputEvent;

import main.Buttons;

public class None implements iclick {
	/* (non-Javadoc)
	 * @see iclick#execute(java.awt.Robot)
	 */
	public void execute(java.awt.Robot a, Buttons b) {
		isNone = !isNone;
		
	}
	
	public boolean isNone = false;
}
