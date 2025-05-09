package dev.arun.productservice.Controllers;

import dev.arun.productservice.Service.ProductService;
import dev.arun.productservice.dtos.ExceptionDto;
import dev.arun.productservice.dtos.GenericProductDto;
import dev.arun.productservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.NotActiveException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductsById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }


    @ExceptionHandler(NoClassDefFoundError.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(
            NotFoundException notFoundException
    ){
        return new ResponseEntity(
                new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping ("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id")Long id){
        return productService.deleteProduct(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return  productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
