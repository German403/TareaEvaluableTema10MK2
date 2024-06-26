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
 * Este programa registra empleados de una empresa y los almacena en un archivo de texto simulando una base de datos con persistencia de datos
 * En el programa se podran tratar esos datos como añadir nuevos empleados, eliminarlos o verlos mediante los diferentes metodos del programa usando un ArrayList como intermediario
 */
public class Main {
    public static boolean repetir = false;//Variable que se usara para los bucles de las excepciones de los diferentes metodos del codigo
    public static ArrayList<Empleado> empresa = new ArrayList<Empleado>();//Lista donde se almacenaran los empleados de la empresa
    public static ArrayList<Empleado> antiguosEmpleados = new ArrayList<Empleado>();//Lista donde se almacenaran los empleados eliminados

    /**
     * Metodo donde se crean los empleados y se introducen en un ArayList
     * Los empleados seran tomados del archivo de texto se ajustaran sus variables y se introduciran en la lista
     * @throws FileNotFoundException excepcion para los casos en los que el archivo de texto no es encontrado
     * @throws Exception excepcion para los casos en los que el ocurra un error al cerrar el Scanner
     */
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

    /**
     * Metodo que toma el ArrayList de la empresa y acutaliza el archivo de texto con los mismos empleados que el ArrayList
     * @throws IOException Excepcion para los casos en los que no se pudo actualizar el archivo de texto
     */
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

    /**
     * Metodo menu donde se detallan las opciones que da el codigo para manipular los datos
     * Se utilizara un array para que el metodo pueda usar botones con la libreria JOptionPane
     * @return opcion
     */
    public static int menu() {
        String[] opciones = {"Añadir un empleado", "Eliminar un empleado", "Buscar un empleado", "Ver los empleados", "Calcular los gastos totales","Ver los empleados despedidos" , "Salir del programa"};
        int opcion = JOptionPane.showOptionDialog(null, "Seleccione una de las siguientes opciones", "MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]) + 1;
        return opcion;
    }//Fin metodo menu

    /**
     * Metodo para añadir un nuevo empleado al ArrayList
     * En el se crearan unas variables temporales donde se les dara los valores correspondientes que introducira el usuario y luego se creara el objeto con esos valores
     * Una vez creado el objeto se añadira al ArrayList en ultima posicion
     * Una vez creado el empleado se invocara el metodo actualizador() para añadir el nuevo empleado al archivo de texto
     * En el metodo se le asignara un sistema de excepciones que no permitira avanzar hasta que las condiciones sean correctas
     * @throws InputMismatchException
     * @throws NumberFormatException excepcion para los casos en que se utilice un caracter diferente a un numero entero
     * @throws IllegalAccessException excepcion personalizada para los casos en que el entero este fuera del rango establecido
     * @throws DateTimeException excepcion solo para las variables de tipo LocalDate que se activara en caso de que la fehca sea imposible
     */
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

    /**
     * Metodo para eliminar empleados en el cual se eliminaran los empleados que se den coincidencia de nombre
     * Los empleados seran eliminados del ArrayList empresa y luego usando el metodo actualizador() el archivo de texto se corregira con los empleados existentes
     * Los empleados eliminados seran enviados al archivo de texto empleadosAntiguos donde se almacenaran
     * @param empresa
     * @param nombre
     * @throws IOException excepcion para los casos en los que no se pudo actualizar el archivo de texto
     * @throws IllegalAccessException excepcion personalizada para los casos en que no se localice ningun empleado con el nombre introducido
     */
    public static void eliminarEmpleado(ArrayList<Empleado> empresa, String nombre) {
        int comprobador = 0;
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
                    comprobador++;
                }//Fin if
            }//Fin while
            if (comprobador == 0){
                throw new IllegalAccessException();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar los empleados en el archivo", "Error", 0);
        } catch (IllegalAccessException e2){
            JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con el nombre introducido", "Error de busqueda", 0);//En caso de no encontrarse al empleado se mostrara el mensaje de no encontrado
        }
    }//Fin metodo eliminarEmpleado

    /**
     * Metodo que busca entre los empleados existentes del ArrayList empresa con el nombre dado anteriormente y en caso de coincidencia lo muestra en pantalla
     * En caso de que existan varias empleados con el que existe coincidencia en el nombre se mostraran todos los empleados con la coincidencia
     * @param empresa
     * @param nombre
     */
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

    /**
     * Metodo de ordencion del ArrayList de mediante diferentes preferencias segun el caso elegido
     * Los empleados se ordenaran segun su antigÜedad en la empresa, segun su salario o segun su apellido mediante la comparacion de sus valores
     * @throws NumberFormatException excepcion para los casos en que se utilice un caracter diferente a un numero entero
     * @throws IllegalAccessException excepcion personalizada para los casos en que el entero se menor a 1 o mayor a 3
     */
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

    /**
     * Metodo donde se crean los empleados usando el archivo de texto de empleadosAntigos y se introducen en un ArrayList
     * Luego estos empleados seran mostrados
     * @throws FileNotFoundException excepcion para los casos en los que el archivo de texto no es encontrado
     * @throws Exception excepcion para los casos en los que el ocurra un error al cerrar el Scanner
     * @throws ArrayIndexOutOfBoundsException excepcion para los casos en los que se de error en la asignacion de variables
     * Es importante que en caso de no existir datos en el archivo de texto deben borrarse las lineas en blanco para evitar que el programa busque datos
     */
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
        } catch (ArrayIndexOutOfBoundsException e2){
            JOptionPane.showMessageDialog(null, "Existe un error entre los datos de los empleados o el archivo de texto tiene una linea en blanco sobrante", "Error en el archivo", 2);
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

    /**
     * Metodo donde se ejecutara la opcion segun la elegida en el metodo menu y este usara el metodo al que corresponda cada caso del switch
     * @throws InputMismatchException
     */
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
     * Se invoca el metodo ListaEmpleados que es en el cual se toman los datos del archivo de texto y se crean los empleados con sus valores y se introducen en la lista
     * Se invoca el metodo ejecucion donde estan todas las opcion para la manipulacion de los datos
     * @param args
     */
    public static void main(String[] args) {
        ListaEmpleados();
        ejecucion();
    }//Fin main
}