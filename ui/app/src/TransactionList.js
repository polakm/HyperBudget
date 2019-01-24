import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table, Row, Col, ListGroup, ListGroupItem, Badge, Card, CardBody, CardTitle } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import {PieChart} from 'react-easy-chart';



class TransactionList extends Component {

    emptyStatistics: {
      sumOfIncomes:0,
      sumOfExpenses:0,
      totalSum:0
    }

  constructor(props) {
    super(props);
    this.state = {
        transactions: [],
        accounts: [],
        statistics: this.emptyStatistics,
        range: {
             month: new Date().getMonth()+1,
             year: new Date().getFullYear(),
             monthName: ''
        },
        isLoading: true
       };

    this.remove = this.remove.bind(this);
    this.previousMonth = this.previousMonth.bind(this);
    this.nextMonth = this.nextMonth.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});


    fetch('/api/summaries/' + this.state.range.year + '/' + this.state.range.month,{headers:{"X-API-Version":"1"}})
      .then(response => response.json())
      .then(data => this.setState({
      transactions: data.transactions,
      statistics: data.statistics,
      range: data.range,
      links: data._links,
      isLoading: false
     }));
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

      fetch('/api/summaries/' + this.state.range.year + '/' + this.state.range.month,{headers:{"X-API-Version":"1"}})
            .then(response => response.json())
            .then(data => this.setState({
            transactions: data.transactions,
            statistics: data.statistics,
            range: data.range,
            links: data._links,
            isLoading: false
           }));
    });
  }

  async previousMonth() {

    fetch(this.state.links.previousMonth.href,{headers:{"X-API-Version":"1"}})
      .then(response => response.json())
      .then(data => this.setState({
      transactions: data.transactions,
       range: data.range,
      statistics : data.statistics,
      links: data._links,
      isLoading: false
    }));
  }

   async nextMonth() {

      fetch(this.state.links.nextMonth.href,{headers:{"X-API-Version":"1"}})
        .then(response => response.json())
        .then(data => this.setState({
            transactions: data.transactions,
            range: data.range,
            statistics : data.statistics,
            links: data._links,
            isLoading: false
        }));
   }


  render() {
    const {transactions, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const transactionList = transactions.map(transaction => {
      return <tr key={transaction.id} class={transaction.type}>
        <td style={{whiteSpace: 'nowrap'}}>{transaction.title}</td>
        <td>{Math.abs(transaction.amount)}</td>
        <td>{transaction.executionDate.substring(0,10)}</td>
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
    // TODO: Get rounded value from service
    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <h3>Transaction List</h3>

          <Row>
          <Col md={8}>
          <Row>
          <Col className="md-6 offset-3">
           <h5>{this.state.range.monthName} {this.state.range.year}</h5>
                 <ButtonGroup>
                        <Button color="info" outline onClick={this.previousMonth}> &lt;&lt; Previous Month</Button>
                        <Button color="info" outline onClick={this.nextMonth}>Next Month &gt;&gt;</Button>
               </ButtonGroup>
           </Col>
           <Col className="md-6 offset-3" >

                    <ButtonGroup>
                        <Button color="success" outline tag={Link} to="/transactions/income">Add Income</Button>
                        <Button color="danger" outline tag={Link} to="/transactions/expense">Add Expense</Button>
                     </ButtonGroup>
             </Col>
          </Row>
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

                <h5>Income: <Badge className="float-right" color="success">{Number(this.state.statistics.sumOfIncomes).toFixed(2)}</Badge></h5>
                <h5>Outgoings: <Badge className="float-right" color="danger">{Number(this.state.statistics.sumOfExpenses).toFixed(2)}</Badge></h5>
                <hr/>
                <h5>Balance: <Badge className="float-right" color="info">{Number(this.state.statistics.totalSum).toFixed(2)}</Badge></h5>
              </CardBody>
            </Card>
            <Card>
              <CardBody>
              <div id = "chart-container" style={{textAlign:"center"}}>
          <PieChart size={200} inner innerHoleSize={150} data={[
               { title: 'Expenses', value: Number(this.state.statistics.absSumOfExpenses), color: '#dc3545' },
               { title: 'Balance', value: Number(this.state.statistics.totalSum), color: '#17a2b8' },
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