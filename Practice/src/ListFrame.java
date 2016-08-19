import javafx.beans.binding.StringBinding;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kingroc on 16-8-19.
 */
public class ListFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;
    private JPanel listPanel;
    private JList wordList;
    private JLabel label;
    private JPanel buttonPanel;
    private ButtonGroup group;
    private String prefix = "The ";
    private String suffix = "fox jumps over the lazy dog.";

    public ListFrame(){
        setTitle("ListTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        String[] words = {"quick", "brown", "hungry", "wild", "silent", "hug", "private", "abstract", "static", "final"};

        wordList = new JList(words);
        wordList.setVisibleRowCount(4);
        JScrollPane scrollPane = new JScrollPane(wordList);
        listPanel  = new JPanel();
        listPanel.add(scrollPane);

        wordList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object[] values = wordList.getSelectedValues();
                StringBuilder text = new StringBuilder(prefix);
                for (int i = 0; i < values.length; i++) {
                    String word = (String) values[i];
                    text.append(word);
                    text.append(" ");
                }
                text.append("suffix");
                label.setText(text.toString());
            }
        });

        buttonPanel = new JPanel();
        group = new ButtonGroup();
        makeButton("Vertical", JList.VERTICAL);
        makeButton("Vertical Wrap", JList.VERTICAL_WRAP);
        makeButton("Horizontal Wrap", JList.HORIZONTAL_WRAP);

        add(listPanel, BorderLayout.NORTH);
        label = new JLabel(prefix + suffix);
        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void makeButton(String label, final int orientation){
        JRadioButton button = new JRadioButton(label);
        buttonPanel.add(button);
        if(group.getButtonCount() == 0)
            button.setSelected(true);
        group.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordList.setLayoutOrientation(orientation);
                listPanel.revalidate();
            }
        });
    }
}
