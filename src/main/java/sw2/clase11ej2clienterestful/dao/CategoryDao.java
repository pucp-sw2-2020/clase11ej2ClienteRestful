package sw2.clase11ej2clienterestful.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sw2.clase11ej2clienterestful.entity.Category;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryDao {

    public List<Category> listarCategorias() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Category[]> response = restTemplate.getForEntity(
                "http://localhost:8080/clase11ej2ServidorRest/category",
                Category[].class);

        Category[] arrayCategorias = response.getBody();
        return Arrays.asList(arrayCategorias);
    }

}
