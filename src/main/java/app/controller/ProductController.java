package app.controller;

import app.data.web.dto.ProductDTO;
import app.data.web.dto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/createProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createProduct(@Valid @RequestBody ProductDTO product) {
        try {
            productService.createProduct(product);
        } catch (Exception e) {
            return "Error";
        }
        return "greeting";
    }

    @PostMapping(value = "/findProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String findProduct(@Valid @RequestBody ProductDTO product) {
        try {
            productService.findProductsByName(product.getName());
        } catch (Exception e) {
            return "Error";
        }
        return "greeting";
    }

    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "test";
    }

}