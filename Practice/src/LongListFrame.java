import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by kingroc on 16-8-19.
 */
public class LongListFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private final int DEFAUlT_HEIGHT = 400;
    private JList wordList;
    private JLabel label;
    private String prefix = "The quick brown ";
    private String suffix = " jumps over the lazy dog.";

    public LongListFrame(){
        setTitle("LongListTest");
        setSize(DEFAULT_WIDTH, DEFAUlT_HEIGHT);

        wordList = new JList(new WordListModel(3));
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wordList.setPrototypeCellValue("www");
        JScrollPane scrollPane = new JScrollPane(wordList);

        JPanel p = new JPanel();
        p.add(scrollPane);
        wordList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                StringBuilder word = (StringBuilder)wordList.getSelectedValue();
                setSubject(word.toString());
            }
        });

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);
        label = new JLabel(prefix + suffix);
        contentPane.add(label, BorderLayout.CENTER);
        setSubject("fox");
    }

    private void setSubject(String word) {
        StringBuilder text = new StringBuilder(prefix);
        text.append(word);
        text.append(suffix);
        label.setText(text.toString());
    }
}
