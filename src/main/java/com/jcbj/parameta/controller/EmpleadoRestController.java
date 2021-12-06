/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.parameta.controller;

import com.jcbj.parameta.domain.EmpleadoDomain;
import com.jcbj.parameta.entities.Empleado;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Juan
 */
@Api(tags = "API Empleados")
@RestController
@RequestMapping("/empleado")
public class EmpleadoRestController {

    @Autowired
    EmpleadoDomain empleadoDomain;

    @ApiOperation(value = "Retorna una lista con todos los objetos tipo Empleado almacenados en la BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontraron empleados en la BD."),
        @ApiResponse(code = 204, message = "No hay empleados en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/list")
    public ResponseEntity<List<Empleado>> list() {
        ResponseEntity<List<Empleado>> empleadosList = empleadoDomain.listEmpleados();
        return empleadosList;
    }

    @ApiOperation(value = "Retorna un unico objeto de tipo empleado, el cual coincida con el ID enviado por PATH.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontro el empleado en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El empleado con ese ID no esta en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> get(@PathVariable Long id) {
        ResponseEntity<Empleado> empleadoGet = empleadoDomain.findByIDEmpleado(id);
        return empleadoGet;
    }

    @ApiOperation(value = "Recibe un objeto de tipo Empleado con id ya existente, con datos para actualizar.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se actualizaron los datos con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Empleado no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PutMapping()
    public ResponseEntity<Empleado> put(@RequestBody Empleado input) {
        return empleadoDomain.updateEmpleado(input);
    }

    @ApiOperation(value = "Retorna una lista con todos los objetos tipo Empleado almacenados en la BD",
            notes = "En esta operación se valida el formato de la fecha 'YYYY-MM-DD' y que el empleado"
            + "sea mayor de edad (18 años), si esto no se cumple, no se guardara la información. ")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Se guardo el empleado en la BD."),
        @ApiResponse(code = 204, message = "Se proceso correctamente la petición, pero no se cumplio una condición."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado input) {
        return empleadoDomain.createEmpleado(input);

    }

    @ApiOperation(value = "Elimina un empleado de la BD, tomando como parametro un ID enviado por el PATH")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Se elimino el Empleado con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Empleado no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable Long id) {
        return empleadoDomain.deleteEmpleado(id);
    }

}
