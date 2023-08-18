package com.example.kardex.kardex.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kardex.kardex.DTO.ProveedorDTO;
import com.example.kardex.kardex.Repository.ProveedorRepository;
import com.example.kardex.kardex.Repository.TipoDocumentoRepository;
import com.example.kardex.kardex.Services.ProveedorServices;
import com.example.kardex.kardex.model.Proveedor;
import com.example.kardex.kardex.model.TipoDocumento;

@Controller
@RequestMapping("proveedores")
public class ProveedorController {
    
    ProveedorRepository proveedorRepository;
    TipoDocumentoRepository tipoDocumentoRepository;

    public ProveedorController(ProveedorRepository proveedorRepository, TipoDocumentoRepository tipoDocumentoRepository){
        this.proveedorRepository = proveedorRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    };

    @Autowired
    private ProveedorServices proveedorServices;

    @GetMapping
    public String list(Model model,@Param("palabraClave") String palabraClave){
        List<Proveedor> proveedores = proveedorServices.listAll(palabraClave);
        model.addAttribute("proveedores",proveedores);
        model.addAttribute("palabraClave", palabraClave);
        return "proveedores/list";
    }

    @GetMapping("create")
    public String showCreateForm(Model model){
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        List<TipoDocumento> tipoDocumento = tipoDocumentoRepository.findAll();
        model.addAttribute("proveedorForm",proveedorDTO);
        model.addAttribute("tipos",tipoDocumento);
        return "proveedores/create";
    }

    @PostMapping
    public String create(ProveedorDTO proveedorDTO){
        Proveedor proveedor = new Proveedor(proveedorDTO.getIdTipoDocumento(),proveedorDTO.getNumeroDocumento(),
        proveedorDTO.getRazonSocial(),proveedorDTO.getPais(),proveedorDTO.getCiudad(),proveedorDTO.getDireccion());
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if(proveedorOptional.isEmpty()) {
            return "404";
        }

        Proveedor proveedor = proveedorOptional.get();
        model.addAttribute("proveedor", proveedor);
        return "proveedore/detail";
    }

    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if(proveedorOptional.isEmpty()) {
            return "404";
        }

        List<TipoDocumento> tipoDocumento = tipoDocumentoRepository.findAll();
        Proveedor proveedor = proveedorOptional.get();
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("tipos",tipoDocumento);
        return "proveedores/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Integer id, Proveedor proveedorDataForm) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if(proveedorOptional.isEmpty()) {
            return "404";
        }

        Proveedor proveedor = proveedorOptional.get();
        proveedor.setIdTipoDocumento(proveedorDataForm.getIdTipoDocumento());
        proveedor.setNumeroDocumento(proveedorDataForm.getNumeroDocumento());
        proveedor.setRazonSocial(proveedorDataForm.getRazonSocial());
        proveedor.setPais(proveedorDataForm.getPais());
        proveedor.setCiudad(proveedorDataForm.getCiudad());
        proveedor.setDireccion(proveedorDataForm.getDireccion());

        proveedorRepository.save(proveedor);

        return "redirect:/proveedores";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        proveedorRepository.deleteById(id);
        return "redirect:/proveedores";
    }


}
