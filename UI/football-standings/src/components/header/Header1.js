import { useEffect, useState } from "react";
import StandingsService from "./../../services/FootBallStandingService";

const Header = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchDataFromApi = async () => {
      try {
        const result = await StandingsService();
        setData(result);
      } catch (error) {
        console.log("There is some error:/");
      }
    };

    fetchDataFromApi();
  }, []);

  return (
    <>
      {" "}
      Header
      {data &&
        data.map((item, index) => (
          <div key={index}>
            <p>Country: {item.country_name}</p>
            <p>League ID: {item.league_id}</p>
            <p>League Name: {item.league_name}</p>
            {/* Render other properties as needed */}
          </div>
        ))}
    </>
  );
};

export default Header;
