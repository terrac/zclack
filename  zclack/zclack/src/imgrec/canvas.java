package imgrec;

import java.awt.Frame;

public class canvas extends Frame {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public canvas() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Frame");

	}

}
