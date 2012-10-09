import java.awt.Color;
import java.awt.event.*;
import java.util.*;

//Extiende la clase Thread y ademas implementa la interfaz MouseListener.
public class Controller extends Thread implements MouseListener {
    
    //Atributos.
    private Drawable escena;
    private boolean Stopping, Bricks;
    private List<Ball> listaBalls;
    private Random rnd = new Random();

    //Constructor.
    public Controller(Drawable value) {
        escena = value;
        Stopping = true;
        Bricks = false;
        //Declaramos que nuestro ArrayList de bolas va a ser sincronizado.
        listaBalls = Collections.synchronizedList(new ArrayList<Ball>());
    }
    
    //Implementacion de la función run heredada de Thread.
    @Override
    public void run() {
        //Bucle infinito.
        while(Stopping) {
            try {
                //Mandamos el hilo a la cola durante un tiempo.
                sleep(10);
                synchronized(listaBalls) {
                    for(Ball bola: listaBalls) {
                        //Invocamos a la función colision del composite.
                        escena.colision(bola);
                        //Invocamos a la función move de la bola.
                        bola.move();
                    }
                }
            }
            catch (InterruptedException ex) {
                
            } 
        }
    }
    
    //Implementación de la funciones de la interfaz MouseListener.
    @Override
    public void mouseClicked(MouseEvent Mevent) {
        float x0,y0;
        int cont = 0;
        //Cuando ocurre un evento de raton de tipo click capturamos tanto la coordenada
        //x como la y del cursor.
        x0 = Mevent.getX();
        y0 = Mevent.getY();
        //Comprobamos que el evento lo haya generado el boton izquierdo del raton.
        if (Mevent.getButton() == MouseEvent.BUTTON1) {
            //Generamos un color aleatorio para la bola que vamos a crear.
            Color color = new Color(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
            //Creamos una bola en la coordenadas x e y del cursor.
            Ball bola = new Ball(x0,y0, 1+rnd.nextFloat(), 1+rnd.nextFloat(), color);
            synchronized(listaBalls) {
                //Añadimos la bola la lista de bolas del controller.
                listaBalls.add(bola);
            }
            //Añadimos la bola a la lsta de objeto de la escena.
            escena.add(bola);
        }
        //Comprobamos que el evento lo haya generado el boton derecho del raton.
        if (Mevent.getButton() == MouseEvent.BUTTON3) {
            //Comprobamos que no existan ya los bricks;
            if (!Bricks) {
                //Creamos cuatro objetos de tipo Brick.
                escena.add(new Brick(75, 95, 80, 50));
                escena.add(new Brick(247, 95, 80, 50));
                escena.add(new Brick(75, 215, 80, 50));
                escena.add(new Brick(247, 215, 80, 50));
                Bricks = true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent Mevent) {}

    @Override
    public void mouseReleased(MouseEvent Mevent) {}

    @Override
    public void mouseEntered(MouseEvent Mevent) {}

    @Override
    public void mouseExited(MouseEvent Mevent) {}
    
    //Función seter.
    public void setStopping(boolean value) {
        Stopping = value;
    }
    
    //Función geter.
    public boolean getStopping() {
        return Stopping;
    }
    
    //Función para añadir una bola a la lista.
    public void add(Ball bola) {
        listaBalls.add(bola);
    }
}
