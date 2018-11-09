import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import TransactionList from './TransactionList';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/transactions' exact={true} component={TransactionList}/>
        </Switch>
      </Router>
    )
  }
}

export default App;