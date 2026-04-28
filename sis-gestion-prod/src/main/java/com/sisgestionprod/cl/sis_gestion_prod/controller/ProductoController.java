package com.sisgestionprod.cl.sis_gestion_prod.controller;

import com.sisgestionprod.cl.sis_gestion_prod.model.Producto;
import com.sisgestionprod.cl.sis_gestion_prod.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        log.info("Listando productos");
        List<Producto> productos = productoService.findAll();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @Valid
    @PostMapping
    public ResponseEntity<Producto> guardar(@Valid @RequestBody Producto p){
        return  ResponseEntity.status(201).body(productoService.save(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id){
        try{
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto){
        try{
            log.info("Actualizando producto con id: {}", id);
            Producto prod = productoService.findById(id);
            prod.setNombre(producto.getNombre());
            prod.setPrecio(producto.getPrecio());
            prod.setCantidad(producto.getCantidad());
            productoService.save(prod);
            return ResponseEntity.ok(prod);
        }catch (Exception e){
            log.error("Error al actualizar producto", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> eliminar(@PathVariable String nombre){
        try{
            productoService.delete(nombre);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}