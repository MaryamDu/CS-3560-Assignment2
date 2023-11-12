import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionListener;
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

    // Layout
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel topPanel;
    private JPanel topPanelCont;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JTree tree;

    private int width;
    private int height;

    // Constructor initializes the layout and interactions
    public GUI(int w, int h) {
        frame = new JFrame();
        mainPanel = new JPanel(new GridLayout(1, 2));
        rightPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridLayout(2, 1));
        topPanelCont = new JPanel(new GridLayout(2, 2));
        middlePanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new GridLayout(2, 2));

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

        width = w;
        height = h;
    }

    // Separate the GUI by its panels
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
        bottomPanel.add(msgTotal);
        bottomPanel.add(groupTotal);
        bottomPanel.add(posperc);

        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(tree);
        mainPanel.add(rightPanel);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Make the tree
    public void setUpTree() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode cs1 = new DefaultMutableTreeNode("CS3560");
        DefaultMutableTreeNode cs2 = new DefaultMutableTreeNode("CS3560-1");

        tree = new JTree(root);
        tree.setBounds(25, 25, 200, 200);

        root.add(cs1);
        root.add(cs2);

    }

    // Button Interactions
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

                } else if (ae.getSource() == userView) {
                    UserGUI userWindow = new UserGUI(width, height);
                    userWindow.setUpGUI();
                    userWindow.SetUpButtonListeners();
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
    }

}
