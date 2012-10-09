import java.awt.Color;
import java.util.Random;
import javax.swing.JApplet;

//Extiende la clase JApplet
public class Principal extends JApplet {
    
    //Implementación de la función init heredada de JApplet.
    @Override
    public void init() {
        //Establecemos el tamaño del Applet
        this.setSize(400, 400);
        //Creamos una variable que genera números aleatorios.
        Random rnd = new Random();
        //Generamos un color aleatorio para la primera bola que creamos.
        Color color = new Color(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
        //Creamos el objeto de tipo Ball.
        Drawable bola = new Ball((getWidth()/2), (getHeight()/2), 1+rnd.nextFloat(), 1+rnd.nextFloat(), color);
        //Creamos el objeto de tipo Boundary.
        Drawable contorno = new Boundary(getX(), getY(), getWidth(), getHeight());
        //Creamos el objeto de tipo Composite.
        Drawable escena = new Composite();
        //Creamos el objeto de tipo Renderer.
        Renderer renderer = new Renderer(escena, getGraphics());
        //Creamos el objeto de tipo Controller.
        Controller controller = new Controller(escena);
        //Añadimos un MouseListener e indicamos quien lo implementa.
        addMouseListener(controller);
        //Añadimos a la lista de elementos del Composite tanto el Boundary como el Ball.
        escena.add(contorno);
        escena.add(bola);
        //Añadimos a la lista de bolas del controller la el objeto de tipo Ball.
        controller.add((Ball)bola);
        //Arancamos los hilos de renderer y controller.
        renderer.start();
        controller.start();
    }
}
