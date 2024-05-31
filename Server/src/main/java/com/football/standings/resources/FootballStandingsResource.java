/**
 * 
 */
package com.football.standings.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VigneshwaranK
 *
 */
@RestController
public class FootballStandingsResource {

	@GetMapping
	public static String getStandings() {
		return "Hello World!";
	}
}
