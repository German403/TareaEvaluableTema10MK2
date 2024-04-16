package TareaEvaluable;

import java.time.LocalDate;

/**
 * Esta clase sera utilizada para la construccion de objetos del main
 * Tendra los atributos del empleado y luego se crearan estos objetos en el main y se almacenaran en un ArrayList
 */
public class Empleado {
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private String puesto;
    private double salario;

    private LocalDate fechaDespido;

    public Empleado() {}

    /**
     * Constructor de Empleado
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param fechaIngreso
     * @param puesto
     * @param salario
     */
    public Empleado(String nombre, String apellidos, LocalDate fechaNacimiento, LocalDate fechaIngreso, String puesto, double salario){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Empleado(String nombre, String apellidos, LocalDate fechaNacimiento, LocalDate fechaIngreso, String puesto, double salario, LocalDate fechaDespido){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaDespido = fechaDespido;
    }

    //Metodos getter & setter
    /**
     * Retorna el nombre del empleado
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del empleado
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del empleado
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Asigna el apellido del empleado
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Retorna la fecha de nacimiento del empleado
     * @return fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Asigna la fecha de nacimiento del empleado
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna la fecha de ingreso del empleado
     * @return fechaIngreso
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Asigna la fecha de ingreso del empleado
     * @param fechaIngreso
     */
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Retorna el puesto del empleado
     * @return puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Asigna el puesto del empleado
     * @param puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Retorna el salario del empleado
     * @return salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Asigna el salario del empleado
     * @param salario
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaDespido() {
        return fechaDespido;
    }

    public void setFechaDespido(LocalDate fechaDespido) {
        this.fechaDespido = fechaDespido;
    }

    public String empleadosEliminados(){
        return nombre+" "+apellidos+" nacido en "+fechaNacimiento+" llego a la empresa en "+fechaIngreso+" con puesto de "+puesto+" salario de "+salario+"€" + " y fue despedido en " + fechaDespido;
    }

    //Metodos propios
    /**
     * Retorna una linea de texto donde se detallan todos los datos del empleado
     * @return los datos del empleado
     */
    @Override
    public String toString(){
        return nombre+" "+apellidos+" nacido en "+fechaNacimiento+" llego a la empresa en "+fechaIngreso+" con puesto de "+puesto+" y salario de "+salario+"€";
    }
}