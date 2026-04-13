package itson.matchu_presentacion;

import itson.matchu_dominio.models.Estudiante;
import itson.matchu_servicios.EstudianteService;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ricardo
 */
public class LoginFrame extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private EstudianteService estudianteService;

    public LoginFrame() {
        this.estudianteService = new EstudianteService();
        initComponents();
    }

    private void initComponents() {
        setTitle("MatchU - Inicio de Sesión");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatos.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelDatos.add(txtCorreo);

        panelDatos.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelDatos.add(txtContrasena);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(e -> realizarLogin());

        add(new JLabel("BIENVENIDO A MATCHU", SwingConstants.CENTER));
        add(panelDatos);
        add(btnIngresar);
        
        JButton btnRegistro = new JButton("¿No tienes cuenta? Regístrate");
        btnRegistro.addActionListener(e -> {
        new RegistroFrame().setVisible(true);
        this.dispose();
        });
        add(btnRegistro);
    }

    private void realizarLogin() {
        String correo = txtCorreo.getText();
        String password = new String(txtContrasena.getPassword());

        try {
            Estudiante usuarioLogueado = estudianteService.login(correo, password);
            JOptionPane.showMessageDialog(this, "¡Bienvenido, " + usuarioLogueado.getNombre() + "!");
            
            
            new MainFrame(usuarioLogueado).setVisible(true);
            this.dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}