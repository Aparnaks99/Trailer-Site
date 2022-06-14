package com.watchtrailer.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;


/**
 * The Class HeaderModel.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

	/** The primarylinks. */
	@ChildResource
	private List<LinkDetailModel> primarylinks;

	/**
	 * Gets the primarylinks.
	 *
	 * @return the primarylinks
	 */
	public List<LinkDetailModel> getPrimarylinks() {
		return primarylinks;
	}

}
