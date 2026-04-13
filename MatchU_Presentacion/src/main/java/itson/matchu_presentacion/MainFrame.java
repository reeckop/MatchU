
package itson.matchu_presentacion;

import itson.matchu_dominio.models.Estudiante;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Kamilala
 */


public class MainFrame extends JFrame {

    private Estudiante estudianteLogueado;
    private JButton btnExplorar, btnMatches, btnPerfil, btnCerrarSesion;

    public MainFrame(Estudiante estudianteLogueado) {
        this.estudianteLogueado = estudianteLogueado;
        initComponents();
    }

    private void initComponents() {
        setTitle("MatchU - Menu Principal");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblBienvenida = new JLabel(
            "Bienvenido, " + estudianteLogueado.getNombre(),
            SwingConstants.CENTER
        );
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

        JLabel lblInfo = new JLabel(
            estudianteLogueado.getCarrera() + " - Semestre " + estudianteLogueado.getSemestre(),
            SwingConstants.CENTER
        );
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblInfo.setForeground(Color.GRAY);

        JPanel panelNorth = new JPanel(new GridLayout(2, 1));
        panelNorth.add(lblBienvenida);
        panelNorth.add(lblInfo);
        add(panelNorth, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        btnExplorar = new JButton("Explorar Estudiantes");
        btnMatches = new JButton("Mis Matches");
        btnPerfil = new JButton("Mi Perfil");

        btnExplorar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMatches.setFont(new Font("Arial", Font.PLAIN, 14));
        btnPerfil.setFont(new Font("Arial", Font.PLAIN, 14));

        panelBotones.add(btnExplorar);
        panelBotones.add(btnMatches);
        panelBotones.add(btnPerfil);
        add(panelBotones, BorderLayout.CENTER);

        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setForeground(Color.RED);
        add(btnCerrarSesion, BorderLayout.SOUTH);

        btnExplorar.addActionListener(e -> {
            new ExplorarFrame(estudianteLogueado).setVisible(true);
        });

        btnMatches.addActionListener(e -> {
            new MatchesFrame(estudianteLogueado).setVisible(true);
        });

        btnPerfil.addActionListener(e -> {
            new PerfilFrame(estudianteLogueado).setVisible(true);
        });

        btnCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this, "Seguro que quieres cerrar sesion?",
                "Confirmar", JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginFrame().setVisible(true);
                this.dispose();
            }
        });
    }
}