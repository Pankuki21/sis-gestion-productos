package com.sisgestionprod.cl.sis_gestion_prod.repository;

import com.sisgestionprod.cl.sis_gestion_prod.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Optional<Producto> findById(Long id);

    void deleteByNombre(String nombre);
}