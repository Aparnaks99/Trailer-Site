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

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.watchtrailer.core.services.FetchConfigValuesService;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */


@Component(service = FetchConfigValuesService.class,immediate = true)
//@Designate(ocd = ApiConfiguration.class)

	public class FetchConfigValuesServiceImpl implements FetchConfigValuesService {

	 private static final long serialVersionUID = 1L;
	  
		
		/**
		 * Logger
		 */
		private static final Logger log = LoggerFactory.getLogger(FetchConfigValuesServiceImpl.class);
		

		 // private String apiUrl;
		  
		//private ApiConfiguration config;
		  String response;
		  private String apiUrl=null;
		  
		  //private ApiConfiguration config;
		  
	   // @Property(label="API url",value = "https://www.googleapis.com/youtube/v3/search?part=snippet")
	    private static final String API_URL = "apiUrl.value";

		   
		 

		  public String getapiUrl()
		  {
		    return this.apiUrl;
		  }

		 // @Activate
		//  protected void activate(ApiConfiguration config)
		  {
			  log.info("[*** AEM ConfigurationService]: activating configuration service");
			//  apiUrl = config.getApiUrl();
		    //readProperties();
		  }


			@Override
		  public String readProperties(){
			  log.info("readProperties");
		  
			  try {
				  /**
				  * Reading values from the configuration
				  */
//				  boolean enable = configuration.enableConfig();
//				   apiUrl = config.getApiUrl();
//				  String server = configuration.getServer();
//				  String endpoint = configuration.getEndpoint();
//				  /**
//				  * Constructing the URL
//				  */
//				  String url = protocol + "://" + server + "/" + endpoint;
				  /**
				  * Make HTTP call only if "enable" is true
				  */
//				  if (enable) {
//				  /**
//				  * Making the actual HTTP call
//				  */
				   response = apiUrl;
				  /**
				  * Printing the response in the logs
				  */

				    log.info("response"+response);
				 
				
				  } catch (Exception e) {
					  log.error("exception"+e.getMessage());
					 
				  }
			return apiUrl;
			}

			@Override
			public String getApiurl() {
				// TODO Auto-generated method stub
				return apiUrl;
			}

		
		

	}

    
    
    
    

