package sw2.clase11ej2clienterestful.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sw2.clase11ej2clienterestful.entity.Product;
import sw2.clase11ej2clienterestful.entity.ProductDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDao {

    public List<Product> listarProductos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> response = restTemplate.getForEntity(
                "http://localhost:8080/clase11ej2ServidorRest/product",
                Product[].class);

        Product[] arrayProductos = response.getBody();
        return Arrays.asList(arrayProductos);
    }

    public void guardarProducto(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);

        RestTemplate restTemplate = new RestTemplate();
        if (product.getId() > 0) {
            restTemplate.put(
                    "http://localhost:8080/clase11ej2ServidorRest/product",
                    httpEntity,
                    Product.class);
        } else {
            restTemplate.postForEntity(
                    "http://localhost:8080/clase11ej2ServidorRest/product",
                    httpEntity,
                    Product.class);
        }

    }

    public Product obtenerProductoPorId(int id) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ProductDto> responseMap = restTemplate.getForEntity(
                "http://localhost:8080/clase11ej2ServidorRest/product/"+id,
                ProductDto.class);

        ProductDto productDto = responseMap.getBody();

        return productDto.getProducto();
    }

    public void borrarProducto(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/clase11ej2ServidorRest/product/" + id);
    }
}
