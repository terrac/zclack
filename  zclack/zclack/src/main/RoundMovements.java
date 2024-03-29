package main;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import click.click;
import clicks.iclick;

public class RoundMovements extends Buttons {
	
	
	
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
