
package modelo;

import componente.Menu;


public class ModeloMenu {

    public ModeloMenu(String string, Object o, TipoMenu MENU) {
    }

    
    public String getNome() {
        return nome;
    }

   
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public TipoMenu getType() {
        return type;
    }


    public void setType(TipoMenu type) {
        this.type = type;
    }
    public String nome;
    private TipoMenu type;
    public static enum TipoMenu{
        TITULO, MENU, VAZIO
    }
}
