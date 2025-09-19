
package main;

import java.awt.CardLayout;
import java.awt.Color;
 import api.ReceitaApiClient;
import modelo.Receita;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    
    public Main() {
        initComponents();
        popularTabela();
        setBackground(new Color(0, 0, 0, 0));
        menu1.moverIniciar(Main.this);
        
    }
    private void popularTabela() {
        // 1. Apaga as linhas antigas da tabela
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // 2. Cria o cliente da API e busca as receitas
        ReceitaApiClient apiClient = new ReceitaApiClient();
        List<Receita> receitas = apiClient.buscarReceitas();

        // 3. Popula a tabela com os novos dados vindos da API
        for (Receita r : receitas) {
            model.addRow(new Object[]{
                r.getId(),
                r.getNome(),
                r.getIngredientes(),
                r.getModoPreparo(),
                r.getTempoPreparo(),
                r.getCategoria()
            });
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBorda1 = new swing.PainelBorda();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover7 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover5 = new rojeru_san.complementos.RSButtonHover();
        menu1 = new componente.Menu();
        painelGradiente1 = new com.mycompany.sistemareceitas.menusistema.componente.Painel_Gradiente.PainelGradiente();
        painelGradiente2 = new com.mycompany.sistemareceitas.menusistema.componente.Painel_Gradiente.PainelGradiente();
        abapesquisa = new javax.swing.JTextField();
        rSButtonHover6 = new rojeru_san.complementos.RSButtonHover();
        jComboBox1 = new javax.swing.JComboBox<>();
        painelMain = new javax.swing.JPanel();
        Inicio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Adicionar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        ingrediente = new javax.swing.JTextField();
        tempop = new javax.swing.JTextField();
        modop = new javax.swing.JTextField();
        nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        categoria = new javax.swing.JTextField();
        Remover = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        painelBorda1.setBackground(new java.awt.Color(255, 255, 255));
        painelBorda1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonHover1.setBackground(new java.awt.Color(66, 134, 244));
        rSButtonHover1.setText("Adicionar");
        rSButtonHover1.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        rSButtonHover1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        rSButtonHover1.setIconTextGap(0);
        rSButtonHover1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        painelBorda1.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 224, -1));

        rSButtonHover2.setBackground(new java.awt.Color(66, 134, 244));
        rSButtonHover2.setText("Inicio");
        rSButtonHover2.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        rSButtonHover2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        rSButtonHover2.setIconTextGap(0);
        rSButtonHover2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        painelBorda1.add(rSButtonHover2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 224, -1));

        rSButtonHover7.setBackground(new java.awt.Color(66, 134, 244));
        rSButtonHover7.setText("Editar");
        rSButtonHover7.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        rSButtonHover7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        rSButtonHover7.setIconTextGap(0);
        rSButtonHover7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rSButtonHover7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover7ActionPerformed(evt);
            }
        });
        painelBorda1.add(rSButtonHover7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 224, -1));

        rSButtonHover5.setBackground(new java.awt.Color(66, 134, 244));
        rSButtonHover5.setText("Remover");
        rSButtonHover5.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        rSButtonHover5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        rSButtonHover5.setIconTextGap(0);
        rSButtonHover5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rSButtonHover5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover5ActionPerformed(evt);
            }
        });
        painelBorda1.add(rSButtonHover5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 224, -1));
        painelBorda1.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 566));

        painelGradiente1.setCorFinal(new java.awt.Color(55, 73, 105));
        painelGradiente1.setCorInicial(new java.awt.Color(66, 134, 244));
        painelGradiente1.setRaioBorda(0);
        painelGradiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        painelBorda1.add(painelGradiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 0, 813, -1));

        painelGradiente2.setCorFinal(new java.awt.Color(66, 143, 224));
        painelGradiente2.setCorInicial(new java.awt.Color(66, 134, 244));
        painelGradiente2.setRaioBorda(0);

        abapesquisa.setBackground(new java.awt.Color(66, 134, 244));
        abapesquisa.setFont(new java.awt.Font("Raleway Light", 2, 14)); // NOI18N
        abapesquisa.setForeground(new java.awt.Color(255, 255, 255));
        abapesquisa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        abapesquisa.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        abapesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abapesquisaActionPerformed(evt);
            }
        });

        rSButtonHover6.setBackground(new java.awt.Color(66, 121, 204));
        rSButtonHover6.setText("Buscar");
        rSButtonHover6.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        rSButtonHover6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        rSButtonHover6.setIconTextGap(0);
        rSButtonHover6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rSButtonHover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover6ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(66, 121, 204));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Doce", "Salgado", "Almoço", "Janta" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelGradiente2Layout = new javax.swing.GroupLayout(painelGradiente2);
        painelGradiente2.setLayout(painelGradiente2Layout);
        painelGradiente2Layout.setHorizontalGroup(
            painelGradiente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGradiente2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(abapesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        painelGradiente2Layout.setVerticalGroup(
            painelGradiente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGradiente2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(painelGradiente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(abapesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        painelBorda1.add(painelGradiente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 820, 116));

        painelMain.setBackground(new java.awt.Color(255, 255, 255));
        painelMain.setAlignmentX(0.0F);
        painelMain.setAlignmentY(0.0F);
        painelMain.setLayout(new java.awt.CardLayout());

        Inicio.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "sdas", "dsada", "dsadsa",  new Float(1.0),  new Integer(1)}
            },
            new String [] {
                "Id", "Nome", "Ingredientes", "Modo de Preparo", "Tempo de Preparo", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                .addContainerGap())
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        painelMain.add(Inicio, "inicio");

        Adicionar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Nome");

        jLabel4.setBackground(new java.awt.Color(255, 0, 51));
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("ID");

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Ingrediente");

        jLabel5.setText("Categoria");

        jLabel6.setText("Modop");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tempop");

        javax.swing.GroupLayout AdicionarLayout = new javax.swing.GroupLayout(Adicionar);
        Adicionar.setLayout(AdicionarLayout);
        AdicionarLayout.setHorizontalGroup(
            AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarLayout.createSequentialGroup()
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdicionarLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ingrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modop, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tempop, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AdicionarLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jButton1)))
                .addContainerGap(543, Short.MAX_VALUE))
        );
        AdicionarLayout.setVerticalGroup(
            AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AdicionarLayout.createSequentialGroup()
                        .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ingrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(modop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jButton1)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        painelMain.add(Adicionar, "adicionar");

        Remover.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Raleway ExtraBold", 3, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pagina 3");

        javax.swing.GroupLayout RemoverLayout = new javax.swing.GroupLayout(Remover);
        Remover.setLayout(RemoverLayout);
        RemoverLayout.setHorizontalGroup(
            RemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoverLayout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        RemoverLayout.setVerticalGroup(
            RemoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RemoverLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelMain.add(Remover, "remover");

        painelBorda1.add(painelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 116, 815, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBorda1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBorda1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        CardLayout card = (CardLayout) painelMain.getLayout();
        card.show(painelMain, "adicionar");
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        CardLayout card = (CardLayout) painelMain.getLayout();
        card.show(painelMain, "inicio");
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void abapesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abapesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abapesquisaActionPerformed

    private void rSButtonHover5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover5ActionPerformed
        CardLayout card = (CardLayout) painelMain.getLayout();
        card.show(painelMain, "remover");
    }//GEN-LAST:event_rSButtonHover5ActionPerformed

    private void rSButtonHover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonHover6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int tempo = Integer.parseInt(tempop.getText());
        model.addRow(new Object []{id.getText(),nome.getText (), ingrediente.getText(), modop.getText(), tempo, categoria.getText()});
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonHover7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Adicionar;
    private javax.swing.JPanel Inicio;
    private javax.swing.JPanel Remover;
    private javax.swing.JTextField abapesquisa;
    private javax.swing.JTextField categoria;
    private javax.swing.JTextField id;
    private javax.swing.JTextField ingrediente;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private componente.Menu menu1;
    private javax.swing.JTextField modop;
    private javax.swing.JTextField nome;
    private swing.PainelBorda painelBorda1;
    private com.mycompany.sistemareceitas.menusistema.componente.Painel_Gradiente.PainelGradiente painelGradiente1;
    private com.mycompany.sistemareceitas.menusistema.componente.Painel_Gradiente.PainelGradiente painelGradiente2;
    private javax.swing.JPanel painelMain;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover5;
    private rojeru_san.complementos.RSButtonHover rSButtonHover6;
    private rojeru_san.complementos.RSButtonHover rSButtonHover7;
    private javax.swing.JTextField tempop;
    // End of variables declaration//GEN-END:variables
}
