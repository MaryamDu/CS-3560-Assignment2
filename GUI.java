import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

    private JFrame frame;

    private JTextField userID;
    private JTextField groupID;
    private JButton userButton;
    private JButton groupButton;
    private JButton userView;
    private JButton userTotal;
    private JButton msgTotal;
    private JButton groupTotal;
    private JButton posperc;
    private JButton placeHolder;

    private JPanel mainPanel;
    private JPanel rightPanel;
    private JTree tree;
    private int width;
    private int height;

    public GUI(int w, int h) {
        frame = new JFrame();
        mainPanel = new JPanel(new GridLayout(1, 2));
        rightPanel = new JPanel(new GridLayout(5, 2));
        setUpTree();

        userID = new JTextField(10);
        groupID = new JTextField(10);
        userButton = new JButton("Add User");
        groupButton = new JButton("Add Group");
        userView = new JButton("Open User View");
        userTotal = new JButton("Show Total Users");
        msgTotal = new JButton("Show Total Messages");
        groupTotal = new JButton("Show Total Groups");
        posperc = new JButton("Show Positive Percentage");
        placeHolder = new JButton("Blank");

        width = w;
        height = h;
    }

    public void setUpGUI() {
        frame.setSize(width, height);
        frame.setTitle("Demo");

        rightPanel.add(userID);
        rightPanel.add(userButton);
        rightPanel.add(groupID);
        rightPanel.add(groupButton);
        rightPanel.add(userView);
        rightPanel.add(userTotal);
        rightPanel.add(msgTotal);
        rightPanel.add(groupTotal);
        rightPanel.add(posperc);
        rightPanel.add(placeHolder);

        mainPanel.add(tree);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);
        // frame.add(rightPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpTree() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode cs1 = new DefaultMutableTreeNode("CS3560");
        DefaultMutableTreeNode cs2 = new DefaultMutableTreeNode("CS3560-1");

        tree = new JTree(root);
        tree.setBounds(25, 25, 200, 200);

        root.add(cs1);
        root.add(cs2);

    }

    public void SetUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == userButton) {
                    // String u_id = userID.getText();
                    System.out.println("Click");
                    userID.setText("");
                } else if (ae.getSource() == groupButton) {
                    System.out.println("Clicking");

                }
            }
        };

        userButton.addActionListener(buttonListener);
        groupButton.addActionListener(buttonListener);
    }

}
