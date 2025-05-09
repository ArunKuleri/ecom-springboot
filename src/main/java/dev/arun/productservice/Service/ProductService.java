package dev.arun.productservice.Service;

import dev.arun.productservice.Models.Product;
import dev.arun.productservice.dtos.GenericProductDto;
import dev.arun.productservice.exception.NotFoundException;

import javax.swing.text.GapContent;
import java.util.List;

public interface ProductService {
     GenericProductDto createProduct(GenericProductDto product);

      GenericProductDto getProductById(Long id) throws NotFoundException;
      List<GenericProductDto> getAllProducts();
      GenericProductDto deleteProduct(Long id);


}
