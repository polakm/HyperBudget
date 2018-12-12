import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, InputGroupAddon, InputGroup} from 'reactstrap';
import AppNavbar from './AppNavbar';

class TransactionEdit extends Component {

  emptyItem = {
    title: '',
    amount: '',
    currencyCode: 'PLN',
    executionDate: new Date().toISOString().substring(0,10),
    dateFormat: 'YYYY-MM-DD',
    accountId:'',
    categoryId:''
  };


  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem,
      categories:[],
      accounts:[]
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.resolveTitle = this.resolveTitle.bind(this);

  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'income' && this.props.match.params.id !== 'expense') {
      const transaction = await (await fetch(`/api/transactions/${this.props.match.params.id}`)).json();

      this.setState({item: transaction});

      //TODO "Push transaction type to server side"
      this.state.item.type  = ( Number(this.state.item.amount) > 0) ? "income" : "expense";
    }else{
        this.state.item.type = this.props.match.params.id;
    }
    if(this.state.item.type === "expense"){
       this.state.item.amount = -this.state.item.amount;
    }
    const categories = await (await fetch(`/api/categories`)).json();
    const accounts = await (await fetch(`/api/accounts`)).json();
    this.setState({ item: this.state.item,
                    categories: categories,
                    accounts: accounts});
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

    //TODO Push transaction type to server side
    if(item.type === "expense"){
        item.amount = - item.amount;
    }
    await fetch('/api/transactions' + (item.id ? ('/'+ item.id) : ''), {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
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

       //TODO Push transaction type to server side
      if (this.state.item.type === 'income'){
        title+="Income"
     }else{
         title+="Expense"
     }
     return title;
  };

  render() {


    const accounts = this.state.accounts;
    const accountOptions = accounts.map(account => {
              return <option value={account.id}>{account.name}</option>
      });

    const categories = this.state.categories;
    const categoryOptions = categories.map(category => {
          return <option value={category.id}>{category.name}</option>
      });

    const {item} = this.state;
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
            <Input type="number" min="0.01" step="0.01" name="amount" id="amount" value={item.amount || ''}
                   onChange={this.handleChange} autoComplete="amount"/>
                   <InputGroupAddon addonType="prepend">{item.currencyCode}</InputGroupAddon>
                   </InputGroup>
          </FormGroup>
          <FormGroup>
            <Label for="account">Account</Label>
            <Input type="select" name="accountId" id="account" value={item.accountId || ''}
                   onChange={this.handleChange} autoComplete="accountId">
                 {accountOptions}
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="category">Category</Label>
            <Input type="select" name="categoryId" id="category" value={item.categoryId || ''}
                   onChange={this.handleChange} autoComplete="categoryId">
              {categoryOptions}
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