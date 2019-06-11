package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class JoystickDisplayer extends Pane {
	
	Circle bigCircle;
	Circle smallCircle;
	
	int sLayoutX, sLayoutY, bLayoutX, bLayoutY;
	
	public JoystickDisplayer() {
		sLayoutX=0;
		sLayoutY=0;
		bLayoutX=0;
		bLayoutY=0;
		bigCircle.setFill(Paint.valueOf("black"));
		smallCircle.setFill(Paint.valueOf("blue"));
	}

	public int getsLayoutX() {
		return sLayoutX;
	}

	public int getsLayoutY() {
		return sLayoutY;
	}

	public int getbLayoutX() {
		return bLayoutX;
	}

	public int getbLayoutY() {
		return bLayoutY;
	}
	
	

}
