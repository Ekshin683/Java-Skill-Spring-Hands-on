import React, { useState } from 'react';
import BookDetails from './components/BookDetails';
import BlogDetails from './components/BlogDetails';
import CourseDetails from './components/CourseDetails';

function App() {

  // State to track which tab is active
  const [activeTab, setActiveTab] = useState('books');

  const tabs = [
    { id: 'books',   label: '📚 Books'   },
    { id: 'blogs',   label: '📝 Blogs'   },
    { id: 'courses', label: '🎓 Courses' },
  ];

  // Conditional rendering Method 1: if/else with element variable
  let content;
  if (activeTab === 'books') {
    content = <BookDetails />;
  } else if (activeTab === 'blogs') {
    content = <BlogDetails />;
  } else {
    content = <CourseDetails />;
  }

  return (
    <div style={{ maxWidth: '750px', margin: '0 auto' }}>

      {/* Header */}
      <div style={{
        backgroundColor: '#1a237e', color: 'white',
        padding: '20px', textAlign: 'center'
      }}>
        <h1>📖 Blogger App</h1>
        <p>Browse Books, Blogs and Courses</p>
      </div>

      {/* Tab navigation — map() with key */}
      <div style={{ display: 'flex', borderBottom: '2px solid #ddd' }}>
        {tabs.map(tab => (
          <button
            key={tab.id}
            onClick={() => setActiveTab(tab.id)}
            style={{
              flex: 1,
              padding: '12px',
              border: 'none',
              borderBottom: activeTab === tab.id ? '3px solid #1a237e' : 'none',
              backgroundColor: activeTab === tab.id ? '#e8eaf6' : 'white',
              fontWeight: activeTab === tab.id ? 'bold' : 'normal',
              cursor: 'pointer',
              fontSize: '15px'
            }}
          >
            {tab.label}
          </button>
        ))}
      </div>

      {/* Render active component via element variable */}
      <div>{content}</div>
    </div>
  );
}

export default App;