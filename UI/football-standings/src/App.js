import logo from "./logo.svg";
import "./App.css";
import Header from "./components/header/Header";
import FootBallStandings from "./components/forms/FootBallStandings";

function App() {
  return (
    <div>
      <Header></Header>
      <FootBallStandings className="football-standings"></FootBallStandings>
    </div>
  );
}

export default App;
