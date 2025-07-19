import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;


public class Screen extends JPanel implements ActionListener {
    //variables
     ImageIcon backgroundImage;
     ImageIcon flappyBird;
     ImageIcon topPipe;
     ImageIcon bottomPipe;
     Random randomCoordinatesForTopPipe;
     Random randomCoordinatesForBottomPipe;
     Image scaledTopPipe;
     Image scaledBottomPipe;
     Image scaledFlappyBird;
     
     
    
    
    
    public Screen() {
        //J(everything)
        setFocusable(true);
        backgroundImage = new ImageIcon("flappybirdbg.png");
        topPipe = new ImageIcon("toppipe.png");
        flappyBird = new ImageIcon("flappybird.png");
        bottomPipe = new ImageIcon("bottompipe.png");
        randomCoordinatesForTopPipe = new Random();
        randomCoordinatesForBottomPipe = new Random();
        scaledTopPipe = topPipe.getImage().getScaledInstance(40, 250, Image.SCALE_SMOOTH);
        scaledBottomPipe = bottomPipe.getImage().getScaledInstance(40, 200, Image.SCALE_SMOOTH);
        scaledFlappyBird = flappyBird.getImage().getScaledInstance(35, 30, Image.SCALE_SMOOTH);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(360,640);
    }

    //fuctions


    


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (backgroundImage != null) {
            graphics.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        if(scaledTopPipe != null) {
            graphics.drawImage(scaledTopPipe, 150, 30, this);
        }
        if (scaledBottomPipe != null) {
            graphics.drawImage(scaledBottomPipe, 150, 390, this);
        }
        if (flappyBird != null) {
            
            graphics.drawImage(scaledFlappyBird, 100, 400,this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }

    public class spaceButton extends JFrame implements KeyListener{
        
        
    }
}
