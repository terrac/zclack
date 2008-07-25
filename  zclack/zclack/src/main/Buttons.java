package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import clicks.Left;
import clicks.iclick;

public class Buttons extends Frame {

	private static final long serialVersionUID = 1L;

	private JButton left = null;

	private JButton right = null;

	private JButton dleft = null;

	private JButton middle = null;

	private JButton dragged = null;

	private JLabel current = null;//

	private JLabel time = null;//
	private static JLabel guess = null;//

	/**
	 * This is the default constructor
	 */
	public Buttons() {
		super();
		this.setUndecorated(true);
		initialize();
		addexit();
	}

	public iclick ic = new Left();

	private List<JComponent> list = new ArrayList<JComponent>();

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	 void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(100, 200);
		this.setTitle("Frame");

		list.add(getLeft());
		list.add(getRight());
		list.add(getDleft());
		list.add(getMiddle());
		list.add(getDragged());
		
		for (JComponent a : list) {
			a.setOpaque(false);
			add(a, null);
		}
		add(getCurrent());
		add(getTime());
		
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		setLocation(screenSize.width - 120, screenSize.height - 300);
		setAlwaysOnTop(true);

	}

	protected void addexit() {
		JButton button = new JButton();
		add(button);
		button.setText("X");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				System.exit(0);
			}
		});
	}

	public void setupButtons(final Map<String, iclick> buttonMap) {
		final MouseListener ml = new java.awt.event.MouseAdapter() {

			public void mouseEntered(java.awt.event.MouseEvent e) {
				JButton f = (JButton) e.getSource();
				String c = f.getText();
				setCurrent(buttonMap, c);
			}
		};

		addlistener(ml);
		show();
	}

	public void runButtons(int count) {
	}

	public void addlistener(MouseListener b) {
		for (JComponent a : list) {

			a.addMouseListener(b);
		}
	}

	/**
	 * This method initializes left
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getLeft() {
		if (left == null) {
			left = new JButton();
			left.setText("Left");
		}
		return left;
	}

	/**
	 * This method initializes right
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRight() {
		if (right == null) {
			right = new JButton();
			right.setText("Right");
		}
		return right;
	}

	/**
	 * This method initializes dleft
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDleft() {
		if (dleft == null) {
			dleft = new JButton();
			dleft.setText("Double");
		}
		return dleft;
	}

	/**
	 * This method initializes middle
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getMiddle() {
		if (middle == null) {
			middle = new JButton();
			middle.setText("Repeat");

		}
		return middle;
	}

	/**
	 * This method initializes dragged
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDragged() {
		if (dragged == null) {
			dragged = new JButton();
			dragged.setText("Drag");
		}
		return dragged;
	}

	public JLabel getCurrent() {
		if (current == null) {
			current = new JLabel();
			current.setText("Current");
		}
		return current;
	}

	public JLabel getTime() {
		if (time == null) {
			time = new JLabel();
			time.setText("Time");
		}
		return time;
	}
	
	public static JLabel getGuess() {
		if (guess == null) {
			guess = new JLabel();
			guess.setText("guess");
		}
		return guess;
	}
	public void setGuess(String a){
		guess.setText(a);
	}
	protected void setCurrent(final Map<String, iclick> buttonMap, String c) {

		// posMap.put(key, buttonMap.get(c));
		ic = buttonMap.get(c);
		// a.mouseMove(b.x, b.y);
		// buttonMap.get(c).execute(a);
		// bu.setVisible(false);
		// bu.removeMouseListener(this);
		getCurrent().setText(c);
	}

} // @jve:decl-index=0:visual-constraint="80,9"

