import React from 'react';
import ListofPlayers from './components/ListofPlayers';
import IndianPlayers from './components/IndianPlayers';

function App() {

  // flag = true → show ListofPlayers
  // flag = false → show IndianPlayers
  const flag = true; // change to false to see IndianPlayers

  return (
    <div>
      <h1>Cricket App</h1>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;