import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Created by kingroc on 16-8-19.
 */
public class MainWindow extends JFrame implements ActionListener {
    private JTextArea mDisplay;
    private JTextArea mMessage;
    private JPanel contentPane;
    private JButton mSendButton = new JButton("发送");
    private JButton mShutButton = new JButton("关闭");

    public MainWindow() {
        setTitle("聊天输入界面");// 设置窗体的标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体退出时操作
        setBounds(100, 100, 550, 600);// 设置窗体位置和大小
        contentPane = new JPanel();// 创建内容面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框
        setContentPane(contentPane);// 应用内容面板
        GridBagLayout gbl_contentPane = new GridBagLayout();// 创建网格组布局
        contentPane.setLayout(gbl_contentPane);// 使用网格组布局

        mDisplay = new JTextArea();
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1, 1));
        displayPanel.setBorder(BorderFactory.createTitledBorder("Edit Area"));
        displayPanel.add(new JScrollPane(mDisplay));

        // --begin:分别将两个JTextArea通过JPanel放到JFrame中。
        mMessage = new JTextArea();
        mMessage.setEditable(false);// 利用setEditable()方法将另一个JTextArea设置为不可编辑.
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(1, 1));
        messagePanel.setBorder(BorderFactory.createTitledBorder("Message"));
        messagePanel.add(new JScrollPane(mMessage));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        //buttonPanel.setSize(100, 20);

        mSendButton.setMnemonic(KeyEvent.VK_I);
        mSendButton.addActionListener(this);
        buttonPanel.add(mSendButton);

        mShutButton.setMnemonic(KeyEvent.VK_E);
        mShutButton.addActionListener(this);
        buttonPanel.add(mShutButton);

        GridBagConstraints gbc_p2 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_p2.insets = new Insets(0, 0, 5, 5);// 设置控件的空白
        gbc_p2.fill = GridBagConstraints.BOTH;// 设置填充方式
        gbc_p2.weightx = 100.0;// 第一列的分布方式为10%
        gbc_p2.weighty = 80.0;
        gbc_p2.gridx = 0;// 起始点为第1列
        gbc_p2.gridy = 0;// 起始点为第1行
        contentPane.add(messagePanel, gbc_p2);// 增加按钮及其约束条件

        GridBagConstraints gbc_p1 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_p1.insets = new Insets(0, 0, 5, 5);// 设置控件的空白
        gbc_p1.fill = GridBagConstraints.BOTH;// 设置填充方式
        gbc_p1.weightx = 100.0;// 第一列的分布方式为10%
        gbc_p1.weighty = 18.0;
        gbc_p1.gridx = 0;// 起始点为第1列
        gbc_p1.gridy = 1;// 起始点为第1行
        contentPane.add(displayPanel, gbc_p1);// 增加按钮及其约束条件

        GridBagConstraints gbc_p3 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_p3.insets = new Insets(0, 0, 5, 5);// 设置控件的空白
        gbc_p3.fill = GridBagConstraints.BOTH;// 设置填充方式
        gbc_p3.weightx = 100.0;// 第一列的分布方式为10%
        gbc_p3.weighty = 2.0;
        gbc_p3.gridx = 0;// 起始点为第1列
        gbc_p3.gridy = 2;// 起始点为第1行
        contentPane.add(buttonPanel, gbc_p3);// 增加按钮及其约束条件
    }

    public static void main(String args[]) {

        JFrame f = new MainWindow();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== mSendButton){
            mMessage.append(mDisplay.getText() + "\n\r");
        }else if(e.getSource()==mShutButton){
            System.exit(0);
        }
    }
}
