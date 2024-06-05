import { urls, apiVersion, localhost } from "./../constants/url.constants";

const API_URL = localhost + apiVersion + urls.footballStandings;

async function StandingsService(league, teamId = null) {
  if (!league || !league.length) {
    throw new Error("League information is missing or invalid");
  }

  const url = `${API_URL}/${league}${
    teamId !== null ? `?teamName=${teamId}` : ""
  }`;

  console.log("URL constructed : " + url);

  try {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}

export default StandingsService;
