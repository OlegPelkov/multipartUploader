package app.data.db.repo;

import app.data.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>,
        ProductPropertiesRepository<ProductEntity> {

    String getDescriptionById(int id) throws Exception;

    List<ProductEntity> findByNameContaining(String nameStartsWith);

    @Query("select p from product_properties p where p.key = ?1 group by id")
    List<ProductEntity> findByParam(String param);
}