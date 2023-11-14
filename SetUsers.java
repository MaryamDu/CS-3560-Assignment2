import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * The user (node's) information is saved with their node, 
 * current following, and news feed outside of the user gui 
 * so it isn't deprecated when the user gui is disposed.
 * Set Users makes an accessible array list of said information while
 * UserInformation saves the individual pieces of information. 
 */

public class SetUsers {

    public ArrayList<UserInformation> user_info = new ArrayList<UserInformation>();

    public int setUserInfo(DefaultMutableTreeNode node, ArrayList<String> currentFollowing,
            ArrayList<String> newsFeed) {

        UserInformation us_in = new UserInformation(node, currentFollowing, newsFeed);
        boolean flag = false;
        int index = 0;

        // if the current node selected DOESNT have information, make a new instance
        // if there isnt any information, add the user by default
        if (user_info.size() < 1) {
            us_in.addFollowing("--List of Current Following--");
            us_in.addNewsFeed("--List of New Tweets--");
            user_info.add(us_in);
        } else {
            for (int i = 0; i < user_info.size(); i++) {
                if (user_info.get(i).getNode() != node) {
                    flag = true;
                } else {
                    flag = false;
                    index = i;
                    break;
                }
            }
        }

        // add the user if they don't have information registered
        if (flag) {

            us_in.addFollowing("--List of Current Following--");
            us_in.addNewsFeed("--List of New Tweets--");

            user_info.add(us_in);
            index = user_info.indexOf(us_in);
        }

        return index;

    }

    public ArrayList<UserInformation> getUserInformation() {
        return user_info;
    }

}
