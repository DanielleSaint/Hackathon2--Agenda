package agendaTelefonica.Agenda;

import agendaTelefonica.Contacto.Contacto;

import java.util.ArrayList;

public class Agenda extends Contacto {


    private ArrayList<Contacto> agenda;
    private final int LIMITE = 10;

    public Agenda(){
        agenda = new ArrayList<>();
    }


    public Agenda(String nombre, String apellido, int telefono) {
        super(nombre, apellido, telefono);
        agenda = new ArrayList<>();
        this.agenda = agenda;
    }

    public boolean espacioLibre(){
        int espacionDisponible = LIMITE - agenda.size();
        if (espacionDisponible > 0){
            System.out.println("Aun puedes ingresar contactos. Tienes " + espacionDisponible + " espacios disponibles, para un maximo de " + LIMITE + " contactos");
            return true;
        }else {
            System.out.println("No puedes ingresar más contactos. Límite de " + LIMITE + " alcanzado.");
            return false;
        }
    }

    public boolean agendaLlena(){
        int limite = 9;
        boolean salida = false;
        for (int i = 0; i<= limite; i++){
            if (agenda.size() > limite){
                System.out.println("Agenda llena, no puedes ingresar mas contactos");
                salida = true;
                break;
            }
        }
        return salida;
    }

    public void anadirContacto(Contacto contacto){
        if (contacto.getNombre().isEmpty() && contacto.getApellido().isEmpty()){
            System.out.println("No se puede añadir un contacto con nombre y apellido vacio");
        }else {
            if (agendaLlena()) {
                System.out.println("La agenda esta llena");
            } else {
                if (existeContacto(contacto)) {
                    System.out.println("El contacto ya existe");
                } else {
                    agenda.add(contacto);
                    System.out.println("Contacto añadido exitosamente");
                }
            }
        }

    }

    public boolean existeContacto(Contacto contacto){
        for (Contacto cont : agenda) {
            if (cont.getNombre().equalsIgnoreCase(contacto.getNombre()) && cont.getApellido().equalsIgnoreCase(contacto.getApellido())) {
                return true;
            }
        }
        return false;
    }

    public void listarContactos(){
        if (agenda.isEmpty()){
            System.out.println("Lista vacia, nada para mostrar");
        }
        System.out.println("----Lista de contactos-----");
        for (Contacto cont : agenda){
            System.out.println("- Nombre y apellido: " + cont.getNombre() + " " + cont.getApellido() + " - Telefono: " + cont.getTelefono());
        }
        System.out.println("----Fin lista de contactos----");
    }

    public Contacto buscarContacto(String nombre, String apellido) {
        for (Contacto cont : agenda) {
            if (cont.getNombre().equals(nombre) && cont.getApellido().equals(apellido)) {
                System.out.println("¡Contacto encontrado!");
                System.out.println("El contacto " + nombre + " " + apellido + " su numero de telefono es " + cont.getTelefono());
                return cont;
            }
        }
        System.out.println("El contacto no existe");
        return null;
    }

    public void eliminarContacto(Contacto cont){
        boolean eliminado = false;

        for (int i = 0; i < agenda.size(); i++) {
            Contacto actual = agenda.get(i);
            if (actual.getNombre().equalsIgnoreCase(cont.getNombre()) &&
                    actual.getApellido().equalsIgnoreCase(cont.getApellido())) {
                agenda.remove(i);
                System.out.println("Contacto eliminado con éxito");
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            System.out.println("Contacto no encontrado");
        }
    }

    public void modificarTelefono(String nombre, String apellido, int nuevoTelefono) {
        boolean encontrado = false;

        for (Contacto cont : agenda) {
            if (cont.getNombre().equalsIgnoreCase(nombre) && cont.getApellido().equalsIgnoreCase(apellido)) {
                cont.setTelefono(nuevoTelefono);
                System.out.println("¡Teléfono actualizado!");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Contacto no encontrado");
        }
    }
}
