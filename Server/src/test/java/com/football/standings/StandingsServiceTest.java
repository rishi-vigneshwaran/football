package com.football.standings;

import com.football.standings.exceptions.InternalException;
import com.football.standings.models.StandingsModel;
import com.football.standings.service.AuthService;
import com.football.standings.service.StandingsService;

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
public class StandingsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AuthService authService;

    @InjectMocks
    private StandingsService standingsService;

    @BeforeEach
    public void setup() {
        reset(restTemplate, authService);
    }

    @Test
    public void testGetStandings_WithTeamName() throws InternalException {
        String mockToken = "valid-auth-token";
        when(authService.getAuthenticationKey()).thenReturn(mockToken);

        List<StandingsModel> mockStandings = Arrays.asList(
                new StandingsModel("Country 1", "123", "League 1", "1", "Team 1", "Promotion", "1", "10", "7", "2", "1", "20", "10", "6", "4", "0", "10", "5", "5", "0", "15", "5", "3", "2", "0", "10", "5", "5", "0", "5", "5", "2", "1", "1", "10", "Badge 1"),
                new StandingsModel("Country 1", "123", "League 1", "2", "Team 2", "Promotion", "2", "10", "6", "3", "1", "18", "12", "6", "3", "0", "15", "6", "4", "2", "0", "12", "6", "2", "3", "1", "9", "6", "4", "1", "6", "6", "2", "2", "2", "8")
        );
        ResponseEntity<List<StandingsModel>> mockResponse = ResponseEntity.ok(mockStandings);
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(mockResponse);

        List<StandingsModel> standings = standingsService.getStandings(123, "Team 1");

        verify(authService, times(1)).getAuthenticationKey();
        verify(restTemplate, times(1)).exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        );

        assertEquals(1, standings.size());
        assertEquals("Team 1", standings.get(0).getTeamName());
    }

    // Add more test methods for other scenarios as needed
}
