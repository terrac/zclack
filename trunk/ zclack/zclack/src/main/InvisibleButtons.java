package main;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import click.click;
import clicks.iclick;

public class InvisibleButtons extends Buttons {

	void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(100, 30);
		this.setTitle("ZClack");

		add(getCurrent());
		// add(getTime());

		add(getGuess());

		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		setLocation(screenSize.width - 100, screenSize.height - 30);
		setAlwaysOnTop(true);
		getCurrent().addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (getCurrent().getText().equals("Stop"))
					getCurrent().setText("Return");
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	boolean shouldProcess = false;

	MouseGesturesRecognizer mgl;

	@Override
	public void runButtons(int count) {
		// TODO Auto-generated method stub
		if (count > click.interval / 2) {
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
