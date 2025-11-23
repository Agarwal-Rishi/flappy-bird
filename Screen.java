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
    
    JLabel gameOverPanel;
    

    boolean gameStarted;
    int jumpFrames;
    int heightTopPipe;
    int heightBottomPipe;


    Random rand;    
    
    int minTopY;
    int maxTopY;
    int minBottomY;
    int maxBottomY;
    int boundsTopPipe;
    int boundsBottomPipe;
    int updatedTopValue;
    
    boolean gameOver;
    
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

        
        
        
        gameStarted = false;
        jumpFrames = 0;


        
        flappyBirdY = 308; 
        topPipeX = 320;
        topPipeY = 30;
        bottomPipeX = 320;
        bottomPipeY = 390;

        gameOverPanel = new JLabel();
        gameOverPanel.setBounds(290, 40, 240, 130);
        
        
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
            
        } 
        
    }

    
  

    public void randomCoordinatesY() {
        heightBottomPipe = 75 + rand.nextInt(390);
        heightTopPipe = 640 - (heightBottomPipe + 150);
        scaledTopPipe = topPipe.getImage().getScaledInstance(40, heightTopPipe, Image.SCALE_SMOOTH);
        scaledBottomPipe = bottomPipe.getImage().getScaledInstance(40, heightBottomPipe, Image.SCALE_SMOOTH);
    }

    public void activateGameOverPanel() {
        gameOverPanel.setText(TOOL_TIP_TEXT_KEY);
    }

    

    private boolean checkCollision() {
        // heightTopPipe, topPipeX, topPipeY, 40: scaledTopPipe.getWidth()
        // flappybirdwidth = 34, height = 24

        // check collision for bottom pipe=
        if (flappyBirdY + 24 > bottomPipeY && flappyBirdY + 24 < bottomPipeY + heightBottomPipe && 134 > bottomPipeX && 134 < bottomPipeX + 40) {
            return true;
        }
        if (flappyBirdY > 0 && flappyBirdY < heightTopPipe && 134 > topPipeX && 134 < topPipeX + 40) {
            return true;
        }
        if (flappyBirdY > 640 || flappyBirdY < 0) {
            return true;
        }

        return false;
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
                topPipeX = 360;
                bottomPipeX = 360;
            }

            if (topPipeY != 0) {
                topPipeY = 0;
            }
            

            bottomPipeY = 640 - heightBottomPipe;
            
            this.checkCollision();

            if (checkCollision() == true) {
                gameOver = true;
            }

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

        if (!gameOver) {
            if (backgroundImage != null) {
                graphics.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
            System.out.println(topPipe);
            if(topPipe != null) {
                graphics.drawImage(scaledTopPipe, topPipeX, topPipeY, this);
                // graphics.drawImage(topPipe.getImage(), topPipeX, topPipeY, this);
                //System.out.println(topPipeX);
            }
            if (bottomPipe != null) {
    
                graphics.drawImage(scaledBottomPipe, bottomPipeX, bottomPipeY, this);
                //System.out.println(bottomPipeX);
            }
            if (scaledFlappyBird != null) { 
                graphics.drawImage(scaledFlappyBird, 100, flappyBirdY, this);
            }
        } else {
            // game over screen
            graphics.drawString("Game Over!", 100, 100);
        }
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
