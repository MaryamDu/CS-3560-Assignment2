import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;

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

        return index;

    }

    public ArrayList<UserInformation> getUserInformation() {
        return user_info;
    }

}
