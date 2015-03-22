import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
/**
 * Created by Constantine on 13.03.15.
 */
public class Field extends JPanel{

    private final int COLS = 10;

    private final int VISIBLE_ROWS = 20;

    public int FIG_SIZE = 20;

    public int BORDER_WIDTH = 5;

    public int PANEL_WIDTH = COLS * FIG_SIZE;

    public int PANEL_HEIGHT = VISIBLE_ROWS * FIG_SIZE;

    private Tetris tetris;

    ArrayList<Point> field;

    private static final long serialVersionUID = 2181495598854992747L;

    public Field(Tetris tetris){
        this.tetris = tetris;
        field = new ArrayList<Point>(80);

        setPreferredSize(new Dimension(200, tetris.HEIGHT - 55));
        setBackground(Color.BLACK);
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
            Point newP = new Point();
            newP.x = coord.x;
            newP.y = coord.y+1;
            if((newP.y == 20) || (field.contains(newP)))
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
            Point newP = new Point();
            newP.x = coord.x+1;
            newP.y = coord.y;
            if((newP.x == 10) || (field.contains(newP)))
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
            Point newP = new Point();
            newP.x = coord.x-1;
            newP.y = coord.y;
            if((newP.x == -1) || (field.contains(newP)))
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
                Point tmpPoint = new Point(i,j);
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

    public void removeLine(ArrayList<Point> coordinates)
    {
        ArrayList<Integer> lines = fillingLine(coordinates);

        if (!lines.isEmpty())
        {
            for (Point filled:field)
            {
                //have to remove Points which has such y coordinates
                if(lines.contains(filled.getY()))
                {
                    field.remove(filled);
                }
                //moving down points if they are situated upper removing lines
                if (filled.y < lines.get(0))
                {
                    filled.setLocation(filled.x, filled.y+lines.size());
                }
            }
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //positioning relative to border
        g.translate(tetris.BORDER_WIDTH, tetris.BORDER_HEIGHT);

        //draw field tiles
        g.setColor(Color.WHITE);
        for (Point f: field){
            g.fillRect(f.x * FIG_SIZE, f.y * FIG_SIZE, FIG_SIZE, FIG_SIZE);
        }

        //background grid
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < COLS; x++){
            for (int y = 0; y < VISIBLE_ROWS; y++){
                g.drawLine(0, y * FIG_SIZE, COLS * FIG_SIZE, y * FIG_SIZE);
                g.drawLine(x * FIG_SIZE, 0, x * FIG_SIZE, VISIBLE_ROWS * FIG_SIZE);
            }
        }

        //border line
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, COLS * FIG_SIZE, VISIBLE_ROWS * FIG_SIZE);

    }
}