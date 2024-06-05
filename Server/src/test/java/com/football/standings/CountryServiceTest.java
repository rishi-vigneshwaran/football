/**
 * 
 */
package com.football.standings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import com.football.standings.models.CountryModel;
import com.football.standings.service.AuthService;
import com.football.standings.service.CountryService;

/**
 * @author VigneshwaranK
 *
 */
@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
	
    @Mock
    private AuthService authService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CountryService countryService;

    @Test
    public void testGetCountryList_Success() throws Exception {
        
        String mockToken = "valid-auth-token";
        Mockito.when(authService.getAuthenticationKey()).thenReturn(mockToken);

        List<CountryModel> mockCountries = Arrays.asList(new CountryModel(), new CountryModel());
        ResponseEntity<List<CountryModel>> mockResponse = new ResponseEntity<>(mockCountries, HttpStatus.OK);
        
        Mockito.when(restTemplate.exchange(
        	    "https://apiv3.apifootball.com/?action=get_countries&APIkey=valid-auth-token",
        	    HttpMethod.GET,
        	    null,
        	    new ParameterizedTypeReference<List<CountryModel>>() {}
        	)).thenReturn(mockResponse);
        
        List<CountryModel> countries = countryService.getCountryList();

        assertEquals(mockCountries, countries);
        Mockito.verify(authService, times(1)).getAuthenticationKey();

    }
}