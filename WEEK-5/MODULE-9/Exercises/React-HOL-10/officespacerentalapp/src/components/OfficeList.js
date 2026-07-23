import React from 'react';

function OfficeList() {

  // Single office object — JSX displaying object properties
  const office = {
    name: 'Tech Park Office',
    rent: 75000,
    address: '123 Tech Park, Whitefield, Bangalore'
  };

  // List of office objects — looped with map()
  const officeSpaces = [
    { id: 1, name: 'Downtown Office',   rent: 55000, address: '10 MG Road, Bangalore' },
    { id: 2, name: 'Tech Park Office',  rent: 75000, address: '123 Tech Park, Whitefield' },
    { id: 3, name: 'Airport Office',    rent: 90000, address: '45 Airport Road, Chennai' },
    { id: 4, name: 'Budget Space',      rent: 40000, address: '7 Old Town, Pune' },
    { id: 5, name: 'Premium Hub',       rent: 120000, address: '1 Cyber City, Hyderabad' },
  ];

  // Inline image style
  const imgStyle = {
    width: '100%',
    maxWidth: '600px',
    borderRadius: '10px',
    marginBottom: '20px'
  };

  // Page heading style
  const headingStyle = {
    backgroundColor: '#2c3e50',
    color: 'white',
    padding: '20px',
    textAlign: 'center',
    borderRadius: '8px',
    marginBottom: '20px'
  };

  // Card style
  const cardStyle = {
    border: '1px solid #ddd',
    borderRadius: '8px',
    padding: '16px',
    marginBottom: '12px',
    backgroundColor: '#f9f9f9',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
  };

  return (
    <div style={{ maxWidth: '700px', margin: '20px auto', padding: '0 20px' }}>

      {/* JSX heading element */}
      <h1 style={headingStyle}>Office Space Rental App</h1>

      {/* JSX image attribute */}
      <img
        src="https://images.unsplash.com/photo-1497366216548-37526070297c?w=600"
        alt="Office Space"
        style={imgStyle}
      />

      {/* Single office object display */}
      <h2>Featured Office</h2>
      <div style={cardStyle}>
        <h3>{office.name}</h3>
        {/* Inline CSS: rent colour based on value */}
        <p>
          Rent: &nbsp;
          <span style={{ color: office.rent < 60000 ? 'red' : 'green',
                         fontWeight: 'bold', fontSize: '18px' }}>
            ₹{office.rent.toLocaleString()}
          </span>
        </p>
        <p>Address: {office.address}</p>
      </div>

      {/* List of office objects using map() */}
      <h2>All Available Office Spaces</h2>
      {officeSpaces.map(space => (
        <div key={space.id} style={cardStyle}>
          <h3>{space.name}</h3>
          <p>
            Rent: &nbsp;
            {/* Red if below 60000, Green if 60000 or above */}
            <span style={{
              color: space.rent < 60000 ? 'red' : 'green',
              fontWeight: 'bold',
              fontSize: '18px'
            }}>
              ₹{space.rent.toLocaleString()}
            </span>
            &nbsp;
            <small style={{ color: '#888' }}>
              {space.rent < 60000 ? '(Budget)' : '(Premium)'}
            </small>
          </p>
          <p>📍 {space.address}</p>
        </div>
      ))}
    </div>
  );
}

export default OfficeList;