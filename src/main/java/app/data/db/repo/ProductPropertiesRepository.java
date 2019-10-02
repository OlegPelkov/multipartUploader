package app.data.db.repo;

import java.util.List;
import java.util.Map;

public interface ProductPropertiesRepository<T> {
    List<T> findProductsByParams(Map<String, String> params) throws Exception;
}
