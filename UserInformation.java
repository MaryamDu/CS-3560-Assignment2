import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * The user (node's) information is saved with their node, 
 * current following, and news feed outside of the user gui 
 * so it isn't deprecated when the user gui is disposed.
 */

public class UserInformation {

    private ArrayList<String> currentFollowing = new ArrayList<String>();
    private ArrayList<String> newsFeed = new ArrayList<String>();
    private DefaultMutableTreeNode node = new DefaultMutableTreeNode();

    public UserInformation(DefaultMutableTreeNode n, ArrayList<String> CF, ArrayList<String> NF) {
        node = n;
        currentFollowing = CF;
        newsFeed = NF;
    }

    public void addFollowing(String text) {
        this.currentFollowing.add(text);
    }

    public void addNewsFeed(String text) {
        this.newsFeed.add(text);
    }

    public ArrayList<String> getFollowing() {
        return this.currentFollowing;
    }

    public ArrayList<String> getNewsFeed() {
        return this.newsFeed;
    }

    public DefaultMutableTreeNode getNode() {
        return this.node;
    }

}
