import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavItem, NavLink, Badge } from 'reactstrap';
import { Link } from 'react-router-dom';

export default class AppNavbar extends Component {
  constructor(props) {
    super(props);

    // FIXME: load application name and version from config file
    var application = {
        name: 'HyperBudget',
        version: '0.6.0-SNAPSHOT'
    };
    this.state = {application: application, isOpen: false};
    this.toggle = this.toggle.bind(this);
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  render() {
    return <Navbar color="dark" dark expand="md">
     <Nav>
      <NavbarBrand tag={Link} to="/">{this.state.application.name}</NavbarBrand>
       </Nav>
       <Nav className="ml-auto" navbar>
        <NavItem>
            <h5> <Badge pill color="light">{this.state.application.version}</Badge></h5>
        </NavItem>
       </Nav>
    </Navbar>;
  }
}