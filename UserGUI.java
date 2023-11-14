import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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

    private DefaultMutableTreeNode node = new DefaultMutableTreeNode();
    private ArrayList<String> currentFollowing = new ArrayList<String>();
    private ArrayList<String> newsFeed = new ArrayList<String>();
    private ArrayList<UserInformation> user_info = new ArrayList<UserInformation>();

    private SetUsers setUp;
    private ArrayList<String> userNames = new ArrayList<String>();
    private int index = 0;

    private int width;
    private int height;

    // Constructor initializes the layout and interactions
    public UserGUI(DefaultMutableTreeNode n, int w, int h, SetUsers su) {
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

        // Get the specific information of the selected node

        this.node = n;

        setUp = su;

        index = setUp.setUserInfo(node, currentFollowing, newsFeed);
        user_info = setUp.getUserInformation();
        instantiateJList();

        userNames = GUI.getUserNames();

        width = w;
        height = h;

    }

    // Separate the GUI by its panels
    /*
     * Layout combines grid layouts and border layouts.
     */
    public void setUpGUI() {
        frame.setSize(width, height);
        frame.setTitle("User View");

        updateFollowing();
        updateTweets();

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
                    String u_id = userID.getText();
                    addFollowers(u_id);
                    userID.setText("");
                } else if (ae.getSource() == postTweet) {
                    String msg = tweetMsg.getText();
                    addTweets(msg);
                    tweetMsg.setText("");

                }
            }
        };

        followUser.addActionListener(buttonListener);
        postTweet.addActionListener(buttonListener);

    }

    // Add followers to the selected node's list then update the lists
    public void addFollowers(String id) {

        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        // Only adds to the list if the node exists
        for (int i = 0; i < userNames.size(); i++) {
            if (userNames.get(i).equals(id)) {
                user_info.get(index).addFollowing(id);
            }
        }

        updateFollowing();
        updateTweets();
    }

    // Add messages to the selected nodes feed and anyone they follow
    public void addTweets(String msg) {

        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        AddAdmin.addMsgs();

        if (msg.contains("good") || msg.contains("great") || msg.contains("cool") || msg.contains("excellent")
                || msg.contains("awesome")) {
            AddAdmin.updatePercentage();
        }

        user_info.get(index).addNewsFeed(userNames.get(index) + ": \"" + msg + "\"");
        updateTweets();
    }

    // updates ONLY the specified node
    public void updateFollowing() {

        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        // Checks if the username is followed by the current node then adds
        // their feed to the node's feed
        for (int i = 0; i < user_info.size(); i++) {
            for (int j = 0; j < user_info.get(index).getFollowing().size(); j++) {
                if (userNames.get(i).equals(user_info.get(index).getFollowing().get(j))) {
                    String NF[] = new String[user_info.get(index).getNewsFeed().size()];
                    NF = user_info.get(index).getNewsFeed().toArray(NF);
                    for (int k = 1; k < NF.length; k++) {
                        if (!user_info.get(i).getNewsFeed().get(k).equals(NF[k])) {
                            user_info.get(i).addNewsFeed(NF[k]);
                        }
                    }
                }
            }
        }

        // Update List

        String cF[] = new String[user_info.get(index).getFollowing().size()];
        cF = user_info.get(index).getFollowing().toArray(cF);

        following.setListData(cF);
    }

    public void updateTweets() {

        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        // Update List
        String nF[] = new String[user_info.get(index).getNewsFeed().size()];
        nF = user_info.get(index).getNewsFeed().toArray(nF);

        tweets.setListData(nF);
    }

    public void instantiateJList() {

        // get the information of the clicked on user to make the JList
        String cF[] = new String[user_info.get(index).getFollowing().size()];
        cF = user_info.get(index).getFollowing().toArray(cF);

        String nF[] = new String[user_info.get(index).getNewsFeed().size()];
        nF = user_info.get(index).getNewsFeed().toArray(nF);

        // instantiate it
        following = new JList<>(cF);
        tweets = new JList<>(nF);

    }

}
