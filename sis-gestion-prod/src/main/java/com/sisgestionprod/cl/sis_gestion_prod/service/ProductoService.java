package com.sisgestionprod.cl.sis_gestion_prod.service;

import com.sisgestionprod.cl.sis_gestion_prod.model.Producto;
import com.sisgestionprod.cl.sis_gestion_prod.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    public void delete(String nombre) {
        productoRepository.deleteByNombre(nombre);
    }
}

