import React from 'react';

function IndianPlayers() {

  // ES6 Destructuring — split into odd and even indexed players
  const teamPlayers = [
    'Virat Kohli',
    'Rohit Sharma',
    'KL Rahul',
    'Shubman Gill',
    'Shreyas Iyer',
    'Hardik Pandya',
    'Rishabh Pant',
    'Ravindra Jadeja',
    'Kuldeep Yadav',
    'Mohammed Siraj',
    'Jasprit Bumrah'
  ];

  // Destructuring: extract odd and even index players
  const [first, second, third, fourth, fifth,
         sixth, seventh, eighth, ninth, tenth, eleventh] = teamPlayers;

  const oddTeam  = [first, third, fifth, seventh, ninth, eleventh];
  const evenTeam = [second, fourth, sixth, eighth, tenth];

  // ES6 Spread operator — merge two arrays
  const T20players = ['Virat Kohli', 'Rohit Sharma', 'KL Rahul'];
  const RanjiTrophyPlayers = ['Shubman Gill', 'Shreyas Iyer', 'Hardik Pandya'];

  // Spread: merge both arrays into one
  const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div style={{ padding: '20px' }}>

      <h2>Odd Team Players</h2>
      <ul>
        {oddTeam.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h2>Even Team Players</h2>
      <ul>
        {evenTeam.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h2>Merged Players (T20 + Ranji Trophy)</h2>
      <ul>
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;