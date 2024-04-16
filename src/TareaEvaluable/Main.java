package TareaEvaluable;

import java.io.*;
import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author German Escudero Rodriguez
 * @version jdk-17
 * 13/04/2024
 */
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean repetir = false;//Variable que se usara para los bucles de las excepciones de los diferentes metodos del codigo
    public static ArrayList<Empleado> empresa = new ArrayList<Empleado>();
    public static ArrayList<Empleado> antiguosEmpleados = new ArrayList<Empleado>();

    public static void ListaEmpleados(){
        File fichero = new File (".\\src\\TareaEvaluable\\empleados.txt");
        Scanner scanner = null;
        try{
            scanner = new Scanner(fichero);
            while (scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] separador = linea.split("::");
                Empleado empleado = new Empleado();
                empleado.setNombre(separador[0]);
                empleado.setApellidos(separador[1]);
                empleado.setFechaNacimiento(LocalDate.parse(separador[2]));
                empleado.setFechaIngreso(LocalDate.parse(separador[3]));
                empleado.setPuesto(separador[4]);
                empleado.setSalario(Double.parseDouble(separador[5]));
                empresa.add(empleado);
            }
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }finally {
            try {
                if (scanner !=null){
                    scanner.close();
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error al cerrar el fichero", "Error", 0);
                e.printStackTrace();
            }
        }//Fin try-catch-finally
    }//Fin metodo listaEmpleados

    public static void actualizador(){
        try (BufferedWriter actualizador = new BufferedWriter(new FileWriter(".\\src\\TareaEvaluable\\empleados.txt"))){
            for (Empleado empleado : empresa){
                actualizador.write(empleado.getNombre() + "::" +
                        empleado.getApellidos() + "::" +
                        empleado.getFechaNacimiento() + "::" +
                        empleado.getFechaIngreso() + "::" +
                        empleado.getPuesto() + "::" +
                        empleado.getSalario());
                actualizador.newLine();
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error al actualizar los datos del archivo", "Error", 0);
        }
    }

    public static int menu() {
        String[] opciones = {"Añadir un empleado", "Eliminar un empleado", "Buscar un empleado", "Ver los empleados", "Calcular los gastos totales","Ver los empleados despedidos" , "Salir del programa"};
        int opcion = JOptionPane.showOptionDialog(null, "Seleccione una de las siguientes opciones", "MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]) + 1;
        return opcion;
    }//Fin metodo menu

    public static void introducirEmpleado() {
        String nombreTemp = "";
        String apellidoTemp = "";
        int añoN = 0;
        int mesN = 0;
        int diaN = 0;
        LocalDate fechaNacimientoTemp = null;
        int añoI = 0;
        int mesI = 0;
        int diaI = 0;
        LocalDate fechaIngresoTemp = null;
        String puestoTemp = "";
        double salarioTemp = 0;
        //Introduce el nombre
        do {
            repetir = false;
            try{
                nombreTemp = JOptionPane.showInputDialog("Indique el nombre del nuevo empleado");
            }catch (InputMismatchException e1){
                JOptionPane.showMessageDialog(null, "Solo se permiten caracteres de texto", "Error de formato", 0);
                repetir = true;
            }
        }while(repetir);

        //Introduce el apellido
        do {
            repetir = false;
            try{
                apellidoTemp = JOptionPane.showInputDialog("Indique el apellido del nuevo empleado");
            }catch (InputMismatchException e1){
                JOptionPane.showMessageDialog(null, "Solo se permiten caracteres de texto", "Error de formato", 0);
                repetir = true;
            }
        }while(repetir);


        //Introduce la fehca de nacimiento
        do{
            repetir = false;
            do {
                repetir = false;
                try{
                    añoN = Integer.parseInt(JOptionPane.showInputDialog("Indique la fecha de nacimiento del nuevo empleado"+ "\nIndique el año de nacimiento"));
                    if (añoN < 1950){
                        throw new IllegalAccessException();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros enteros", "Error de formato", 0);
                    repetir = true;
                }catch (IllegalAccessException e2){
                    JOptionPane.showMessageDialog(null, "La fecha debe ser posterior a 1950", "Error de feccha", 0);
                    repetir = true;
                }
            }while(repetir);
            do {
                repetir = false;
                try{
                    mesN = Integer.parseInt(JOptionPane.showInputDialog("Indique la fecha de nacimiento del nuevo empleado"+ "\nIndique el mes de nacimiento"));
                    if (mesN < 01 || mesN > 12){
                        throw new IllegalAccessException();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros enteros", "Error de formato", 0);
                    repetir = true;
                }catch (IllegalAccessException e2){
                    JOptionPane.showMessageDialog(null, "Elige un mes de 1 a 12", "Error de fecha", 0);
                    repetir = true;
                }
            }while(repetir);
            do {
                repetir = false;
                try{
                    diaN = Integer.parseInt(JOptionPane.showInputDialog("Indique la fecha de nacimiento del nuevo empleado"+ "\nIndique el dia de nacimiento"));
                    if (diaN < 01 || diaN > 31){
                        throw new IllegalAccessException();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros enteros", "Error de formato", 0);
                    repetir = true;
                }catch (IllegalAccessException e2){
                    JOptionPane.showMessageDialog(null, "Elige un dia de 1 a 31", "Error de fecha", 0);
                    repetir = true;
                }
            }while(repetir);
            do {
                repetir = false;
                try{
                    fechaNacimientoTemp = LocalDate.of(añoN, mesN, diaN);
                }catch (DateTimeException e1){
                    JOptionPane.showMessageDialog(null, "Fecha no posible", "Error de fehca", 0);
                    repetir = true;
                }
                break;
            }while(repetir);
        }while(repetir);

        //Introduce la fecha de ingreso
        do {
            repetir = false;
            do {
                repetir = false;
                try{
                    añoI = Integer.parseInt(JOptionPane.showInputDialog("Indique la fecha de ingreso del nuevo empleado"+ "\nIndique el año de ingreso"));
                    if (añoN < 1980){
                        throw new IllegalAccessException();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros enteros", "Error de formato", 0);
                    repetir = true;
                }catch (IllegalAccessException e2){
                    JOptionPane.showMessageDialog(null, "La fecha de contratacion debe ser posterior a 1980", "Error de fecha", 0);
                    repetir = true;
                }
            }while(repetir);
            do {
                repetir = false;
                try{
                    mesI = Integer.parseInt(JOptionPane.showInputDialog("Indique la fecha de ingreso del nuevo empleado"+ "\nIndique el mes de ingreso"));
                    if (mesI < 01 || mesI > 12){
                        throw new IllegalAccessException();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros enteros", "Error de formato", 0);
                    repetir = true;
                }catch (IllegalAccessException e2){
                    JOptionPane.showMessageDialog(null, "Elige un mes de 1 a 12", "Error de fecha", 0);
                    repetir = true;
                }
            }while(repetir);
            do {
                repetir = false;
                try{
                    diaI = Integer.parseInt(JOptionPane.showInputDialog("Indique la fecha de ingreso del nuevo empleado"+ "\nIndique el dia de ingreso"));
                    if (diaI < 01 || diaI > 31){
                        throw new IllegalAccessException();
                    }
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros enteros", "Error de formato", 0);
                    repetir = true;
                }catch (IllegalAccessException e2){
                    JOptionPane.showMessageDialog(null, "Elige un dia de 1 a 31", "Error de fecha", 0);
                    repetir = true;
                }
            }while(repetir);
            do {
                repetir = false;
                try{
                    fechaIngresoTemp = LocalDate.of(añoI, mesI, diaI);
                }catch (DateTimeException e1){
                    JOptionPane.showMessageDialog(null, "Fecha no posible", "Error de fehca", 0);
                    repetir = true;
                }
                break;
            }while(repetir);
        }while (repetir);

        //Introduce el puesto
        do {
            repetir = false;
            try{
                puestoTemp = JOptionPane.showInputDialog("Indique el puesto del nuevo empleado");
            }catch (InputMismatchException e1){
                JOptionPane.showMessageDialog(null, "Solo se permiten caracteres de texto", "Error de formato", 0);
                repetir = true;
            }
        }while(repetir);

        //Introduce el salario
        do {
            repetir = false;
            try{
                salarioTemp = Double.parseDouble(JOptionPane.showInputDialog("Indique el salario del nuevo empleado"));
            }catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(null, "Solo se permiten numeros y decimales, recuerde que el decimal se usa con el '.'", "Error de formato", 0);
                repetir = true;
            }
        }while(repetir);

        //Se muestra el empleado recien creado
        JOptionPane.showMessageDialog(null, "El nuevo empleado "+nombreTemp+" "+apellidoTemp+" nacido en "+fechaNacimientoTemp+" ha sido contratado en "+fechaIngresoTemp+" con puesto de "+puestoTemp+" y salario de "+salarioTemp+"€", "Datos del nuevo empleado contratado", 1);

        //Crea el nuevo empleado usando todas las variables anteriores
        Empleado empleadoTemp = new Empleado(nombreTemp, apellidoTemp, fechaNacimientoTemp, fechaIngresoTemp, puestoTemp, salarioTemp);
        empresa.add(empleadoTemp);
        actualizador();
    }//Fin metodo introducir empleado

    public static void eliminarEmpleado(ArrayList<Empleado> empresa, String nombre) {
        try (BufferedWriter eliminador = new BufferedWriter(new FileWriter(".\\src\\TareaEvaluable\\empleadosAntiguos.txt", true))){
            Iterator<Empleado> eliminar = empresa.iterator();
            while (eliminar.hasNext()){
                Empleado empleado = eliminar.next();
                if (empleado.getNombre().equals(nombre)){
                    eliminador.write(empleado.getNombre() + "::" +
                            empleado.getApellidos() + "::" +
                            empleado.getFechaNacimiento() + "::" +
                            empleado.getFechaIngreso() + "::" +
                            empleado.getPuesto() + "::" +
                            empleado.getSalario() + "::" +
                            LocalDate.now());//Con el "LocalDate.now()" tomaremos la fecha actual y asi se tomara el dato automaticamente
                    eliminador.newLine();
                    eliminar.remove();
                    actualizador();
                    JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado correctamente", "Fin de proceso", 1);
                    return;
                }//Fin if
            }//Fin while
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar los empleados en el archibo", "Error", 0);
        }

        JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con el nombre introducido", "Error de busqueda", 0);//En caso de no encontrarse al empleado se mostrara el mensaje de no encontrado
    }//Fin metodo eliminarEmpleado

    public static void mostrarEmpleado(ArrayList<Empleado> empresa, String nombre) {
        String buscados = "";
        int i = 1;
        boolean encontrado = false;
        for (Empleado empleado : empresa) {
            if (empleado.getNombre().equals(nombre)) {
                encontrado = true;
                buscados += i+"-"+empleado;
                buscados += "\n";
                i++;
            }//Fin if
        }//Fin for
        if(!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con el nombre introducido", "Error de busqueda", 0);//En caso de no encontrarse al empleado se mostrara el mensaje de no encontrado
            return;
        }
            JOptionPane.showMessageDialog(null, buscados, "Datos del empleado", 1);
    }//Fin metodo mostrarEmpleado

    public static void mostrarEmpresa() {
        String[] opciones = {"Antigüedad", "Salario", "Apellido"};
        int opcionOrden = JOptionPane.showOptionDialog(null, "Seleccione para ver los empleados ordenados por:", "MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]) + 1;
        switch (opcionOrden){
            case 1:
                //Ordenacion de la lista de objetos por la antiguedad (fecha de ingreso)
                Collections.sort(empresa, new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado a1, Empleado a2) {
                        return a1.getFechaIngreso().compareTo(a2.getFechaIngreso());
                    }//Fin compare
                });//Fin iterator
                break;
            case 2:
                //Ordenacion de la lista de objetos por el salario
                Collections.sort(empresa, new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado a1, Empleado a2) {
                        return Double.compare(a1.getSalario(), a2.getSalario());
                    }//Fin compare
                });//Fin iterator
                break;
            case 3://Terminado
                //Ordenacion de la lista de objetos por el apellido
                Collections.sort(empresa, new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado a1, Empleado a2) {
                        return a1.getApellidos().compareTo(a2.getApellidos());
                    }//Fin compare
                });//Fin iterator
                break;
        }
    }//Fin metodo mostrarEmpresa

    /**
     * Metodo que toma el valor del salario de todos los empleados y los suma devolviendo el valor total de la suma de todos los empleados del ArrayList
     */
    public static void gastoTotal() {
        double total = 0;
        Iterator<Empleado> salarios = empresa.iterator();
        while (salarios.hasNext()){
            Empleado empleado = salarios.next();
            total += empleado.getSalario();
        }//Fin while
        JOptionPane.showMessageDialog(null, "El gasto total de todos los empleados de la empresa es de "+total+"€", "Gastos de la empresa", 1);
    }//Fin metodo gastoTotal

    public static void mostrarEmpleadosAntiguos(){
        File empleadosAntiguos = new File (".\\src\\TareaEvaluable\\empleadosAntiguos.txt");
        Scanner scanner = null;
        try{
            scanner = new Scanner(empleadosAntiguos);
            while (scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] separador = linea.split("::");
                Empleado empleadoEliminado = new Empleado();
                empleadoEliminado.setNombre(separador[0]);
                empleadoEliminado.setApellidos(separador[1]);
                empleadoEliminado.setFechaNacimiento(LocalDate.parse(separador[2]));
                empleadoEliminado.setFechaIngreso(LocalDate.parse(separador[3]));
                empleadoEliminado.setPuesto(separador[4]);
                empleadoEliminado.setSalario(Double.parseDouble(separador[5]));
                empleadoEliminado.setFechaDespido(LocalDate.parse(separador[6]));
                antiguosEmpleados.add(empleadoEliminado);
            }
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }finally {
            try {
                if (scanner !=null){
                    scanner.close();
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error al cerrar el fichero", "Error", 0);
                e.printStackTrace();
            }
        }//Fin try-catch-finally
    }

    public static void ejecucion(){
        int opcion;
        do {
            opcion = menu();
            switch (opcion){
                case 1:
                    introducirEmpleado();
                    break;
                case 2:
                    String eliminarE = "";
                    do {
                        repetir = false;
                        try{
                            eliminarE = JOptionPane.showInputDialog("Introduce el nombre del empleado que desea despedir");
                        }catch (InputMismatchException e1){
                            JOptionPane.showMessageDialog(null, "Solo se permiten caracteres de texto", "Error de formato", 0);
                            repetir = true;
                        }
                    }while (repetir);
                    eliminarEmpleado(empresa, eliminarE);
                    break;
                case 3:
                    String mostrarE = "";
                    do {
                        repetir = false;
                        try{
                            mostrarE = JOptionPane.showInputDialog("Introduce el nombre del empleado del que desea ver sus datos");
                        }catch (InputMismatchException e1){
                            JOptionPane.showMessageDialog(null, "Solo se permiten caracteres de texto", "Error de formato", 0);
                            repetir = true;
                        }
                    }while (repetir);
                    mostrarEmpleado(empresa, mostrarE);
                    break;
                case 4:
                    mostrarEmpresa();
                    int j = 1;
                    String mostrar = "";
                    for (Empleado empresa : empresa){
                        mostrar += j+"-"+empresa;
                        mostrar += "\n";
                        j++;
                    }
                    JOptionPane.showMessageDialog(null, mostrar, "Empleados de la empresa", 1);
                    break;
                case 5:
                    gastoTotal();
                    break;
                case 6:
                    mostrarEmpleadosAntiguos();
                    int k = 1;
                    String mostrarAntiguosEmpleados = "";
                    for (Empleado antiguos : antiguosEmpleados){
                        mostrarAntiguosEmpleados += k+"-"+antiguos.empleadosEliminados();
                        mostrarAntiguosEmpleados += "\n";
                        k++;
                    }
                    JOptionPane.showMessageDialog(null, mostrarAntiguosEmpleados, "Empleados de la empresa", 1);
                    antiguosEmpleados.clear();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...", "Saliendo", 2);
                    break;
            }//Fin switch
        }while (opcion!=7);//Fin do-while
    }//Fin metodo ejecucion

    /**
     * Metodo main del programa
     * Se invoca el metodo ListaEmpleados que es en el cual se crearon los objetos y fueron añadidos al ArrayList para que la lista contenga datos desde el principio
     * Se invoca el metodo ejecucion donde estan todas las opcion para la manipulacion de los datos
     * @param args
     */
    public static void main(String[] args) {
        ListaEmpleados();
        ejecucion();
    }//Fin main
}