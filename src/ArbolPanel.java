import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class ArbolPanel extends JPanel {
    private ArbolRojinegro arbol;

    public ArbolPanel(ArbolRojinegro arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g, getWidth() / 2, 30, arbol.raiz, getWidth() / 4);
    }

    private void dibujarArbol(Graphics g, int x, int y, Nodo nodo, int espacio) {
        if (nodo != null) {
            g.setColor(nodo.esRojo ? Color.RED : Color.BLACK);
            g.fillOval(x - 15, y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(nodo.dato), x - 5, y + 5);

            if (nodo.izquierdo != null) {
                g.setColor(Color.GRAY);
                g.drawLine(x, y, x - espacio, y + 30);
                dibujarArbol(g, x - espacio, y + 30, nodo.izquierdo, espacio / 2);
            }

            if (nodo.derecho != null) {
                g.setColor(Color.GRAY);
                g.drawLine(x, y, x + espacio, y + 30);
                dibujarArbol(g, x + espacio, y + 30, nodo.derecho, espacio / 2);
            }
        }
    }
}
