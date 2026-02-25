import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { Form, Button, Alert } from 'react-bootstrap';
import { AuthContext } from '../components/AuthContext';
import config from '../config';

const Login = () => {
  const { login } = useContext(AuthContext);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    setError('');

    fetch(`${config.API_BASE_URL}/users/auth/login?email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' }
    })
      .then((res) => {
        if (!res.ok) throw new Error('Invalid credentials');
        return res.json();
      })
      .then((data) => {
        login(data, email, password); // Store user in context/localStorage
        navigate('/products'); // Redirect after login
      })
      .catch((err) => setError(err.message));
  };

  return (
    <div className="container mt-5" style={{ maxWidth: '400px' }}>
      <h2>Login</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control 
            type="email" value={email} required
            onChange={(e) => setEmail(e.target.value)} />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control 
            type="password" value={password} required
            onChange={(e) => setPassword(e.target.value)} />
        </Form.Group>

        <Button variant="primary" type="submit">Login</Button>
      </Form>
    </div>
  );
};

export default Login;


