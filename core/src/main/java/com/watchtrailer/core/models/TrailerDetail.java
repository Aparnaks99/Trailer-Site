package com.watchtrailer.core.models;

// TODO: Auto-generated Javadoc
/**
 * The Class TrailerDetail.
 */
public class TrailerDetail {

	/** The name. */
	private String name;

	/** The imagepath. */
	private String imagepath;

	/** The comments. */
	private String comments;

	/** The id. */
	private String id;

	/** The modulo val. */
	private Integer moduloVal;

	/**
	 * Gets the modulo val.
	 *
	 * @return the modulo val
	 */
	public Integer getModuloVal() {
		return moduloVal;
	}

	/**
	 * Sets the modulo val.
	 *
	 * @param i the new modulo val
	 */
	public void setModuloVal(Integer i) {
		this.moduloVal = i % 6;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the imagepath.
	 *
	 * @return the imagepath
	 */
	public String getImagepath() {
		return imagepath;
	}

	/**
	 * Sets the imagepath.
	 *
	 * @param imagepath the new imagepath
	 */
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
