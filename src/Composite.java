import java.awt.Graphics;
import java.util.*;

//Iplementa la interfaz drawable y su comportamiento es el del patron composite.
public class Composite implements Drawable {
    
    //Atributos.
    private List<Drawable> listaDrawables;
    
    //Constructor.
    public Composite() {
        //Declaramos que nuestro ArrayList de elemento va a ser sincronizado.
        listaDrawables = Collections.synchronizedList(new ArrayList<Drawable>());
    }
    
    //Implementacion de las funciones de la interfaz.
    @Override
    public void render(Graphics CG) {
        //Recorremos cada uno de los elementos e invocamos su función render.
        synchronized(listaDrawables) {
            for (Drawable elemento : listaDrawables) {
                elemento.render(CG);
            }
        }
    }
    
    @Override
    public void add(Drawable figura) {
        //Añadimos un objeto a la lista de elementos.
        synchronized(listaDrawables) {
            listaDrawables.add(figura);
        }
    }
    
    @Override
    public void removed(Drawable figura) {}
    
    @Override
    public Drawable getChild(int index) {
        //Obtenemos un objeto de la lista de elementos sin borrarlo.
        synchronized(listaDrawables) {
            return listaDrawables.get(index);
        }
    }
    
    @Override
    public Drawable colision(Drawable figura) {
        //Recorremos cada uno de los elementos e invocamos su función colision.
        Drawable auxDraw = null;
        synchronized(listaDrawables) {
            for (Drawable elemento : listaDrawables) {
                auxDraw = elemento.colision(figura);
                if (auxDraw != null) {
                    break;
                }
            }
            return auxDraw;
        }
    }
}
