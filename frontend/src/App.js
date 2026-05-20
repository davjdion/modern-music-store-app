import './App.css';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import React from 'react';
import { AuthProvider, AuthContext } from './components/AuthContext';
import Navigation from './components/Navigation';
import Login from './components/Login';
import Products from './components/Products';
import Cart from './components/Cart';
import Home from './Home';


function PrivateRoute({ children }) {
  const { user } = React.useContext(AuthContext);
  return user && user.id ? children : <Navigate to="/login" />;
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" viewBox="0 0 24 24"
             fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round"
             strokeLinejoin="round" style={{marginRight: '12px', verticalAlign: 'middle'}}>
          <path d="M3 12h2"/>
          <path d="M7 9v6"/>
          <path d="M11 6v12"/>
          <path d="M15 4v16"/>
          <path d="M19 8v8"/>
          <path d="M23 11h-2"/>
        </svg>
        Modern Music Equipment Store
      </header>

      <AuthProvider>
        <Router>
          <Navigation />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/products" element={<Products />} />
            <Route
              path="/cart"
              element={
                <PrivateRoute>
                  <Cart />
                </PrivateRoute>
              }
            />
          </Routes>
        </Router>
      </AuthProvider>
    </div>
  );
}

export default App;
