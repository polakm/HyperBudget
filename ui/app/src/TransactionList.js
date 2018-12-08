import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table, Row, Col, ListGroup, ListGroupItem, Badge, Card, CardBody, CardTitle } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import {PieChart} from 'react-easy-chart';



class TransactionList extends Component {

  constructor(props) {
    super(props);
    this.state = {transactions: [], accounts: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/api/transactions/full')
      .then(response => response.json())
      .then(data => this.setState({transactions: data, isLoading: false}));
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
    const statistics ={
      totalIncome:"0",
      totalOutgoing:"0",
      balance:"0"
    }; 
    //TODO push to server site
    transactions.map(transaction => {
      if (transaction.amount > 0){
        statistics.totalIncome = Number(statistics.totalIncome) + Number(transaction.amount);
      }
      if (transaction.amount < 0){
       statistics.totalOutgoing = Number(statistics.totalOutgoing) - Number(transaction.amount);
      }
    });
    //TODO push to server site
    statistics.balance =  Number(statistics.totalIncome) -  Number(statistics.totalOutgoing);

    const getAmountStyle = function(transaction){
        return Number(transaction.amount)>0 ? 'green':'red';
     }

    const transactionList = transactions.map(transaction => {
      return <tr key={transaction.id} style={{"color" : getAmountStyle(transaction)}}>
        <td style={{whiteSpace: 'nowrap'}}>{transaction.title}</td>
        <td>{Math.abs(transaction.amount)}</td>
        <td>{transaction.executionDate}</td>
        <td>{transaction.accountName}</td>
        <td>{transaction.categoryName}</td>
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

          <Row>
          <Col md={8}>

                  <div className="float-right add-buttons">
                    <ButtonGroup>
                        <Button color="success" tag={Link} to="/transactions/income">Add Income</Button>
                        <Button color="danger" tag={Link} to="/transactions/expense">Add Expense</Button>
                     </ButtonGroup>
                  </div>
          <Table striped className="mt-4">
            <thead>
            <tr>
              <th width="20%">Title</th>
              <th width="20%">Amount</th>
              <th width="20%">Date</th>
              <th width="15%">Account</th>
              <th width="15%">Category</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {transactionList}
            </tbody>
          </Table>


          </Col>
        <Col md={3} >
            <Card>
              <CardBody>
                <h5>Income: <Badge className="float-right" color="success">{statistics.totalIncome.toFixed(2)}</Badge></h5>
                <h5>Outgoings: <Badge className="float-right" color="danger">{statistics.totalOutgoing.toFixed(2)}</Badge></h5>
                <hr/>
                <h5>Balance: <Badge className="float-right" color="info">{statistics.balance.toFixed(2)}</Badge></h5>
              </CardBody>
            </Card>
            <Card>
              <CardBody>
              <div id = "chart-container" style={{"text-align":"center"}}>
          <PieChart size={200} inner innerHoleSize={150} data={[
               { title: 'Outgoings', value: statistics.totalOutgoing, color: '#dc3545' },
               { title: 'Balance', value: statistics.balance, color: '#17a2b8' },
            ]}
            ></PieChart>
             </div>
             </CardBody>
            </Card>
          </Col>
        </Row>
        </Container>
      </div>
    );
  }
}

export default TransactionList;