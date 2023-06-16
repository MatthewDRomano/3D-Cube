import java.awt.event.*;

public class KeyListenerMod implements KeyListener
{   
    Cube cube;
    public KeyListenerMod(Cube cube)
    {
        this.cube = cube;
    }
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_D -> cube.turningRight = true;
            case KeyEvent.VK_A -> cube.turningLeft = true;
            case KeyEvent.VK_W -> cube.turningUp = true;
            case KeyEvent.VK_S -> cube.turningDown = true;
            case KeyEvent.VK_Q -> cube.faceLeft = true;
            case KeyEvent.VK_E -> cube.faceRight = true;
        }            
    }
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_D -> cube.turningRight = false;
            case KeyEvent.VK_A -> cube.turningLeft = false;
            case KeyEvent.VK_W -> cube.turningUp = false;
            case KeyEvent.VK_S -> cube.turningDown = false;
            case KeyEvent.VK_Q -> cube.faceLeft = false;
            case KeyEvent.VK_E -> cube.faceRight = false;
        }            
    }
    public void keyTyped(KeyEvent e) {}
}