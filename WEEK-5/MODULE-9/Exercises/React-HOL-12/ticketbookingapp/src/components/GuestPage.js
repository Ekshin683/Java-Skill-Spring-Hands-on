import React from 'react';
import FlightDetails from './FlightDetails';

function GuestPage({ onLogin }) {
  return (
    <div style={{ padding: '20px', maxWidth: '700px', margin: '0 auto' }}>
      <div style={{
        backgroundColor: '#fff3e0',
        padding: '16px',
        borderRadius: '8px',
        marginBottom: '20px',
        border: '1px solid #FFB74D'
      }}>
        <h2>👤 Guest User</h2>
        <p>You are browsing as a guest. Login to book tickets.</p>
        <button
          style={{
            backgroundColor: '#2196F3',
            color: 'white',
            padding: '10px 24px',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer',
            fontWeight: 'bold'
          }}
          onClick={onLogin}
        >
          Login to Book
        </button>
      </div>

      {/* Guest can VIEW flights but canBook=false hides the Book button */}
      <FlightDetails canBook={false} />
    </div>
  );
}

export default GuestPage;