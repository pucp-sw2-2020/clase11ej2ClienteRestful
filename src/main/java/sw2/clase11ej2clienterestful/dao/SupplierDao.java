package sw2.clase11ej2clienterestful.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sw2.clase11ej2clienterestful.entity.Supplier;

import java.util.Arrays;
import java.util.List;

@Component
public class SupplierDao {

    public List<Supplier> listarProveedores() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Supplier[]> response = restTemplate.getForEntity(
                "http://localhost:8080/clase11ej2ServidorRest/supplier",
                Supplier[].class);

        Supplier[] arrayProveedores = response.getBody();
        return Arrays.asList(arrayProveedores);
    }

}
