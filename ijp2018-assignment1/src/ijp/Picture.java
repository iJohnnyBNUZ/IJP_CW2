// IJP Assignment 1, Version 18.1.3, 2018-10-19 14:11:19
package ijp;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

/**
 * A picture for use in the PictureViewer Application. A picture contains an image,
 * as well as some meta-information such as the subject, index, source, and any
 * error information.
 * 
 * @author  Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 18.1.3, 2018-10-19 14:11:19
 */
public class Picture {

	private Image image = null;
	private String subject = "";
	private String source = "";
	private Integer index = 0;
	private String errorMessage = "empty picture";
	
    /**
     * Create an empty picture.
     */
	public Picture() {
	}
	
    /**
     * Create a picture from a BufferedImage.
     *
     * @param image the image as a BufferedImage
     * @param subject the subject
     * @param source the source (e.g. Flickr service)
     * @param index the index of the image on the service
     */
	public Picture(BufferedImage image, String subject, String source, Integer index) {
		this.setImage(image);
		this.setSubject(subject);
		this.setSource(source);
		this.setIndex(index);
		this.errorMessage = null;
	}
	
    /**
     * Create a picture from an Image.
     *
     * @param image the image as an Image
     * @param subject the subject
     * @param source the source (e.g. Flickr service)
     * @param index the index of the image on the service
     */
	public Picture(Image image, String subject, String source, Integer index) {
		this.setImage(image);
		this.setSubject(subject);
		this.setSource(source);
		this.setIndex(index);
		this.errorMessage = null;
	}
	
    /**
     * Create a picture representing an error.
	 * For example a service failure retrieving the image.
     *
     * @param errorMessage the error message
     */
	public Picture(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
    /**
     * Set the image of the picture from a BufferedImage.
     *
     * @param bufferedImage the BufferedImage
     */
	public void setImage(BufferedImage bufferedImage) {
        this.image = (bufferedImage==null) ? null : SwingFXUtils.toFXImage(bufferedImage, null);
	}
	
    /**
     * Set the image of the picture from an Image.
     *
     * @param image the Image
     */
	public void setImage(Image image) {
		this.image = image;
	}
	
    /**
     * Return the image of the picture.
     *
	 * @return the image
     */
	public Image image() {
		return this.image;
	}
	
    /**
     * Set the subject of the picture.
     *
     * @param subject the subject
     */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
    /**
     * Return the subject of the picture.
     *
	 * @return the subject
     */
	public String subject() {
		return this.subject;
	}
	
    /**
     * Set the source of the picture (e.g. Flickr service).
     *
     * @param source the source
     */
	public void setSource(String source) {
		this.source = source;
	}
	
    /**
     * Return the source of the picture.
     *
	 * @return the image source
     */
	public String source() {
		return this.source;
	}
	
    /**
     * Set the error message.
     *
     * @param errorMessage the error message
     */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
    /**
     * Return the error message.
     *
	 * @return the error message (null if no error)
     */
	public String errorMessage() {
		return this.errorMessage;
	}
	
    /**
     * Set the index of the picture on the service.
     *
     * @param index the index
     */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
    /**
     * Return the index of the picture on the service.
     *
	 * @return the index
     */
	public Integer index() {
		return this.index;
	}

    /**
     * Return true if the picture is valid.
     *
	 * @return the image
     */
	public Boolean isValid() {
		return errorMessage == null;
	}

    /**
     * Return a text description of the picture.
     *
	 * @return the description
     */
	public String description() {
		String description = (subject==null) ? "" : subject;
		description = description + ((index<=1) ? "" : " #" + index);
		description = description + ((source==null) ? "" : " (from " + source + ")");
		return description;
	}
}
