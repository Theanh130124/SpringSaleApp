import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./component/layouts/Header";
import Footer from "./component/layouts/Footer";
import Register from "./component/Register";
import Home from "./component/Home";

import { Container } from "react-bootstrap";

// Nhớ import cả css để dùng được các class
import 'bootstrap/dist/css/bootstrap.min.css';


//Tổ chức sắp xếp các component
const App = () => {

  return (

    <BrowserRouter>
    {/* // những thằng viết ở đây là cố định sẽ không đổi */}
      <Header />
      <Container>



        <Routes>
          {/* 1 cái này là 1 trang */}
          <Route path="/" element={<Home />}>
          </Route>
          <Route path="/register" element={<Register />}>
          </Route>

        </Routes>


      </Container>
      <Footer />
    </BrowserRouter>


  )

}

export default App;

// BrowserRouter	Bọc toàn bộ ứng dụng, giúp quản lý lịch sử trình duyệt.
// Routes	Bao gồm các route con.
// Route	Đại diện cho một đường dẫn cụ thể và component tương ứng.
// Link	Thay cho thẻ <a>, giúp điều hướng mà không reload trang.
// useNavigate()	Hook để điều hướng bằng code (thường dùng sau khi submit form...).