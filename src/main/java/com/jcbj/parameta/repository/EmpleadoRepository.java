/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.parameta.repository;

import com.jcbj.parameta.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
}
