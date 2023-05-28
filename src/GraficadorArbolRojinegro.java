import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraficadorArbolRojinegro {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graficador de Árbol Rojinegro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        ArbolRojinegro arbol = new ArbolRojinegro();

        JPanel botonesPanel = new JPanel();
        frame.add(botonesPanel, BorderLayout.SOUTH);

        JButton insertarButton = new JButton("Insertar");
        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datoStr = JOptionPane.showInputDialog(frame, "Ingrese un número a insertar:");
                try {
                    int dato = Integer.parseInt(datoStr);
                    arbol.insertar(dato);
                    frame.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida");
                }
            }
        });
        botonesPanel.add(insertarButton);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datoStr = JOptionPane.showInputDialog(frame, "Ingrese un número a eliminar:");
                try {
                    int dato = Integer.parseInt(datoStr);
                    arbol.eliminar(dato);
                    frame.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Entrada inválida");
                }
            }
        });
        botonesPanel.add(eliminarButton);

        ArbolPanel panel = new ArbolPanel(arbol);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
