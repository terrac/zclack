package main;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;

import clicks.iclick;

public class InvisibleButtons extends Buttons {

	void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(60, 30);
		this.setTitle("ZClack");

		add(getCurrent());
		add(getTime());

		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		setLocation(screenSize.width - 60, screenSize.height - 30);
		setAlwaysOnTop(true);
		mgl = new MouseGesturesRecognizer(new MouseGesturesListener() {

			public void gestureMovementRecognized(String currentGesture) {
				
				if (currentGesture.startsWith("DL"))
				setCurrent(buttonMap, "Left");
			if (currentGesture.startsWith("RD"))
				setCurrent(buttonMap, "Right");
			
				System.out.println(currentGesture);
			}

			public void processGesture(String gesture) {
				// TODO Auto-generated method stub

			}

		});
	}

	MouseGesturesRecognizer mgl;

	@Override
	public void runButtons(int count) {
		// TODO Auto-generated method stub
		if(count > 50){
			mgl.clearTemporaryInfo();
		}
		
		mgl.processMouseEvent();
	}

	@Override
	public void setupButtons(Map<String, iclick> buttonMap) {
		// TODO Auto-generated method stub
		this.buttonMap = buttonMap;
	}

	Map<String, iclick> buttonMap;

}
