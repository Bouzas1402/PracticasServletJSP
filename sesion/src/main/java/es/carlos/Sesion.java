package es.carlos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Sesion  {

    private String nombre = "";
    private String apellidos = "";
    private String fechaNacimiento = "";
    private String genero = "";
    private String estadoSocial = "";
    private String hijos = "";
    private String nacionalidad = "";
    private String departamento = "";
    private double salario = 0;
    private String comentarios = "";
    private String cuentaCorriente = "";


    public Sesion() {

    }


}
