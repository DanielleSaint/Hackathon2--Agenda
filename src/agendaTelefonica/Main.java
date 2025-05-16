package agendaTelefonica;

import agendaTelefonica.Agenda.Agenda;
import agendaTelefonica.Contacto.Contacto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion;
        do {
            Contacto contacto = new Contacto();
            System.out.println("----AGENDA GENERATION----");
            System.out.println(
                    "1. Añadir contacto \n" +
                    "2. Mostrar contactos \n" +
                    "3. Buscar contacto \n" +
                    "4. Actualizar telefono de contacto \n" +
                    "5. Eliminar contacto \n" +
                    "6. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("Escriba el nombre");
                    contacto.setNombre(scanner.nextLine());
                    System.out.println("Escriba el apellido");
                    contacto.setApellido(scanner.nextLine());
                    System.out.println("Escriba el telefono");
                    contacto.setTelefono(scanner.nextInt());
                    scanner.nextLine();
                    agenda.anadirContacto(contacto);
                    agenda.espacioLibre();
                    break;
                case 2:
                    agenda.listarContactos();
                    break;
                case 3:
                    System.out.println("Escriba el nombre");
                    String nombre = scanner.nextLine();
                    System.out.println("Escriba el apellido");
                    String apellido = scanner.nextLine();
                    agenda.buscarContacto(nombre, apellido);
                    break;
                case 4:
                    System.out.println("Escriba el nombre");
                    String nombre1 = scanner.nextLine();
                    System.out.println("Escriba el apellido");
                    String apellido1 = scanner.nextLine();
                    System.out.println("Escriba el telefono nuevo");
                    int telefono = scanner.nextInt();
                    scanner.nextLine();
                    agenda.modificarTelefono(nombre1, apellido1, telefono);
                    break;
                case 5:
                    System.out.println("Escriba el nombre");
                    String nombre2 = scanner.nextLine();
                    System.out.println("Escriba el apellido");
                    String apellido2 = scanner.nextLine();
                    agenda.eliminarContacto(agenda.buscarContacto(nombre2, apellido2));
                    break;
                case 6:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        }while (opcion != 6);
    }
}
