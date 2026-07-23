import React from 'react';
import FlightDetails from './FlightDetails';

function UserPage({ onLogout }) {
  return (
    <div style={{ padding: '20px', maxWidth: '700px', margin: '0 auto' }}>
      <div style={{
        backgroundColor: '#e8f5e9',
        padding: '16px',
        borderRadius: '8px',
        marginBottom: '20px',
        border: '1px solid #81C784'
      }}>
        <h2>✅ Welcome, User!</h2>
        <p>You are logged in. You can now book your tickets.</p>
        <button
          style={{
            backgroundColor: '#f44336',
            color: 'white',
            padding: '10px 24px',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer',
            fontWeight: 'bold'
          }}
          onClick={onLogout}
        >
          Logout
        </button>
      </div>

      {/* Logged-in user gets canBook=true — Book buttons appear */}
      <FlightDetails canBook={true} />
    </div>
  );
}

export default UserPage;