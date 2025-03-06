# SpringSaleApp
DemoSpringMVC


    
    public void addOrUpdateProduct(Product p) {
        try (Session s = hibernationUtils.getFACTORY().openSession()) {

            s.getTransaction().begin();
            if (p.getId() != null) {
                s.merge(p);

            } else {
                s.persist(p);
            }
            s.getTransaction().commit();

        }

    }


                if(params!=null){
               String namePro = params.get("namePro");
                if (namePro != null && !namePro.isEmpty()) {

                    predicates.add(builder.like(rP.get("name").as(String.class), String.format("%%%s%%", namePro)));

                } 
            }

            //JOIN NEW
//            Join<OrderDetail,Product> join = rO.join("productId",JoinType.RIGHT);
//            Join<OrderDetail,SaleOrder> join_2 = rO.join("orderId",JoinType.INNER);
//            predicates.add(join);
//            predicates.add(join_2)
