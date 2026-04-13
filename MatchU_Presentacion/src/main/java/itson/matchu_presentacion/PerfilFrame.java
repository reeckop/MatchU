/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package itson.matchu_presentacion;

import itson.matchu_dominio.models.Estudiante;
import itson.matchu_servicios.EstudianteService;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Kamilala
 */

public class PerfilFrame extends JFrame {

    private Estudiante estudianteLogueado;
    private EstudianteService estudianteService;

    private JTextField txtNombre, txtApellidos, txtCarrera;
    private JSpinner spnSemestre;
    private JTextArea txtBio;
    private JButton btnGuardar, btnVolver;

    public PerfilFrame(Estudiante estudianteLogueado) {
        this.estudianteLogueado = estudianteLogueado;
        this.estudianteService = new EstudianteService();
        initComponents();
    }

    private void initComponents() {
        setTitle("MatchU - Mi Perfil");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Mi Perfil", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(6, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(estudianteLogueado.getNombre());
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField(estudianteLogueado.getApellidos());
        panelForm.add(txtApellidos);

        panelForm.add(new JLabel("Correo:"));
        JTextField txtCorreo = new JTextField(estudianteLogueado.getCorreo());
        txtCorreo.setEditable(false);
        txtCorreo.setForeground(Color.GRAY);
        panelForm.add(txtCorreo);

        panelForm.add(new JLabel("Carrera:"));
        txtCarrera = new JTextField(estudianteLogueado.getCarrera());
        panelForm.add(txtCarrera);

        panelForm.add(new JLabel("Semestre:"));
        spnSemestre = new JSpinner(new SpinnerNumberModel(
            estudianteLogueado.getSemestre() != null ? estudianteLogueado.getSemestre() : 1,
            1, 12, 1
        ));
        panelForm.add(spnSemestre);

        panelForm.add(new JLabel("Bio:"));
        txtBio = new JTextArea(2, 20);
        txtBio.setText(estudianteLogueado.getBio() != null ? estudianteLogueado.getBio() : "");
        panelForm.add(new JScrollPane(txtBio));

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar cambios");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardar());
        btnVolver.addActionListener(e -> this.dispose());
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String carrera = txtCarrera.getText().trim();
        int semestre = (int) spnSemestre.getValue();
        String bio = txtBio.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El nombre es obligatorio.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            estudianteLogueado.setNombre(nombre);
            estudianteLogueado.setApellidos(apellidos);
            estudianteLogueado.setCarrera(carrera);
            estudianteLogueado.setSemestre(semestre);
            estudianteLogueado.setBio(bio);

            estudianteService.actualizarEstudiante(estudianteLogueado);

            JOptionPane.showMessageDialog(this,
                "Perfil actualizado correctamente.",
                "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}