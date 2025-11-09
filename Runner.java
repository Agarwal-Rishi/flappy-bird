import javax.swing.JFrame;
public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        Screen screen = new Screen();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(screen);
        frame.pack();
        frame.setVisible(true);
        screen.animate();
        
    }
}
