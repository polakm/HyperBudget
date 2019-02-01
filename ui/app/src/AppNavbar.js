import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavItem, NavLink, Badge } from 'reactstrap';
import { Link } from 'react-router-dom';
import AppInfo from './application.json';

export default class AppNavbar extends Component {
  constructor(props) {
    super(props);
    this.state = {isOpen: false};
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
      <NavbarBrand tag={Link} to="/">{AppInfo.name}</NavbarBrand>
       </Nav>
       <Nav className="ml-auto" navbar>
        <NavItem>
            <h5> <Badge pill color="light">{AppInfo.version}</Badge></h5>
        </NavItem>
       </Nav>
    </Navbar>;
  }
}