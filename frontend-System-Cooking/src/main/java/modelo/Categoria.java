package modelo;

public class Categoria {
    
    private int id;
    private String nome;

    
    public Categoria() {
    }
    
    
      @Override
    public String toString() {
     
        if (this.nome == null) {
            return "Selecione uma categoria";
        }
        
    
        return this.nome;
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}