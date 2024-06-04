package com.football.standings.models;

import java.util.List;

import lombok.Data;

@Data
public class TeamModel {
	
	private String team_key;
    private String team_name;
    private String team_country;
    private String team_founded;
    private String team_badge;
    private Venue venue;
    private List<Player> players;
    private List<Coach> coaches;
   
    @Data
    public static class Venue {
        private String venue_name;
        private String venue_address;
        private String venue_city;
        private String venue_capacity;
        private String venue_surface;

    }

    @Data
    public static class Player {
        private String player_key;
        private String player_id;
        private String player_image;
        private String player_name;
        private String player_number;
        private String player_country;
        private String player_type;
        private String player_age;
        private String player_match_played;
        private String player_goals;
        private String player_yellow_cards;
        private String player_red_cards;
        private String player_injured;
        private String player_substitute_out;
        private String player_substitutes_on_bench;
        private String player_assists;
        private String player_birthdate;
        private String player_is_captain;
        private String player_shots_total;
        private String player_goals_conceded;
        private String player_fouls_committed;
        private String player_tackles;
        private String player_blocks;
        private String player_crosses_total;
        private String player_interceptions;
        private String player_clearances;
        private String player_dispossesed;
        private String player_saves;
        private String player_inside_box_saves;
        private String player_duels_total;
        private String player_duels_won;
        private String player_dribble_attempts;
        private String player_dribble_succ;
        private String player_pen_comm;
        private String player_pen_won;
        private String player_pen_scored;
        private String player_pen_missed;
        private String player_passes;
        private String player_passes_accuracy;
        private String player_key_passes;
        private String player_woordworks;
        private String player_rating;

    }

    @Data
    public static class Coach {
        private String coach_name;
        private String coach_country;
        private String coach_age;

    }

}
