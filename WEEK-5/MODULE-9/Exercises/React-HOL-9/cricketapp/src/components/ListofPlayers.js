import React from 'react';

function ListofPlayers() {

  // ES6 array of 11 players with name and score
  const players = [
    { id: 1,  name: 'Virat Kohli',    score: 82 },
    { id: 2,  name: 'Rohit Sharma',   score: 65 },
    { id: 3,  name: 'KL Rahul',       score: 55 },
    { id: 4,  name: 'Shubman Gill',   score: 91 },
    { id: 5,  name: 'Shreyas Iyer',   score: 48 },
    { id: 6,  name: 'Hardik Pandya',  score: 73 },
    { id: 7,  name: 'Rishabh Pant',   score: 60 },
    { id: 8,  name: 'Ravindra Jadeja',score: 35 },
    { id: 9,  name: 'Kuldeep Yadav',  score: 20 },
    { id: 10, name: 'Mohammed Siraj',  score: 15 },
    { id: 11, name: 'Jasprit Bumrah',  score: 10 },
  ];

  // ES6 map() — display all players
  const allPlayersList = players.map(player => (
    <li key={player.id}>
      {player.name} — Score: {player.score}
    </li>
  ));

  // ES6 arrow function + filter() — players with score below 70
  const lowScorers = players.filter(player => player.score < 70);

  const lowScorersList = lowScorers.map(player => (
    <li key={player.id} style={{ color: 'red' }}>
      {player.name} — Score: {player.score}
    </li>
  ));

  return (
    <div style={{ padding: '20px' }}>
      <h2>List of All Players</h2>
      <ul>{allPlayersList}</ul>

      <h2>Players with Score Below 70</h2>
      <ul>{lowScorersList}</ul>
    </div>
  );
}

export default ListofPlayers;