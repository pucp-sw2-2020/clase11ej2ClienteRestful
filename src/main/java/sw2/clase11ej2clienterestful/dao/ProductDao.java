package sw2.clase11ej2clienterestful.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sw2.clase11ej2clienterestful.entity.Product;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductDao {

    public List<Product> listarProductos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[]> response = restTemplate.getForEntity(
                "http://localhost:8080/clase11ej1ServidorRest/product",
                Product[].class);

        Product[] arrayProductos = response.getBody();
        return Arrays.asList(arrayProductos);
    }
}
