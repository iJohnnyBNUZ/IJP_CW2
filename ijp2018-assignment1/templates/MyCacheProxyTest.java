// IJP Assignment 1, Version 18.1.3, 2018-10-30 10:52:23
package ijp.test;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;

import org.junit.Test;

import ijp.Picture;
import ijp.proxy.MyCacheProxy;
import ijp.service.Service;

/**
 * A template for testing a cache proxy for the PictureViewer application.
 * 
 * @author YOUR NAME HERE
 * @version YOUR VERSION HERE
 */
public class MyCacheProxyTest implements Service {
	
	/**
	 * Test that requests for the same subject and index return the same image.
	 */
	@Test
	public void equalityTest() {

		Service proxy = new MyCacheProxy(this);
		Picture firstPicture = proxy.getPicture("equalityTest",2);
		Picture secondPicture = proxy.getPicture("equalityTest",2);
		assertTrue(
				"different picture returned for same subject (and index)",
				firstPicture == secondPicture);
	}

	/**
	 * Return a picture from the simulated service.
	 * This service simply returns an empty picture every time that it called.
	 * Note that a <em>different</em> object is returned each time, even if the
	 * subject and index are the same.
	 *
	 * @param subject the requested subject
	 * @param index the index of the picture within all pictures for the requested topic
	 *
	 * @return the picture
	 */
	@Override
	public Picture getPicture(String subject, int index) {
		return new Picture((BufferedImage)null, subject ,serviceName(), index);
	}
	
	/**
	 * Return a textual name to identify the simulated service.
	 *
	 * @return the name of the service
	 */
	@Override
	public String serviceName() {
		return "MyCacheProxyTest";
	}
}
