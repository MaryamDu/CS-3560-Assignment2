import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI {

    private JFrame frame;

    // Interactions
    private JTextField userID;
    private JButton followUser;
    private JList following;
    private JTextField tweetMsg;
    private JButton postTweet;
    private JList tweets;

    // Layout
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel topPanelGrid;
    private JPanel topPanelList;
    private JPanel bottomPanelList;
    private JPanel bottomPanelGrid;
    private JPanel bottomPanel;
    private JTree tree;

    private int width;
    private int height;

    // Constructor initializes the layout and interactions
    public UserGUI(int w, int h) {
        frame = new JFrame();
        mainPanel = new JPanel(new GridLayout(2, 1));

        topPanel = new JPanel(new BorderLayout());
        topPanelGrid = new JPanel(new GridLayout(1, 2));
        topPanelList = new JPanel(new FlowLayout(FlowLayout.LEFT));

        bottomPanelGrid = new JPanel(new GridLayout(1, 2));
        bottomPanelList = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel = new JPanel(new BorderLayout());

        userID = new JTextField(10);
        tweetMsg = new JTextField(10);
        followUser = new JButton("Follow User");
        postTweet = new JButton("Post Tweet");

        String placeholder[] = { "hello", "nice", "cool" };

        following = new JList<>(placeholder);
        tweets = new JList<>(placeholder);

        width = w;
        height = h;
    }

    // Separate the GUI by its panels
    public void setUpGUI() {
        frame.setSize(width, height);
        frame.setTitle("User View");

        topPanelGrid.add(userID);
        topPanelGrid.add(followUser);
        topPanelList.add(following);

        topPanel.add(topPanelGrid, BorderLayout.NORTH);
        topPanel.add(topPanelList, BorderLayout.CENTER);

        //

        bottomPanelGrid.add(tweetMsg);
        bottomPanelGrid.add(postTweet);
        bottomPanelList.add(tweets);

        bottomPanel.add(bottomPanelGrid, BorderLayout.NORTH);
        bottomPanel.add(bottomPanelList, BorderLayout.CENTER);

        //

        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        frame.add(mainPanel);

        frame.dispose();
        frame.setVisible(true);
    }

    // Button Interactions
    public void SetUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == followUser) {
                    // String u_id = userID.getText();
                    System.out.println("Click");
                    userID.setText("");
                } else if (ae.getSource() == postTweet) {
                    System.out.println("Clicking");

                }
            }
        };

        followUser.addActionListener(buttonListener);
        postTweet.addActionListener(buttonListener);

    }

}
