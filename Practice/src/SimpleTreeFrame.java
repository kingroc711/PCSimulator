import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by kingroc on 16-8-19.
 */
public class SimpleTreeFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleTreeFrame(){
        setTitle("SimpleTree");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("world");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);

        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);

        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);

        city = new DefaultMutableTreeNode("Cupertino");
        state.add(city);

        state = new DefaultMutableTreeNode("Michigan");
        city.add(state);

        city = new DefaultMutableTreeNode("Ann arbor");
        state.add(city);

        country = new DefaultMutableTreeNode("Germany");
        root.add(country);

        state = new DefaultMutableTreeNode("Schleswig-Hostein");
        country.add(state);

        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);

        JTree tree = new JTree(root);
        add(new JScrollPane(tree));
    }
}
