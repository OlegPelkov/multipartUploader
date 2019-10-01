package app.data.db.repo;

import app.data.db.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findProductByParams() throws Exception;

    String getDescriptionById(int id) throws Exception;

    List<ProductEntity> findByNameLike(String nameStartsWith);

}