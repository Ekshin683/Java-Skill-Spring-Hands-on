import React, { Component } from 'react';

class CurrencyConverter extends Component {

  constructor(props) {
    super(props);
    this.state = {
      rupees: '',    // input value
      euros: null,   // converted result
      error: ''
    };
    this.handleChange  = this.handleChange.bind(this);
    this.handleSubmit  = this.handleSubmit.bind(this);
  }

  // Handle input change event
  handleChange(e) {
    this.setState({
      rupees: e.target.value,
      euros: null,
      error: ''
    });
  }

  // Handle convert button click — conversion logic
  handleSubmit(e) {
    e.preventDefault();
    const rupees = parseFloat(this.state.rupees);

    if (isNaN(rupees) || rupees <= 0) {
      this.setState({ error: 'Please enter a valid amount in rupees.' });
      return;
    }

    // 1 Euro = approximately 89 Indian Rupees (fixed rate for demo)
    const conversionRate = 89;
    const euros = (rupees / conversionRate).toFixed(2);
    this.setState({ euros });
  }

  render() {
    const { rupees, euros, error } = this.state;

    const containerStyle = {
      border: '1px solid #ddd',
      borderRadius: '8px',
      padding: '24px',
      maxWidth: '400px',
      margin: '20px auto',
      backgroundColor: '#fff8e1',
      boxShadow: '0 2px 8px rgba(0,0,0,0.1)'
    };

    return (
      <div style={containerStyle}>
        <h2>💱 Currency Converter</h2>
        <p>Convert Indian Rupees (₹) to Euro (€)</p>

        <form onSubmit={this.handleSubmit}>
          <div style={{ marginBottom: '16px' }}>
            <label htmlFor="rupees">
              <strong>Amount in Rupees (₹):</strong>
            </label>
            <br />
            <input
              type="number"
              id="rupees"
              value={rupees}
              onChange={this.handleChange}
              placeholder="Enter amount in ₹"
              style={{
                padding: '8px',
                width: '100%',
                marginTop: '8px',
                borderRadius: '4px',
                border: '1px solid #ccc',
                fontSize: '16px'
              }}
            />
          </div>

          {error && (
            <p style={{ color: 'red', marginBottom: '12px' }}>{error}</p>
          )}

          <button
            type="submit"
            style={{
              backgroundColor: '#2196F3',
              color: 'white',
              padding: '10px 24px',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
              fontWeight: 'bold',
              fontSize: '16px'
            }}
          >
            Convert
          </button>
        </form>

        {euros && (
          <div style={{
            marginTop: '20px',
            padding: '16px',
            backgroundColor: '#e8f5e9',
            borderRadius: '8px',
            textAlign: 'center'
          }}>
            <p style={{ fontSize: '20px', margin: 0 }}>
              ₹{rupees} = <strong style={{ color: 'green' }}>€{euros}</strong>
            </p>
            <small style={{ color: '#666' }}>Rate: 1 € = ₹89</small>
          </div>
        )}
      </div>
    );
  }
}

export default CurrencyConverter;