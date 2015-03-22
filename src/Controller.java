import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Constantine on 16.03.15.
 */
public class Controller implements KeyListener {

    Tetris game;
    public boolean left, right, rotate, down, pause, enter;

    public Controller(Tetris game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.left)){
            left = true;
            if (game.field.checkLeftMove(game.figure.getCoords())){
                game.figure.moveleft();
            }
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.right)){
            right = true;
            if (game.field.checkRightMove(game.figure.getCoords())){
            game.figure.moveright();
            }
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.rotate)){
            rotate = true;
            game.figure.rotate();
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.down)){
            down = true;
            if (!game.isPaused){
                game.logictimer.setCyclesPerSecond(25.0f);
            }
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.pause)){
            pause = true;
            if(!game.isEndGame && !game.isNewGame) {
                game.isPaused = !game.isPaused;
                game.logictimer.setPaused(game.isPaused);
            }
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.enter)){
            enter = true;
            if(game.isEndGame || game.isNewGame) {
                game.resetGame();
            }
        }
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){
        if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.left)){
            left = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.right)){
            right = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.rotate)){
            rotate = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.down)){
            down = false;
            game.logictimer.setCyclesPerSecond(game.gameSpeed);
            game.logictimer.reset();
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.pause)){
            pause = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.enter)){
            enter = false;
        }
    }
}
