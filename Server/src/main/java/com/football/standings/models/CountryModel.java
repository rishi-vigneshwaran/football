package com.football.standings.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CountryModel {

	@JsonProperty("country_id")
	private String countryId;
	@JsonProperty("country_name")
	private String countryName;
	@JsonProperty("country_logo")
	private String countryLogo;
}
