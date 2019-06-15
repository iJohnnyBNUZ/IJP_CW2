// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.controller;

/**
 * A controller for the PictureViewer application.
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public interface Controller {

	/**
	 * Start the controller.
	 */
	public void start();

	/**
	 * Handle the specified selection from the interface.
	 *
	 * @param selectionID the id of the selected item
	 */
	public void select(int selectionID);
}
