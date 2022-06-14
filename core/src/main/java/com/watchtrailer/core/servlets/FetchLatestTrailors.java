/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.watchtrailer.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watchtrailer.core.services.FetchConfigValuesService;
import com.watchtrailer.core.services.TrailerListService;
import static com.watchtrailer.core.constants.APIConstants.*;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */

@Component(service = Servlet.class, property = {
		"sling.servlet.description = JSON Servlet to read the data from the external webservice",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/services/fetchTrailors" })
public class FetchLatestTrailors extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(FetchLatestTrailors.class);

	@Reference
	private TrailerListService trailerListService;

	@Reference
	private FetchConfigValuesService configValuesService;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		String searchKey = getSearchField(req);
		String endPointURL = trailerListService.getApiConfigValues(PROPERTY_VALUE_ENDPOINT_URL);
		if (searchKey.equals(""))
			endPointURL = endPointURL + searchKey
					+ trailerListService.getApiConfigValues(PROPERTY_VALUE_SEARCH_KEY_DEFAULT_URL)
					+ trailerListService.getApiConfigValues(PROPERTY_VALUE_API_KEY) + "&" + PROPERTY_VALUE_MAX_RESULTS
					+ "=" + trailerListService.getApiConfigValues(PROPERTY_VALUE_MAX_RESULTS);
		else
//    	String convalue = configValuesService.getApiurl();
			endPointURL = endPointURL + searchKey
					+ trailerListService.getApiConfigValues(PROPERTY_VALUE_SEARCH_KEY_PARAM_URL)
					+ trailerListService.getApiConfigValues(PROPERTY_VALUE_API_KEY) + "&" + PROPERTY_VALUE_MAX_RESULTS
					+ "=" + trailerListService.getApiConfigValues(PROPERTY_VALUE_MAX_RESULTS);
		log.info("URL is" + endPointURL);
		String result = null;
		result = trailerListService.getData(endPointURL, "GET");

		resp.getWriter().write(result);
	}

	/**
	 * Gets the search field.
	 *
	 * @return the search field
	 */
	public String getSearchField(SlingHttpServletRequest req) {
		String searchField = "";
		searchField = req.getParameter("search_field");
		if (StringUtils.isBlank(searchField)) {
			searchField = "";
		}
		log.info("searchField"+searchField);
		return searchField;
	}

}
