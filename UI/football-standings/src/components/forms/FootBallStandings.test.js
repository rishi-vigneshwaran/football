import React from "react";
import { render, fireEvent, waitFor, getByRole } from "@testing-library/react";
import { act } from "react";
import "@testing-library/jest-dom/extend-expect";
import FootBallStandings from "./FootBallStandings";
import CountryService from "../../services/CountryService";
import TeamService from "../../services/TeamService";
import LeagueService from "../../services/LeagueService";

jest.mock("../../services/CountryService");
jest.mock("../../services/TeamService");
jest.mock("../../services/LeagueService");

describe("FootBallStandings component", () => {
  const countryList = [
    { country_id: 1, country_name: "Country 1" },
    { country_id: 2, country_name: "Country 2" },
    { country_id: 3, country_name: "Country 3" },
  ];
  const leagueList = [{ league_id: 1, league_name: "League 1" }];
  const teamList = [{ team_id: 1, team_name: "Team 1" }];

  beforeEach(() => {
    CountryService.mockResolvedValue(countryList);
    LeagueService.mockResolvedValue(leagueList);
    TeamService.mockResolvedValue(teamList);
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it("renders correctly", async () => {
    let getByLabelText, getByText;

    await act(async () => {
      const renderResult = render(<FootBallStandings />);
      getByLabelText = renderResult.getByLabelText;
      getByText = renderResult.getByText;
    });

    expect(getByLabelText("Country")).toBeInTheDocument();
    expect(getByLabelText("League")).toBeInTheDocument();
    expect(getByLabelText("Team")).toBeInTheDocument();
  });

  it("country api should be invoked once component rendered", async () => {
    const { getByText, getByLabelText } = render(<FootBallStandings />);
    await act(async () => {
      fireEvent.change(getByLabelText("Country"), {
        target: { value: "Country 2" },
      });

      await waitFor(() => {
        expect(CountryService).toBeCalled();
      });
    });
  });

  it("disables submit button if no team selected", async () => {
    const { getByText, getByLabelText } = render(<FootBallStandings />);
    const submitButton = getByText("Submit");
    await act(async () => {
      fireEvent.change(getByLabelText("Country"), { target: { value: "1" } });
      fireEvent.change(getByLabelText("League"), { target: { value: "1" } });

      await waitFor(() => {
        expect(submitButton).toBeDisabled();
      });
    });
  });
});
