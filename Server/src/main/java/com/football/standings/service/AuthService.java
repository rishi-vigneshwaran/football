/**
 * 
 */
package com.football.standings.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.football.standings.exceptions.InternalException;
import com.football.utils.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author VigneshwaranK
 *
 */
@Service
@Slf4j
public class AuthService extends Constants{
	
	@Value("${encrypted.api.key}")
    private String apiKey;
	
	@Cacheable
	public String getAuthenticationKey() throws InternalException {
		log.debug(ENTRY_MESSAGE);	
		
		log.info("API Key successfully fetched...");
		
		if(apiKey == null || apiKey.length() == 0) throw new InternalException("AUTH-01", "Failed to retrieve authentication token");
		
		log.debug(EXIT_MESSAGE);
		return apiKey;
	}

}
