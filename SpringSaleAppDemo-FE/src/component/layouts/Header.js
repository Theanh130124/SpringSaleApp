import { useEffect, useState } from "react";
import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import Apis, { endpoint } from "../../configs/Apis";

const Header = () => {

  const [categories, setCategories] = useState([]);

  //Gán api
  const loadCates = async () => {
    let res = await Apis.get(endpoint['categories'])
    setCategories(res.data);
  }
  //Thực hiện load 1 lần từ khi chạy trang
  useEffect(() => { loadCates(); }, [])


  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">Logo SaleApp</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Link to="/" className="nav-link">Trang chủ</Link>
            <NavDropdown title="Danh mục" id="basic-nav-dropdown">
              {/* Mình chưa có api này :)) */}
              {categories.map(c => <Link key={c.id} to={`/?cateId=${c.id}`} className="dropdown-item">{c.name}</Link>)}


            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default Header;