package com.mycompany.receitas_culinarias.model;

public class Receita {
    private int id;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private int tempoPreparo;
    private String categoria;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    public String getModoPreparo() { return modoPreparo; }
    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }
    public int getTempoPreparo() { return tempoPreparo; }
    public void setTempoPreparo(int tempoPreparo) { this.tempoPreparo = tempoPreparo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
