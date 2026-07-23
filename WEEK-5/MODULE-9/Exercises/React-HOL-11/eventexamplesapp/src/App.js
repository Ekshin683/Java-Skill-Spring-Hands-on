import React from 'react';
import CounterEvents from './components/CounterEvents';
import CurrencyConverter from './components/CurrencyConverter';

function App() {
  return (
    <div>
      <h1 style={{ textAlign: 'center', padding: '20px' }}>
        Event Examples App
      </h1>
      <CounterEvents />
      <CurrencyConverter />
    </div>
  );
}

export default App;