async function LeagueService(country) {
  if (!country || country.length === 0) {
    console.error("Invalid country data:", country);
    return [];
  }

  let url = "http://localhost:8080/api/v1/league/" + country;

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
