package Graphics;

import Logic.Data;
import Logic.Ordine;
import Logic.Piatto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JList ListaOrdini;
    private JList ListaPiattiOrdine;
    private JFrame frame;
    JList<Ordine> ordini = new JList<>();
    DefaultListModel<Ordine> model = new DefaultListModel<>();

    public Cuoco(){

        frame= new JFrame("Cuoco");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CuocoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //DefaultListModel<Piatto> piatto = new DefaultListModel();
        DefaultListModel m = new DefaultListModel();
        ListaOrdini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String data = ListaOrdini.getSelectedValue().toString();

                m.addElement(data);

                ListaPiattiOrdine.setModel(m);

            }
        });
    }

}
