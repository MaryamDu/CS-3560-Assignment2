import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private UserInformation user_info = new UserInformation();

    private int width;
    private int height;

    // Constructor initializes the layout and interactions
    public UserGUI(DefaultMutableTreeNode n, int w, int h, UserInformation us_in) {
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

        // currentFollowing.add("--List of Current Followers--");
        // newsFeed.add("--List of New Tweets--");

        us_in.addFollowing("--List of Current Followers--");
        us_in.addNewsFeed("--List of New Tweets--");

        String cF[] = new String[user_info.getFollowing().size()];
        cF = user_info.getFollowing().toArray(cF);

        String nF[] = new String[user_info.getNewsFeed().size()];
        nF = user_info.getNewsFeed().toArray(nF);

        // instantiate it
        following = new JList<>(cF);
        tweets = new JList<>(nF);

        node = n;
        user_info = us_in;

        width = w;
        height = h;
    }

    // Separate the GUI by its panels
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

    public void addFollowers(String id) {
        // currentFollowing.add(id);
        user_info.addFollowing(id);
        updateFollowing();

    }

    public void addTweets(String msg) {
        // newsFeed.add(msg);
        user_info.addNewsFeed(msg);
        updateTweets();
    }

    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public void updateFollowing() {
        String cF[] = new String[user_info.getFollowing().size()];
        cF = user_info.getFollowing().toArray(cF);

        following.setListData(cF);
    }

    public void updateTweets() {
        String nF[] = new String[user_info.getNewsFeed().size()];
        nF = user_info.getNewsFeed().toArray(nF);

        tweets.setListData(nF);
    }

}
