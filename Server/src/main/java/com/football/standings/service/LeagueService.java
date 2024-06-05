/**
 * 
 */
package com.football.standings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.football.standings.exceptions.InternalException;
import com.football.standings.models.LeagueModel;
import com.football.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author VigneshwaranK
 *
 */
@Service
@Slf4j
public class LeagueService extends Constants {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AuthService authService;
	
	@Cacheable
	public List<LeagueModel> getLeagues(Integer countryId) throws InternalException {
		
		log.debug(ENTRY_MESSAGE);
	    
	    String authToken = authService.getAuthenticationKey();
	    
	    try {
	    	
	    String url = "https://apiv3.apifootball.com/?action=get_leagues&country_id=" + countryId + "&APIkey=" + authToken;
	    
	    log.info("Constructed URL : " + url);
	    
	    ParameterizedTypeReference<List<LeagueModel>> responseType = new ParameterizedTypeReference<List<LeagueModel>>() {};
	    ResponseEntity<List<LeagueModel>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
	    
	    log.info("Response received " + response.toString());
	    log.debug(EXIT_MESSAGE);
	    
	    return response.getBody();
	    
	    }catch(Exception exception) {
	    	throw new InternalException("LEAG-01", "Failed to retrieve League data");
	    }
		
	}
}
