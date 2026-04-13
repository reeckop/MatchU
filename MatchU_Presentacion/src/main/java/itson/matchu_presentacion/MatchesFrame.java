/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.matchu_presentacion;


import itson.daos.MatchDAO;
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_dominio.models.Match;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Kamilala
 */

public class MatchesFrame extends JFrame {

    private Estudiante estudianteLogueado;
    private MatchDAO matchDAO;

    public MatchesFrame(Estudiante estudianteLogueado) {
        this.estudianteLogueado = estudianteLogueado;
        this.matchDAO = new MatchDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("MatchU - Mis Matches");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Mis Matches", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> listaMatches = new JList<>(modelo);
        listaMatches.setFont(new Font("Arial", Font.PLAIN, 14));
        listaMatches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMatches.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
                return label;
            }
        });

        List<Match> matches = matchDAO.listarMatchesDeEstudiante(
                estudianteLogueado.getIdEstudiante());

        if (matches.isEmpty()) {
            modelo.addElement("Aun no tienes matches.");
            modelo.addElement("Sigue explorando!");
        } else {
            for (Match m : matches) {
                long idE1 = m.getEstudiante1().getIdEstudiante();
                long idYo = estudianteLogueado.getIdEstudiante();

                String nombreOtro;
                String carreraOtro;

                if (idE1 == idYo) {
                    nombreOtro = m.getEstudiante2().getNombre() + " " + m.getEstudiante2().getApellidos();
                    carreraOtro = m.getEstudiante2().getCarrera();
                } else {
                    nombreOtro = m.getEstudiante1().getNombre() + " " + m.getEstudiante1().getApellidos();
                    carreraOtro = m.getEstudiante1().getCarrera();
                }

                modelo.addElement(nombreOtro + " - " + carreraOtro);
            }
        }

        JScrollPane scroll = new JScrollPane(listaMatches);
        add(scroll, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> this.dispose());
        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);
    }
}