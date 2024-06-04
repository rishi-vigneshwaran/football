/**
 * 
 */
package com.football.standings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.football.standings.models.CountryModel;
import com.football.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author VigneshwaranK
 *
 */
@Service
@Slf4j
public class CountryService extends Constants{
	
	@Autowired
	AuthService authService;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<CountryModel> getCountryList() {

	    log.debug(ENTRY_MESSAGE);
	    
	    String authToken = authService.getAuthenticationKey();
	    String url = "https://apiv3.apifootball.com/?action=get_countries&APIkey=" + authToken;
	    
	    log.info("Constructed URL : " + url);
	    
	    ParameterizedTypeReference<List<CountryModel>> responseType = new ParameterizedTypeReference<List<CountryModel>>() {};
	    ResponseEntity<List<CountryModel>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
	    
	    log.info("Response received " + response.toString());
	    log.debug(EXIT_MESSAGE);
	    
	    return response.getBody();
	
		
		
	}
	

}
