/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.parameta.domain;

import com.jcbj.parameta.entities.Empleado;
import com.jcbj.parameta.exceptions.ExceptionApiHandler;
import com.jcbj.parameta.repository.EmpleadoRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan
 */
@Service
public class EmpleadoDomain {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public ResponseEntity<List<Empleado>> listEmpleados() {

        List<Empleado> empleadosList = empleadoRepository.findAll();

        if (!empleadosList.isEmpty()) {
            return ResponseEntity.ok(empleadosList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(empleadosList);
        }
    }

    public ResponseEntity<Empleado> findByIDEmpleado(Long id) {

        if (empleadoRepository.existsById(id)) {

            Empleado empleadoEncontrado = empleadoRepository.findById(id).get();
            return ResponseEntity.ok(empleadoEncontrado);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Empleado> createEmpleado(Empleado empSave) {

        //Valida que los campos no esten vacios, de lo contrario se manda un estado http 400
        if (!empSave.getNombreEmpleado().equals("") && !empSave.getApellidoEmpleado().equals("")
                && !empSave.getTipoDocEmpleado().equals("") && !empSave.getNumeroDocEmpleado().equals("")
                && !empSave.getFechaNacEmpleado().toString().equals("") && !empSave.getFechaVincEmpresaEmpleado().toString().equals("")
                && !empSave.getCargoEmpleado().equals("") && empSave.getSalario() != null) {

            //Valida que el formato de la fecha sea como se solicita en la documentación, de lo contrario se manda un estado http 400
            if (validarFecha(empSave.getFechaNacEmpleado()) && validarFecha(empSave.getFechaVincEmpresaEmpleado())) {

                //Valida que el empleado nuevo sea mayor de edad (18 años)
                if (empleadoMayorEdad(empSave.getFechaNacEmpleado())) {

                    empSave.setTiempoVincEmpleado(calcularAñosMesDia(empSave.getFechaVincEmpresaEmpleado()));
                    empSave.setEdadActualEmpleado(calcularAñosMesDia(empSave.getFechaNacEmpleado()));
                    empSave = empleadoRepository.save(empSave);

                    return ResponseEntity.status(HttpStatus.CREATED).body(empSave);

                } else {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(empSave);
                }

            } else {
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Empleado> updateEmpleado(Empleado empEdit) {

        if (empleadoRepository.existsById(empEdit.getIdEmpleado())) {

            try {

                empEdit.setTiempoVincEmpleado(calcularAñosMesDia(empEdit.getFechaVincEmpresaEmpleado()));
                empEdit.setEdadActualEmpleado(calcularAñosMesDia(empEdit.getFechaNacEmpleado()));

                empEdit = empleadoRepository.save(empEdit);
                return ResponseEntity.ok(empEdit);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Empleado> deleteEmpleado(Long id) {

        if (empleadoRepository.existsById(id)) {

            try {

                empleadoRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public boolean validarFecha(Date fechaValidar) {

        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");

        String patronFechaValida = "\\d{4}-\\d{1,2}-\\d{1,2}";

        try {

            String fechaConFormato = formateadorFecha.format(fechaValidar);
            return fechaConFormato.matches(patronFechaValida);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }

    }

    public boolean empleadoMayorEdad(Date fechaNac) {

        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate fechaNacimiento = LocalDate.parse(formateadorFecha.format(fechaNac));
        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fechaNacimiento, fechaActual);

        return periodo.getYears() >= 18;

    }

    public String calcularAñosMesDia(Date fechaInicial) {

        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate fecha = LocalDate.parse(formateadorFecha.format(fechaInicial));
        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fecha, fechaActual);

        String fechaCalculada = "Años:" + periodo.getYears() + "/Meses:" + periodo.getMonths() + "/Dias:" + periodo.getDays();

        return fechaCalculada;
    }

}
