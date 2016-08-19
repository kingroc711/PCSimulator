import javax.swing.*;
import java.awt.*;

/**
 * Created by kingroc on 16-8-18.
 */
public class SimpleFrameTest {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    static class SimpleFrame extends JFrame {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;

        public SimpleFrame(){
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }
}
