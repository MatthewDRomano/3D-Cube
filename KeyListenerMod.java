import java.awt.event.*;

public class KeyListenerMod implements KeyListener
{   
    Cube ellipse;
    public KeyListenerMod(Cube ellipse)
    {
        this.ellipse = ellipse;
    }
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            ellipse.setRightStatus(true);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            ellipse.setLeftStatus(true);
    }
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            ellipse.setRightStatus(false);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            ellipse.setLeftStatus(false);
    }
    public void keyTyped(KeyEvent e) {}
}
