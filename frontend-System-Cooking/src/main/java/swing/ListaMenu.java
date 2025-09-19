
package swing;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import modelo.ModeloMenu;


public class ListaMenu<E extends Object> extends JList<E>{
    
    private final DefaultListModel modelo;
    
    public ListaMenu() {
        modelo = new DefaultListModel();
        setModel(modelo);
        
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int i, boolean bln, boolean bln1) {
                ModeloMenu dado;
                if (o instanceof ModeloMenu){
                    dado = (ModeloMenu) o;
                }else{
                    dado = new ModeloMenu("", o + "", ModeloMenu.TipoMenu.VAZIO);
                }
                ItemMenu item = new ItemMenu(dado);
                return item;
            }
            
        };
    }
    public void addItem(ModeloMenu dado){
        modelo.addElement(dado);
    }

}
