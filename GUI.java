import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI {

    private JFrame frame;

    // Interactions
    private JTextField userID;
    private JTextField groupID;
    private JButton userButton;
    private JButton groupButton;
    private JButton userView;
    private JButton userTotal;
    private JButton msgTotal;
    private JButton groupTotal;
    private JButton posperc;
    private JButton validIDs;
    private JButton lastUpdate;

    // Layout
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel topPanel;
    private JPanel topPanelCont;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JTree tree;

    // Arraylists to handle added information
    private ArrayList<String> groupNames = new ArrayList<String>();
    private static ArrayList<String> userNames = new ArrayList<String>();
    private ArrayList<DefaultMutableTreeNode> folders = new ArrayList<DefaultMutableTreeNode>();
    private ArrayList<DefaultMutableTreeNode> users = new ArrayList<DefaultMutableTreeNode>();
    private ArrayList<Long> userTimes = new ArrayList<Long>();
    private ArrayList<Long> groupTimes = new ArrayList<Long>();
    public static ArrayList<Long> userUpdateTime = new ArrayList<Long>();
    private AddAdmin admin = new AddAdmin();
    private UserGUI userPages;
    private SetUsers setUp = new SetUsers();

    private int width;
    private int height;

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("**Root**");

    private static GUI instance = null;

    // Constructor initializes the layout and interactions
    public GUI(int w, int h) {
        frame = new JFrame();
        mainPanel = new JPanel(new GridLayout(1, 2));
        rightPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(2, 1));
        topPanelCont = new JPanel(new GridLayout(2, 2));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new GridLayout(2, 2));
        centerPanel = new JPanel(new GridLayout(1, 2));

        tree = new JTree(root);
        tree.setBounds(25, 25, 200, 200);

        userID = new JTextField(10);
        groupID = new JTextField(10);
        userButton = new JButton("Add User");
        groupButton = new JButton("Add Group");
        userView = new JButton("Open User View");
        userTotal = new JButton("Show Total Users");
        msgTotal = new JButton("Show Total Messages");
        groupTotal = new JButton("Show Total Groups");
        posperc = new JButton("Show Positive Percentage");

        validIDs = new JButton("Show Validation");
        lastUpdate = new JButton("Show Last Update");

        width = w;
        height = h;
    }

    // Separate the GUI by its panels
    /*
     * It's separated by 5 panels which combine into the
     * main panel. The tree is its own panel while the
     * right panel is comprised of two grid layouts and a
     * flow layout, which are combined into a border layout
     * and then are combined into the right panel.
     */
    public void setUpGUI() {
        frame.setSize(width, height);
        frame.setTitle("Admin View");

        topPanelCont.add(userID);
        topPanelCont.add(userButton);
        topPanelCont.add(groupID);
        topPanelCont.add(groupButton);

        middlePanel.add(userView);

        topPanel.add(topPanelCont);
        topPanel.add(middlePanel);

        bottomPanel.add(userTotal);
        bottomPanel.add(groupTotal);
        bottomPanel.add(msgTotal);
        bottomPanel.add(posperc);

        centerPanel.add(validIDs);
        centerPanel.add(lastUpdate);

        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);
        rightPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(tree);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Button Interactions
    public void SetUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == userButton) {
                    // add users
                    admin.addUserNum();
                    String u_id = userID.getText();
                    userNames.add(u_id);
                    addUser(u_id);
                    userID.setText("");

                } else if (ae.getSource() == groupButton) {
                    // add groups
                    admin.addGroupNum();
                    String g_id = groupID.getText();
                    groupNames.add(g_id);
                    addGroup(g_id);
                    groupID.setText("");

                } else if (ae.getSource() == userView) {
                    // show userview
                    openUsers();

                } else if (ae.getSource() == groupTotal) {
                    // show group total
                    JDialog d = new JDialog(frame, "Group Total");
                    JLabel l = new JLabel(String.valueOf(admin.getGroupT()));
                    d.add(l);
                    d.setSize(100, 100);
                    d.setVisible(true);

                } else if (ae.getSource() == userTotal) {
                    // show user total
                    JDialog d = new JDialog(frame, "User Total");
                    JLabel l = new JLabel(String.valueOf(admin.getUserT()));
                    d.add(l);
                    d.setSize(100, 100);
                    d.setVisible(true);
                } else if (ae.getSource() == msgTotal) {
                    // show message total
                    JDialog d = new JDialog(frame, "Total Messages");
                    JLabel l = new JLabel(String.valueOf(admin.getMsgs()));
                    d.add(l);
                    d.setSize(100, 100);
                    d.setVisible(true);
                } else if (ae.getSource() == posperc) {
                    // show positive precentage
                    AddAdmin.calculateAverage();
                    JDialog d = new JDialog(frame, "Positive Percentage");
                    JLabel l = new JLabel(String.valueOf(admin.getAvg()));
                    d.add(l);
                    d.setSize(100, 100);
                    d.setVisible(true);
                } else if (ae.getSource() == validIDs) {
                    // show if all IDs are valid
                    JDialog d = new JDialog(frame, "Valid users");
                    boolean flag = checkValidIDs();
                    JLabel l;
                    if (flag) {
                        l = new JLabel("All User and Group names are valid.");
                    } else {
                        l = new JLabel(
                                "Not all User and Group names are valid. Please do not include duplicates or spaces.");
                    }
                    d.add(l);
                    d.setSize(600, 100);
                    d.setVisible(true);
                } else if (ae.getSource() == lastUpdate) {
                    // show last updated user
                    int index = checkLastUser();
                    JDialog d = new JDialog(frame, "Last User Update");
                    JLabel l = new JLabel(
                            "Last user update was made by: " + userNames.get(index) + ".");
                    d.add(l);
                    d.setSize(600, 100);
                    d.setVisible(true);
                }
            }
        };

        userButton.addActionListener(buttonListener);
        groupButton.addActionListener(buttonListener);
        userView.addActionListener(buttonListener);
        userTotal.addActionListener(buttonListener);
        msgTotal.addActionListener(buttonListener);
        groupTotal.addActionListener(buttonListener);
        posperc.addActionListener(buttonListener);
        validIDs.addActionListener(buttonListener);
        lastUpdate.addActionListener(buttonListener);
    }

    // Add a group to the tree
    public void addGroup(String groupName) {

        groupName = "**" + groupName + "**";
        DefaultMutableTreeNode cs = new DefaultMutableTreeNode(groupName);
        folders.add(cs);

        if (groupNames.size() == 1) {
            root.add(folders.get(0));
        } else {
            for (int i = 1; i < groupNames.size(); i++) {
                DefaultMutableTreeNode cs1 = folders.get(i - 1);
                DefaultMutableTreeNode cs2 = folders.get(i);
                cs1.add(cs2);
            }
        }

        Long creationTime = System.currentTimeMillis();
        groupTimes.add(creationTime);

        System.out.println("New group made at: " + creationTime + " ms.");

    }

    // Add a user to the tree if a group is selected
    public void addUser(String userName) {

        DefaultMutableTreeNode cs = new DefaultMutableTreeNode(userName);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        TreePath path = tree.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        users.add(cs);

        if (groupNames.size() == 0) {
            root.add(users.get(0));
        } else {
            if (!users.contains(node)) {
                model.insertNodeInto(cs, node, node.getChildCount());
            }
        }

        Long creationTime = System.currentTimeMillis();
        userTimes.add(creationTime);
        userUpdateTime.add(creationTime);

        System.out.println("New user made at: " + creationTime + " ms.");
    }

    // Open the user view
    public void openUsers() {

        TreePath path = tree.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        userPages = new UserGUI(node, width, height, setUp);

        userPages.setUpGUI();
        userPages.SetUpButtonListeners();
    }

    public static ArrayList<String> getUserNames() {
        return userNames;
    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI(750, 480);
        }
        return instance;
    }

    public boolean checkValidIDs() {
        boolean flag = true;

        // check for spaces then check if it matches any other user names
        for (int i = 0; i < userNames.size(); i++) {
            if (userNames.get(i).contains(" ")) {
                flag = false;
            }
            if (flag == false) {
                break;
            }
            for (int j = 0; j < userNames.size(); j++) {
                if (i != j && userNames.get(i).equals(userNames.get(j))) {
                    flag = false;
                    break;
                }
            }
        }

        // check for spaces then check if it matches any other group names
        for (int i = 0; i < groupNames.size(); i++) {
            if (groupNames.get(i).contains(" ")) {
                flag = false;
            }
            if (flag == false) {
                break;
            }
            for (int j = 0; j < groupNames.size(); j++) {
                if (i != j && groupNames.get(i).equals(groupNames.get(j))) {
                    flag = false;
                }
            }
        }

        return flag;
    }

    public int checkLastUser() {
        long max = 0;
        int index = 0;
        for (int i = 0; i < userUpdateTime.size(); i++) {
            if (userUpdateTime.get(i) > max) {
                max = userUpdateTime.get(i);
                index = i;
            }
        }
        return index;
    }

}
