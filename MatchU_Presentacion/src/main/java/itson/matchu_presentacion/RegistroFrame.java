
package itson.matchu_presentacion;


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Kamilala
 */
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_servicios.EstudianteService;


public class RegistroFrame extends JFrame {

    private JTextField txtNombre, txtApellidos, txtCorreo, txtCarrera;
    private JSpinner spnSemestre;
    private JTextArea txtBio;
    private JPasswordField txtContrasena, txtConfirmar;
    private JButton btnRegistrar, btnCancelar;
    private EstudianteService estudianteService;

    public RegistroFrame() {
        this.estudianteService = new EstudianteService();
        initComponents();
    }

    private void initComponents() {
        setTitle("MatchU - Registro");
        setSize(420, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Crear cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario
        JPanel panelForm = new JPanel(new GridLayout(9, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelForm.add(txtApellidos);

        panelForm.add(new JLabel("Correo institucional:"));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);

        panelForm.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelForm.add(txtContrasena);

        panelForm.add(new JLabel("Confirmar contraseña:"));
        txtConfirmar = new JPasswordField();
        panelForm.add(txtConfirmar);

        panelForm.add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        panelForm.add(txtCarrera);

        panelForm.add(new JLabel("Semestre:"));
        spnSemestre = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        panelForm.add(spnSemestre);

        panelForm.add(new JLabel("Bio (opcional):"));
        txtBio = new JTextArea(2, 20);
        panelForm.add(new JScrollPane(txtBio));

        add(panelForm, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnRegistrar = new JButton("Registrarme");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnRegistrar.addActionListener(e -> registrar());
        btnCancelar.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
    }

    private void registrar() {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String confirmar = new String(txtConfirmar.getPassword());
        String carrera = txtCarrera.getText().trim();
        int semestre = (int) spnSemestre.getValue();
        String bio = txtBio.getText().trim();

        // Validaciones en la vista
        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nombre, correo y contraseña son obligatorios.",
                "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(this,
                "Las contraseñas no coinciden.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un correo con el siguiente formato (correo@ejemplo)",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        

        try {
            Estudiante nuevo = new Estudiante();
            nuevo.setNombre(nombre);
            nuevo.setApellidos(apellidos);
            nuevo.setCorreo(correo);
            nuevo.setContrasena(contrasena);
            nuevo.setCarrera(carrera);
            nuevo.setSemestre(semestre);
            nuevo.setBio(bio);

            estudianteService.registrarEstudiante(nuevo);

            JOptionPane.showMessageDialog(this,
                "¡Cuenta creada exitosamente! Ahora puedes iniciar sesión.",
                "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

            new LoginFrame().setVisible(true);
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(), "Error al registrar",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}