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
    private JTextArea area = new JTextArea(20,40);
    private JLabel footerLabel = new JLabel("Devs G-8: Diana, Daniela, Hermel, Andres @2025");

    public AgendaSwing() {
        setTitle("Agenda Telefónica 📱");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //PADDING AREA
        area.setMargin(new Insets(30, 80, 10, 80));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        nombreLabel.setForeground(new Color(0, 102, 204)); // un azul suave

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        apellidoLabel.setForeground(new Color(0, 102, 204));

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        telefonoLabel.setForeground(new Color(0, 102, 204));

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);
        inputPanel.add(apellidoLabel);
        inputPanel.add(apellidoField);
        inputPanel.add(telefonoLabel);
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

        // Pie de página
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        footerLabel.setForeground(Color.white);
        footerLabel.setBackground(new Color(0, 102, 204));
        footerLabel.setOpaque(true);

        // Panel inferior con área y pie de página
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(footerLabel, BorderLayout.SOUTH);

        //COLORES PARA BOTONES
        deleteButton.setBackground(Color.RED);
        updateButton.setBackground(new Color(132, 94, 194));
        addButton.setBackground(new Color(79, 251, 223));
        listButton.setBackground(new Color(0, 210, 252));
        searchButton.setBackground(new Color(0, 158, 250));

        //FONDO
        buttonPanel.setBackground(new Color(195, 195, 195));
        inputPanel.setBackground(new Color(195, 195, 195));
        area.setBackground(new Color(242, 240, 233));

        //COLORES TEXTO
        deleteButton.setForeground(Color.white);
        updateButton.setForeground(Color.white);
        addButton.setForeground(Color.white);
        listButton.setForeground(Color.white);
        searchButton.setForeground(Color.white);
        inputPanel.setForeground(Color.green);
        area.setForeground(new Color(132, 94, 194));

        //TEXTO
        area.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        deleteButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        updateButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        addButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        listButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        searchButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));

        // Agregar componentes al frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // AÑADIR
        addButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            try {
                Contacto contacto = new Contacto();
                contacto.setNombre(nombreField.getText());
                contacto.setApellido(apellidoField.getText());
                contacto.setTelefono(Integer.parseInt(telefonoField.getText()));

                if (contacto.getNombre().isEmpty() && contacto.getApellido().isEmpty()) {
                    area.append("❌ No se puede añadir un contacto con nombre y apellido vacíos.\n");
                } else if (agenda.agendaLlena()) {
                    area.append("📛 Agenda llena, no puedes ingresar más contactos.\n");
                } else if (agenda.existeContacto(contacto)) {
                    area.append("⚠️ El contacto ya existe.\n");
                } else {
                    if (agenda.espacioLibre()) {
                        area.append("ℹAún puedes ingresar contactos. Tienes "
                                + (9 - agenda.getAgenda().size())
                                + " espacios disponibles, para un máximo de 10 contactos.\n");
                        agenda.anadirContacto(contacto);
                        area.append("✅ Contacto añadido exitosamente.\n");
                    } else {
                        area.append("📛 No puedes ingresar más contactos. Límite de 10 alcanzado.\n");
                    }
                }
            } catch (Exception ex) {
                area.append("❌ Error al añadir contacto.\n");
            }

            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        // LISTAR
        listButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            if (agenda.getAgenda().isEmpty()) {
                area.append("📭 Lista vacía, nada para mostrar.\n");
            } else {
                area.append("📋 ---- Lista de contactos ----\n");
                for (Contacto cont : agenda.getAgenda()) {
                    area.append("👤 " + cont.getNombre() + " " + cont.getApellido() + " - 📞 " + cont.getTelefono() + "\n");
                }
                area.append("📚 ---- Fin de la lista ----\n");
            }
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        // BUSCAR
        searchButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            Contacto c = agenda.buscarContacto(nombre, apellido);
            if (c != null) {
                area.append("🔍 Encontrado: " + c.getNombre() + " " + c.getApellido() + " - 📞 " + c.getTelefono() + "\n");
            } else {
                area.append("❌ Contacto no encontrado.\n");
            }
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        // ACTUALIZAR
        updateButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            try {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                int telefono = Integer.parseInt(telefonoField.getText());
                agenda.modificarTelefono(nombre, apellido, telefono);
                area.append("🔁 Teléfono actualizado correctamente.\n");
            } catch (Exception ex) {
                area.append("❌ Error al actualizar teléfono.\n");
            }
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
        });

        // ELIMINAR
        deleteButton.addActionListener((ActionEvent e) -> {
            area.setText("");
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            Contacto c = agenda.buscarContacto(nombre, apellido);
            if (c != null) {
                agenda.eliminarContacto(c);
                area.append("🗑️ Contacto eliminado.\n");
            } else {
                area.append("❌ Contacto no encontrado para eliminar.\n");
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
