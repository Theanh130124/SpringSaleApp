import {  useEffect, useState } from "react";
import { endpoint } from "../configs/Apis";
//tên class -> hoặc không có export thì không bỏ trong {}
import  Apis from "../configs/Apis";
import { Button, Card, Col, Row, Spinner } from "react-bootstrap";
import { useSearchParams } from "react-router-dom";

const Home = () => {
    const [product, setProduct] = useState([]);
    const [page, setPage] = useState([]);
    const [loading, setLoading] = useState([false])
    const [q] = useSearchParams();

    const loadProduct = async () => {
        try {

            let url = `${endpoint['products']}?page=${page}`;
            let res = await Apis.get(url);

            let cateId = q.get('cateId');

            //
            if(cateId){
                url = `${url}&categoryId=${cateId}`;
            }

            setProduct(res.data);
        } catch (ex) {
            console.error(ex)
        }
        finally {
            setLoading(true);
        }
    }

    useEffect(() => { loadProduct(); }, [page]);



    return (
        <>



            {/* lƯỚI VÀ CARD */}
            <Row>
                {product.map(p => <Col key={p.id} md={6} ms={3}>

                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={p.image}/>
                        <Card.Body>
                            <Card.Title>{p.name}</Card.Title>
                            <Card.Text>
                                {p.price} VNĐ
                            </Card.Text>
                            <Button variant="success">Xem chi tiết</Button>
                            <Button variant="danger">Đặt hàng</Button>
                        </Card.Body>
                    </Card>
                </Col>)
                }
            </Row>

            {loading && <Spinner animation="border" variant="primary" />
            }
        </>
    )
}

export default Home;