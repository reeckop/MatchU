/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.matchu_presentacion;

import itson.matchu_dominio.models.Estudiante;
import itson.matchu_servicios.EstudianteService;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author daya
 */
public class RegistroFrame extends JFrame {
     private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtContrasena;
    private JTextField txtCarrera;
    private JTextField txtSemestre;

    private JButton btnRegistrar;
    private JButton btnVolver;

    private EstudianteService estudianteService;

    public RegistroFrame() {
        estudianteService = new EstudianteService();
        initComponents();
    }

    private void initComponents() {
        setTitle("MatchU - Registro");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 5, 5));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        add(txtApellidos);

        add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        add(txtCorreo);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField();
        add(txtContrasena);

        add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        
         add(new JLabel("Semestre:"));
        txtSemestre = new JTextField();
        add(txtSemestre);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarEstudiante());

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        add(btnRegistrar);
        add(btnVolver);
    }

    private void registrarEstudiante() {
        try {
            String nombre = txtNombre.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String correo = txtCorreo.getText().trim();
            String contrasena = txtContrasena.getText().trim();
            String carrera = txtCarrera.getText().trim();
            Integer semestre = Integer.valueOf(txtSemestre.getText().trim());

            Estudiante estudiante = new Estudiante(
                    nombre,
                    apellidos,
                    correo,
                    contrasena,
                    carrera,
                    semestre
            );

            estudianteService.registrarEstudiante(estudiante);

            JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente.");
            new LoginFrame().setVisible(true);
            this.dispose();
            
             } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El semestre debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

