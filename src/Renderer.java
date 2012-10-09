import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

//Extiende la clase Thread.
public class Renderer extends Thread {
    
    //Atributos.
    private Drawable escena;
    private boolean Stopping;
    private Graphics CG;
    private Image DB;
    
    //Constructor.
    public Renderer(Drawable value, Graphics g) {
        escena = value;
        Stopping = true;
        CG = g;
    }
    
    //Implementacion de la función run heredada de Thread.
    @Override
    public void run() {
        //Obtenemos de la lista de elementos de la escena el boundary.
        Boundary contorno = (Boundary)escena.getChild(0);
        //Creamos un objeto del tipo BufferedImage para la implementación del doble buffer.
        DB = new BufferedImage((int)contorno.getW(), (int)contorno.getH(), BufferedImage.TYPE_INT_RGB);
        //Obtenemos el contexto grafico del BufferedImage.
        Graphics CGDB = DB.getGraphics();
        //Hacemos un cast del tipo Graphics2D al contexto grafico del BufferedImage.
        Graphics2D g2 = (Graphics2D)CGDB;
        //Bucle infinito.
        while(Stopping) {
            try {
                //Mandamos al hilo un tiempo a la cola.
                sleep(10);
                //Establecemos el color del fondo del contexto grafico del BufferedImage.
                g2.setBackground(Color.WHITE);
                //Invocamos al metodo render del composite.
                escena.render(CGDB);
                //Dibujamos la imagen pintada en el contexto grafico de BufferedImage
                //en el contexto grafico del JApplet.
                CG.drawImage(DB, 0, 0, null);
                //Borramos el area de dibujo del contexto grafico del BufferedImage.
                CGDB.clearRect((int)contorno.getX(), (int)contorno.getY(), (int)contorno.getW(), (int)contorno.getH());
            }
            catch (InterruptedException ex) {
                
            } 
        }
    }
    
    //Función seter.
    public void setStopping(boolean value) {
        Stopping = value;
    }
    
    //Función geter.
    public boolean getStopping() {
        return Stopping;
    }
}
