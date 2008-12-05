package main;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
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
		setLocation(screenSize.width - 100, screenSize.height - 130);
		setAlwaysOnTop(true);
		getCurrent().addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (getCurrent().getText().equals("Stop"))
					getCurrent().setText("Return");
				
				 Transferable tr =Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
				 try {
					String a =(String) tr.getTransferData(DataFlavor.stringFlavor);
					a = a.replace('"', ' ');
					 String string = "C:\\Program Files\\eSpeak\\command_line\\espeak.exe -a 10 -v en\\en-r+f3 \""+a+"\"";
					 //System.out.println(string);
					Runtime.getRuntime().exec( string );
				} catch (UnsupportedFlavorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


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
