package modelo;

public class Receita {
    private int id;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private int tempoPreparo;
    private String categoria;

    // 🔹 Construtor vazio (necessário para Gson e para instanciar sem parâmetros)
    public Receita() {}

    // 🔹 Construtor completo (se quiser criar passando todos os dados de uma vez)
    public Receita(int id, String nome, String ingredientes, String modoPreparo, int tempoPreparo, String categoria) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
    return this.nome != null ? this.nome : "Selecione uma categoria";
}
    
    // 🔹 Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIngredientes() { return ingredientes; }
    public String getModoPreparo() { return modoPreparo; }
    public int getTempoPreparo() { return tempoPreparo; }
    public String getCategoria() { return categoria; }

    // 🔹 Setters
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }
    public void setTempoPreparo(int tempoPreparo) { this.tempoPreparo = tempoPreparo; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
