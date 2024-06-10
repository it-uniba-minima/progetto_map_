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
        setIconImage(new ImageIcon("src/main/resources/docs/img/gameIcon.jpg").getImage());

        // Set the properties of the label
        Font font = new Font("Papyrus", Font.BOLD, 12);
        listaComandi.setOpaque(true);
        listaComandi.setBackground(new Color(204, 173, 27));
        listaComandi.setForeground(Color.BLACK);
        listaComandi.setFont(font);
        listaComandi.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(107, 90, 13)));
        listaComandi.setText(
                "<html>" +
                        "Comandi di movimento:<br>" +
                        "Nord - Permette all'utente di muoversi in avanti<br>" +
                        "Est - Permette all'utente di muoversi a destra<br>" +
                        "Sud - Permette all'utente di muoversi indietro<br>" +
                        "Ovest - Permette all'utente di muoversi a sinistra<br>" +
                        "<br>Comandi di gioco:<br>" +
                        "Inventario - Mostra l'inventario dell'utente<br>" +
                        "Aiuto - Mostra i comandi disponibili<br>" +
                        "Osserva - Mostra la descrizione della stanza.<br>" +
                        "Osserva [oggetto|personaggio] - Mostra la descrizione dell'oggetto o del personaggio, presente nella stanza.<br>" +
                        "Usa [oggetto] - Utilizza l'oggetto specificato.<br>" +
                        "Usa [oggetto1] [oggetto2] - Utilizza l'oggetto1 sull'oggetto2.<br>" +
                        "Prendi [oggetto] - Prendi l'oggetto specificato.<br>" +
                        "Lascia [oggetto] - Lascia l'oggetto specificato, deve essere presente nell'inventario.<br>" +
                        "Parla [personaggio] - Parla con il personaggio specificato.<br>" +
                        "Fondi [oggetto1] [oggetto2] - Fonde l'oggetto1 con l'oggetto2, creando un [oggetto3] presente nell'inventario<br>" +
                        "Dai [oggetto] [personaggio] - Dai l'oggetto specificato al personaggio specificato.<br>" +
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
