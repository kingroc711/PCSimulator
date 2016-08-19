import javax.swing.*;
import java.awt.*;

/**
 * Created by kingroc on 16-8-19.
 */
public class ListTest {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                JFrame frame = new ListFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
