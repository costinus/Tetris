/**
 * Created by Constantine on 29.12.14.
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Figure extends JPanel{

    Figure figure;

    ArrayList<Point> coords;

    public static final int FIG_SIZE = 20;

    public static final int COLOR_MIN = 35;

    public static final int COLOR_MAX = 255 - COLOR_MIN;

    private Tetris tetris;

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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.translate(tetris.BORDER_WIDTH, tetris.BORDER_HEIGHT);

        g.setColor(Color.WHITE);
        for (Point fig: coords){
            int a = fig.x*FIG_SIZE;//60
            int b = fig.y*FIG_SIZE;//0
            g.fillRect(fig.x * FIG_SIZE, fig.y * FIG_SIZE, FIG_SIZE, FIG_SIZE);
        }

    }
}
