package sw2.clase11ej2clienterestful.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sw2.clase11ej2clienterestful.entity.Product;

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

        HttpEntity<Product> httpEntity = new HttpEntity<>(product,headers);

        RestTemplate restTemplate = new RestTemplate();
        if(product.getId() > 0){
            restTemplate.put(
                    "http://localhost:8080/clase11ej2ServidorRest/product",
                    httpEntity,
                    Product.class);
        }else{
            restTemplate.postForEntity(
                    "http://localhost:8080/clase11ej2ServidorRest/product",
                    httpEntity,
                    Product.class);
        }

    }

    public Product obtenerProductoPorId(int id){

        RestTemplate restTemplate = new RestTemplate();
        HashMap<String,Object> responseMap = restTemplate.getForObject(
                "http://localhost:8080/clase11ej2ServidorRest/product/"+id,
                HashMap.class);

        if(responseMap.get("estado").equals("ok")){
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.convertValue(responseMap.get("producto"),Product.class);
            return product;
        }else{
            return null;
        }

    }

    public void borrarProducto(int id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/clase11ej2ServidorRest/product/"+id);
    }
}
