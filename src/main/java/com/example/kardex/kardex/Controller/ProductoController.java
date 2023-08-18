package com.example.kardex.kardex.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.kardex.kardex.DTO.ProductoDTO;
import com.example.kardex.kardex.Repository.ProductoRepository;
import com.example.kardex.kardex.Services.ProductoServices;
import com.example.kardex.kardex.model.Producto;


@Controller
@RequestMapping("productos")
public class ProductoController {
    
    ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Autowired
    private ProductoServices productoServices;

    @GetMapping
    public String list(Model model, @Param("palabraClave") String palabraClave){
        List<Producto> productos = productoServices.listAll(palabraClave);
        model.addAttribute("productos",productos);
        model.addAttribute("palabraClave", palabraClave);
        return "productos/list";
    }

    @GetMapping("create")
    public String showCreateForm(Model model){
        ProductoDTO productoDTO = new ProductoDTO();
        model.addAttribute("productoForm",productoDTO);
        return "productos/create";
    }

    @PostMapping
    public String create(ProductoDTO productoDTO){
        Producto producto = new Producto(productoDTO.getDetalle(),productoDTO.getMarca());
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isEmpty()) {
            return "404";
        }

        Producto producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "productos/detail";
    }

    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isEmpty()) {
            return "404";
        }

        Producto producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Integer id, Producto productoDataForm) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isEmpty()) {
            return "404";
        }

        Producto producto = productoOptional.get();
        producto.detalle = productoDataForm.getDetalle();
        producto.marca = productoDataForm.getMarca();
        
        productoRepository.save(producto);

        return "redirect:/productos";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
}
