import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import TransactionList from './TransactionList';
import TransactionEdit from './TransactionEdit';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={TransactionList}/>
          <Route path='/transactions' exact={true} component={TransactionList}/>
          <Route path='/transactions/:id' component={TransactionEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;