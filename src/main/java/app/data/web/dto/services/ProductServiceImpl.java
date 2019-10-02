package app.data.web.dto.services;

import app.data.db.entity.ProductEntity;
import app.data.db.repo.ProductRepository;
import app.data.web.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductServiceImpl implements ProductService {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void createProduct(ProductDTO productDTO) throws Exception {
        productRepository.save(modelMapper.map(productDTO, ProductEntity.class));
    }

    @Override
    @Transactional
    public List<ProductDTO> findProductsByName(String name) throws Exception {
        List<ProductEntity> productEntities = productRepository.findByNameContaining(name);
        return productEntities.stream().map(e -> modelMapper.map(e, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDTO> findProductsByParams(Map<String, String> params) throws Exception {
        List<ProductEntity> productEntities = productRepository.findProductsByParams(params);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            productDTOs.add(modelMapper.map(entity, ProductDTO.class));
        }
        return productDTOs;
    }

    @Override
    public String getDescriptionById(int id) throws Exception {
        return null;
    }
}
