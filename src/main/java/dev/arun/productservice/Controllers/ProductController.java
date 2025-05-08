package dev.arun.productservice.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {
    @GetMapping()
    public void getAllProducts(){

    }
    @GetMapping("{id}")
    public String getProductsById(@PathVariable("id") Long id){
        return "Here is the product id:"+ id;

    }
    @DeleteMapping ("{id}")
    public void deleteProductById(){

    }
    @PostMapping
    public void createProduct(){

    }
    @PutMapping("{id}")
    public void updateProductById(){

    }
}
