import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Random;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Screen extends JPanel implements ActionListener, KeyListener {
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
     int flappyBirdY;
     

     
     
    
    
    
    public Screen() {
        //J(everything)
        setFocusable(true);
        flappyBirdY = 308; 
        topPipeX = 320;
        topPipeY = 30;
        bottomPipeX = 320;
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
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == 32) {
            System.out.println("The button is working");
            this.space();
        }
    }

    public void keyReleased(KeyEvent event) {
        //you don't need this function
    }

    public void keyTyped(KeyEvent event) {
        //you don't need this function
    }

    public void space() {
         flappyBirdY = flappyBirdY - 12;
         //System.out.println(String.format("beginning flappyBirdY : %d", flappyBirdY));
         while (flappyBirdY > 640 ) {
            //System.out.println("over here");
            flappyBirdY--;
            //System.out.println("now here");
         }
         //System.out.println(String.format("beginning flappyBirdY : %d", flappyBirdY));
         
    }

    public void animate() {
        while (true) {
            //animation code
            bottomPipeX = bottomPipeX - 3;
            topPipeX = topPipeX - 3;
            //System.out.println(String.format("beginning topPipeX : %d", topPipeX));
            if (topPipeX <= -40) {
                //System.out.println("over here");
                topPipeX = 320;

            }
            if (bottomPipeX <= -40) {
                //System.out.println("now here");
                bottomPipeX = 320;

            }
            //System.out.println(String.format("end topPipeX : %d" , topPipeX));
            repaint();
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
        
        if(scaledTopPipe != null) {
            graphics.drawImage(scaledTopPipe, topPipeX, topPipeY, this);
            System.out.println(topPipeX);
        }
        if (scaledBottomPipe != null) {
            graphics.drawImage(scaledBottomPipe, bottomPipeX, bottomPipeY, this);
            System.out.println(bottomPipeX);
        }
        if (flappyBird != null) { 
            graphics.drawImage(scaledFlappyBird,100, 308, this);
        }
    }


    @Override
    public void actionPerformed(ActionEvent event) {

    }


}
