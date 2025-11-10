import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Random;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.random.*;


public class Screen extends JPanel implements ActionListener, KeyListener {
    //variables
    ImageIcon backgroundImage;
    ImageIcon flappyBird;
    ImageIcon topPipe;
    ImageIcon bottomPipe;

    Image scaledFlappyBird;
    Image scaledTopPipe;
    Image scaledBottomPipe;

    int topPipeX;
    int topPipeY;
    int bottomPipeX;
    int bottomPipeY;
    int flappyBirdY;
    

    

    boolean gameStarted;
    int jumpFrames;
    int heightTopPipe;
    int heightBottomPipe;


    Random rand;
    int updatedBottomPipe;

    
    int minTopY;
    int maxTopY;
    int minBottomY;
    int maxBottomY;
    int boundsTopPipe;
    int boundsBottomPipe;
    int updatedTopValue;
    int updatedBottomValue;
  
    
    public Screen() {
        //J(everything)
        setFocusable(true);
        addKeyListener(this);
        
        minTopY = 75;
        maxTopY = 515;
        minBottomY = 75;
        maxBottomY = 515;
        
        heightBottomPipe = 200;
        heightTopPipe = 250;

        rand = new Random();
        updatedBottomPipe = 0;
        
        gameStarted = false;
        jumpFrames = 0;


        
        flappyBirdY = 308; 
        topPipeX = 320;
        topPipeY = 30;
        bottomPipeX = 320;
        bottomPipeY = 390;
        
        backgroundImage = new ImageIcon("flappybirdbg.png");
        topPipe = new ImageIcon("toppipe.png");
        flappyBird = new ImageIcon("flappybird.png");
        bottomPipe = new ImageIcon("bottompipe.png");
        scaledFlappyBird = flappyBird.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);

        this.randomCoordinatesY();
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(360,640);
    }

    //fuctions
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == 32) {
            jumpFrames += 2;
            gameStarted = true;
            this.space();
        } 
        
    }

    
  

    public void randomCoordinatesY() {
        int updatedBottomPipe = 75 + rand.nextInt(540);
        heightBottomPipe = updatedBottomPipe;
        heightTopPipe = 640 - (heightBottomPipe + 150);
    }
    
    public void animate() {
        while (true) {
            //animation code
            System.out.println("bottom x:" + bottomPipeX);
            System.out.println("top x:" + topPipeX);

            
            bottomPipeX = bottomPipeX - 6;
            topPipeX = topPipeX - 6;
            

            if (gameStarted == true) {
                flappyBirdY += 7;
            }

            if (jumpFrames > 0) {
                jumpFrames -= 1;
                flappyBirdY -= 43;
            }
            
            if (topPipeX <= -40) {
                this.randomCoordinatesY();
                topPipeX = 320;
            }

            if (topPipeY != 0) {
                topPipeY = 0;
            }
            

            if (bottomPipeX <= -40) {
                bottomPipeX = 320;
            }
            bottomPipeY = 640 - heightBottomPipe;
            


            repaint();
            
            //System.out.println(String.format("end topPipeX : %d" , topPipeX));
            try {
                Thread.sleep(50);// sleeps for 50 milliseconds
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
        System.out.println(topPipe);
        if(topPipe != null) {
            Image scaledTopPipe = topPipe.getImage().getScaledInstance(40, heightTopPipe, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledTopPipe, topPipeX, topPipeY, this);
            // graphics.drawImage(topPipe.getImage(), topPipeX, topPipeY, this);
            //System.out.println(topPipeX);
        }
        if (bottomPipe != null) {
            Image scaledBottomPipe = topPipe.getImage().getScaledInstance(40, heightBottomPipe, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledBottomPipe, bottomPipeX, bottomPipeY, this);
            //System.out.println(bottomPipeX);
        }
        if (scaledFlappyBird != null) { 
            graphics.drawImage(scaledFlappyBird,100, flappyBirdY, this);
        }
    }

    public void space() {
        //flappyBirdY = flappyBirdY - 50;
        // System.out.println(String.format("beginning flappyBirdY : %d", flappyBirdY));
        // System.out.println(String.format("beginning flappyBirdY : %d", flappyBirdY));  
    }


    @Override
    public void actionPerformed(ActionEvent event) {


    }

    @Override
    public void keyReleased(KeyEvent event) {
        //you don't need this function
    }

    @Override
    public void keyTyped(KeyEvent event) {
        //you don't need this function
    }

}
