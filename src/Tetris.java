import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Constantine on 15.12.14.
 */
public class Tetris extends JFrame{

    public static final int WIDTH = 400, HEIGHT = 500;
    public static final int BORDER_WIDTH = 5, BORDER_HEIGHT = 30;
    private static final long FRAME_TIME = 1000L / 50L;
    public Clock logictimer;
    public boolean isNewGame = false;
    public boolean isPaused = false;
    public boolean isEndGame = false;
    public float gameSpeed;
    public int level;
    public int score;
    public int type;

    Controller control;
    //static JFrame frame;
    static Field field;
    static Figure figure;

    public static void main(String[] args)
    {

        /*frame = new JFrame("Tetris");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        final Tetris tetris = new Tetris();
        tetris.setBounds(0,25,WIDTH,HEIGHT-25);

        KeyGetter.loadKeys();
        try {
            Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JMenuBar bar = new JMenuBar();
        bar.setBounds(0,0,WIDTH,25);

        JMenu file = new JMenu("File");
        file.setBounds(0,0,45,24);

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting new game...");
                tetris.createFig();
            }
        });

        JMenuItem highScore = new JMenuItem("High Score");
        highScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int highscore = 0;
                final JFrame high = new JFrame("High Score");
                high.setSize(200,150);
                high.setLayout(null);
                high.setLocationRelativeTo(null);

                JLabel score = new JLabel("The high score is " + highscore);
                score.setBounds(0,0,200,50);

                JButton okayButton = new JButton("Ok");
                okayButton.setBounds(50,80,100,30);
                okayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        high.dispose();
                    }
                });

                high.add(score);
                high.add(okayButton);
                high.setResizable(false);
                high.setVisible(true);
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("closing");
                System.exit(0);
            }
        });

        JMenuItem options = new JMenuItem("Options");
        options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Config.openConfig(frame);
            }
        });


        frame.add(tetris);
        field = new Field();
        frame.add(field);

        file.add(newGame);
        file.add(highScore);
        file.add(options);
        file.add(exit);
        frame.add(bar);
        bar.add(file);
        frame.setVisible(true);
        tetris.start();*/
        Tetris tetris = new Tetris();
        tetris.start();
    }

    private Tetris(){
        super("Tetris");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLayout(null);
        //setResizable(false);

        setBounds(0, 25, WIDTH, HEIGHT - 25);

        KeyGetter.loadKeys();
        try {
            Config.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }

        field = new Field(this);
        add(field);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void start()
    {
        init();
        boolean running = true;
        isNewGame = true;
        gameSpeed = 1.0f;

        logictimer = new Clock(gameSpeed);
        logictimer.setPaused(true);

        while(running)
        {
            long start = System.nanoTime();

            logictimer.update();

            if (logictimer.hasElapsedCycle()){
                update();
            }

            render();

            long delta = (System.nanoTime() - start) / 1000000L;
            if(delta < FRAME_TIME) {
                try {
                    Thread.sleep(FRAME_TIME - delta);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void init(){
        control = new Controller(this);
        this.addKeyListener(control);
    }

    public void resetGame(){
        isNewGame = false;
        isEndGame = false;
        gameSpeed = 1.0f;
        level = 1;
        score = 0;
        field.clear();
        logictimer.reset();
        logictimer.setCyclesPerSecond(gameSpeed);
        createFig();
    }

    public void update(){

        ArrayList<Point> coords = figure.getCoords();
        if (field.checkForStop(coords)){
            figure.movedown();
        }
        else{
            field.addFigToField(coords);
            //check for filling lines and if it is then remove those lines
            field.removeLine(coords);

            gameSpeed += 0.035f;
            logictimer.setCyclesPerSecond(gameSpeed);
            logictimer.reset();

            createFig();

            if (!field.checkForStop(coords)){
                isEndGame = true;
                return;
            }
        }

    }

    public void createFig(){
        Random random = new Random();
        type = random.nextInt(2);
        figure = new Figure(type);
        if (!isNewGame){
            add(figure);
            isNewGame = true;
        }

    }

    public void render()
    {
        field.repaint();
        if (figure != null){
            figure.repaint();
            //figure.paintComponent(getGraphics());
        }
    }



}
