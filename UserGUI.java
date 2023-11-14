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
    private ArrayList<UserInformation> user_info = new ArrayList<UserInformation>();

    private AddAdmin admin = new AddAdmin();

    private int width;
    private int height;

    // Constructor initializes the layout and interactions
    public UserGUI(DefaultMutableTreeNode n, int w, int h) {
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

        this.node = n;

        setUserInfo();

        width = w;
        height = h;

        System.out.println("New user window made: " + node + " " + user_info);
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

        int index = 0;
        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        user_info.get(index).addFollowing(id);
        updateFollowing();

    }

    public void addTweets(String msg) {
        // newsFeed.add(msg);

        int index = 0;
        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        user_info.get(index).addNewsFeed(msg);
        updateTweets();
    }

    // updates ONLY the specified node
    public void updateFollowing() {

        int index = 0;
        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        String cF[] = new String[user_info.get(index).getFollowing().size()];
        cF = user_info.get(index).getFollowing().toArray(cF);

        following.setListData(cF);
    }

    public void updateTweets() {

        int index = 0;
        for (int i = 0; i < user_info.size(); i++) {
            if (user_info.get(i).getNode() == node) {
                index = i;
                break;
            }
        }

        String nF[] = new String[user_info.get(index).getNewsFeed().size()];
        nF = user_info.get(index).getNewsFeed().toArray(nF);

        tweets.setListData(nF);
    }

    public void setUserInfo() {

        UserInformation us_in = new UserInformation(node, newsFeed, currentFollowing);
        boolean flag = false;
        int index = 0;

        // if the current node selected DOESNT have information, make a new instance
        // if there isnt any information, add the user by default
        if (user_info.size() < 1) {
            us_in.addFollowing("--List of Current Followers--");
            us_in.addNewsFeed("--List of New Tweets--");
            user_info.add(us_in);
            System.out.println("Size is: " + user_info.size());
        } else {
            for (int i = 0; i < user_info.size(); i++) {
                if (user_info.get(i).getNode() != node) {
                    flag = true;
                    System.out.println(user_info.get(i).getNode() + " is not equal to " + node);
                } else {
                    flag = false;
                    index = i;
                    System.out.println(user_info.get(i).getNode() + " is equal to " + node);
                    break;
                }
            }
        }

        // add the user if they don't have information registered
        if (flag) {

            us_in.addFollowing("--List of Current Followers--");
            us_in.addNewsFeed("--List of New Tweets--");

            user_info.add(us_in);
            index = user_info.indexOf(us_in);
        }

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
