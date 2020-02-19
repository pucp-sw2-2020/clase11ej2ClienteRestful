package sw2.clase11ej2clienterestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase11ej2clienterestful.dao.ProductDao;
import sw2.clase11ej2clienterestful.entity.Product;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductDao productDao;

    @GetMapping({"/list", "", "/"})
    public String listarProductos(Model model) {
        model.addAttribute("listaProductos", productDao.listarProductos());
        return "product/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("product") Product product,
                                   Model model) {
        //model.addAttribute("listaCategorias", categoryRepository.findAll());
        //model.addAttribute("listaProveedores", supplierRepository.findAll());
        return "product/form";
    }

    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                                  Model model, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            //model.addAttribute("listaCategorias", categoryRepository.findAll());
            //model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/form";
        } else {
            attr.addFlashAttribute("msg", "Producto " + (product.getId() == 0 ? "creado" : "actualizado") + " exitosamente");
            //productRepository.save(product);
            return "redirect:/product";
        }

    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("product") Product product,
                                      Model model, @RequestParam("id") int id) {

        /*Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            product = optProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("listaCategorias", categoryRepository.findAll());
            model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/form";
        } else {
            return "redirect:/product";
        }*/
        return "";
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        //Optional<Product> optProduct = productRepository.findById(id);

        /*if (optProduct.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }*/
        return "redirect:/product";

    }

}
