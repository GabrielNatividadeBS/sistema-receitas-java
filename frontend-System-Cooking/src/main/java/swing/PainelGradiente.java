package com.mycompany.sistemareceitas.menusistema.componente.Painel_Gradiente;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.beans.BeanProperty;

/**
 * Painel Gradiente Horizontal com cores configuráveis e bordas arredondadas
 */
public class PainelGradiente extends JPanel {

    private Color corInicial = Color.BLUE;
    private Color corFinal = Color.MAGENTA;
    private int raioBorda = 20; // valor padrão do arredondamento

    public PainelGradiente() {
        super();
        setOpaque(false); // permite o gradiente
    }

    // Getter e Setter da cor inicial
    public Color getCorInicial() {
        return corInicial;
    }

    @BeanProperty(description = "Cor inicial do gradiente")
    public void setCorInicial(Color corInicial) {
        this.corInicial = corInicial;
        repaint();
    }

    // Getter e Setter da cor final
    public Color getCorFinal() {
        return corFinal;
    }

    @BeanProperty(description = "Cor final do gradiente")
    public void setCorFinal(Color corFinal) {
        this.corFinal = corFinal;
        repaint();
    }

    // Getter e Setter do raio da borda (arredondamento)
    public int getRaioBorda() {
        return raioBorda;
    }

    @BeanProperty(description = "Raio das bordas arredondadas")
    public void setRaioBorda(int raioBorda) {
        this.raioBorda = Math.max(0, raioBorda); // evita valores negativos
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int largura = getWidth();
        int altura = getHeight();

        // Gradiente horizontal
        GradientPaint gp = new GradientPaint(0, 0, corInicial, largura, 0, corFinal);
        g2d.setPaint(gp);

        // Desenha retângulo com bordas arredondadas
        g2d.fillRoundRect(0, 0, largura, altura, raioBorda, raioBorda);
    }
}
