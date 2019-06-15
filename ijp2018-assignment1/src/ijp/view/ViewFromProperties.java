// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.view;

import ijp.Picture;
import ijp.controller.Controller;
import ijp.utils.Properties;

/**
 * A view for the PictureViewer application which uses the implementation
 * class specified in the property file.
 * <p>The following properties are supported:
 * <ul>
 * <li><code>View</code> - the (unqualified) classname of the implementation.</li>
 * <li><code>View.debug</code> - <code>true</code> or <code>false</code> to enable/disable debugging.</li>
 * </ul>
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public class ViewFromProperties implements View {

	protected View delegate = null; 
	protected Boolean debugging = false;

	/**
	 * Create an instance of a view which uses the class
	 * specified in the <code>View</code> property in the property file.
	 */
	public ViewFromProperties() {
		createView();
	}

	/**
	 * Create an instance of a view which uses the class
	 * specified in the <code>View</code> property in the property file.
	 * 
	 * @param controller the controller to notify when selections are made in the interface.
	 */
	public ViewFromProperties(Controller controller) {
		createView();
		setController(controller);
	}
	
	/**
	 * Create an instance of a service which uses the implementation class
	 * specified in the <code>View</code> property in the property file.
	 */
	private void createView() {
		debugging = Properties.getBool("View.debug");
		delegate = (View) Properties.getObject("View","ijp.view:ijp.test");	
		if (debugging) {
			System.err.println("[debug] Service: created " + delegate.getClass().getName());
		}
	}

	/**
	 * Start the view. The interface waits for user interaction and calls
	 * the controller's <code>select()</code> method when the user selects
	 * an item.
	 */
	public void start() {
		if (debugging) {
			System.err.println("[debug] View: start");
		}
 		delegate.start();
 	}
 	
	/**
	 * Display the specified picture in the interface.
	 *
	 * @param picture the picture to display
	 */
	public void showPicture(Picture picture) {
		if (debugging) {
			System.err.println("[debug] View: show picture");
		}
		delegate.showPicture(picture);
	}

	/**
	 * Add an item to the interface which the user can select.
	 *
	 * @param label a label for the item, to be displayed in the interface
	 * @return an identifier for the selection. This identifier will be passed to the controller
	 * as the argument of the <code>select()</code> method when the user selects this item in the interface.
	 * Note that some controllers may return consecutive integers for the selection identifiers, but this 
	 * is not required.
	 */
	public int addSelection(String label) {
		int selectionID = delegate.addSelection(label);
		if (debugging) {
			System.err.println("[debug] View: subject \""+label+"\" = selection "+selectionID);
		}
		return selectionID;
	}

	/**
	 * Set the controller.
	 *
	 * @param controller the controller to be notified when the user selects an item
	 */
	public void setController(Controller controller) {
		if (debugging) {
			System.err.println("[debug] View: set controller");
		}
		delegate.setController(controller);
	}

	/**
	 * Display the specified text in the interface.
	 *
	 * @param caption the text to display
	 */
	public void setCaption(String caption) {
		if (debugging) {
			System.err.println("[debug] View: set caption \""+caption+"\"");
		}
		delegate.setCaption(caption);
	}
	
	/**
	 * Display the specified error message in the interface.
	 *
	 * @param message the error message to display
	 */
	public void showErrorMessage(String message) {
		if (debugging) {
			System.err.println("[debug] View: show error message \""+message+"\"");
		}
		delegate.showErrorMessage(message);		
	}
	
	/**
	 * Clear the error message.
	 */
	public void clearErrorMessage() {
		if (debugging) {
			System.err.println("[debug] View: clear error message");
		}
		delegate.clearErrorMessage();		
	}
}
