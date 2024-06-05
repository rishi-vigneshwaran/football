import { urls, apiVersion, localhost } from "./../constants/url.constants";

async function LeagueService(country) {
  if (!country || country.length === 0) {
    console.error("Invalid country data:", country);
    return [];
  }

  let url = localhost + apiVersion + urls.league + country;

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

export default LeagueService;
