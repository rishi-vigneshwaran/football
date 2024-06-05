/**
 * 
 */
package com.football.standings;


import com.football.standings.exceptions.InternalException;
import com.football.standings.models.TeamModel;
import com.football.standings.service.AuthService;
import com.football.standings.service.TeamService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author VigneshwaranK
 *
 */
@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AuthService authService;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    public void setup() {
        reset(restTemplate, authService);
    }

    @Test
    public void testGetTeams_Success() throws InternalException {
        String mockToken = "valid-auth-token";
        when(authService.getAuthenticationKey()).thenReturn(mockToken);

        List<TeamModel> mockTeams = Arrays.asList(new TeamModel(), new TeamModel());
        ParameterizedTypeReference<List<TeamModel>> responseType = new ParameterizedTypeReference<List<TeamModel>>() {};
        ResponseEntity<List<TeamModel>> mockResponse = ResponseEntity.ok(mockTeams);
        Mockito.when(restTemplate.exchange(
        	    "https://apiv3.apifootball.com/?action=get_teams&league_id=123&APIkey=valid-auth-token",
        	    HttpMethod.GET,
        	    null,
        	    new ParameterizedTypeReference<List<TeamModel>>() {}
        	)).thenReturn(mockResponse);

        List<TeamModel> teams = teamService.getTeams(123); 

        verify(authService, times(1)).getAuthenticationKey(); 
        

        assertEquals(mockTeams, teams);
    }
}
