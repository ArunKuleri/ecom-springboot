package dev.arun.productservice.Controllers;

import dev.arun.productservice.Service.ProductService;
import dev.arun.productservice.dtos.ExceptionDto;
import dev.arun.productservice.dtos.GenericProductDto;
import dev.arun.productservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping ("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id")Long id){
         return new ResponseEntity<>(productService.deleteProduct(id),
                 HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(
            NotFoundException notFoundException
    ){
        return new ResponseEntity(
                new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return  productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
