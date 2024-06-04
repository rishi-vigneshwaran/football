import "./Header.css";

const Header = () => {
  return (
    <div>
      <div className="header-main">
        <div className="header-container">
          <div className="header-right">
            <a href="/login" className="pad-40">
              Login
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Header;
