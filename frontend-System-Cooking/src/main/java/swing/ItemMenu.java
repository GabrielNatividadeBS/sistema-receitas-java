
package swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import modelo.ModeloMenu;


public class ItemMenu extends javax.swing.JPanel {

   
    

    private boolean selecionado;
    public ItemMenu(ModeloMenu dado) {
        initComponents();
        setOpaque(false);
        if (dado.getType() == ModeloMenu.TipoMenu.MENU) {
            lbmenu.setText(dado.getNome());
        } else if (dado.getType() == ModeloMenu.TipoMenu.TITULO) {
            lbmenu.setVisible(false);
        } else {
            lbmenu.setText(" ");
        }
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbmenu = new javax.swing.JLabel();

        lbmenu.setForeground(new java.awt.Color(255, 255, 255));
        lbmenu.setText("Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbmenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(lbmenu)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selecionado){
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255,255,255,00));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        }
        super.paintComponent(grphcs); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbmenu;
    // End of variables declaration//GEN-END:variables
}
