package com.watchtrailer.core.models;

import java.util.Calendar;


/**
 * The Class NewsModel.
 */
public class NewsModel {

	/** The news title. */
	private String newsTitle;

	/** The news desc. */
	private String newsDesc;

	/** The news date. */
	private Calendar newsDate;

	/** The pagepath. */
	private String pagepath;

	/**
	 * Gets the news title.
	 *
	 * @return the news title
	 */
	public String getNewsTitle() {
		return newsTitle;
	}

	/**
	 * Sets the news title.
	 *
	 * @param newsTitle the new news title
	 */
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	/**
	 * Gets the news desc.
	 *
	 * @return the news desc
	 */
	public String getNewsDesc() {
		return newsDesc;
	}

	/**
	 * Sets the news desc.
	 *
	 * @param newsDesc the new news desc
	 */
	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}

	/**
	 * Gets the news date.
	 *
	 * @return the news date
	 */
	public Calendar getNewsDate() {
		return newsDate;
	}

	/**
	 * Sets the news date.
	 *
	 * @param newsDate the new news date
	 */
	public void setNewsDate(Calendar newsDate) {
		this.newsDate = newsDate;
	}

	/**
	 * Gets the pagepath.
	 *
	 * @return the pagepath
	 */
	public String getPagepath() {
		return pagepath;
	}

	/**
	 * Sets the pagepath.
	 *
	 * @param pagepath the new pagepath
	 */
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}

}
