package app.controller;

import app.data.web.dto.ProductDTO;
import app.data.web.dto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/createProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createProduct(@Valid @RequestBody ProductDTO product) {
        try {
            productService.createProduct(product);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/findProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findProduct(@Valid @RequestBody ProductDTO product) {
        try {
            //return ResponseEntity.ok(productService.findProductsByName(product.getName()));
            return ResponseEntity.ok(productService.findProductsByParams(product.getProperties()));
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}