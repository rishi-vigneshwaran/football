import React from "react";
import { Navbar, Container, Nav } from "react-bootstrap";

function Header() {
  return (
    <>
      <Navbar expand="lg" bg="primary" variant="dark">
        <Container>
          <Navbar.Brand href="#home">Standings</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav"></Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
}

export default Header;
