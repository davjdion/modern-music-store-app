import React, { useState, useEffect, useContext } from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import config from '../config';
import { AuthContext } from '../components/AuthContext';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const { credentials, user } = useContext(AuthContext);

  useEffect(() => {
    const basicAuth = 'Basic ' + btoa(`${credentials.email}:${credentials.password}`);

    fetch(`${config.API_BASE_URL}/cart/user/${user.id}`, {
      method: 'GET',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': basicAuth
      }
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setCartItems(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching cart:', error);
        setLoading(false);
      });
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  const handleDelete = (itemId) => {
    const basicAuth = 'Basic ' + btoa(`${credentials.email}:${credentials.password}`);

    fetch(`${config.API_BASE_URL}/cart/${itemId}`, {
      method: 'DELETE',
      headers: {
        'Authorization': basicAuth
      }
    })
      .then((res) => {
        if (!res.ok) throw new Error('Failed to delete item');
        setCartItems(prev => prev.filter(item => item.id !== itemId));
      })
      .catch(err => {
        console.error(err);
        alert('Failed to delete item');
      });
  };

  if (loading) {
    return <p>Loading cart...</p>;
  }

  return (
    <div className="px-5 pt-3">
      <h1>Cart for {user.firstName} {user.lastName}</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Product</th>
            <th>Description</th>
            <th>Img</th>
            <th>Cnt</th>
            <th>UnitPrice</th>
            <th>TotalPrice</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((item) => (
            <tr>
              <td>{item.name}</td>
              <td>{item.description}</td>
              <td><img src={item.img} width="40" alt="" /></td>
              <td>{item.cnt}</td>
              <td>{item.unitPrice}</td>
              <td>{item.cnt * item.unitPrice}</td>
              <td>
                <Button 
                  variant="danger" 
                  size="sm"
                  onClick={() => handleDelete(item.id)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default Cart;