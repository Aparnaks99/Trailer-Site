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
package com.watchtrailer.core.services.impl;

import static com.watchtrailer.core.constants.APIConstants.API_PROPERTY_PATH;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watchtrailer.core.services.TrailerListService;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */


@Component(immediate = true, service = TrailerListService.class)

	public class TrailerListServiceImpl implements TrailerListService {

	 private static final long serialVersionUID = 10L;
	  
		
		/**
		 * Logger
		 */
		private static final Logger log = LoggerFactory.getLogger(TrailerListServiceImpl.class);
		
		@Reference
		private ResourceResolverFactory resourceResolverFactory;

		/**
		 * Overridden method which will read the JSON data via an HTTP GET call
		 */
		@Override
		public String getData(String targetURL, String httpMethod) {
			
			URL url;
			HttpURLConnection connection = null; 
			StringBuffer response = new StringBuffer();
			try {
					url = new URL(targetURL); 
					if (httpMethod == "GET") {
						url = new URL(url.toString());
					}
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestProperty("accept", "application/json"); 
					InputStream is = connection.getInputStream();
					BufferedReader rd = new BufferedReader(new InputStreamReader(is));
					String line;
					while ((line = rd.readLine()) != null) {
						response.append(line);
						response.append('\r');
					}
					log.info("Value response "+response.toString());
					rd.close();
			} catch (Exception e) {
				log.info("Exception" +e.getMessage());
			}
			finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
			JSONArray Apiresponse = filterData(response.toString());
			return Apiresponse.toString();
			
		}


		/** Filter Data
		 * @param data
		 * @return finalArray
		 */
		private JSONArray filterData(String data) {
			
			JSONObject jsonObj;
			JSONArray finalArray = new JSONArray();
			String name,id,imagePath,comments;
			try {
				
				jsonObj = new JSONObject(data);
				JSONArray arr =jsonObj.getJSONArray("items");
				for(int i=0; i <= arr.length(); i++)   
				{  
					JSONObject jsobject = arr.getJSONObject(i);  
					name = jsobject.getJSONObject("snippet").getString("title");
					id = jsobject.getJSONObject("id").getString("videoId"); 
					JSONObject url = jsobject.getJSONObject("snippet").getJSONObject("thumbnails"); 
					imagePath=url.getJSONObject("medium").getString("url");
					//To Do : Fetch each video ID and call youtube data api again to get individual comments
					Random rand = new Random(); 
					int randomNum = rand.nextInt((20 - 6) + 1) + 6;
					comments = randomNum+"";
					JSONObject finalObject = new JSONObject();
					finalObject.put("name", name);
					finalObject.put("imagePath", imagePath);
					finalObject.put("comments", comments);
					finalObject.put("id", id);
					finalArray.put(finalObject);
					log.info("finalArray "+finalArray);
					log.info("finalArray "+finalArray.length());
				}
				
			} catch (JSONException e) {
				log.info("JSONException "+e.getMessage());
			}
			return finalArray;
				
		}


		/**
		 * Get ApiConfigValues
		 * @param Property Name
		 * @return Property value
		 */
		@Override
		public String getApiConfigValues(String PropName) {
			ResourceResolver resolver = getResourceResolver();
			if(resolver!=null) {	
				return getPropertyValue(resolver,API_PROPERTY_PATH,PropName);
			}
			return null;
			
			
		}


		/** Get ResourceResolver
		 * @return ResourceResolver
		 */
		private ResourceResolver getResourceResolver() {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put(ResourceResolverFactory.SUBSERVICE,"readService");
			try {
				return resourceResolverFactory.getServiceResourceResolver(param);
			} catch (LoginException e) {
				log.error("login Exception in getting resource resolver:{}",e.getMessage(),e);
				
			}
			return null;
		}
		
		
		/**
		 * Get PropertyValue
		 * @param resolver
		 * @param configPath
		 * @param propName
		 * @return Property value
		 */
		private String getPropertyValue(ResourceResolver resolver,String configPath,String propName) {
			Resource propertyResource = resolver.getResource(configPath);
			
			if(propertyResource!=null) {
				ValueMap propertyValues = propertyResource.adaptTo(ValueMap.class);
				if(propertyValues!=null && propertyValues.containsKey(propName)) {
					return propertyValues.get(propName).toString();
				}
				
			}
			return null;		
			
		}
		
		
}
	

    
    
    
    

