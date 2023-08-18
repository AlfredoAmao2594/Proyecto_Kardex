package com.example.kardex.kardex.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kardex.kardex.DTO.NotaDTO;
import com.example.kardex.kardex.DTO.ReporteDTO;
import com.example.kardex.kardex.Repository.ClienteRepository;
import com.example.kardex.kardex.Repository.NotaRepository;
import com.example.kardex.kardex.Repository.ProductoRepository;
import com.example.kardex.kardex.Repository.ProveedorRepository;
import com.example.kardex.kardex.Repository.TipoComprobanteRepository;
import com.example.kardex.kardex.Services.NotaServices;
import com.example.kardex.kardex.model.Cliente;
import com.example.kardex.kardex.model.Nota;
import com.example.kardex.kardex.model.Producto;
import com.example.kardex.kardex.model.Proveedor;
import com.example.kardex.kardex.model.TipoComprobante;



@Controller
@RequestMapping("notas")
public class NotaController {
    
    NotaRepository notaRepository;
    ProveedorRepository proveedorRepository;
    TipoComprobanteRepository tipoComprobanteRepository;
    ProductoRepository productoRepository;
    ClienteRepository clienteRepository;

    public NotaController (NotaRepository notaRepository,ProveedorRepository proveedorRepository
    ,TipoComprobanteRepository tipoComprobanteRepository,ProductoRepository productoRepository,
    ClienteRepository clienteRepository){
        this.notaRepository = notaRepository;
        this.proveedorRepository = proveedorRepository;
        this.tipoComprobanteRepository = tipoComprobanteRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Autowired
    private NotaServices notaServices;

    @GetMapping("list")
    public String list(Model model){
        List<Nota> notas = notaRepository.findAll();
        model.addAttribute("notas",notas);
        return "notas/list";
    }

    @GetMapping("createPurchase")
    public String showCreateForm(Model model){
        NotaDTO notaDTO = new NotaDTO();
        List<Proveedor> proveedor = proveedorRepository.findAll();
        List<TipoComprobante> comprobantes = tipoComprobanteRepository.findAll();
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("notaFormPurchase",notaDTO);
        model.addAttribute("proveedores", proveedor);
        model.addAttribute("comprobantes", comprobantes);
        model.addAttribute("productos", productos);
        return "notas/createPurchase";
    }

    @PostMapping("createPurchase")
    public String createPurchase(NotaDTO notaDTO){
        notaDTO.setTipoNota("ingreso");
        Nota nota = new Nota(notaDTO.getFecha(),notaDTO.getTipoNota(),notaDTO.getIdProveedor(),notaDTO.getIdTipoComprobante(),
        notaDTO.getIdCliente(),notaDTO.getIdProducto(),notaDTO.getCantidad(),notaDTO.getPrecio(),notaDTO.getCantidad()*notaDTO.getPrecio());
        notaRepository.save(nota);
        return "redirect:/notas/list";
    }

    @GetMapping("createSell")
    public String showCreateFormSell(Model model){
        NotaDTO notaDTO = new NotaDTO();
        List<Cliente> cliente = clienteRepository.findAll();
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("notaFormSell",notaDTO);
        model.addAttribute("clientes", cliente);
        model.addAttribute("productos", productos);
        return "notas/createSell";
    }

    @PostMapping("createSell")
    public String createSell(NotaDTO notaDTO){
        notaDTO.setTipoNota("egreso");
        int cantidad = -1 * notaDTO.getCantidad();
        Nota nota = new Nota(notaDTO.getFecha(),notaDTO.getTipoNota(),notaDTO.getIdProveedor(),notaDTO.getIdTipoComprobante(),
        notaDTO.getIdCliente(),notaDTO.getIdProducto(),cantidad,notaDTO.getPrecio(),cantidad*notaDTO.getPrecio());
        notaRepository.save(nota);
        return "redirect:/notas/list";
    }

   
    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Nota> notaOptional = notaRepository.findById(id);
        if(notaOptional.isEmpty()) {
            return "404";
        }

        Nota nota = notaOptional.get();
        List<Proveedor> proveedor = proveedorRepository.findAll();
        List<TipoComprobante> comprobantes = tipoComprobanteRepository.findAll();
        List<Producto> productos = productoRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        if("ingreso".equals(nota.getTipoNota())){
            model.addAttribute("nota", nota);
            model.addAttribute("proveedores", proveedor);
            model.addAttribute("comprobantes", comprobantes);
            model.addAttribute("productos", productos);
            return "notas/editPurchase";
        }
        else if("egreso".equals(nota.getTipoNota())){
            model.addAttribute("nota", nota);
            model.addAttribute("clientes", clientes);
            model.addAttribute("productos", productos);
            return "notas/editSell";
        }else{
            return "404";
        }
        
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Integer id,@ModelAttribute Nota notaDataForm) {
        Optional<Nota> notaOptional = notaRepository.findById(id);
        if(notaOptional.isEmpty()) {
            return "404";
        }

        Nota nota = notaOptional.get();

        if("egreso".equals(nota.getTipoNota())){
            int cantidad =-1*notaDataForm.getCantidad();

            nota.setFecha(notaDataForm.getFecha());
            nota.setIdProveedor(notaDataForm.getIdProveedor());
            nota.setIdTipoComprobante(notaDataForm.getIdTipoComprobante());
            nota.setIdCliente(notaDataForm.getIdCliente());
            nota.setIdProducto(notaDataForm.getIdProducto());
            nota.setCantidad(cantidad);
            nota.setPrecio(notaDataForm.getPrecio());
            nota.setImpTotal(cantidad*notaDataForm.getPrecio());

        }else if ("ingreso".equals(nota.getTipoNota())){
        
            nota.setFecha(notaDataForm.getFecha());
            nota.setIdProveedor(notaDataForm.getIdProveedor());
            nota.setIdTipoComprobante(notaDataForm.getIdTipoComprobante());
            nota.setIdCliente(notaDataForm.getIdCliente());
            nota.setIdProducto(notaDataForm.getIdProducto());
            nota.setCantidad(notaDataForm.getCantidad());
            nota.setPrecio(notaDataForm.getPrecio());
            nota.setImpTotal(notaDataForm.getPrecio()*notaDataForm.getCantidad());
        }else
            return "404";
        
        
        notaRepository.save(nota);

        return "redirect:/notas/list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        notaRepository.deleteById(id);
        return "redirect:/notas";
    }

    @GetMapping("reportes")
    public String listReporte(Model model, @Param("palabraClave") String palabraClave){
        List<ReporteDTO> reporte = notaServices.listAll(palabraClave);
        model.addAttribute("reportes",reporte);
        model.addAttribute("palabraClave", palabraClave);
        return "notas/listReports";
    }
}
