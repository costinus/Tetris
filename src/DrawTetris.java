import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
/**
 * Created by Constantine on 23.03.15.
 */
public class DrawTetris extends JPanel {
    //class for drawing field and figure

    private static final long serialVersionUID = 2181495598854992747L;

    private final int COLS = 10;

    private final int VISIBLE_ROWS = 20;

    public int FIG_SIZE = 20;

    public int PANEL_WIDTH = COLS * FIG_SIZE;

    public int PANEL_HEIGHT = VISIBLE_ROWS * FIG_SIZE;

    private Tetris tetris;

    public ArrayList<Point> VisibleField;

    public DrawTetris(Tetris tetris){
        this.tetris = tetris;

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
    }

    public void addForDraw(Field field, Figure figure){
        VisibleField = new ArrayList<Point>(200);
        VisibleField.addAll(field.getFilledPoints());
        VisibleField.addAll(figure.getCoords());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //positioning relative to border
        g.translate(tetris.BORDER_WIDTH, tetris.BORDER_HEIGHT);

        if (tetris.isPaused){
            g.setColor(Color.WHITE);
            String msg = "PAUSED";
            g.drawString(msg, PANEL_WIDTH / 2 - g.getFontMetrics().stringWidth(msg) / 2, PANEL_HEIGHT / 2);
        }
        else if (tetris.isNewGame || tetris.isEndGame){
            //g.setFont(LARGE_FONT);
            g.setColor(Color.WHITE);

            String msg = tetris.isNewGame ? "TETRIS" : "GAME OVER";
            g.drawString(msg, PANEL_WIDTH / 2 - g.getFontMetrics().stringWidth(msg) / 2, PANEL_HEIGHT / 5);
            //g.setFont(SMALL_FONT);
            msg = "Press Enter to Play" + (tetris.isNewGame ? "" : " Again");
            g.drawString(msg, PANEL_WIDTH / 2 - g.getFontMetrics().stringWidth(msg) / 2, PANEL_HEIGHT / 3);
        }
        else{
            //draw score and level
            g.setColor(Color.WHITE);
            g.drawString("Level: " + tetris.level, tetris.BORDER_WIDTH + PANEL_WIDTH + 20, PANEL_HEIGHT / 2 - 10);
            g.drawString("Score: " + tetris.score, tetris.BORDER_WIDTH + PANEL_WIDTH + 20, PANEL_HEIGHT / 2 + 10);

            //draw field tiles
            g.setColor(Color.WHITE);
            for (Point f: VisibleField){
                if (f.y >= 0){
                    g.fillRect(f.x * FIG_SIZE, f.y * FIG_SIZE, FIG_SIZE, FIG_SIZE);
                }
            }

            //background grid
            g.setColor(Color.DARK_GRAY);
            for (int x = 0; x < COLS; x++){
                for (int y = 0; y < VISIBLE_ROWS; y++){
                    g.drawLine(0, y * FIG_SIZE, COLS * FIG_SIZE, y * FIG_SIZE);
                    g.drawLine(x * FIG_SIZE, 0, x * FIG_SIZE, VISIBLE_ROWS * FIG_SIZE);
                }
            }

        }
        //border line
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, COLS * FIG_SIZE, VISIBLE_ROWS * FIG_SIZE);
    }

}
