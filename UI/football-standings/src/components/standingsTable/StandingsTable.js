import { Table } from "react-bootstrap";
import "./StandingsTable.css";
import { useState, useEffect } from "react";
import FootBallStandingService from "./../../services/FootBallStandingService";

const StandingsTable = ({ league, team }) => {
  const [standings, setStandings] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        console.log("Team : " + team);
        const standingsData = await FootBallStandingService(league, team);
        setStandings(standingsData);
      } catch (error) {
        console.error("Error fetching country data:", error);
      }
    };

    fetchData();
    console.log("Standings inside table List : " + standings);
  }, []);

  return (
    <>
      <Table striped bordered hover variant="dark" className="table-container">
        <thead>
          <tr>
            <th>Position</th>
            <th>Team Name</th>
            <th>P</th>
            <th>W</th>
            <th>D</th>
            <th>L</th>
            <th>GF</th>
            <th>GA</th>
            <th>Pts</th>
          </tr>
        </thead>
        <tbody>
          {standings.map((standing, index) => (
            <tr key={index}>
              <td>{standing.overall_league_position}</td>
              <td>{standing.team_name}</td>
              <td>{standing.overall_league_payed}</td>
              <td>{standing.overall_league_W}</td>
              <td>{standing.overall_league_D}</td>
              <td>{standing.overall_league_L}</td>
              <td>{standing.overall_league_GF}</td>
              <td>{standing.overall_league_GA}</td>
              <td>{standing.overall_league_PTS}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
};

export default StandingsTable;
