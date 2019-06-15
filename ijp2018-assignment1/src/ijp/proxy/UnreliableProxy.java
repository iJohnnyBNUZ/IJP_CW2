// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.proxy;

import java.util.Random;

import ijp.Picture;
import ijp.service.Service;
import ijp.service.ServiceFromProperties;
import ijp.utils.Properties;

/**
 * A proxy service for the PictureViewer application which provides an unreliable version
 * of the base service (this is useful for testing).
 * <p>The following properties are supported:
 * <ul>
 * <li><code>UnreliableProxy.base_service</code> - the class to use for the base service if not specified in the constructor.</li>
 * <li><code>UnreliableProx.percentage_fails</code> - the average percentage of calls to <code>getPicture()</code>
 * which will return an artificial failure.</li>
 * <li><code>UnreliableProxy.debug</code> - <code>true</code> or <code>false</code> to enable/disable debugging.</li>
 * </ul>
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public class UnreliableProxy implements Service {

    protected Service baseService = null;
    Boolean debugging = false;
    int percentageFails = 0;
	
	/**
	 * Return a textual name for the service.
	 *
	 * @return the name of the service
	 */
	public String serviceName() {
		return baseService.serviceName();
	}
	
	/**
	 * Create an unreliable proxy service based on the service specified in the
	 * <code>UnreliableProxy.base_service</code> resource.
	 */
	public UnreliableProxy() {
		baseService = new ServiceFromProperties("UnreliableProxy.base_service");
		init();
	}

	/**
	 * Create an unreliable proxy service based on the specified base service.
	 *
	 * @param baseService the base service
	 */
	public UnreliableProxy(Service baseService) {
		this.baseService = baseService;
		init();
	}

	private void init() {
	    this.debugging = Properties.getBool("UnreliableProxy.debug");
	    this.percentageFails = Properties.getInt("UnreliableProxy.percentage_fails");
	}
	
	/**
	 * Return a picture from the base service, with a random possibility of returning an error.
	 * the probably of the error is given the <code>percentage_fails</code> resource.
	*
    * @param subject the free-text subject string
    * @param index the index of the matching picture to return
    * @return the requested picture
    */
    public Picture getPicture(String subject, int index) {
    	
    	Random rand = new Random();
    	int random = rand.nextInt(101);
    	if (random<percentageFails) {
    		if (debugging) {
    			System.err.println("[debug] UnreliableProxy: returning failure ("
    					+ random + "<" + percentageFails + ")");
    		}
    		return new Picture("UnreliableProxy failed\nfor picture #"+index+" of \""+subject+"\"\nfrom "
    				+ baseService.serviceName() );
    	} else {
    		if (debugging) {
    			System.err.println("[debug] UnreliableProxy: fetching picture from " +
    					baseService.serviceName() + " (" + random + ">=" + percentageFails + ")");
    		}
    		return baseService.getPicture(subject, index);	
    	}
    } 
}
