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
