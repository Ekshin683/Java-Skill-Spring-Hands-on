import React, { useState } from 'react';
import GuestPage from './components/GuestPage';
import UserPage from './components/UserPage';

function App() {

  // State controls which page is shown
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin  = () => setIsLoggedIn(true);
  const handleLogout = () => setIsLoggedIn(false);

  return (
    <div>
      <div style={{
        backgroundColor: '#1a237e',
        color: 'white',
        padding: '16px 20px',
        textAlign: 'center'
      }}>
        <h1>✈️ Ticket Booking App</h1>
        <p>Status: {isLoggedIn ? '🟢 Logged In' : '🔴 Guest'}</p>
      </div>

      {/* Conditional rendering — ternary operator */}
      {isLoggedIn
        ? <UserPage  onLogout={handleLogout} />
        : <GuestPage onLogin={handleLogin}  />
      }
    </div>
  );
}

export default App;