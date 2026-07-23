import React from 'react';

// Flight data displayed to both guest and logged-in users
const flights = [
  { id: 1, from: 'Mumbai',   to: 'Delhi',     time: '06:00 AM', price: 4500,  seats: 45 },
  { id: 2, from: 'Chennai',  to: 'Bangalore', time: '08:30 AM', price: 2800,  seats: 30 },
  { id: 3, from: 'Delhi',    to: 'Kolkata',   time: '11:00 AM', price: 5200,  seats: 12 },
  { id: 4, from: 'Hyderabad',to: 'Mumbai',    time: '02:00 PM', price: 3900,  seats: 60 },
];

function FlightDetails({ canBook }) {
  return (
    <div>
      <h3>Available Flights</h3>
      {flights.map(flight => (
        <div key={flight.id} style={{
          border: '1px solid #ddd',
          borderRadius: '8px',
          padding: '16px',
          marginBottom: '12px',
          backgroundColor: '#f9f9f9'
        }}>
          <p>
            ✈️ <strong>{flight.from}</strong> → <strong>{flight.to}</strong>
            &nbsp; | &nbsp; 🕐 {flight.time}
            &nbsp; | &nbsp; 💺 {flight.seats} seats
            &nbsp; | &nbsp;
            <span style={{ color: 'green', fontWeight: 'bold' }}>
              ₹{flight.price}
            </span>
          </p>

          {/* Book button only shown to logged-in users */}
          {canBook && (
            <button
              style={{
                backgroundColor: '#4CAF50',
                color: 'white',
                padding: '8px 20px',
                border: 'none',
                borderRadius: '4px',
                cursor: 'pointer'
              }}
              onClick={() => alert(`Booking flight: ${flight.from} to ${flight.to}`)}
            >
              Book Ticket
            </button>
          )}
        </div>
      ))}
    </div>
  );
}

export default FlightDetails;