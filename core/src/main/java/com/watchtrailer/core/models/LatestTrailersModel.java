package com.watchtrailer.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class LatestTrailersModel.
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LatestTrailersModel {

	/** The request. */
	@ScriptVariable
	private SlingHttpServletRequest request;

	/**
	 * Gets the trailer list.
	 *
	 * @return the trailer list
	 */
	private List<TrailerDetail> getTrailerList() {
		List<TrailerDetail> searchedTrailerList = new ArrayList<>();
		try {
			String trailerListStr = "[{\"name\":\"F8\",\"imagepath\":\"/content/dam/watchtrailer/f8.jpg\",\"comments\":\"12\",\"id\":\"JwMKRevYa_M\"},{\"name\":\"FistFight\",\"imagepath\":\"/content/dam/watchtrailer/fistfight.jpg\",\"comments\":\"13\",\"id\":\"9aIzXYo6VCE\"},{\"name\":\"TheMummy\",\"imagepath\":\"/content/dam/watchtrailer/themummy.jpg\",\"comments\":\"11\",\"id\":\"IjHgzkQM2Sg\"},{\"name\":\"GuardiansoftheGalaxy2\",\"imagepath\":\"/content/dam/watchtrailer/guardiansofthegalaxy2.jpg\",\"comments\":\"12\",\"id\":\"dW1BI8Osg\"},{\"name\":\"Logan\",\"imagepath\":\"/content/dam/watchtrailer/logan.jpg\",\"comments\":\"10\",\"id\":\"Div0iP65aZo\"},{\"name\":\"WonderWomen\",\"imagepath\":\"/content/dam/watchtrailer/wonderwomen.jpg\",\"comments\":\"16\",\"id\":\"VSB4wGIdDwo\"},{\"name\":\"thedarktower\",\"imagepath\":\"/content/dam/watchtrailer/thedarktower.jpg\",\"comments\":\"15\",\"id\":\"GjwfqXTebIY\"},{\"name\":\"Cars3\",\"imagepath\":\"/content/dam/watchtrailer/cars3.jpg\",\"comments\":\"10\",\"id\":\"2LeOH9AGJQM\"},{\"name\":\"KUNGFUPANDA3\",\"imagepath\":\"/content/dam/watchtrailer/kungfupanda3.jpg\",\"comments\":\"09\",\"id\":\"10r9ozshGVE\"},{\"name\":\"poc\",\"imagepath\":\"/content/dam/watchtrailer/poc.jpg\",\"comments\":\"07\",\"id\":\"Hgeu5rhoxxY\"},{\"name\":\"Captainunderpants\",\"imagepath\":\"/content/dam/watchtrailer/captainunderpants.jpg\",\"comments\":\"13\",\"id\":\"E_xlMWImhxk\"},{\"name\":\"beautyandthebeast\",\"imagepath\":\"/content/dam/watchtrailer/beautyandthebeast.jpg\",\"comments\":\"15\",\"id\":\"OvW_L8sTu5E\"},{\"name\":\"KingArthur\",\"imagepath\":\"/content/dam/watchtrailer/kingarthur.jpg\",\"comments\":\"11\",\"id\":\"4luDtkC3Oy0\"},{\"name\":\"SucideSquad\",\"imagepath\":\"/content/dam/watchtrailer/sucidesquad.jpg\",\"comments\":\"13\",\"id\":\"CmRih_VtVAs\"},{\"name\":\"TheMartin\",\"imagepath\":\"/content/dam/watchtrailer/themartin.jpg\",\"comments\":\"10\",\"id\":\"ej3ioOneTy8\"},{\"name\":\"spidermanhomecoming\",\"imagepath\":\"/content/dam/watchtrailer/spidermanhomecoming.jpg\",\"comments\":\"13\",\"id\":\"DiTECkLZ8HM\"},{\"name\":\"warofape\",\"imagepath\":\"/content/dam/watchtrailer/warofape.jpg\",\"comments\":\"11\",\"id\":\"JDcAlo8i2y8\"},{\"name\":\"kingsman2\",\"imagepath\":\"/content/dam/watchtrailer/kingsman2.jpg\",\"comments\":\"10\",\"id\":\"6Nxc-3WpMbg\"}]";
			Gson gson = new Gson();
			List<TrailerDetail> trailerList = new ArrayList<>();
			trailerList = Arrays.asList(gson.fromJson(trailerListStr, TrailerDetail[].class));
			String searchField = getSearchField();
			if (StringUtils.isNotBlank(searchField)) {
				for (TrailerDetail trailerDetail : trailerList) {
					if (trailerDetail.getName().toLowerCase().contains(searchField.toLowerCase())) {
						searchedTrailerList.add(trailerDetail);
					}
				}
			} else {
				searchedTrailerList = trailerList;
			}
			for (int i = 1; i <= searchedTrailerList.size(); i++) {
				searchedTrailerList.get(i - 1).setModuloVal(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchedTrailerList;
	}

	/**
	 * Gets the adjusted trailer list.
	 *
	 * @return the adjusted trailer list
	 */
	public List<List<TrailerDetail>> getAdjustedTrailerList() {
		List<List<TrailerDetail>> adjustedTrailerList = new ArrayList<>();
		List<TrailerDetail> trailerList = getTrailerList();
		for (int i = 0; i < trailerList.size(); i = i + 6) {
			Integer finalIndex = trailerList.size() > i + 6 ? i + 6 : trailerList.size();
			adjustedTrailerList.add(trailerList.subList(i, finalIndex));
		}
		return adjustedTrailerList;

	}

	/**
	 * Gets the search field.
	 *
	 * @return the search field
	 */
	public String getSearchField() {
		String searchField = "";
		searchField = request.getParameter("search_field");
		if (StringUtils.isBlank(searchField)) {
			searchField = "";
		}
		return searchField;
	}

}
