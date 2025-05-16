package agendaTelefonica;

import agendaTelefonica.Agenda.Agenda;
import agendaTelefonica.Contacto.Contacto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgendaSwing extends JFrame {
    private Agenda agenda = new Agenda();

    private JTextField nombreField = new JTextField(10);
    private JTextField apellidoField = new JTextField(10);
    private JTextField telefonoField = new JTextField(10);
    private JTextArea area = new JTextArea(10, 30);

    public AgendaSwing() {
        setTitle("Agenda Telefónica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Apellido:"));
        inputPanel.add(apellidoField);
        inputPanel.add(new JLabel("Teléfono:"));
        inputPanel.add(telefonoField);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Añadir");
        JButton listButton = new JButton("Listar");
        JButton searchButton = new JButton("Buscar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Eliminar");

        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Área de resultados
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Acciones de botones
        // AÑADIR
        addButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            try {
                Contacto contacto = new Contacto();
                contacto.setNombre(nombreField.getText());
                contacto.setApellido(apellidoField.getText());
                contacto.setTelefono(Integer.parseInt(telefonoField.getText()));
                agenda.anadirContacto(contacto);
                area.append("Contacto añadido.\n");
                area.append("");
                nombreField.setText("");
                apellidoField.setText("");
                telefonoField.setText("");
            } catch (Exception ex) {
                area.append("Error al añadir contacto.\n");

            }
        });

        //LISTAR
        listButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            if (agenda.getAgenda().isEmpty()){
                area.append("Lista vacia, nada para mostrar");
            }
                area.append("----Lista de contactos----- \n");
            for (Contacto cont : agenda.getAgenda()){
                area.append("- Nombre y apellido: " + cont.getNombre() + " " + cont.getApellido() + " - Telefono: " + cont.getTelefono() +"\n");
            }
                area.append("----Fin lista de contactos----");
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        //BUSCAR
        searchButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            Contacto c = agenda.buscarContacto(nombre, apellido);
            if (c != null) {
                area.append("Encontrado: " + c.getNombre() + " " + c.getApellido() + " - " + c.getTelefono() + "\n");
            } else {
                area.append("No encontrado.\n");
            }
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        //ACTUALIZAR
        updateButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            try {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                int telefono = Integer.parseInt(telefonoField.getText());
                agenda.modificarTelefono(nombre, apellido, telefono);
                area.append("Teléfono actualizado.\n");
                nombreField.setText("");
                apellidoField.setText("");
                telefonoField.setText("");
            } catch (Exception ex) {
                area.append("Error al actualizar teléfono.\n");
            }
        });

        //BORRAR
        deleteButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            Contacto c = agenda.buscarContacto(nombre, apellido);
            if (c != null) {
                agenda.eliminarContacto(c);
                area.append("Contacto eliminado.\n");
            } else {
                area.append("No encontrado para eliminar.\n");
            }
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgendaSwing::new);
    }
}
