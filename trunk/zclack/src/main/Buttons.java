package main;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * This is the default constructor
	 */
	public Buttons() {
		super();
		initialize();
	}
	public iclick ic = new Left();
	List<JComponent> list = new ArrayList<JComponent>();
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(100, 200);
		this.setTitle("Frame");
		
		
		list.add(getLeft());
		list.add(getRight());
		list.add(getDleft());
		list.add(getMiddle());
		list.add(getDragged());
		this.setUndecorated(true);
		for (JComponent a : list) {
			a.setOpaque(false);
			add(a, null);
		}
		add(getCurrent());
		add(getTime());
		JButton button = new JButton();
		add( button);
		button.setText("X");
		button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				System.exit(0);
			}
		});
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

} // @jve:decl-index=0:visual-constraint="80,9"

