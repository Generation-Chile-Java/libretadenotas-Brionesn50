import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibretaDeNotas {

    HashMap<String, ArrayList<Double>> calificaciones;
    Scanner sc;

    {
        sc = new Scanner(System.in);
        calificaciones = new HashMap<>();
        ejecutar();
    }

    public void ejecutar() {
        System.out.println("Ingrese la cantidad de alumnos: ");
        int numAlumnos = sc.nextInt();

        if (numAlumnos <= 0) {
            System.out.println("Cantidad No valida. Debe ingresar al menos un alumno ");
            return;
        }

        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int numNotas = sc.nextInt();

        if (numNotas <= 0) {
            System.out.println("Cantidad No valida. Debe ingresar al menos una nota por alumno ");
            return;
        }

        sc.nextLine();

        for (int i = 0; i < numAlumnos; i++) {
            System.out.println("Ingrese el nombre del estudiante: ");
            String nombre = sc.nextLine();

            ArrayList<Double> notas = new ArrayList<>();
            for (int j = 0; j < numNotas; j++) {
                System.out.println("Ingrese la nota " + (j + 1) + " para " + nombre );
                double nota = sc.nextDouble();

                while (nota < 1.0 || nota > 7.0) {
                    System.out.println("Nota No valida. Ingrese una nota entre 1.0 y 7.0 ");
                    nota = sc.nextDouble();
                }
                notas.add(nota);
            }
            sc.nextLine();
            calificaciones.put(nombre, notas);
        }

        mostrarMenu();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Menu de Opciones ");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante ");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante ");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso ");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                for (String estudiante : calificaciones.keySet()) {
                    ArrayList<Double> notas = calificaciones.get(estudiante);
                    double suma = 0;
                    for (double nota : notas) {
                        suma += nota;
                    }
                    double promedio = suma / notas.size();
                    System.out.println("Promedio de " + estudiante + "," + promedio);
                }
            } else if (opcion == 2) {
                System.out.println("Ingrese el nombre del estudiante: ");
                String nombreEstudiante = sc.nextLine();
                if (calificaciones.containsKey(nombreEstudiante)) {
                    System.out.println("Ingrese la nota: ");
                    double notaEvaluar = sc.nextDouble();
                    if (notaEvaluar >= 4.0) {
                        System.out.println("La nota es aprobatoria ");
                    } else {
                        System.out.println("La nota es reprobatoria ");
                    }
                } else {
                    System.out.println("Estudiante no existe ");
                }
            } else if (opcion == 3) {
                double sumaTotal = 0;
                int cantidadNotas = 0;
                for (ArrayList<Double> notas : calificaciones.values()) {
                    for (double nota : notas) {
                        sumaTotal += nota;
                        cantidadNotas++;
                    }
                }
                double promedioCurso = sumaTotal / cantidadNotas;
                System.out.println("Promedio del curso: " + promedioCurso);

                System.out.print("Ingrese el nombre del estudiante: ");
                String estudianteEvaluar = sc.nextLine();
                if (calificaciones.containsKey(estudianteEvaluar)) {
                    System.out.print("Ingrese la nota a comparar: ");
                    double notaComparar = sc.nextDouble();

                    if (notaComparar >= promedioCurso) {
                        System.out.println("La nota está sobre el promedio del curso ");
                    } else {
                        System.out.println("La nota está debajo el promedio del curso ");
                    }
                } else {
                    System.out.println("Estudiante no existe ");
                }
            } else if (opcion == 0) {
                System.out.println("Salir");
            } else {
                System.out.println("Opción no valida, intente nuevamente ");
            }
        } while (opcion != 0);
    }
}