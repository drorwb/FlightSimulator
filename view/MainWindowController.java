package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class MainWindowController implements Initializable{
	
	@FXML
	TextArea txtArea;
	@FXML
	RadioButton autopilotButton;
	@FXML
	RadioButton manualButton;
	@FXML
	ScrollBar rudderBar;
	@FXML
	ScrollBar throtlleBar;
	@FXML
	Circle bigCircle;
	@FXML
	Circle smallCircle;
	

	private double maxBorder;
//	private double radius=0;
//	private double centerX=0;
//	private double centerY=0;
//	
//	private double initializedCenterX = 0;
//	private double initializedCenterY = 0;
	
	ToggleGroup group= new ToggleGroup();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rudderBar.setMin(-1);
		rudderBar.setMax(1);
		throtlleBar.setMin(0);
		throtlleBar.setMax(1);
		autopilotButton.setToggleGroup(group);
		manualButton.setToggleGroup(group);
		manualButton.setSelected(true);
		maxBorder = bigCircle.getRadius();
		
		
//		initializedCenterX = smallCircle.getLayoutX();
//		initializedCenterY = smallCircle.getLayoutY();
		
		/*smallCircle.setOnMouseDragged(e-> {
				smallCircle.setTranslateX(e.getX());
				smallCircle.setTranslateY(e.getY());
		});
		smallCircle.setOnMouseReleased(e->{
			smallCircle.setTranslateX(0);
			smallCircle.setTranslateY(0);
		});*/
		
//		smallCircle.setOnMouseClicked(e->{
//			mouse.add(e.getSceneX(), e.getSceneY());
//		});

		//smallCircle.setOnMouseMoved(e->{
		//	changeX=e.getSceneX()-orgX;
		//	changeY=e.getSceneY()-orgY;
		//});
		
	}
	
//	public double dist(double x1, double y1, double x2, double y2) {
//		return Math.sqrt( (x1-x2) * (y1-y2) + (y1-y2) * (y1-y2));
//	}
//	
	public void dragable(MouseEvent e) {
		if((smallCircle.getCenterX()<=maxBorder && smallCircle.getCenterY()<= maxBorder)
				&& (smallCircle.getCenterX()>= -maxBorder && smallCircle.getCenterY()>= -maxBorder)) {
			smallCircle.setCenterX(e.getX());
			smallCircle.setCenterY(e.getY());
			//aileron.setValue((double) 0.1*event.getX()%1);
			//elevator.setValue((double) 0.1*event.getY()%1);
			
		}else {
			smallCircle.setCenterX(0);
			smallCircle.setCenterY(0);
			return;
		}
		
		
//		if(radius==0) {
//			radius = bigCircle.getRadius();
//			centerX = (smallCircle.localToScene(smallCircle.getBoundsInLocal()).getMinX()
//					+ smallCircle.localToScene(smallCircle.getBoundsInLocal()).getMaxX())/2;
//			centerY = (smallCircle.localToScene(smallCircle.getBoundsInLocal()).getMinY()
//					+ smallCircle.localToScene(smallCircle.getBoundsInLocal()).getMaxY())/2;
//		}
//		double x1= e.getSceneX();
//		double y1= e.getSceneY();
//		double x2,y2;
//		
//		double distance = dist(e.getSceneX(), e.getSceneY(), centerX, centerY);
//		if(distance <= radius) {
//			smallCircle.setLayoutX(initializedCenterX+ x1 - centerX);
//			smallCircle.setLayoutY(initializedCenterY + y1 + centerY);
//			
//			x2=x1;
//			y2=y1;
//		}
//		else {
//			if(x1>centerX) {
//				double alfa = Math.atan((y1 - centerY)/(x1-centerX));
//				double w= radius * Math.cos(alfa);
//				double z= radius * Math.sin(alfa);
//				
//				x2=centerX + w;
//				y2= centerY + z;
//				
//				smallCircle.setLayoutX(initializedCenterX+x2 - centerX);
//				smallCircle.setLayoutY(initializedCenterY+y2-centerY);
//			}
//			else {
//				double alfa = Math.atan((centerY - y1) / (centerX - x1));
//				double w = radius * Math.cos(alfa);
//				double z = radius * Math.sin(alfa);
//				
//				x2 = centerX - w;
//				y2 = centerY - z;
//			}
//			
//			smallCircle.setLayoutX(initializedCenterX+x2-centerX);
//			smallCircle.setLayoutY(initializedCenterY+y2-centerY);
//		}
		
		return;
	}
	
	public void dragable_exit() {
		smallCircle.setCenterX(0);
		smallCircle.setCenterY(0);
//		smallCircle.setLayoutX(initializedCenterX);
//		smallCircle.setLayoutY(initializedCenterY);
	}

	public void Connect() {
		
		Dialog<connectionData> dialog= new Dialog<connectionData>();
		dialog.setTitle("Connection Dialog");
		dialog.setHeaderText("Please enter IP and Port");
		DialogPane dialogPane = dialog.getDialogPane();
		ButtonType connecectBtn = new ButtonType("Connect");
		dialogPane.getButtonTypes().addAll(connecectBtn, ButtonType.CANCEL);
		TextField textField1 = new TextField("IP");
		TextField textField2 = new TextField("Port");
		dialogPane.setContent(new VBox(10, textField1, textField2));
		Platform.runLater(textField1::requestFocus);
		Platform.runLater(textField2::requestFocus);

		dialog.setResultConverter((ButtonType button) -> {
		   if (button == ButtonType.OK) {
		       return new connectionData(textField1.getText(),textField2.getText());
		   }
		   return null;
		});
		@SuppressWarnings("unused")
		Optional<connectionData> optionalResult = dialog.showAndWait();

	}
	
	public void LoadCSV() {
		FileChooser fc= new FileChooser();
		fc.setTitle("open maze file");
		fc.setInitialDirectory(new File("./resources"));
		FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fc.getExtensionFilters().add(extFilter1);
		File chosen = fc.showOpenDialog(null);
		if(chosen!= null) {
			System.out.println(chosen.getName());
		}

	}
	
	public void calculate() {
		Dialog<connectionData> dialog= new Dialog<connectionData>();
		dialog.setTitle("Calculage path Dialog");
		dialog.setHeaderText("The ip and port are:");
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		Label ipLable=new Label("ip adress");
		Label portLable = new Label("port number");
		dialogPane.setContent(new VBox(10, ipLable, portLable));
		Platform.runLater(ipLable::requestFocus);
		Platform.runLater(portLable::requestFocus);

		dialog.setResultConverter((ButtonType button) -> {
		   if (button == ButtonType.OK) {
			   //rest of the code
		       
		   }
		   return null;
		});
		@SuppressWarnings("unused")
		Optional<connectionData> optionalResult = dialog.showAndWait();
	}
	
	public void Load() {
		FileChooser fc= new FileChooser();
		fc.setTitle("open maze file");
		fc.setInitialDirectory(new File("./resources"));
		FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("txt File (*.txt)", "*.txt");
		fc.getExtensionFilters().add(extFilter1);
		File chosen = fc.showOpenDialog(null);
		if(chosen!= null) {
			Scanner scaner;
			try {
				scaner = new Scanner(new File(chosen.getAbsolutePath()));
				while(scaner.hasNextLine()) {
					txtArea.appendText(scaner.nextLine());
					txtArea.appendText("\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}


}
