/**
 * 
 */
package com.football.standings;
import com.football.standings.exceptions.InternalException;
import com.football.standings.models.LeagueModel;
import com.football.standings.service.AuthService;
import com.football.standings.service.LeagueService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LeagueServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AuthService authService;

    @InjectMocks
    private LeagueService leagueService;

    @BeforeEach
    public void setup() {
        reset(restTemplate, authService);
    }

    @Test
    public void testGetLeagues_Success() throws InternalException {
        String mockToken = "valid-auth-token";
        when(authService.getAuthenticationKey()).thenReturn(mockToken);

        List<LeagueModel> mockLeagues = Arrays.asList(
                new LeagueModel("1", "Country 1", "123", "League 1", "Season 1", "League Logo 1", "Country Logo 1"),
                new LeagueModel("1", "Country 1", "456", "League 2", "Season 2", "League Logo 2", "Country Logo 1")
        );

        ResponseEntity<List<LeagueModel>> mockResponse = ResponseEntity.ok(mockLeagues);
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        List<LeagueModel> leagues = leagueService.getLeagues(789); // Pass your countryId here

        verify(authService, times(1)).getAuthenticationKey();
        verify(restTemplate, times(1)).exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        );

        assertEquals(mockLeagues, leagues);
    }

}