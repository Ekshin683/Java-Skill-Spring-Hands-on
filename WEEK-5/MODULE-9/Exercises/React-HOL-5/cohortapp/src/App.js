import React from 'react';
import CohortDetails from './components/CohortDetails';

// Sample cohort data — mix of ongoing and completed
const cohorts = [
  {
    id: 1,
    name: 'React Batch 2024 A',
    status: 'ongoing',
    startDate: '01-Jan-2024',
    endDate: '31-Mar-2024',
    trainer: 'Alice Johnson',
    participants: 25
  },
  {
    id: 2,
    name: 'Java Spring Batch',
    status: 'completed',
    startDate: '01-Oct-2023',
    endDate: '31-Dec-2023',
    trainer: 'Bob Smith',
    participants: 30
  },
  {
    id: 3,
    name: 'React Batch 2024 B',
    status: 'ongoing',
    startDate: '15-Feb-2024',
    endDate: '15-May-2024',
    trainer: 'Carol White',
    participants: 20
  },
  {
    id: 4,
    name: 'Python Full Stack',
    status: 'completed',
    startDate: '01-Jul-2023',
    endDate: '30-Sep-2023',
    trainer: 'Dave Brown',
    participants: 28
  }
];

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <h1>My Academy — Cohort Dashboard</h1>
      <p>Displaying ongoing and completed cohorts</p>

      <div>
        {cohorts.map(cohort => (
          <CohortDetails key={cohort.id} cohort={cohort} />
        ))}
      </div>
    </div>
  );
}

export default App;