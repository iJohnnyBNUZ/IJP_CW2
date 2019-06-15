// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.proxy;

import ijp.Picture;
import ijp.service.Service;
import ijp.service.ServiceFromProperties;
import ijp.utils.Properties;

/**
 * A proxy service for the PictureViewer application which provides a more reliable version
 * of the base service by retrying failed attempts.
 * <p>The following properties are supported:
 * <ul>
 * <li><code>RetryProxy.base_service</code> - the class to use for the base service if not specified in the constructor.</li>
 * <li><code>RetryProxy.count</code> - the maximum number of times to attempt to fetch a picture from the base service.</li>
 * <li><code>RetryProxy.debug</code> - <code>true</code> or <code>false</code> to enable/disable debugging.</li>
 * </ul>
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public class RetryProxy implements Service {

	private Service baseService = null;
	private Boolean debugging = false;
	private int maxAttempts = 0;
	
	/**
	 * Return a textual name for the service.
	 *
	 * @return the name of the service
	 */
	public String serviceName() {
		return baseService.serviceName();
	}

	/**
	 * Create a retry proxy service based on the specified service.
	 * <code>RetryProxy.base_service</code> resource.
	 *
	 * @param baseService the base service
	 */
	public RetryProxy(Service baseService) {
		this.baseService = baseService;
		init();
	}

	/**
	 * Create a retry proxy service based on the service specified in the
	 */
	public RetryProxy() {
		baseService = new ServiceFromProperties("RetryProxy.base_service");
		init();
	}
	
	private void init() {
	    debugging = Properties.getBool("RetryProxy.debug");
    	maxAttempts = Properties.getInt("RetryProxy.max_attempts");		
	}
	
	/**
	 * Return a picture from the base service.
	 * If the base service returns an error, it is retried a maximum of
	 * <code>RetryProxy.count</code> before returning an error.
	 *
    * @param subject the free-text subject string
    * @param index the index of the matching picture to return
    * @return the requested picture
    */
   public Picture getPicture(String subject, int index) {
        if (debugging) {
        	System.err.println("[debug] RetryProxy: fetching picture from " + baseService.serviceName());
        }
		Picture picture = baseService.getPicture(subject, index);
		int attempt = 1;
    	while (!picture.isValid() && attempt<=maxAttempts) {
            if (debugging) {
            	System.err.println("[debug] RetryProxy: retrying " + attempt);
            }
            picture = baseService.getPicture(subject, index);
    		++attempt;
    	}
        if (debugging) {
        	if (picture.isValid()) {
        		System.err.println("[debug] RetryProxy: got picture");
        	} else {
        		System.err.println("[debug] RetryProxy: failed to get picture");
        	}
        }   	
    	return picture;
    } 
}
