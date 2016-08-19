import javax.swing.*;
import java.awt.*;

/**
 * Created by kingroc on 16-8-18.
 */
public class NotHelloWorld {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                JFrame frame = new NotHelloWorldFrame();
                frame.setTitle("NothelloWorld");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    static class NotHelloWorldFrame extends JFrame{
        public NotHelloWorldFrame(){
            setSize(400, 500);
            add(new NotHelloWorldComponent());
            //pack();
        }
    }

    static class NotHelloWorldComponent extends JComponent{
        public static final int MESSAGE_X = 75;
        public static final int MESSAGE_Y = 100;

        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;

        public void painComponent(Graphics g){
            g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
        }

        public Dimension getPreferedSize(){
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }
}
