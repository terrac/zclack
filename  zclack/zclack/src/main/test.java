package main;

import java.awt.event.MouseEvent;


public class test {
	public static void main(String[] args) {
		MouseGesturesRecognizer a = new MouseGesturesRecognizer(new MouseGesturesListener(){

			public void gestureMovementRecognized(String currentGesture) {
				// TODO Auto-generated method stub
				System.out.println(currentGesture);
			}

			public void processGesture(String gesture) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		while (true) {
			a.processMouseEvent();
		}
	}
}
