// IJP Assignment 1, Version 18.1.3, 2018-10-30 10:52:23
package ijp.controller;

import ijp.Picture;
import ijp.service.Service;
import ijp.service.ServiceFromProperties;
import ijp.view.View;
import ijp.view.ViewFromProperties;

/**
 * A template for implementing a controller for the PictureViewer application.
 * 
 * @author YOUR NAME HERE
 * @version YOUR VERSION HERE
 */
public class MyController implements Controller {

	private View view;
	private Service service;

	private int selection1;
	private int selection2;

	/**
	 * Start the controller.
	 */
	public void start() {

		// create the view and the service objects
		view = new ViewFromProperties(this);
		service = new ServiceFromProperties();

		// create two selections in the interface
		selection1 = view.addSelection("Stob Binnein");
		selection2 = view.addSelection("Gairich");

		// start the interface
		view.start();
	}

	/**
	 * Handle the specified selection from the interface.
	 *
	 * @param selectionID the id of the selected item
	 */
	public void select(int selectionID) {
		
		// a picture corresponding to the selectionID
		// by default, this is an empty picture
		// (this is used if the selectionID does not match)
		Picture picture = new Picture();

		// create a picture corresponding to the selectionID
		if (selectionID==selection1) {
			picture = service.getPicture("Stob Binnein",1);
		}
		else if (selectionID==selection2) {
			picture = service.getPicture("Gairich",1);
		}
		
		// show the picture in the interface
		view.showPicture(picture);
	}
}
