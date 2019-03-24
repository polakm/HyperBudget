import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, InputGroupAddon, InputGroup} from 'reactstrap';
import AppNavbar from './AppNavbar';
import moment from 'moment';
import tz from 'moment-timezone';

class TransactionEdit extends Component {

  emptyItem = {
    title: '',
    amount: '',
    currencyCode: 'USD',
    executionDate: moment.utc().format(),
    accountId:'',
    categoryId:''
  };
 
  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem,
      categories:[],
      accounts:[],
      isLoading: true
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeDate = this.handleChangeDate.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.resolveTitle = this.resolveTitle.bind(this);

  }

  async componentDidMount() {
    
    if (this.props.match.params.id !== 'income' && this.props.match.params.id !== 'expense') {
        const transaction = await (await fetch(`/api/transactions/${this.props.match.params.id}`, {
            headers:{"X-API-Version":"1"}
        })).json();
       this.setState({item: transaction});
    }else{
        this.state.item.type = this.props.match.params.id;
    }

    const categories = await (await fetch('/api/categories?type=' + this.state.item.type, {
        headers:{"X-API-Version":"1"}
    })).json();

    const accounts = await (await fetch('/api/accounts',{
        headers:{"X-API-Version":"1"}
    })).json();
    
    this.setState({ item: this.state.item, categories: categories, accounts: accounts, isLoading: false});
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  handleChangeDate(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = moment.utc(value).format();
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/transactions' + (item.id ? ('/'+ item.id) : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'X-API-Version':'1'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/transactions');
  }

  resolveTitle(){
    var title;
      if(this.state.item.id){
       title = "Edit ";
      }else{
         title = "Add ";
      }
        title += this.state.item.type.charAt(0).toUpperCase() + this.state.item.type.slice(1);

     return title;
  };

  render() {

    if (this.state.isLoading) {
      return <p>Loading...</p>;
    }

    const accounts = this.state.accounts;
    const accountOptions = accounts.map(account => {
              return <option value={account.id}>{account.name}</option>
      });

    const categories = this.state.categories;
    const categoryOptions = categories.map(category => {
          return <option value={category.id}>{category.name}</option>
      });


    const {item} = this.state;

    item.accountId  || (item.accountId = accounts.length>0 ? accounts[0].id : '');
    item.categoryId || (item.categoryId = categories.length>0 ? categories[0].id : '');

    var formattedExecutionDate = item.executionDate && moment.utc(item.executionDate).tz(moment.tz.guess()).format("YYYY-MM-DD");

    const title = <h2>{this.resolveTitle()}</h2>;

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
            <InputGroup>
            <Input type="number" min="0.01" step="0.01" name="amount" id="amount" value={Math.abs(item.amount) || ''}
                   onChange={this.handleChange} autoComplete="amount"/>
                   <InputGroupAddon addonType="prepend">{item.currencyCode}</InputGroupAddon>
                   </InputGroup>
          </FormGroup>
          <FormGroup>
            <Label for="account">Account</Label>
            <Input type="select" name="accountId" id="account" value={item.accountId}
                   onChange={this.handleChange} autoComplete="accountId">
                 {accountOptions}
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="category">Category</Label>
            <Input type="select" name="categoryId" id="category" value={item.categoryId}
                   onChange={this.handleChange} autoComplete="categoryId">
              {categoryOptions}
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="executionDate">Date</Label>           
            <Input type="date" name="executionDate" id="executionDate" value={formattedExecutionDate}
                   onChange={this.handleChangeDate} autoComplete="executionDate"/>
                    
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