/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.matchu_presentacion;

import itson.daos.EstudianteDAO;
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_servicios.InteraccionService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Kamilala
 */
public class ExplorarFrame extends JFrame {

    private Estudiante estudianteLogueado;
    private InteraccionService interaccionService;
    private EstudianteDAO estudianteDAO;

    private List<Estudiante> listaEstudiantes;
    private int indiceActual = 0;

    private JLabel lblNombre, lblCarrera, lblSemestre, lblBio;
    private JButton btnLike, btnNoMeInteresa, btnVolver;
    private JPanel panelPerfil;

    public ExplorarFrame(Estudiante estudianteLogueado) {
        this.estudianteLogueado = estudianteLogueado;
        this.interaccionService = new InteraccionService();
        this.estudianteDAO = new EstudianteDAO();
        initComponents();
        cargarEstudiantes();
    }

    private void initComponents() {

        setTitle("MatchU - Explorar Estudiantes");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // 🔥 importante para animación

        JLabel lblTitulo = new JLabel("Explorar Estudiantes", SwingConstants.CENTER);
        lblTitulo.setBounds(0, 10, 420, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);

        panelPerfil = new JPanel(null);
        panelPerfil.setBounds(50, 60, 300, 200);
        panelPerfil.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(panelPerfil);

        lblNombre = new JLabel("", SwingConstants.CENTER);
        lblCarrera = new JLabel("", SwingConstants.CENTER);
        lblSemestre = new JLabel("", SwingConstants.CENTER);
        lblBio = new JLabel("", SwingConstants.CENTER);

        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblBio.setFont(new Font("Arial", Font.ITALIC, 12));

        lblNombre.setBounds(20, 10, 260, 30);
        lblCarrera.setBounds(20, 50, 260, 25);
        lblSemestre.setBounds(20, 80, 260, 25);
        lblBio.setBounds(20, 110, 260, 50);

        panelPerfil.add(lblNombre);
        panelPerfil.add(lblCarrera);
        panelPerfil.add(lblSemestre);
        panelPerfil.add(lblBio);

        btnNoMeInteresa = new JButton("No me interesa");
        btnNoMeInteresa.setBounds(50, 280, 130, 40);
        btnNoMeInteresa.setForeground(Color.RED);

        btnLike = new JButton("Me gusta");
        btnLike.setBounds(220, 280, 130, 40);
        btnLike.setForeground(new Color(0, 150, 0));

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 330, 100, 30);

        add(btnNoMeInteresa);
        add(btnLike);
        add(btnVolver);

        btnLike.addActionListener(e -> darLike());
        btnNoMeInteresa.addActionListener(e -> animarSwipe(false));
        btnVolver.addActionListener(e -> this.dispose());
    }

    private void cargarEstudiantes() {
        try {
            listaEstudiantes = estudianteDAO.listarPendientesDeEvaluar(
                estudianteLogueado.getIdEstudiante());
            mostrarActual();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes: " + ex.getMessage());
        }
    }

    private void mostrarActual() {
        if (listaEstudiantes == null || listaEstudiantes.isEmpty()
                || indiceActual >= listaEstudiantes.size()) {
            lblNombre.setText("No hay mas perfiles");
            lblCarrera.setText("");
            lblSemestre.setText("");
            lblBio.setText("Vuelve mas tarde.");
            btnLike.setEnabled(false);
            btnNoMeInteresa.setEnabled(false);
            return;
        }

        Estudiante actual = listaEstudiantes.get(indiceActual);
        lblNombre.setText(actual.getNombre() + " " + actual.getApellidos());
        lblCarrera.setText(actual.getCarrera() != null ? actual.getCarrera() : "Sin carrera");
        lblSemestre.setText("Semestre: " + (actual.getSemestre() != null ? actual.getSemestre() : "-"));
        lblBio.setText(actual.getBio() != null && !actual.getBio().isEmpty() ? actual.getBio() : "Sin bio");
    }

    private void darLike() {
        if (listaEstudiantes == null || indiceActual >= listaEstudiantes.size()) return;

        Estudiante receptor = listaEstudiantes.get(indiceActual);

        try {
            interaccionService.registrarLike(
                    estudianteLogueado.getIdEstudiante(),
                    receptor.getIdEstudiante()
            );

            animarSwipe(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void siguiente() {
        indiceActual++;
        mostrarActual();
    }
    
    private void animarSwipe(boolean like) {

        int velocidad = 20;
        int direccion = like ? 1 : -1;

        Timer timer = new Timer(10, null);

        timer.addActionListener(e -> {
            Point p = panelPerfil.getLocation();

            panelPerfil.setLocation(p.x + velocidad * direccion, p.y);

            panelPerfil.setBorder(BorderFactory.createLineBorder(
                    like ? Color.GREEN : Color.RED, 2));

            if (p.x > getWidth() || p.x < -panelPerfil.getWidth()) {
                timer.stop();

                panelPerfil.setLocation(50, 80);
                panelPerfil.setBorder(null);

                siguiente();
            }
        });

        timer.start();
    }
}