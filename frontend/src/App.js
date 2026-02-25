import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import React from 'react';
import { AuthProvider, AuthContext } from './components/AuthContext';
import Navigation from './components/Navigation';
import Login from './components/Login';
// import Users from './components/Users';
import Products from './components/Products';
import Cart from './components/Cart';

function PrivateRoute({ children }) {
  const { user } = React.useContext(AuthContext);
  return user ? children : <Navigate to="/login" />;
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        Spring Boot REST API Backend + React Boostrap Frontend
      </header>
      <AuthProvider>
        <Router>
          <Navigation />
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/products" element={<Products />} />
            <Route path="/cart" element={<PrivateRoute><Cart /></PrivateRoute>} />
          </Routes>
        </Router>
      </AuthProvider>
    </div>
  );
}

export default App;
