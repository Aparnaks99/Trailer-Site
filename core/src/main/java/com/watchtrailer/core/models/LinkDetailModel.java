package com.watchtrailer.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkDetailModel.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkDetailModel {

	/** The linktitle. */
	@ValueMapValue
	private String linktitle;

	/** The linkpath. */
	@ValueMapValue
	private String linkpath;

	/**
	 * Gets the linktitle.
	 *
	 * @return the linktitle
	 */
	public String getLinktitle() {
		return linktitle;
	}

	/**
	 * Gets the linkpath.
	 *
	 * @return the linkpath
	 */
	public String getLinkpath() {
		return linkpath;
	}

}
