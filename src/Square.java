import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Constantine on 15.12.14.
 */
public class Square extends Figure {

    private ArrayList<Point> array;//coords

    public Square()
    {
        array = new ArrayList<Point>(4);
        array.add(new Point(3,0));
        array.add(new Point(4,0));
        array.add(new Point(3,1));
        array.add(new Point(4,1));
    }

    @Override
    public void rotate(){}

    public ArrayList<Point> getCoords(){
        return array;
    }
    @Override
    public void movedown(){
        for(Point p:array)
        {
            p.setLocation(p.x,p.y+1);
        }
    }
    @Override
    public void moveright(){
        for(Point p:array)
        {
            p.setLocation(p.x+1,p.y);
        }
    }
    @Override
    public void moveleft(){
        for(Point p:array)
        {
            p.setLocation(p.x-1,p.y);
        }
    }
}
