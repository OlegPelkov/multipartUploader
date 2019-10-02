package app.data.db.repo;

import app.data.db.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class ProductPropertiesRepositoryImpl implements ProductPropertiesRepository<ProductEntity> {

    @PersistenceContext
    EntityManager em;

    public List<ProductEntity> findProductsByParams(Map<String, String> params) throws Exception{
        return em.createQuery("from Products where salary = (select max(salary) from Employees )", ProductEntity.class)
                .getResultList();
    }
}
