import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
/**
 * Created by Constantine on 13.03.15.
 */
public class Field {

    private Tetris tetris;

    ArrayList<Point> field;

    public Field(){
        field = new ArrayList<Point>(80);
    }

    public ArrayList<Point> getFilledPoints(){

        ArrayList<Point> coords = new ArrayList<Point>(200);
        for (Point f: field){
            coords.add(f);
        }
        return coords;
    }

    public void addFigToField(ArrayList<Point> coordinates)
    {
        field.addAll(coordinates);
    }

    public void clear(){
        field.clear();
    }

    public boolean checkForStop(ArrayList<Point> coordinates)
    {//check for falling down of figures
        for(Point coord:coordinates)
        {
            Point p = new Point(coord);
            p.y = p.y + 1;
            if((p.y == 20) || (field.contains(p)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean checkRightMove(ArrayList<Point> coordinates)
    {

        for(Point coord: coordinates)
        {
            Point maxX = new Point(coord);
            maxX.x = maxX.x+1;

            if((maxX.x == 10) || (field.contains(maxX)))
            {
                return false;
            }
        }



        return true;
    }

    public boolean checkLeftMove(ArrayList<Point> coordinates)
    {

        for(Point coord: coordinates)
        {
            Point minX = new Point(coord);
            minX.x = minX.x-1;

            if((minX.x == -1) || (field.contains(minX)))
            {
                return false;
            }
        }


        return true;
    }

    public ArrayList fillingLine(ArrayList<Point> coordinates)
    {
        ArrayList lines = new ArrayList(4);

        boolean isLine = false;

        for(int i = 0; i < 20; i++)//rows
        {
            for(int j = 0; j < 10; j++)//columns
            {
                Point tmpPoint = new Point(j,i);
                if(!field.contains(tmpPoint))
                {
                    isLine = false;
                    break;
                }
                else
                {
                    isLine = true;
                }
            }
            if(isLine)
            {
                lines.add(i);
            }
        }

        return lines;//lines for removing = y coord
    }

    public int removeLine(ArrayList<Point> coordinates)
    {
        ArrayList<Integer> lines = fillingLine(coordinates);

        if (!lines.isEmpty())
        {
            for (int y = 19; y >= 0; y--){
                if (lines.contains(y)){
                    for (int x = 9; x >= 0; x--){
                        field.remove(new Point(x, y));
                    }
                }
            }


            for (Point filled:field)
            {
                //moving down points if they are situated upper removing lines
                if (filled.y < lines.get(0))
                {
                    filled.setLocation(filled.x, filled.y+lines.size());
                }
            }
        }
        return lines.size();
    }

    public boolean checkForEndGame(ArrayList<Point> coordinates){
        for (Point p: coordinates){
            if (field.contains(p)){
                //true = game over
                return true;
            }
        }
        return false;

    }

}