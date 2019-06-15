// IJP Assignment 1, Version 18.1.3, 2018-10-30 10:52:23
package ijp.proxy;

import ijp.Picture;
import ijp.service.Service;
import ijp.service.ServiceFromProperties;

/**
 * A skeleton (null)proxy service for the PictureViewer application.
 * <p>The following properties are supported:
 * <ul>
 * <li><code>MyCacheProxy.base_service</code> - the class to use for the base service if not specified in the constructor.</li>
 * <li><code>MyCacheProxy.debug</code> - <code>true</code> or <code>false</code> to enable/disable debugging.</li>
 * </ul>
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-30 10:52:23
 */
public class MyCacheProxy implements Service {

	private Service baseService = null;

	/**
	 * Return a textual name for the service.
	 *
	 * @return the name of the base service
	 */
	public String serviceName() {
		return baseService.serviceName();
	}
	
	/**
	 * Create a cache proxy service based on the service specified in the
	 * <code>MyCacheProxy.base_service</code> resource.
	 */
	public MyCacheProxy() {
		baseService = new ServiceFromProperties("MyCacheProxy.base_service");
	}
	
	/**
	 * Create a cache proxy service based on the specified service.
	 *
	 * @param baseService the base service
	 */
	public MyCacheProxy(Service baseService) {
		this.baseService = baseService;
	}

	/**
	 * Return a picture from the base service. 
	 *
    * @param subject the free-text subject string
    * @param index the index of the matching picture to return
    * @return the requested picture
    */
	public Picture getPicture(String subject, int index) {	
		return baseService.getPicture(subject, index);
    }
}
