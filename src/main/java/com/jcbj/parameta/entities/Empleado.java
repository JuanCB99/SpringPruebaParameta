/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.parameta.entities;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Juan
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleados")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "idEmpleado")
    private Long idEmpleado;
    @ApiModelProperty(name = "nombreEmpleado", required = true, example = "Juan")
    private String nombreEmpleado;
    @ApiModelProperty(name = "apellidoEmpleado", required = true, example = "Bermudez")
    private String apellidoEmpleado;
    @ApiModelProperty(name = "tipoDocEmpleado", required = true, example = "C.C")
    private String tipoDocEmpleado;
    @ApiModelProperty(name = "numeroDocEmpleado", required = true, example = "1234567890")
    private String numeroDocEmpleado;
    @ApiModelProperty(name = "fechaNacEmpleado", required = true, example = "1996-09-15")
    @Temporal(TemporalType.DATE)
    private Date fechaNacEmpleado;
    @ApiModelProperty(name = "fechaVincEmpresaEmpleado", required = true, example = "2015-03-01")
    @Temporal(TemporalType.DATE)
    private Date fechaVincEmpresaEmpleado;
    @ApiModelProperty(name = "cargoEmpleado", required = true, example = "supervisor")
    private String cargoEmpleado;
    @ApiModelProperty(name = "salario", required = true, example = "2550000")
    private Double salario;
    @ApiModelProperty(name = "tiempoVincEmpleado", required = false, example = "Años:5/Meses:8/Dias:12", hidden = true)
    private String tiempoVincEmpleado;
    @ApiModelProperty(name = "edadActualEmpleado", required = false, example = "Años:25/Meses:6/Dias:14", hidden = true)
    private String edadActualEmpleado;

}
