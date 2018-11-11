import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table, Input   } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';



class TransactionList extends Component {

  constructor(props) {
    super(props);
    this.state = {transactions: [], accounts: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/api/transactions')
      .then(response => response.json())
      .then(data => this.setState({transactions: data.transactions, isLoading: false}));
      
  }

  async remove(id) {
    await fetch(`/api/transactions/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedTransactions = [...this.state.transactions].filter(i => i.id !== id);
      this.setState({transactions: updatedTransactions});
    });
  }

  render() {
    const {transactions, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }
    
    //TODO push to server site
    const accountsMap = {
      "aaaaaa":"Bank",
      "bbbbbb":"Wallet",
      "cccccc":"Company Account",
      "dddddd":"Piggybank"
    }

    //TODO push to server site
    const categoriesMap = {
      "aaaaaa":"Other",
      "bbbbbb":"Shopping",
      "cccccc":"Car",
      "dddddd":"Home",
      "eeeeee":"Food",
      "ffffff":"Education"
    }

    const transactionList = transactions.map(transaction => {
      return <tr key={transaction.id}>
        <td style={{whiteSpace: 'nowrap'}}>{transaction.title}</td>
        <td>{transaction.amount}</td>
        <td>{transaction.executionDate}</td>
        <td>{accountsMap[transaction.accountId]}</td>
        <td>{categoriesMap[transaction.categoryId]}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/transactions/" + transaction.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(transaction.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <h3>Transaction List</h3>
          <Table striped className="mt-4">
            <thead>
            <tr>
              <th width="30%">Title</th>
              <th width="20%">Amount</th>
              <th width="10%">Date</th>
              <th width="15%">Account</th>
              <th width="15%">Category</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {transactionList}
            </tbody>
          </Table>

          <div className="float-right">
            <Button color="success" tag={Link} to="/transactions/new">Add Transaction</Button>
          </div>
        </Container>
      </div>
    );
  }
}

export default TransactionList;