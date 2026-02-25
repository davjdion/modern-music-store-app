import React, { useState, useEffect, useContext } from 'react';
import { Button } from 'react-bootstrap';
import Table from 'react-bootstrap/Table';
import { AuthContext } from '../components/AuthContext';
import config from '../config';


const Products = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [buyingId, setBuyingId] = useState(null);
  const { credentials, user } = useContext(AuthContext);

  useEffect(() => {
    // Fetch data from the backend API
    fetch(`${config.API_BASE_URL}/products/with-stock`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setProducts(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching products:', error);
        setLoading(false);
      });
  }, []);

  const handleBuy = (productId) => {
    setBuyingId(productId);

    const basicAuth = 'Basic ' + btoa(`${credentials.email}:${credentials.password}`);

    fetch(`${config.API_BASE_URL}/cart`, {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': basicAuth
      },
      body: JSON.stringify({ productId: productId, userId: user.id, cnt: 1 })
    })
      .then((res) => {
        if (!res.ok) throw new Error('Failed to add product to cart');
        return res.json();
      })
      .then((data) => {
        console.log('Product added to cart:', data);
        alert('Product added to cart!');
      })
      .catch((err) => {
        console.error(err);
        alert('Failed to add to cart.');
      })
      .finally(() => setBuyingId(null));
  };

  if (loading) {
    return <p>Loading products...</p>;
  }

  return (
    <div className="px-5 pt-3">
      <h1>Products</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Img</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.description}</td>
              <td><img src={product.img} width="100" alt="" /></td>
              <td>{product.unitPrice}</td>
              <td>{product.cnt}</td>
              <td>
                <Button
                  variant="primary"
                  size="sm"
                  disabled={buyingId === product.id}
                  onClick={() => handleBuy(product.id)}
                >
                  {buyingId === product.id ? 'Buying...' : 'Buy'}
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default Products;