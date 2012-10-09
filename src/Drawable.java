import java.awt.Graphics;

//Interfaz que nos permite abstraernos del tipo de objeto con el que se vaya a trabajar.
public interface Drawable {
    public void render(Graphics CG);
    public void add(Drawable figura);
    public void removed(Drawable figura);
    public Drawable getChild(int index);
    public Drawable colision(Drawable figura);
}
