package com.watchtrailer.core.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


/**
 * The Class NewsListModel.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsListModel {

	/** The news parent path. */
	@ValueMapValue
	private String newsParentPath;

	/** The resolver. */
	@ScriptVariable
	ResourceResolver resolver;

	/**
	 * Gets the news list.
	 *
	 * @return the news list
	 */
	public List<NewsModel> getNewsList() {
		List<NewsModel> newsList = new ArrayList<>();
		if (StringUtils.isNotBlank(newsParentPath)) {
			PageManager pm = resolver.adaptTo(PageManager.class);
			Page newsPage = pm.getPage(newsParentPath);
			Iterator<Page> childPagesItr = newsPage.listChildren();
			while (childPagesItr.hasNext()) {
				Page childPage = childPagesItr.next();
				String pageTitle = childPage.getPageTitle();
				String description = childPage.getDescription();
				ValueMap childPageProps = childPage.getContentResource().getValueMap();
				Calendar calendar = childPageProps.get("jcr:created", Calendar.getInstance());
				NewsModel nm = new NewsModel();
				nm.setNewsTitle(pageTitle);
				nm.setNewsDesc(description);
				nm.setNewsDate(calendar);
				nm.setPagepath(childPage.getPath());
				newsList.add(nm);
			}
		}
		return newsList;
	}

}
