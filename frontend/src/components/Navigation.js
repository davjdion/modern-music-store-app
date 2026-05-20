import React, { useContext } from 'react';
import { Navbar, Nav, Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from './AuthContext';

const Navigation = () => {
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <Navbar bg="light" expand="lg" className="px-5 app-navbar">
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link as={Link} to="/">Home</Nav.Link>
          {user && (
            <>
              <Nav.Link as={Link} to="/products">Products</Nav.Link>
              <Nav.Link as={Link} to="/cart">Cart</Nav.Link>
            </>
          )}
        </Nav>
        <Nav className="ms-auto">
          {user ? (
            <>
              <Navbar.Text className="me-2">
                Signed in as: <strong>{user.firstName || user.email}</strong>
              </Navbar.Text>
              <Button variant="outline-primary" onClick={handleLogout}>
                Logout
              </Button>
            </>
          ) : (
            <Button variant="primary" as={Link} to="/login">
              Login
            </Button>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Navigation;
