/**
 * Created by Constantine on 29.12.14.
 */
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;


public class Figure{

    private Tetris tetris;

    private Color color;

    Figure figure;

    ArrayList<Point> coords;

    public Figure(){

    }

    public Figure(int type){

        if(type == 0)
        {
            Figure stick = new Stick();
            figure = stick;
            coords = stick.getCoords();
        }
        else if(type == 1)
        {
            Figure square = new Square();
            figure = square;
            coords = square.getCoords();
        }
        else {}
    }

    public ArrayList<Point> getCoords(){
        return coords;
    }

    public void rotate(){
        figure.rotate();
    }
    public void movedown(){
        figure.movedown();
    }
    public void moveright(){
        figure.moveright();
    }
    public void moveleft(){
        figure.moveleft();
    }
}
