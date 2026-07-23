import React from 'react';
import '../Stylesheets/mystyle.css';

// Functional component that accepts props:
// name, school, total (array of marks), goal
function CalculateScore(props) {

  const { name, school, total, goal } = props;

  // Calculate average score from the total array
  const sum = total.reduce((acc, mark) => acc + mark, 0);
  const average = (sum / total.length).toFixed(2);

  // Check if goal is achieved
  const goalAchieved = parseFloat(average) >= goal;

  return (
    <div className="container">
      <h2>Student Score Calculator</h2>

      <p>
        <span className="label">Student Name : </span>
        {name}
      </p>

      <p>
        <span className="label">School : </span>
        {school}
      </p>

      <p>
        <span className="label">Marks : </span>
        {total.join(', ')}
      </p>

      <p>
        <span className="label">Goal : </span>
        {goal}%
      </p>

      <p className="score">
        Average Score : {average}%
      </p>

      <p style={{ textAlign: 'center', color: goalAchieved ? 'green' : 'red' }}>
        {goalAchieved
          ? '🎉 Goal Achieved!'
          : '❌ Goal Not Achieved. Keep trying!'}
      </p>
    </div>
  );
}

// Default props — used when parent doesn't pass values
CalculateScore.defaultProps = {
  name: 'Unknown',
  school: 'Unknown School',
  total: [0],
  goal: 50
};

export default CalculateScore;