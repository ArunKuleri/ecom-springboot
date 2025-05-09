package dev.arun.productservice.Service;

import dev.arun.productservice.dtos.FakeStoreProductDto;
import dev.arun.productservice.dtos.GenericProductDto;
import dev.arun.productservice.exception.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String productRequestBaseUrl ="https://fakestoreapi.com/products";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private GenericProductDto convertFakeStoreIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(productRequestBaseUrl,product, GenericProductDto.class);
        return  response.getBody();
    }


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
      ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
      FakeStoreProductDto fakeStoreProductDto = response.getBody();
      if(fakeStoreProductDto == null){
          throw new NotFoundException("Product with id :"+id + "does'nt exist");
      }
//      response.getStatusCode();
        return convertFakeStoreIntoGenericProduct(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
     ResponseEntity<FakeStoreProductDto[]> response =  restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
     List<GenericProductDto> answer = new ArrayList<>();
     for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){

         answer.add( convertFakeStoreIntoGenericProduct(fakeStoreProductDto));

     }
        return answer;

    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback,responseExtractor,id );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();


        return convertFakeStoreIntoGenericProduct(fakeStoreProductDto);
    }
}
