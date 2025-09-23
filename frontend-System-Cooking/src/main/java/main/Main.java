
package main;

import java.awt.CardLayout;
import java.awt.Color;
import api.ReceitaApiClient;
import exibirdetalhe.DetalheReceita;
import java.text.Normalizer;
import modelo.Receita;
import modelo.Categoria;  
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import java.util.ArrayList;



public class Main extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());
    private List<Receita> listaCompletaDeReceitas;
    private List<Categoria> listaCompletaDeCategorias;
    private final ReceitaApiClient apiClient;
    
    public Main() {
        initComponents();
         this.apiClient = new ReceitaApiClient(); 
        
        carregarDadosIniciaisDaAPI();
        popularTabela(this.listaCompletaDeReceitas); 
        popularComboBoxes(); //
        setBackground(new Color(0, 0, 0, 0));
        menu1.moverIniciar(Main.this);
        
    }
    
     private void carregarDadosIniciaisDaAPI() {
        this.listaCompletaDeReceitas = apiClient.buscarReceitas();
        this.listaCompletaDeCategorias = apiClient.buscarTodasCategorias();
    }
    
   private void popularTabela(List<Receita> receitas) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); 

    if (receitas == null) return;
    for (Receita r : receitas) {
        model.addRow(new Object[]{
            false, r.getId(), r.getNome(), r.getIngredientes(),
            r.getModoPreparo(), r.getTempoPreparo(), r.getCategoria()
        });
    }
}
private void popularComboBoxes() {
    ReceitaApiClient apiClient = new ReceitaApiClient();
    List<Categoria> categorias = this.listaCompletaDeCategorias; 

  
    filtro.removeAllItems();
    filtro.addItem("Todas as Categorias");
    if (categorias != null) {
        for (Categoria c : categorias) {
            filtro.addItem(c); // 
        }
    }

    // --- Popula o ComboBox de ADICIONAR ---
    comboCategoriaAdicionar.removeAllItems();
    comboCategoriaAdicionar.addItem("Selecione uma categoria"); 
    if (categorias != null) {
        for (Categoria c : categorias) {
            comboCategoriaAdicionar.addItem(c); 
        }
    }
}
    
//private void popularComboBoxCategorias() {;
//    ReceitaApiClient apiClient = new ReceitaApiClient();
//    List<Categoria> categorias = apiClient.buscarTodasCategorias();
//
//   
//    filtro.removeAllItems();
//    filtro.addItem("Todas as Categorias");
//    for (Categoria c : categorias) {
//        filtro.addItem(c.getNome());
//    }
//
//    comboCategoriaAdicionar.removeAllItems();
//    comboCategoriaAdicionar.addItem("Selecione uma categoria"); // Texto inicial
//    for (Categoria c : categorias) {
//        comboCategoriaAdicionar.addItem(c.getNome());
//    }
//}   

    
    // Função utilitária que remove acentos de uma String
    private String removerAcentos(String texto) {
    String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(normalizado).replaceAll("");
    }
    
    
    private void buscarReceitasPorNome() {
    String termo = abapesquisa.getText().trim();

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    jTable1.setRowSorter(sorter);

    if (termo.isEmpty()) {
        // Se não digitar nada, remove o filtro e mostra todas
        sorter.setRowFilter(null);
        return;
    }

    // remove acentos do termo buscado
    String termoSemAcento = removerAcentos(termo);

    // aplica filtro no campo "Nome" (coluna 1)
    sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
        @Override
        public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
            String nome = entry.getStringValue(2); // coluna Nome
            if (nome == null) return false;

            String nomeSemAcento = removerAcentos(nome);
            return nomeSemAcento.toLowerCase().contains(termoSemAcento.toLowerCase());
        }
    });
    
    if (jTable1.getRowCount() == 0) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Nenhuma receita encontrada! Digite um nome válido.",
            "Aviso",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
        // Opcional: limpa o filtro para voltar a exibir todas
        sorter.setRowFilter(null);
    }
    }
    
    private void atualizarTabela(List<Receita> receitas) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // limpa tabela

    for (Receita r : receitas) {
        model.addRow(new Object[]{
            false,
            r.getId(),
            r.getNome(),
            r.getIngredientes(),
            r.getModoPreparo(),
            r.getTempoPreparo(),
            r.getCategoria()
        });
    }
    }
    private void mostrarApenasReceitaNaTabela(Receita receita) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Limpa a tabela

    // Adiciona apenas a linha da receita especificada
    model.addRow(new Object[]{
        false,
        receita.getId(),
        receita.getNome(),
        receita.getIngredientes(),
        receita.getModoPreparo(),
        receita.getTempoPreparo(),
        receita.getCategoria()
    });

    // Preenche o campo de busca e o filtro de categoria para refletir o estado
    abapesquisa.setText(receita.getNome());
    filtro.setSelectedItem(receita.getCategoria());
}
    
    private void limparFormularioAdicionar() {
    nome.setText("");
    ingrediente.setText("");
    modop.setText("");
    tempop.setText("");
    comboCategoriaAdicionar.setSelectedIndex(0);
}
    private Receita getReceitaDoFormulario() {
    if (nome.getText().trim().isEmpty() || ingrediente.getText().trim().isEmpty() || 
        modop.getText().trim().isEmpty() || tempop.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    Object categoriaObj = comboCategoriaAdicionar.getSelectedItem();
   
  
 if (categoriaObj == null || !(categoriaObj instanceof Categoria)) {
    JOptionPane.showMessageDialog(this, "Por favor, selecione uma categoria válida!", "Erro", JOptionPane.ERROR_MESSAGE);
    return null;
}
    
     Categoria categoriaSelecionada = (Categoria) categoriaObj;

     Receita receita = new Receita();
    receita.setNome(nome.getText().trim());
    receita.setIngredientes(ingrediente.getText().trim());
    receita.setModoPreparo(modop.getText().trim());
    receita.setTempoPreparo(Integer.parseInt(tempop.getText().trim()));
    receita.setCategoria(categoriaSelecionada.getNome());
    
    return receita;
}

//private void finalizarAcaoComSucesso() {
//    popularTabela(); 
//    mostrarApenasReceitaNaTabela()
//    CardLayout card = (CardLayout) painelMain.getLayout();
//    card.show(painelMain, "inicio");
//    limparFormularioAdicionar();
//    
//}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBorda1 = new swing.PainelBorda();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover7 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover5 = new rojeru_san.complementos.RSButtonHover();
        menu1 = new componente.Menu();
        painelGradiente1 = new swing.PainelGradiente();
        painelGradiente2 = new swing.PainelGradiente();
        abapesquisa = new javax.swing.JTextField();
        rSButtonHover6 = new rojeru_san.complementos.RSButtonHover();
        filtro = new javax.swing.JComboBox<>();
        painelMain = new javax.swing.JPanel();
        Inicio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Adicionar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tempop = new javax.swing.JTextField();
        nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboCategoriaAdicionar = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ingrediente = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        modop = new javax.swing.JTextArea();
        editar = new javax.swing.JPanel();
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
        painelBorda1.add(rSButtonHover2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 118, 224, -1));

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

        filtro.setBackground(new java.awt.Color(66, 121, 204));
        filtro.setForeground(new java.awt.Color(255, 255, 255));
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
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
                .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        painelGradiente2Layout.setVerticalGroup(
            painelGradiente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGradiente2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(painelGradiente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(abapesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonHover6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                {null, "1", "sdas", "dsada", "dsadsa", null,  new Integer(1)}
            },
            new String [] {
                "Selecionar", "Id", "Nome", "Ingredientes", "Modo de Preparo", "Tempo de Preparo", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout InicioLayout = new javax.swing.GroupLayout(Inicio);
        Inicio.setLayout(InicioLayout);
        InicioLayout.setHorizontalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InicioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );
        InicioLayout.setVerticalGroup(
            InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelMain.add(Inicio, "inicio");

        Adicionar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome");

        tempop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempopActionPerformed(evt);
            }
        });

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Ingrediente");

        jLabel5.setText("Categoria");

        jLabel6.setText("Modo de Preparo");

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Tempo de Preparo");

        comboCategoriaAdicionar.setBackground(new java.awt.Color(66, 121, 204));
        comboCategoriaAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        comboCategoriaAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaAdicionarActionPerformed(evt);
            }
        });

        ingrediente.setColumns(20);
        ingrediente.setRows(5);
        jScrollPane2.setViewportView(ingrediente);

        modop.setColumns(20);
        modop.setRows(5);
        jScrollPane3.setViewportView(modop);

        javax.swing.GroupLayout AdicionarLayout = new javax.swing.GroupLayout(Adicionar);
        Adicionar.setLayout(AdicionarLayout);
        AdicionarLayout.setHorizontalGroup(
            AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdicionarLayout.createSequentialGroup()
                            .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdicionarLayout.createSequentialGroup()
                                    .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tempop)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(172, 172, 172)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdicionarLayout.createSequentialGroup()
                                    .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(254, 254, 254)
                                    .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboCategoriaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(272, 272, 272))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdicionarLayout.setVerticalGroup(
            AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdicionarLayout.createSequentialGroup()
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdicionarLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AdicionarLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCategoriaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(AdicionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        painelMain.add(Adicionar, "adicionar");

        editar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Raleway ExtraBold", 3, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pagina 3");

        javax.swing.GroupLayout editarLayout = new javax.swing.GroupLayout(editar);
        editar.setLayout(editarLayout);
        editarLayout.setHorizontalGroup(
            editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarLayout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        editarLayout.setVerticalGroup(
            editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelMain.add(editar, "editar");

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
        limparFormularioAdicionar();
        CardLayout card = (CardLayout) painelMain.getLayout();
        jButton1.setText("Salvar");
        card.show(painelMain, "adicionar");
         
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
    abapesquisa.setText("");
    filtro.setSelectedIndex(0);
    popularTabela(this.listaCompletaDeReceitas); // Mostra a lista completa
    
    CardLayout card = (CardLayout) painelMain.getLayout();
    card.show(painelMain, "inicio");
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void abapesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abapesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abapesquisaActionPerformed

    private void rSButtonHover5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover5ActionPerformed
   // Lista para guardar os IDs e nomes das receitas marcadas
    List<Integer> idsSelecionados = new ArrayList<>();
    List<String> nomesSelecionados = new ArrayList<>();
    
    // 1. Percorre a tabela para encontrar e contar os checkboxes marcados
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        // A coluna 0 é o checkbox (Boolean)
        Boolean isChecked = (Boolean) model.getValueAt(i, 0);
        
        if (isChecked != null && isChecked) {
            // Se a caixa estiver marcada, guarda o ID (coluna 1) e o Nome (coluna 2)
            int id = Integer.parseInt(model.getValueAt(i, 1).toString());
            String nome = model.getValueAt(i, 2).toString();
            idsSelecionados.add(id);
            nomesSelecionados.add(nome);
        }
    }

    // 2. Validação da quantidade de itens selecionados
    
    // CASO 1: Mais de uma receita selecionada
    if (idsSelecionados.size() > 1) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, selecione apenas UMA receita para remover de cada vez.", 
            "Seleção Múltipla Inválida", 
            JOptionPane.WARNING_MESSAGE);
        return; // Interrompe a ação
    }
    
    // CASO 2: Nenhuma receita selecionada
    if (idsSelecionados.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, selecione a receita que deseja remover.", 
            "Nenhuma Receita Selecionada", 
            JOptionPane.WARNING_MESSAGE);
        return; // Interrompe a ação
    }

    // 3. Se chegamos aqui, exatamente UMA receita foi selecionada. Prosseguir com a exclusão.
    int idParaDeletar = idsSelecionados.get(0);
    String nomeParaDeletar = nomesSelecionados.get(0);

    int confirm = JOptionPane.showConfirmDialog(this, 
        "Tem certeza que deseja deletar a receita \"" + nomeParaDeletar + "\"?",
        "Confirmar Exclusão", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        // Chama a API para deletar a receita única
        boolean sucesso = this.apiClient.deletarReceita(idParaDeletar);
        
        // Se a deleção funcionar, atualiza a tabela na tela
        if (sucesso) {
            // Busca a lista mais recente do banco de dados via API
            this.carregarDadosIniciaisDaAPI();
            // Repopula a tabela com os dados atualizados
            this.popularTabela(this.listaCompletaDeReceitas);
        }
    }
    }//GEN-LAST:event_rSButtonHover5ActionPerformed

    private void rSButtonHover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover6ActionPerformed
        buscarReceitasPorNome();
    }//GEN-LAST:event_rSButtonHover6ActionPerformed

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
      
    if (filtro.getSelectedItem() == null) {
        return;
    }

   
    Object itemSelecionado = filtro.getSelectedItem();
    
    
    List<Receita> receitasParaExibir = new ArrayList<>();

  
    if (itemSelecionado instanceof String) {
       
        receitasParaExibir = this.listaCompletaDeReceitas;
    } 
  
    else if (itemSelecionado instanceof Categoria) {
       
        String nomeCategoriaFiltro = ((Categoria) itemSelecionado).getNome();
  
        for (Receita receita : this.listaCompletaDeReceitas) {
            if (receita.getCategoria().equalsIgnoreCase(nomeCategoriaFiltro)) {
               
                receitasParaExibir.add(receita);
            }
        }
    }
    
    // No final, chama o método para popular a tabela com a lista resultante (que pode ser a completa ou a filtrada).
    popularTabela(receitasParaExibir);
    }//GEN-LAST:event_filtroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // Verifica se há um ID guardado. Se houver, estamos em MODO EDIÇÃO.
    Object idObj = nome.getClientProperty("idReceitaEditando");
    
    Receita receitaDoFormulario = getReceitaDoFormulario();
    if (receitaDoFormulario == null) return; // Se a validação falhar, para aqui

    boolean sucesso = false;
    Receita receitaResultante = null;

    if (idObj != null) { 
        // --- MODO EDIÇÃO ---
        int idParaAtualizar = (Integer) idObj;
        sucesso = apiClient.atualizarReceita(idParaAtualizar, receitaDoFormulario);
        if (sucesso) {
            receitaResultante = receitaDoFormulario;
            receitaResultante.setId(idParaAtualizar);
        }
    } else { 
        // --- MODO ADIÇÃO ---
        Receita receitaSalva = apiClient.salvarReceita(receitaDoFormulario);
        if (receitaSalva != null) {
            sucesso = true;
            receitaResultante = receitaSalva;
        }
    }

 
    if (sucesso) {
        carregarDadosIniciaisDaAPI(); // Atualiza a lista local com os dados do banco
        CardLayout card = (CardLayout) painelMain.getLayout();
        card.show(painelMain, "inicio"); // Volta para a tela principal
        mostrarApenasReceitaNaTabela(receitaResultante); // Mostra e foca na receita alterada/adicionada
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int contador = 0;
    int linhaSelecionada = -1;

  
    for (int i = 0; i < model.getRowCount(); i++) {
        Boolean marcado = (Boolean) model.getValueAt(i, 0); 
        if (marcado != null && marcado) {
            contador++;
            linhaSelecionada = i; // 
        }
    }

    
    if (contador == 0) {
       
        JOptionPane.showMessageDialog(this, "Por favor, selecione uma receita para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return; 
    } 
    
    if (contador > 1) {
        
        JOptionPane.showMessageDialog(this, "Por favor, selecione apenas UMA receita para editar de cada vez.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    
    int id = Integer.parseInt(model.getValueAt(linhaSelecionada, 1).toString());
    String nomeReceita = model.getValueAt(linhaSelecionada, 2).toString();
    String ingredientesReceita = model.getValueAt(linhaSelecionada, 3).toString();
    String modoPreparo = model.getValueAt(linhaSelecionada, 4).toString();
    int tempoPreparo = Integer.parseInt(model.getValueAt(linhaSelecionada, 5).toString());
    String categoriaReceita = model.getValueAt(linhaSelecionada, 6).toString();

   
    nome.setText(nomeReceita);
    ingrediente.setText(ingredientesReceita);
    modop.setText(modoPreparo);
    tempop.setText(String.valueOf(tempoPreparo));
    comboCategoriaAdicionar.setSelectedItem(categoriaReceita);

    
    nome.putClientProperty("idReceitaEditando", id);
    
    
    jButton1.setText("Salvar Alterações");

    
    CardLayout card = (CardLayout) painelMain.getLayout();
    card.show(painelMain, "adicionar");
    }//GEN-LAST:event_rSButtonHover7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2 && jTable1.getSelectedRow() != -1) {
        int row = jTable1.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Pegar os dados da linha
        String nome = (String) model.getValueAt(row, 2);
        String ingredientes = (String) model.getValueAt(row, 3);
        String modoPreparo = (String) model.getValueAt(row, 4);
        int tempo = (Integer) model.getValueAt(row, 5);
        String categoria = String.valueOf(model.getValueAt(row, 6));

        // Abrir janela de detalhes
        DetalheReceita dialog = new DetalheReceita(this, true, nome, ingredientes, modoPreparo, tempo, categoria);
        dialog.setVisible(true);
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void tempopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempopActionPerformed

    private void comboCategoriaAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaAdicionarActionPerformed

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
    private javax.swing.JTextField abapesquisa;
    private javax.swing.JComboBox<Object> comboCategoriaAdicionar;
    private javax.swing.JPanel editar;
    private javax.swing.JComboBox<Object> filtro;
    private javax.swing.JTextArea ingrediente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private componente.Menu menu1;
    private javax.swing.JTextArea modop;
    private javax.swing.JTextField nome;
    private swing.PainelBorda painelBorda1;
    private swing.PainelGradiente painelGradiente1;
    private swing.PainelGradiente painelGradiente2;
    private javax.swing.JPanel painelMain;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover5;
    private rojeru_san.complementos.RSButtonHover rSButtonHover6;
    private rojeru_san.complementos.RSButtonHover rSButtonHover7;
    private javax.swing.JTextField tempop;
    // End of variables declaration//GEN-END:variables
}