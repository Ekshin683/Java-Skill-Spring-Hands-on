import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {

  // Step 1: constructor — initialise state with an empty posts array
  constructor(props) {
    super(props);
    this.state = {
      posts: [],        // will hold the fetched Post objects
      loading: true,    // shows a loading message while fetching
      error: null       // holds any error message
    };
  }

  // Step 3: loadPosts — fetches data from the API using Fetch API
  // Creates Post instances from the JSON response
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts. Status: ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        // Map JSON array to Post objects and take first 10
        const posts = data.slice(0, 10).map(
          item => new Post(item.id, item.title, item.body)
        );
        // Update state — triggers a re-render
        this.setState({ posts, loading: false });
      })
      .catch(error => {
        this.setState({ error: error.message, loading: false });
      });
  }

  // Step 2: componentDidMount — called once after the component
  // is inserted into the DOM.
  // Best place to make API calls because the component is ready.
  componentDidMount() {
    this.loadPosts();
  }

  // Step 4: componentDidCatch — catches any JavaScript error
  // that happens in any child component during rendering.
  // Like a try/catch block but for the component tree.
  componentDidCatch(error, info) {
    alert('An error occurred: ' + error.message);
    console.error('Error info:', info);
  }

  // Step 5: render — displays the posts
  render() {
    const { posts, loading, error } = this.state;

    // Show loading message while data is being fetched
    if (loading) {
      return <p>Loading posts...</p>;
    }

    // Show error if fetch failed
    if (error) {
      return <p style={{ color: 'red' }}>Error: {error}</p>;
    }

    return (
      <div style={{ maxWidth: '800px', margin: '20px auto', padding: '0 20px' }}>
        <h1>Blog Posts</h1>
        <p>Showing {posts.length} posts from JSONPlaceholder API</p>

        {posts.map(post => (
          <div
            key={post.id}
            style={{
              border: '1px solid #ddd',
              borderRadius: '8px',
              padding: '16px',
              marginBottom: '16px',
              backgroundColor: '#f9f9f9'
            }}
          >
            {/* Title displayed as heading */}
            <h3 style={{ color: '#2c3e50', textTransform: 'capitalize' }}>
              {post.id}. {post.title}
            </h3>

            {/* Body displayed as paragraph */}
            <p style={{ color: '#555', lineHeight: '1.6' }}>
              {post.body}
            </p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;