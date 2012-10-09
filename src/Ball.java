import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//Iplementa la interfaz drawable.
public class Ball implements Drawable {
    
    //Atributos
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float radio;
    private Color color;

    //Construtor
    public Ball (float x0, float y0, float dx0, float dy0, Color color0) {
        x = x0;
        y = y0;
        dx = dx0;
        dy = dy0;
        radio = 10;
        color = color0;
    }
    
    //Mover la bola
    public void move() {
        x += dx;
        y += dy;
    }
    
    //Implementacion de las funciones de la interfaz.
    @Override
    public void render(Graphics g) {
        //Hacemos un cast del tipo Gaphics a Graphics2D.
        Graphics2D g2 = (Graphics2D)g;
        //Establecemos el color con el que se va  pintar
        g2.setColor(color);
        //Pintamos un circulo relleno
        g2.fillOval((int)x-(int)radio, (int)y-(int)radio, (int)radio*2, (int)radio*2);
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
        //Calculamos la distancia entre el centro de las bolas tanto en el eje x como en el eje y.
        float distx = getX() - bola.getX();
        float disty = getY() - bola.getY();
        //Comprobamos que no le calculamos la colision la misma bola.
        if (this != bola) {
            //Comprobamos si las bola colisionan
            if (Math.sqrt((distx*distx) + (disty*disty)) <= (getRadio()+bola.getRadio())) {
                //Si colisionan intercambiamos los sentidos en el eje x e y entre si.
                float Daux = getDX();
                setDX(bola.getDX());
                bola.setDX(Daux);
                Daux = getDY();
                setDY(bola.getDY());
                bola.setDY(Daux);
                return bola;
            }
        }
        return null;
    }
    
    //Grupo de funciones seter.
    public void setX(float x0) {
        x = x0;
    }
    
    public void setY(float y0) {
        y = y0;
    }
    
    public void setDX(float dx0) {
        dx = dx0;
    }
    
    public void setDY(float dy0) {
        dy = dy0;
    }
    
    public void setRadio(float radio0) {
        radio = radio0;
    }
    
    //Grupo de funciones geter.
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getDX() {
        return dx;
    }
    
    public float getDY() {
        return dy;
    }
    
    public float getRadio() {
        return radio;
    } 
}
