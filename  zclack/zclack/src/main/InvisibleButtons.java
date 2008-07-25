package main;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import clicks.iclick;

public class InvisibleButtons extends Buttons {
	
	
	
	void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(100, 30);
		this.setTitle("ZClack");

		add(getCurrent());
		//add(getTime());
		
		add(getGuess());
		
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		setLocation(screenSize.width - 100, screenSize.height - 30);
		setAlwaysOnTop(true);
		mgl = new MouseGesturesRecognizer(new MouseGesturesListener() {

			public void gestureMovementRecognized(String currentGesture) {
				shouldProcess = currentGesture.length() < 2;
				if (currentGesture.startsWith("DL"))
					setCurrent(buttonMap, "Left");
//				if (currentGesture.startsWith("DR"))
//					setCurrent(buttonMap, "Drag");
				if (currentGesture.startsWith("UL"))
					setCurrent(buttonMap, "DoubleClick");
//				if (currentGesture.startsWith("UR"))
//					setCurrent(buttonMap, "None");
				if (currentGesture.startsWith("RD"))
					setCurrent(buttonMap, "Right");

				//System.out.println(currentGesture);
			}

			public void processGesture(String gesture) {
				// TODO Auto-generated method stub

			}

		});
	}

	boolean shouldProcess = false;

	MouseGesturesRecognizer mgl;

	@Override
	public void runButtons(int count) {
		// TODO Auto-generated method stub
		if (count > click.interval/2) {
			mgl.clearTemporaryInfo();
			shouldProcess = true;
		}
		if (shouldProcess)
			mgl.processMouseEvent();
	}

	@Override
	public void setupButtons(Map<String, iclick> buttonMap) {
		// TODO Auto-generated method stub
		this.buttonMap = buttonMap;
	}

	Map<String, iclick> buttonMap;

}
