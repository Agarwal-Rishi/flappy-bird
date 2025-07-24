import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Time;
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
     int topPipeX;
     int topPipeY;
     int bottomPipeX;
     int bottomPipeY;
     

     
     
    
    
    
    public Screen() {
        //J(everything)
        setFocusable(true);
        topPipeX = 150;
        topPipeY = 30;
        bottomPipeX = 150;
        bottomPipeY = 390;
        backgroundImage = new ImageIcon("flappybirdbg.png");
        topPipe = new ImageIcon("toppipe.png");
        flappyBird = new ImageIcon("flappybird.png");
        bottomPipe = new ImageIcon("bottompipe.png");
        randomCoordinatesForTopPipe = new Random();
        randomCoordinatesForBottomPipe = new Random();
        scaledTopPipe = topPipe.getImage().getScaledInstance(40, 250, Image.SCALE_SMOOTH);
        scaledBottomPipe = bottomPipe.getImage().getScaledInstance(40, 200, Image.SCALE_SMOOTH);
        scaledFlappyBird = flappyBird.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(360,640);
    }

    //fuctions

    public void animate() {
        while (true) {
            //animation code
            bottomPipeX--;
            topPipeX--;
            if (topPipeX == -40) {
                topPipeX = 150;
                topPipe();
            }
            if (bottomPipeX == -40) {
                bottomPipeX = 150;
                bottomPipe();
            }
            try {
                Thread.sleep(10); // sleeps for 10 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // reset the interruption status
                e.printStackTrace(); // or handle it in some other way
            }
        }
    }


    


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (backgroundImage != null) {
            graphics.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        
        if(scaledTopPipe != null) {
            graphics.drawImage(scaledTopPipe, topPipeX, topPipeY, this);
        }
        if (scaledBottomPipe != null) {
            graphics.drawImage(scaledBottomPipe, bottomPipeX, bottomPipeY, this);
        }
        if (flappyBird != null) { 
            graphics.drawImage(scaledFlappyBird, 100, 400,this);
        }
    }

    public void topPipe(Graphics graphics) { //this funtion is made so we can use it as a method in another funtion
        if (scaledTopPipe != null) {
            graphics.drawImage(scaledTopPipe, topPipeX, topPipeY, this);
        }
    }

    public void bottomPipe(Graphics graphics) {
        if (scaledBottomPipe != null) {
            graphics.drawImage(scaledBottomPipe, bottomPipeX, bottomPipeY, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }


}
