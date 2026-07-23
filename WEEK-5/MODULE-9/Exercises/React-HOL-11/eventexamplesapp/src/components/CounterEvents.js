import React, { Component } from 'react';

class CounterEvents extends Component {

  constructor(props) {
    super(props);
    this.state = { count: 0, message: '' };

    // Bind methods so 'this' works inside them
    this.increment       = this.increment.bind(this);
    this.sayHello        = this.sayHello.bind(this);
    this.decrement       = this.decrement.bind(this);
    this.handleWelcome   = this.handleWelcome.bind(this);
    this.handleOnPress   = this.handleOnPress.bind(this);
  }

  // Method 1: increment counter value
  increment() {
    this.setState({ count: this.state.count + 1 });
  }

  // Method 2: say hello with static message
  // Increment button invokes BOTH increment() and sayHello()
  sayHello() {
    this.setState({ message: 'Hello! Welcome to React Events.' });
  }

  // Decrement counter
  decrement() {
    this.setState({ count: this.state.count - 1, message: '' });
  }

  // Passing argument to event handler — "welcome" string passed
  handleWelcome(word) {
    this.setState({ message: `Say ${word}! Have a great day.` });
  }

  // Synthetic event — e is the synthetic event object
  // React wraps native browser events in SyntheticEvent
  handleOnPress(e) {
    // e.type gives the event type (click)
    // e.target gives the element that was clicked
    alert(`I was clicked! Event type: ${e.type}`);
  }

  render() {
    const { count, message } = this.state;

    const boxStyle = {
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '20px',
      marginBottom: '16px',
      backgroundColor: '#f0f8ff'
    };

    const btnStyle = {
      margin: '5px',
      padding: '8px 16px',
      borderRadius: '4px',
      border: 'none',
      cursor: 'pointer',
      fontWeight: 'bold'
    };

    return (
      <div style={{ maxWidth: '600px', margin: '20px auto', padding: '0 20px' }}>
        <h2>Event Examples</h2>

        {/* Section 1: Counter with multiple method calls on Increment */}
        <div style={boxStyle}>
          <h3>1. Counter with Multiple Methods</h3>
          <p style={{ fontSize: '24px', fontWeight: 'bold' }}>
            Count: {count}
          </p>

          {/* Increment calls BOTH increment() and sayHello() */}
          <button
            style={{ ...btnStyle, backgroundColor: '#4CAF50', color: 'white' }}
            onClick={() => { this.increment(); this.sayHello(); }}
          >
            Increment
          </button>

          <button
            style={{ ...btnStyle, backgroundColor: '#f44336', color: 'white' }}
            onClick={this.decrement}
          >
            Decrement
          </button>

          {message && (
            <p style={{ color: '#2196F3', marginTop: '10px' }}>{message}</p>
          )}
        </div>

        {/* Section 2: Passing argument to event handler */}
        <div style={boxStyle}>
          <h3>2. Say Welcome — Passing Argument</h3>
          {/* Arrow function passes "welcome" string to handler */}
          <button
            style={{ ...btnStyle, backgroundColor: '#FF9800', color: 'white' }}
            onClick={() => this.handleWelcome('welcome')}
          >
            Say Welcome
          </button>
          {this.state.message.includes('welcome') && (
            <p style={{ color: '#FF9800' }}>{this.state.message}</p>
          )}
        </div>

        {/* Section 3: Synthetic Event — OnPress */}
        <div style={boxStyle}>
          <h3>3. Synthetic Event — OnPress</h3>
          <p>Synthetic events wrap native browser events — same API across browsers</p>
          {/* e is the SyntheticEvent object */}
          <button
            style={{ ...btnStyle, backgroundColor: '#9C27B0', color: 'white' }}
            onClick={this.handleOnPress}
          >
            OnPress — I was clicked
          </button>
        </div>
      </div>
    );
  }
}

export default CounterEvents;