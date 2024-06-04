import { render, screen } from "@testing-library/react";
import Header from "./Header";
import { act } from "react-dom/test-utils";

// Mock the StandingsService function
jest.mock("./../../services/FootBallStandingService", () => ({
  __esModule: true,
  default: jest.fn().mockResolvedValue([
    {
      country_name: "England",
      league_id: "152",
      league_name: "Premier League",
      team_id: "141",
      team_name: "Arsenal",
      overall_promotion: "Promotion - Champions League (Group Stage: )",
      overall_league_position: "1",
      overall_league_payed: "0",
      overall_league_W: "0",
      overall_league_D: "0",
      overall_league_L: "0",
      overall_league_GF: "0",
      overall_league_GA: "0",
      overall_league_PTS: "0",
      home_league_position: "1",
      home_promotion: "",
      home_league_payed: "0",
      home_league_W: "0",
      home_league_D: "0",
      home_league_L: "0",
      home_league_GF: "0",
      home_league_GA: "0",
      home_league_PTS: "0",
      away_league_position: "1",
      away_promotion: "",
      away_league_payed: "0",
      away_league_W: "0",
      away_league_D: "0",
      away_league_L: "0",
      away_league_GF: "0",
      away_league_GA: "0",
      away_league_PTS: "0",
      league_round: "",
      team_badge: "https://apiv3.apifootball.com/badges/141_arsenal.jpg",
      fk_stage_key: "6",
      stage_name: "Current",
    },
  ]),
}));

test("renders Header", async () => {
  await act(async () => {
    render(<Header />);
  });
  const linkElement = screen.getByText(/Standings/);
  expect(linkElement).toBeInTheDocument();
});
