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
        addButton.addActionListener((ActionEvent e) -> {
            try {
                Contacto contacto = new Contacto();
                contacto.setNombre(nombreField.getText());
                contacto.setApellido(apellidoField.getText());
                contacto.setTelefono(Integer.parseInt(telefonoField.getText()));
                agenda.anadirContacto(contacto);
                area.append("Contacto añadido.\n");
            } catch (Exception ex) {
                area.append("Error al añadir contacto.\n");
            }
        });

        listButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            agenda.listarContactos(); // Este método debe imprimir o retornar la lista
        });

        searchButton.addActionListener((ActionEvent e) -> {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            Contacto c = agenda.buscarContacto(nombre, apellido);
            if (c != null) {
                area.append("Encontrado: " + c.getNombre() + " " + c.getApellido() + " - " + c.getTelefono() + "\n");
            } else {
                area.append("No encontrado.\n");
            }
        });

        updateButton.addActionListener((ActionEvent e) -> {
            try {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                int telefono = Integer.parseInt(telefonoField.getText());
                agenda.modificarTelefono(nombre, apellido, telefono);
                area.append("Teléfono actualizado.\n");
            } catch (Exception ex) {
                area.append("Error al actualizar teléfono.\n");
            }
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            Contacto c = agenda.buscarContacto(nombre, apellido);
            if (c != null) {
                agenda.eliminarContacto(c);
                area.append("Contacto eliminado.\n");
            } else {
                area.append("No encontrado para eliminar.\n");
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgendaSwing::new);
    }
}
