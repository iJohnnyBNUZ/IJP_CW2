// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.controller;

import ijp.utils.Properties;

/**
 * A controller for the PictureViewer application which uses the implementation
 * class specified in the property in the property file.
 * <p>The following properties are supported:
 * <ul>
 * <li><code>Controller</code> - the (unqualified) classname of the implementation.</li>
 * <li><code>Controller.debug</code> - <code>true</code> or <code>false</code> to enable/disable debugging.</li>
 * </ul>
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public class ControllerFromProperties implements Controller {

	private Controller delegate = null;
	private Boolean debugging = false;
	
	/**
	 * Create an instance of a controller which uses the implementation class
	 * specified in the <code>Controller</code> property in the property file.
	 */
	public ControllerFromProperties() {
		debugging = Properties.getBool("Controller.debug");
		delegate = (Controller) Properties.getObject("Controller","ijp.controller:ijp.test");
        if (debugging) {
        	System.err.println("[debug] Controller: created " + delegate.getClass().getName());
        }
	}

	/**
	 * Start the controller.
	 */
	public void start() {
        if (debugging) {
        	System.err.println("[debug] Controller: start");
         }
		delegate.start();
	}

	/**
	 * Handle the specified selection from the interface.
	 *
	 * @param selectionID the id of the selected item.
	 */
	public void select(int selectionID) {
	    if (debugging) {
	     	System.err.println("[debug] Controller: select \""+selectionID+"\"");
	    }
		delegate.select(selectionID);
	}
}
