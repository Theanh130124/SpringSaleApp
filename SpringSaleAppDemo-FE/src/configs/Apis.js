import axios from "axios";

//nữa cấu hình theo biến môi trường của react
const BASE_URL = 'http://localhost:8080/SpringSaleAppDemo/api'


//cái endpoint api
export const endpoint = {
    'categories': '/categories',
    'products': '/products'
}

export default axios.create({
    baseURL: BASE_URL
})
