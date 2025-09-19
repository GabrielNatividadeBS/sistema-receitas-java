package modelo;

public class Receita {
    private int id;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private int tempoPreparo;
    private String categoria;

    public Receita(int id, String nome, String ingredientes, String modoPreparo, int tempoPreparo, String categoria) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIngredientes() { return ingredientes; }
    public String getModoPreparo() { return modoPreparo; }
    public int getTempoPreparo() { return tempoPreparo; }
    public String getCategoria() { return categoria; }
}
