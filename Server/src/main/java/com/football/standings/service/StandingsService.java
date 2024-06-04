/**
 * 
 */
package com.football.standings.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.football.standings.models.StandingsModel;
import com.football.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author VigneshwaranK
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StandingsService extends Constants{
	
	@Autowired
	private final RestTemplate restTemplate;
	
	@Autowired
	private final AuthService authService;

	public List<StandingsModel> getStandings(Integer leagueId, String teamName) {
	    log.debug(ENTRY_MESSAGE);
	    
	    String authToken = authService.getAuthenticationKey();
	    String url = "https://apiv3.apifootball.com/?action=get_standings&league_id=" + leagueId + "&APIkey=" + authToken;
	    
	    log.info("Constructed URL : " + url);
	    
	    ParameterizedTypeReference<List<StandingsModel>> responseType = new ParameterizedTypeReference<List<StandingsModel>>() {};
	    ResponseEntity<List<StandingsModel>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
	    
	    log.info("Response received " + response.toString());
	    
	    List<StandingsModel> standings = response.getBody();
	    
	    if(teamName != null) {
	    	standings = standings.stream().filter((value) -> value.getTeamName().equals(teamName)).collect(Collectors.toList());
	    }
	    
	    log.debug(EXIT_MESSAGE);
	    
	    return standings;
	}

}
