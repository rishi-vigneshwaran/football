/**
 * 
 */
package com.football.standings.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	
	public String getAuthenticationKey() {
		log.debug(ENTRY_MESSAGE);	
		
		log.info("API Key successfully fetched...");
		
		log.debug(EXIT_MESSAGE);
		return apiKey;
	}

}
