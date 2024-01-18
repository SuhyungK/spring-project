package com.example.demo.product;

import com.example.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class ProductController {

    @Autowired
    private ProductService productService;

    // 상품 조회
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product findProduct(@PathVariable("id") int id) {
        return productService.findProduct(id);
    }

//    @RequestMapping(value = "/products", method = RequestMethod.POST)
//    public void saveProduct(@RequestParam(value = "name") String productName) {
//        productService.saveProduct(productName);
//    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> products() {
        return productService.productList();
    }
}
