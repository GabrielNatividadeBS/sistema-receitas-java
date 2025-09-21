package exibirdetalhe;


import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DetalheReceita extends JDialog {

    public DetalheReceita(Frame parent, boolean modal, String nome, String ingredientes, String modoPreparo, int tempo, String categoria) {
        super(parent, modal);
        setTitle("Detalhes da Receita");
        setSize(400, 400);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("🍽️ " + nome));
        panel.add(new JLabel("Categoria: " + categoria));
        panel.add(new JLabel("⏱ Tempo de preparo: " + tempo + " min"));

        panel.add(new JLabel("📋 Ingredientes:"));
        JTextArea txtIng = new JTextArea(ingredientes);
        txtIng.setLineWrap(true);
        txtIng.setWrapStyleWord(true);
        txtIng.setEditable(false);
        panel.add(new JScrollPane(txtIng));

        panel.add(new JLabel("📖 Modo de Preparo:"));
        JTextArea txtModo = new JTextArea(modoPreparo);
        txtModo.setLineWrap(true);
        txtModo.setWrapStyleWord(true);
        txtModo.setEditable(false);
        panel.add(new JScrollPane(txtModo));

        add(panel);
    }
}
