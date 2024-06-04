async function TeamService(league) {
  if (!league) {
    throw new Error("League parameter is missing.");
  }

  const url = `http://localhost:8080/api/v1/teams/${league}`;

  try {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json();

    return data;
  } catch (error) {
    console.error("Error fetching team data:", error);
    throw error;
  }
}

export default TeamService;
