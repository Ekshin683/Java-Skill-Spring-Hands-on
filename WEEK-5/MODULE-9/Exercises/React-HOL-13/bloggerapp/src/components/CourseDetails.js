import React from 'react';

const courses = [
  { id: 1, name: 'React Fundamentals',   duration: '4 weeks', level: 'Beginner',     enrolled: 320, status: 'active'    },
  { id: 2, name: 'Advanced React',       duration: '6 weeks', level: 'Advanced',     enrolled: 180, status: 'active'    },
  { id: 3, name: 'Node.js Backend',      duration: '8 weeks', level: 'Intermediate', enrolled: 250, status: 'completed' },
  { id: 4, name: 'Full Stack with MERN', duration: '12 weeks',level: 'Advanced',     enrolled: 410, status: 'active'    },
  { id: 5, name: 'Python Basics',        duration: '3 weeks', level: 'Beginner',     enrolled: 0,   status: 'upcoming'  },
];

// Separate component for level badge — component extracted with key
function LevelBadge({ level }) {
  const colors = {
    Beginner:     '#4CAF50',
    Intermediate: '#FF9800',
    Advanced:     '#f44336'
  };
  return (
    <span style={{
      backgroundColor: colors[level] || '#888',
      color: 'white',
      padding: '2px 10px',
      borderRadius: '12px',
      fontSize: '12px',
      fontWeight: 'bold'
    }}>
      {level}
    </span>
  );
}

function CourseDetails() {

  // Conditional rendering Method 4: prevent rendering with null
  // Only render courses that are not 'upcoming' (filter first)
  const visibleCourses = courses.filter(c => c.status !== 'upcoming');

  return (
    <div style={{ padding: '16px' }}>
      <h2>🎓 Course Details</h2>
      <p style={{ color: '#888', fontSize: '14px' }}>
        Showing {visibleCourses.length} of {courses.length} courses
        (upcoming courses hidden)
      </p>

      {visibleCourses.map(course => (
        <div key={course.id} style={{
          border: '1px solid #ddd', borderRadius: '8px',
          padding: '12px', marginBottom: '10px',
          backgroundColor: '#f9f0ff'
        }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <h4 style={{ margin: '0' }}>{course.name}</h4>
            {/* Extracted component with key passed from parent */}
            <LevelBadge level={course.level} />
          </div>

          <p style={{ margin: '6px 0', color: '#555' }}>
            ⏱ {course.duration} | 👥 {course.enrolled} enrolled
          </p>

          {/* Conditional rendering Method 2: ternary for status */}
          <p style={{ margin: '4px 0' }}>
            Status: &nbsp;
            <strong style={{
              color: course.status === 'active' ? 'green' : '#888'
            }}>
              {course.status === 'active' ? '🟢 Active' : '✅ Completed'}
            </strong>
          </p>

          {/* Conditional rendering Method 3: && — show only when enrolled > 300 */}
          {course.enrolled > 300 && (
            <p style={{ color: '#9C27B0', fontSize: '13px', margin: '4px 0' }}>
              🏆 Popular Course!
            </p>
          )}
        </div>
      ))}
    </div>
  );
}

export default CourseDetails;