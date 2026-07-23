import React from 'react';

const blogs = [
  { id: 1, title: 'Getting Started with React',  author: 'Alice', date: '10-Jan-2024', likes: 245, featured: true  },
  { id: 2, title: 'Understanding React Hooks',    author: 'Bob',   date: '15-Jan-2024', likes: 189, featured: false },
  { id: 3, title: 'React vs Angular vs Vue',      author: 'Carol', date: '20-Jan-2024', likes: 312, featured: true  },
  { id: 4, title: 'State Management in React',    author: 'Dave',  date: '25-Jan-2024', likes: 98,  featured: false },
  { id: 5, title: 'React Performance Tips',       author: 'Eve',   date: '30-Jan-2024', likes: 156, featured: false },
];

function BlogDetails() {
  return (
    <div style={{ padding: '16px' }}>
      <h2>📝 Blog Details</h2>

      {blogs.map(blog => (
        <div key={blog.id} style={{
          border: '1px solid #ddd', borderRadius: '8px',
          padding: '12px', marginBottom: '10px',
          backgroundColor: '#f0f8ff',
          // Conditional rendering Method 2: inline ternary for style
          borderLeft: blog.featured ? '4px solid gold' : '4px solid #ddd'
        }}>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <h4 style={{ margin: '0 0 4px 0' }}>{blog.title}</h4>

            {/* Conditional rendering Method 3: && operator */}
            {/* Only renders when blog.featured is true */}
            {blog.featured && (
              <span style={{ color: 'gold', fontSize: '20px' }} title="Featured">⭐</span>
            )}
          </div>

          <p style={{ margin: '4px 0', color: '#555' }}>
            By {blog.author} | {blog.date}
          </p>

          <p style={{ margin: '4px 0' }}>
            👍 {blog.likes} likes
            {/* Conditional rendering Method 2: ternary for text */}
            &nbsp;— {blog.likes > 200 ? '🔥 Trending' : '📖 Regular'}
          </p>
        </div>
      ))}
    </div>
  );
}

export default BlogDetails;