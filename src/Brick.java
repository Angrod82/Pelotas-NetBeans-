import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//Iplementa la interfaz drawable.
public class Brick implements Drawable {
    
    private float x;
    private float y;
    private float h;
    private float w;
    
    //Constructor
    public Brick (float x0, float y0, float w0, float h0) {
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
        //Establecemos el color con el que se va a pintar.
        g2.setColor(Color.ORANGE);
        //Pintamos un rectangulo relleno.
        g2.fillRect((int)x, (int)y, (int)w, (int)h);
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
    public Drawable colision(Drawable figura){
        Ball bola = (Ball)figura;
        float bolaX = bola.getX();
        float bolaY = bola.getY();
        float bolaR = bola.getRadio();
        //Calculamos la distancia entre el centro de la bola y el rectangulo.
        float distX = Math.abs(bolaX - x - w/2);
        float distY = Math.abs(bolaY - y - h/2);
        //Calculamos la distancia del centro de la bola a las esquinas del rectangulo.
        double distCorner = (distX - w/2 * distX - w/2) + (distY - h/2 * distY - h/2);
        //Comprobamos que realmente estamos colisionando con el rectangulo.
        if (distX > (w/2 + bolaR)) {
            return null;
        }
        if (distY > (h/2 + bolaR)) {
            return null;
        }
        //Comprobamos que colisiona tanto por la parte derecha como por la izquierda.
        if (distX <= w/2) {
            //Si choca por alguno de los lados cambiamos la dirección en el eje Y.
            bola.setDY(-bola.getDY());
            return bola;
        }
        //Comprobamos que colisiona por arriba o por abajo.
        if (distY <= h/2) {
            //Si cocha cambiamos la dirección en el eje X.
            bola.setDX(-bola.getDX());
            return bola;
        }
        //Comprobamos si colisiona por alguna de las esquinas.
        if (distCorner <= bolaR * bolaR) {
            //Si choca cambiamos ambas direcciones.
            bola.setDX(-bola.getDX());
            bola.setDY(-bola.getDY());
            return bola;
        }
        return null;
    }
}
