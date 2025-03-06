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
