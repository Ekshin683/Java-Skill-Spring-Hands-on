import React from 'react';

const books = [
  { id: 1, title: 'Clean Code',            author: 'Robert Martin', genre: 'Programming', available: true  },
  { id: 2, title: 'The Pragmatic Programmer', author: 'David Thomas', genre: 'Programming', available: false },
  { id: 3, title: 'Design Patterns',       author: 'Gang of Four',  genre: 'Architecture', available: true  },
  { id: 4, title: 'You Don\'t Know JS',    author: 'Kyle Simpson',  genre: 'JavaScript',   available: true  },
];

function BookDetails() {
  return (
    <div style={{ padding: '16px' }}>
      <h2>📚 Book Details</h2>

      {/* Conditional rendering Method 1: element variable */}
      {books.map(book => {

        // Element variable — set before return
        let availabilityBadge;
        if (book.available) {
          availabilityBadge = (
            <span style={{ backgroundColor: 'green', color: 'white',
                           padding: '2px 8px', borderRadius: '4px', fontSize: '12px' }}>
              Available
            </span>
          );
        } else {
          availabilityBadge = (
            <span style={{ backgroundColor: 'red', color: 'white',
                           padding: '2px 8px', borderRadius: '4px', fontSize: '12px' }}>
              Checked Out
            </span>
          );
        }

        return (
          <div key={book.id} style={{
            border: '1px solid #ddd', borderRadius: '8px',
            padding: '12px', marginBottom: '10px', backgroundColor: '#fff8f0'
          }}>
            {/* key prop: unique identifier — React uses this to track list items */}
            <h4 style={{ margin: '0 0 4px 0' }}>{book.title}</h4>
            <p style={{ margin: '2px 0', color: '#555' }}>
              Author: {book.author} | Genre: {book.genre}
            </p>
            {/* Render element variable */}
            {availabilityBadge}
          </div>
        );
      })}
    </div>
  );
}

export default BookDetails;