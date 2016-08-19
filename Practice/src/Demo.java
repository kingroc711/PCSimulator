/**
 * Created by kingroc on 16-8-19.
 */

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoableEdit;

class Demo extends JFrame implements ActionListener {
    private UndoableEdit edit;
    private JTextArea jta;
    private JTextArea message;
    private JMenuItem senditem;
    //private JMenuItem shutitem;
    private JButton mSendButton = new JButton("发送");
    private JButton mShutButton = new JButton("关闭");

    public Demo() {
        super("聊天输入界面");
        jta = new JTextArea();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("Edit Area"));
        p1.add(new JScrollPane(jta)); // --begin:分别将两个JTextArea通过JPanel放到JFrame中。

        message = new JTextArea();
        message.setEditable(false);// 利用setEditable()方法将另一个JTextArea设置为不可编辑.
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1));
        p2.setBorder(BorderFactory.createTitledBorder("Message"));
        p2.add(new JScrollPane(message));

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1));
        //p3.setSize(100, 20);
        mSendButton.setMnemonic(KeyEvent.VK_I);
        mSendButton.addActionListener(this);
        //btShut.setMnemonic(KeyEvent.VK_I);
        //btShut.addActionListener(this);
        p3.add(mSendButton);

        //p3.add(btShut);
        getContentPane().setLayout(new GridLayout(3, 1));
        getContentPane().add(p2);
        getContentPane().add(p1);
        getContentPane().add(p3); // --end // 建立目录菜单并放置到JFrame中.

        JMenuBar bar = new JMenuBar();
        JMenu theMenu = new JMenu("操作");
        senditem = new JMenuItem("发送");
        //shutitem = new JMenuItem("关闭");
        theMenu.add(senditem);
        //theMenu.add(shutitem);
        bar.add(theMenu);
        updateMenuItem();// 构造目录菜单 setJMenuBar(bar);
        setSize(300, 600); // 采用inner class方式，分别构造菜单选项被点选后的运行操作。分别调用undo(),redo()方法来完成.
        senditem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                message.append(jta.getText() + "\n\r\n\r");
            }
        });
//        shutitem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                System.exit(0);
//            }
//        });
    }

    public void updateMenuItem() {
        senditem.setEnabled(true);
        //shutitem.setEnabled(true);
        senditem.setText("发送");
        //shutitem.setText("关闭");
    }

    public static void main(String args[]) {
        JFrame f = new Demo();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mSendButton) {
            message.append(jta.getText() + "\n\r\n\r");
        }
        else if (e.getSource() == mShutButton) {
            System.exit(0);
        }
    }
}