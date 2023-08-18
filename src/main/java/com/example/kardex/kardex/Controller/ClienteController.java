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

import com.example.kardex.kardex.DTO.ClienteDTO;
import com.example.kardex.kardex.Repository.ClienteRepository;
import com.example.kardex.kardex.Repository.TipoDocumentoRepository;
import com.example.kardex.kardex.Services.ClienteServices;
import com.example.kardex.kardex.model.Cliente;
import com.example.kardex.kardex.model.TipoDocumento;

@Controller
@RequestMapping("clientes")
public class ClienteController {
    
    ClienteRepository clienteRepository;
    TipoDocumentoRepository tipoDocumentoRepository;

    public ClienteController(ClienteRepository clienteRepository, TipoDocumentoRepository tipoDocumentoRepository){
        this.clienteRepository = clienteRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping
    public String list(Model model,@Param("palabraClave") String palabraClave){
        List<Cliente> clientes = clienteServices.listAll(palabraClave);
        model.addAttribute("clientes",clientes);
        model.addAttribute("palabraClave", palabraClave);
        return "clientes/list";
    }

    @GetMapping("create")
    public String showCreateForm(Model model){
        ClienteDTO clienteDTO = new ClienteDTO();
        List<TipoDocumento> tipoDocumento = tipoDocumentoRepository.findAll();
        model.addAttribute("clienteForm",clienteDTO);
        model.addAttribute("tipos",tipoDocumento);
        return "clientes/create";
    }

    @PostMapping
    public String create(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO.getIdTipoDocumento(),clienteDTO.getNumeroDocumento(),
        clienteDTO.getRazonSocial(),clienteDTO.getPais(),clienteDTO.getCiudad(),clienteDTO.getDireccion());
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()) {
            return "404";
        }

        Cliente cliente = clienteOptional.get();
        model.addAttribute("cliente",cliente);
        return "clientes/detail";
    }

    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()) {
            return "404";
        }

        List<TipoDocumento> tipoDocumento = tipoDocumentoRepository.findAll();
        Cliente cliente = clienteOptional.get();
        model.addAttribute("cliente", cliente);
        model.addAttribute("tipos",tipoDocumento);
        return "clientes/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Integer id, Cliente clienteDataForm) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()) {
            return "404";
        }

        Cliente cliente = clienteOptional.get();
        cliente.setIdTipoDocumento(clienteDataForm.getIdTipoDocumento());
        cliente.setNumeroDocumento(clienteDataForm.getNumeroDocumento());
        cliente.setRazonSocial(clienteDataForm.getRazonSocial());
        cliente.setPais(clienteDataForm.getPais());
        cliente.setCiudad(clienteDataForm.getCiudad());
        cliente.setDireccion(clienteDataForm.getDireccion());

        clienteRepository.save(cliente);

        return "redirect:/clientes";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}
