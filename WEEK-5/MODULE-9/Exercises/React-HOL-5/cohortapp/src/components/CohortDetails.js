import React from 'react';

// Import CSS Module — styles becomes an object
// styles.box → the locally-scoped "box" class
import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {

  // Inline style for <h3> — green when ongoing, blue otherwise
  // This is the inline styles approach — a JS object with camelCase properties
  const headingStyle = {
    color: cohort.status === 'ongoing' ? 'green' : 'blue'
  };

  return (
    // Apply the CSS Module class using className={styles.box}
    <div className={styles.box}>

      {/* Inline style applied via style prop */}
      <h3 style={headingStyle}>{cohort.name}</h3>

      {/* dl = description list, dt = term, dd = description */}
      <dl>
        <dt>Status</dt>
        <dd>{cohort.status}</dd>

        <dt>Start Date</dt>
        <dd>{cohort.startDate}</dd>

        <dt>End Date</dt>
        <dd>{cohort.endDate}</dd>

        <dt>Trainer</dt>
        <dd>{cohort.trainer}</dd>

        <dt>Participants</dt>
        <dd>{cohort.participants}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;