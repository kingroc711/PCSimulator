import javax.swing.*;
import java.util.Vector;

/**
 * Created by kingroc on 16-8-19.
 */
public class WordListModel extends AbstractListModel {
    private int  length;
    private static final char FIRST = 'a';
    private static final char LAST = 'z';

    public WordListModel(int n) {
         length = n;
    }

    @Override
    public int getSize() {
        return (int)Math.pow(LAST - FIRST + 1, length);
    }

    @Override
    public Object getElementAt(int n) {
        StringBuilder r = new StringBuilder();
        for(int i = 0; i < length; i++){
            char c = (char)(FIRST + n %(LAST - FIRST + 1));
            r.insert(0, c);
            n = n/(LAST - FIRST + 1);
        }
        return r;
    }
}
