import { Card, Form, Button } from "react-bootstrap";
import { useState, useEffect } from "react";
import CountryService from "../../services/CountryService";
import TeamService from "../../services/TeamService";
import LeagueService from "../../services/LeagueService";
import StandingsTable from "../standingsTable/StandingsTable";
import "./FootBallStandings.css";

const FootBallStandings = () => {
  const [countryList, setCountryList] = useState([]);
  const [selectedCountry, setSelectedCountry] = useState("");
  const [leagueList, setLeagueList] = useState([]);
  const [selectedLeague, setSelectedLeague] = useState("");
  const [teamList, setTeamList] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState("");
  const [submission, setSubmission] = useState(false);

  useEffect(() => {
    const fetchCountries = async () => {
      try {
        const data = await CountryService();
        setCountryList(data);
      } catch (error) {
        console.error("Error fetching country data:", error);
      }
    };

    fetchCountries();
  }, []);

  useEffect(() => {
    const fetchLeagues = async () => {
      if (selectedCountry) {
        try {
          const data = await LeagueService(selectedCountry);
          setLeagueList(data);
        } catch (error) {
          console.error("Error fetching league data:", error);
        }
      }
    };

    fetchLeagues();
  }, [selectedCountry]);

  useEffect(() => {
    const fetchTeams = async () => {
      if (selectedLeague) {
        try {
          const data = await TeamService(selectedLeague);
          setTeamList(data);
        } catch (error) {
          console.error("Error fetching team data:", error);
        }
      }
    };

    fetchTeams();
  }, [selectedLeague]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      if (!selectedTeam) {
        throw new Error("Please select a team.");
      }
      setSubmission(true);
    } catch (error) {
      console.error("Error handling submission:", error);
    }
  };

  return (
    <>
      <div className="standings-form-container">
        <div>
          <Card className="standings-form">
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="country">
                <Form.Label>Country</Form.Label>
                <Form.Select
                  value={selectedCountry}
                  onChange={(event) => setSelectedCountry(event.target.value)}
                >
                  <option>Select Country</option>
                  {countryList.map((country) => (
                    <option key={country.country_id} value={country.country_id}>
                      {country.country_name}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>
              <Form.Group controlId="league">
                <Form.Label>League</Form.Label>
                <Form.Select
                  value={selectedLeague}
                  onChange={(event) => setSelectedLeague(event.target.value)}
                  disabled={!selectedCountry}
                >
                  <option>Select League</option>
                  {leagueList.map((league) => (
                    <option key={league.league_id} value={league.league_id}>
                      {league.league_name}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>
              <Form.Group controlId="team">
                <Form.Label>Team</Form.Label>
                <Form.Select
                  value={selectedTeam}
                  onChange={(event) => setSelectedTeam(event.target.value)}
                  disabled={!selectedLeague}
                >
                  <option>Select Team</option>
                  {teamList.map((team) => (
                    <option key={team.team_id} value={team.team_id}>
                      {team.team_name}
                    </option>
                  ))}
                </Form.Select>
              </Form.Group>
              <Button
                type="submit"
                className="submit-button"
                disabled={!selectedTeam || submission}
              >
                Submit
              </Button>
            </Form>
          </Card>
        </div>
      </div>
      {submission && (
        <StandingsTable team={selectedTeam} league={selectedLeague} />
      )}
    </>
  );
};

export default FootBallStandings;
