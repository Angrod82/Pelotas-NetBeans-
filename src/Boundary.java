import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//Iplementa la interfaz drawable.
public class Boundary implements Drawable {
    
    //Atributos
    private float x;
    private float y;
    private float h;
    private float w;
    
    //Constructor
    public Boundary (float x0, float y0, float w0, float h0) {
        x = x0;
        y = y0;
        h = h0;
        w = w0;
    }
    
    //Implementacion de las funciones de la interfaz.
    @Override
    public void render(Graphics g) {
        //Hacemos un cast del tipo Gaphics a Graphics2D.
        Graphics2D g2 = (Graphics2D)g;
        //Establecemos el tamaño del pincel con el que se va a pintar.
        g2.setStroke(new BasicStroke(5.0f));
        //Establecemos el color con el que se va  pintar
        g2.setColor(Color.BLACK);
        //Pintamos un rectangulo sin relleno.
        g2.drawRect((int)x, (int)y, (int)w, (int)h);
    }
    
    @Override
    public void add(Drawable figura) {}
    
    @Override
    public void removed(Drawable figura) {}
    
    @Override
    public Drawable getChild(int index) {
        return null;
    }
    
    @Override
    public Drawable colision(Drawable figura) {
        Ball bola = (Ball)figura;
        //Comprobamos que la bolas no se salgan ni por la derecha ni por la izquierda.
        if (bola.getX()-bola.getRadio() <= x || bola.getX()+bola.getRadio() >= w) {
            //Si se va a salir le cambiamos el sentido en el eje x.
            bola.setDX(-bola.getDX());
            return bola;
        }
        //Comprobamos que la bolas no se salgan ni por arriba ni por abajo.
        if (bola.getY()-bola.getRadio() <= y || bola.getY()+bola.getRadio() >= h) {
            //Si se va a salir le cambiamos el sentido en el eje y.            
            bola.setDY(-bola.getDY());
            return bola;
        }
        return null;
    }
    
    //Grupo de funciones geter.
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getW() {
        return w;
    }
    
    public float getH() {
        return h;
    }
    
    //Grupo de funciones seter.
    public void setX(float x0) {
       x = x0;
    }
    
    public void setY(float y0) {
        y = y0;
    }
    
    public void setW(float w0) {
        w = w0;
    }
    
    public void setH(float h0) {
        h = h0;
    }
}
