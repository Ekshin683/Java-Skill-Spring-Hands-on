import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div>
      {/* Pass name, school, total marks array, and goal percentage */}
      <CalculateScore
        name="Alice Johnson"
        school="Green Valley High School"
        total={[85, 90, 78, 92, 88]}
        goal={80}
      />

      <CalculateScore
        name="Bob Smith"
        school="Sunrise Academy"
        total={[55, 60, 45, 70, 50]}
        goal={70}
      />
    </div>
  );
}

export default App;