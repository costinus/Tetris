/**
 * Created by Constantine on 15.12.14.
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Stick extends Figure {
    private ArrayList<Point> array;//coords

    public Stick()
    {
        array = new ArrayList<Point>(4);
        Random random = new Random();
        int tr = random.nextInt(2);
        if(tr == 1)//vertical
        {
            array.add(new Point(4,0));
            array.add(new Point(4,1));
            array.add(new Point(4,2));
            array.add(new Point(4,3));
        }
        else//horizontal
        {
            array.add(new Point(2,0));
            array.add(new Point(3,0));
            array.add(new Point(4,0));
            array.add(new Point(5,0));
        }

    }

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

    @Override
    public void rotate()
    {
        int i = array.get(0).x;
        int j = array.get(0).y;
        int x = 0;
        int y = 0;
        if (array.get(0).getX() == array.get(1).getX())//if vertical then rotate to horizontal
        {
            if (!(i == 0 || i == 1 || i == 9))
            {
                for(Point p:array)
                {
                    p.setLocation(i - 2 + x, j + 1);
                    x++;
                }
            }
            else if (i == 0)
            {
                for(Point p:array)
                {
                    p.setLocation(i + x, j + 1);
                    x++;
                }
            }
            else if (i == 1)
            {
                for(Point p:array)
                {
                    p.setLocation(i - 1 + x, j + 1);
                    x++;
                }
            }
            else
            {
                for(Point p:array)
                {
                    p.setLocation(i - 3 + x, j + 1);
                    x++;
                }
            }
        }
        else//horizontal to vertical
        {
            for(Point p:array)
            {
                p.setLocation(i + 2, j - 1 + y);
                y++;
            }
        }
    }
}