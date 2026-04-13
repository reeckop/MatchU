/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package itson.matchu_presentacion;

import itson.matchu_dominio.models.Estudiante;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author daya
 */
public class Main extends JFrame{
    
    private Estudiante estudiante;

    public Main(Estudiante estudiante) {
        this.estudiante = estudiante;
        initComponents();
    }
    private void initComponents() {
        setTitle("MatchU - Principal");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblBienvenida = new JLabel(
                "Bienvenido/a, " + estudiante.getNombreCompleto(),
                JLabel.CENTER
        );

        JButton btnVerPerfil = new JButton("Ver mi perfil");
        btnVerPerfil.addActionListener(e -> mostrarPerfil());

        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVerPerfil);
        panelBotones.add(btnCerrarSesion);

        add(lblBienvenida, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarPerfil() {
        String mensaje = "Nombre: " + estudiante.getNombreCompleto()
                + "\nCorreo: " + estudiante.getCorreo()
                + "\nCarrera: " + estudiante.getCarrera()
                + "\nSemestre: " + estudiante.getSemestre();

        JOptionPane.showMessageDialog(this, mensaje, "Perfil", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
       
    }
    
}
