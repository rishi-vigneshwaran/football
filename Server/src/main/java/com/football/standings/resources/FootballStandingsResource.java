/**
 * 
 */
package com.football.standings.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.football.standings.models.CountryModel;
import com.football.standings.models.LeagueModel;
import com.football.standings.models.StandingsModel;
import com.football.standings.models.TeamModel;
import com.football.standings.service.CountryService;
import com.football.standings.service.LeagueService;
import com.football.standings.service.StandingsService;
import com.football.standings.service.TeamService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

/**
 * @author VigneshwaranK
 *
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FootballStandingsResource {
	
	@Autowired
	private final StandingsService standingsService;
	
	@Autowired
	private final CountryService countryService;
	
	@Autowired
	private final LeagueService leagueService;
	
	@Autowired
	private final TeamService teamService;
	
	@GetMapping("/standings/{leagueId}")
	public ResponseEntity<List<StandingsModel>> getStandings(@PathVariable Integer leagueId, @PathParam(value = "teamName") String teamName) {
		List<StandingsModel> response = standingsService.getStandings(leagueId, teamName);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/country")
	public ResponseEntity<List<CountryModel>> getCountry() {
		List<CountryModel> response = countryService.getCountryList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/league/{countryId}")
	public ResponseEntity<List<LeagueModel>> getLeague(@PathVariable Integer countryId) {
		List<LeagueModel> response = leagueService.getLeagues(countryId);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/teams/{leagueId}")
	public ResponseEntity<List<TeamModel>> getTeam(@PathVariable Integer leagueId) {
		List<TeamModel> response = teamService.getTeams(leagueId);
		
		return ResponseEntity.ok(response);
	}
}
