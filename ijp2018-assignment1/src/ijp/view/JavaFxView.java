// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.view;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ijp.Picture;
import ijp.PictureViewer;
import ijp.controller.Controller;
import ijp.utils.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * An abstract view for the PictureViewer application which provides
 * common methods for JavaFx views.
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
abstract public class JavaFxView implements View {

	protected Controller controller = null;

	@FXML
	protected ImageView imageView;

	@FXML
	protected ImageView iconView;

	@FXML
	protected Label captionLabel;

	@FXML
	protected Label errorLabel;

	/**
	 * Create a JavaFx view from an fxml file.
	 *
	 * @param viewClassName the name of the view class
	 * @return the view
	 */
	public static View factory(String viewClassName) {

		if (!viewClassName.contains(".")) { viewClassName = "ijp.view." + viewClassName; }
		String viewFxml = Properties.get("View") + ".fxml";
		URL fxmlURL = null;
		boolean debugging = Properties.getBool("View.debug");
		
		if (debugging) {
			System.err.println("[debug] JavaFxView: creating view \""+viewClassName+"\"");
			System.err.println("[debug] JavaFxView: fxml = "+viewFxml);
		}

		try {
			Class<?> viewClass = Class.forName(viewClassName);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlURL = viewClass.getResource(viewFxml);
			AnchorPane page = (AnchorPane) fxmlLoader
					.load(fxmlURL.openStream());
			Scene scene = new Scene(page);
			scene.getStylesheets().add("pictureviewer.css");
			PictureViewer.mainStage.setScene(scene);
			PictureViewer.mainStage.setResizable(false);
			return (View) fxmlLoader.getController();

		} catch (IOException ex) {
			System.err.println("[error] JavaFxView: can't load fxml file \""
					+ fxmlURL.toString() + "\"\n" + ex.toString());
			System.exit(1);

		} catch (ClassNotFoundException ex) {
			System.err.println("[error] JavaFxView: can't find view class \""+viewClassName+"\"");
			System.exit(1);
		}
		
		return null;
	}

	/**
	 * Set the controller.
	 *
	 * @param controller the controller to be notified when the user selects an item
	 */
	public void setController(Controller controller) { 
 		this.controller = controller;
 	}
 	
	/**
	 * Start the view. Create a default caption and display the main JavaFx stage.
	 * The interface then waits for user interaction and calls
	 * the controller's <code>select()</code> method when the user selects
	 * an item.
	 */
	public void start() {
 		String caption = "PictureViewer 18.1.3, 2018-10-19 14:11:19";
 		if (controller != null) {
 			caption = caption + " (using " + controller.getClass().getSimpleName() + ")";
 		}
 		setCaption(caption);
 		PictureViewer.mainStage.show();
 	}

	/**
	 * Display the specified picture in the interface.
	 *
	 * @param picture the picture to display
	 */
	public void showPicture(Picture picture) {
		if (picture.isValid()) {
			clearErrorMessage();
			imageView.setImage(picture.image());
			setCaption(picture.description());
		} else {
			showErrorMessage(picture.errorMessage());
			imageView.setImage(null);
			setCaption("");	
		}
	}
	
	/**
	 * Display the specified text in the interface.
	 *
	 * @param caption the text to display
	 */
	public void setCaption(String caption) {
		this.captionLabel.setText(caption);
	}
	
	/**
	 * Display the specified error message in the interface.
	 *
	 * @param message the error message to display
	 */
	public void showErrorMessage(String message) {
		errorLabel.setText(message);
		showErrorIcon(true);
	}
	
	/**
	 * Clear the error message.
	 */
	public void clearErrorMessage() {
		errorLabel.setText("");
		showErrorIcon(false);
	}

	/**
	 * Enable/display the error icon.
	 *
	 * @param state true/false to enable/disable the icon
	 */
	protected void showErrorIcon(Boolean state) {
		if (state && iconView.getImage()==null) {
			URL errorIconURL = this.getClass().getResource("/images/Error.png");			
			iconView.setImage(new Image(errorIconURL.toString()));	
		}
		iconView.setVisible(state);
	}
 }
