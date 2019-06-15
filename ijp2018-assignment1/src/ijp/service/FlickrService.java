// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp.service;

import ijp.Picture;
import ijp.utils.Properties;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A service for the PictureViewer application which retrieves pictures from
 * <a href="https://www.flickr.com">Flickr</a>.
 * <p>The following properties are supported:
 * <ul>
 * <li><code>FlickrService.base64_key</code> - the Flickr API key.</li>
 * <li><code>FlickrService.debug</code> - <code>true</code> or <code>false</code> to enable/disable debugging.</li>
 * </ul>
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public class FlickrService implements Service {

	// the URL for the Flickr REST API
	private static String flickrURLString = "https://api.flickr.com/services/rest/";
	// the method for photo search
	private static String searchMethod = "?method=flickr.photos.search";
	// the Flickr API key (see http://bit.ly/8B4ql0)
	private String apiKey;
    // the URL of the image 
    private String photoURL;
    // enable debugging
    private boolean debugging = false;
    
	/**
	 * Create a Flickr service object.
	 */
    public FlickrService() {
    	this.apiKey = Properties.get("FlickrService.api_key");
    	this.debugging = Properties.getBool("FlickrService.debug");
    }

	/**
	 * Return the service name.
	 *
	 * @return the name of the service ("Flickr Service").
	 *
	 */
    public String serviceName() {
    	return "Flickr Service";
    }
    
    // Handles events during the SAX parsing of the XML from the REST request
    private class FlickrHandler extends DefaultHandler {

    	// we are only interested in element start events
    	// stubs for the other events are provided by the default handler
	    public void startElement(
	    		String uri,
	    		String localName,
	    		String qName,
	    		Attributes attributes) {

    		// we are only interested in "photo" elements
    		if (qName.equals("photo")) {

    			// extract the relevant attributes from the photo
    			String server = attributes.getValue("server");
    			String id = attributes.getValue("id");
    			String secret = attributes.getValue("secret");
    			// title currently unused
    			// String title = attributes.getValue("title");

    			// compose a URL to locate the image itself
    			// see here: http://bit.ly/10EGq7
    			// notice that we will accept any size of image ...
    			photoURL = "http://static.flickr.com/" + server
    					+ "/" + id + "_" + secret + ".jpg";
    		}
    	}
    }
	    
    // Handle errors during the SAX parsing
    // It seems that the Flickr API is really flakey and often returns a completely
    // empty response to a query, rather than valid XML
    //
    // See, for example: http://flickrnet.codeplex.com/discussions/397551
    // "Unfortunately Flickr will often return an empty result (rather than some error)
    // when it encounters an issue (such as an internal timeout, or invalid content or
    // whatever). We have no way of knowing what or if this issue is the cause however
    // as we only ever see an empty response."
	    
    private class FlickrErrorHandler implements ErrorHandler {

    	public void	error(SAXParseException ex) {
    		System.err.println("[error] FlickrService: SAX error: " + ex);
    	}
	    public void	fatalError(SAXParseException ex) {
	    	String errMessage = ex.toString();
	    	if (errMessage.contains("The processing instruction target matching \"[xX][mM][lL]\" is not allowed")) {
	    		if (debugging) {
	    			System.err.println("[debug] FlickrService: SAX invalid response");
	    		}
	    	} else {
	    		System.err.println("[error] FlickrService SAX fatal error: " + ex);
	    	}
	    }
	    public void	warning(SAXParseException ex) {
	    	System.err.println("[error] FlickrService SAX warning: " + ex);
	    }
    }

    // return errors as a status message in a photo
    private Picture badPicture(String msg, String searchText, int index) {
    	String errorMessage = "FlickrService failed to retrieve photo " + index
    			+ " for search string: " + searchText + "\n- " + msg;
    	return new Picture(errorMessage);
    }

    /**
    * Return a picture object from a free-text search of Flickr. Normally, there
    * will be more than one match, and the <code>index</code>'th matching picture
    * is returned. <p> If there is no matching image, or <code>index</code> is greater
    * than the number of matching images, or there is any other kind of error,
    * then the returned Picture object will contain the error message.
    *
    * @param subject the free-text subject string
    * @param index the index of the matching picture to return
    * @return the requested picture
    */
    public Picture getPicture(String subject, int index) {

    	// the flickr search API is described here: http://bit.ly/10F8zp

    	// the image of the downloaded photo
    	BufferedImage bufferedImage;

    	try {

    		// initialise the URL
    		// this instance variable is filled in by the SAX tree walker
    		// when it encounters an appropriate node.
    		this.photoURL = null;

    		// based on code from here: http://bit.ly/M6Ee6N
    		String urlString = flickrURLString + searchMethod
    				+ "&api_key=" + apiKey
    				+ "&per_page=1"
    				+ "&page=" + index
    				+ "&content_type=1" // photos only 
    				+ "&media=photos" // no video 
    				+ "&text=" + URLEncoder.encode(subject, "utf-8");

    		if (debugging) {
    			System.err.println("[debug] FlickrService: fetching: "+urlString);
    		}
	            
    		// this handles the SAX parse events
    		FlickrHandler handler = new FlickrHandler();
    		FlickrErrorHandler errorHandler = new FlickrErrorHandler();
	            	
    		try {
	            		
    			// extract the photo url from the metadata
    			// from: http://bit.ly/lvDqMB
    			InputStream stream = new URL(urlString).openStream();
      			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    			SAXParser parser = parserFactory.newSAXParser();
    			XMLReader myReader = parser.getXMLReader();
     			myReader.setContentHandler(handler);
    			myReader.setErrorHandler(errorHandler);
    			myReader.parse(new InputSource(stream));

    			// something went wrong - we never saw any photo info ...
    			if (photoURL == null) {
    	    		return new Picture("FlickrService has no picture #"+index+" of \""+subject+"\"");
    			}
	            		
    		} catch (SAXException | ParserConfigurationException ex) {
    			return badPicture(ex.getMessage(), subject, index);
    		}
	   
    		if (debugging) {
    			System.err.println("[debug] FlickrService: fetching photo: " + photoURL.toString());
    		}

    		// go fetch the image itself
    		bufferedImage = ImageIO.read(new URL(photoURL).openStream());

    	} catch (IOException ex) {
    		return badPicture(ex.getMessage(), subject, index);
    	}

    	// this is the code that all the other services use to fetch their images.
    	// for some reason or other, this produces blank images from Flickr
    	// >>>> return new Picture(new Image(photoURL.toString()),subject,serviceName(),index);
    	// so we do the messing around above to create a bufferedImage & that seems to work
    	
    	return new Picture(bufferedImage,subject,serviceName(),index);
    }
}
