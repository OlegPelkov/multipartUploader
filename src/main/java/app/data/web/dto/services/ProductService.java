package app.data.web.dto.services;

import app.data.web.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void createProduct(ProductDTO p) throws Exception;

    List<ProductDTO> findProductsByName(String name) throws Exception;

    List<ProductDTO> findProductsByParams(Map<String, String> params) throws Exception;

    String getDescriptionById(int id) throws Exception;

}
