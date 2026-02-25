import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState({});
  const [credentials, setCredentials] = useState({ email: '', password: '' });

  useEffect(() => {
    const savedUser = JSON.parse(localStorage.getItem('user'));
    const savedCreds = JSON.parse(localStorage.getItem('credentials'));
    if (savedUser) setUser(savedUser);
    if (savedCreds) setCredentials(savedCreds);
  }, []);

  const login = (userData, email, password) => {
    setUser(userData);
    setCredentials({ email, password });
    localStorage.setItem('user', JSON.stringify(userData));
    localStorage.setItem('credentials', JSON.stringify({ email, password }));
  };

  const logout = () => {
    setUser({});
    setCredentials({ email: '', password: '' });
    localStorage.removeItem('user');
    localStorage.removeItem('credentials');
  };

  return (
    <AuthContext.Provider value={{ user, credentials, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};