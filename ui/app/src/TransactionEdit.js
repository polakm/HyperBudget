import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class TransactionEdit extends Component {

  emptyItem = {
    title: '',
    amount: '',
    currencyCode: 'PLN',
    executionDate:'',
    dateFormat: 'YYYY-MM-DD',
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const transaction = await (await fetch(`/api/transactions/${this.props.match.params.id}`)).json();
      this.setState({item: transaction});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/transactions', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/transactions');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Transaction' : 'Add Transaction'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="title">Title</Label>
            <Input type="text" name="title" id="title" value={item.title || ''}
                   onChange={this.handleChange} autoComplete="title"/>
          </FormGroup>
          <FormGroup>
            <Label for="amount">Amount</Label>
            <Input type="number"  min="0.00" step="0.01"  name="amount" id="amount" value={item.amount || ''}
                   onChange={this.handleChange} autoComplete="amount"/>
          </FormGroup>
          <FormGroup>
            <Label for="account">Account</Label>
            <Input type="select" name="accountId" id="account" value={item.accountId || ''}
                   onChange={this.handleChange} autoComplete="accountId">
              <option value="aaaaaa">Bank</option>
              <option value="bbbbbb">Wallet</option>
              <option value="cccccc">Company Account</option>
              <option value="dddddd">Piggybank</option>
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="executionDate">Date</Label>
            <Input type="date" name="executionDate" id="executionDate" value={item.executionDate || ''}
                   onChange={this.handleChange} autoComplete="executionDate"/>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/transactions">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(TransactionEdit);