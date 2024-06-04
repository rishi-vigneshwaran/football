import React, { useState } from "react";
import "./ToggleButton.css";
import Icon from "./../../assets/WifiLogo";

export default function ToggleButton() {
  const [network, setNetwork] = useState(true);
  const handleToggle = () => {
    setNetwork((current) => {
      return !current;
    });
  };
  return (
    <div className="network-container">
      <div className="network-toggle">
        <div className="container">
          <div className="wifi-icon">
            <Icon fill={network ? "#bf1029" : "#056517"} />
          </div>
          <div className="toggle-switch">
            <input
              type="checkbox"
              className="checkbox"
              name="Network"
              id="Network"
              onClick={handleToggle}
            />
            <label className="label" htmlFor="Network">
              <span className="inner" />
              <span className="switch" />
            </label>
          </div>
        </div>
      </div>
    </div>
  );
}
