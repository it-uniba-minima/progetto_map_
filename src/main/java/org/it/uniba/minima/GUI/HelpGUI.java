package org.it.uniba.minima.GUI;
import javax.swing.*;
import java.awt.*;

/**
 * The GUI of the help dialog.
 */
public class HelpGUI extends JFrame {
    /**
     * The instance of the class.
     */
    private static HelpGUI instance;

    /**
     * Constructor of the class.
     */
    private HelpGUI() {
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * Gets the instance.
     *
     * @return the instance
     */
    public static HelpGUI getInstance() {
        if (instance == null) {
            instance = new HelpGUI();
        }
        return instance;
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        // Create the label
        JLabel listaComandi = new JLabel();

        // Set the properties of the frame
        setTitle("Guida Comandi");
        setPreferredSize(new Dimension(500, 340));
        setMaximumSize(new Dimension(500, 340));
        setMinimumSize(new Dimension(500, 340));
        setResizable(false);
        getContentPane().setBackground(new Color(204, 173, 27));
        setIconImage(new ImageIcon("docs/img/gameIcon.jpg").getImage());

        // Set the properties of the label
        Font font = new Font("Papyrus", Font.BOLD, 12);
        listaComandi.setOpaque(true);
        listaComandi.setBackground(new Color(204, 173, 27));
        listaComandi.setForeground(Color.WHITE);
        listaComandi.setFont(font);
        listaComandi.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(107, 90, 13)));
        listaComandi.setText(
                "<html>" +
                        "Ecco la lista dei comandi:<br>" +
                        "AIUTO<br>" +
                        "NORD<br>" +
                        "SUD<br>" +
                        "EST<br>" +
                        "OVEST<br>" +
                        "OSSERVA<br>" +
                        "INVENTARIO<br>" +
                        "PRENDI<br>" +
                        "LASCIA<br>" +
                        "USA<br>" +
                        "UNISCI<br>" +
                        "PARLA<br>" +
                        "DAI" +
                        "</html>"
        );

        // Set the layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(listaComandi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(listaComandi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
