import java.util.ArrayList;

public class UserInformation {
    private ArrayList<String> currentFollowing = new ArrayList<String>();
    private ArrayList<String> newsFeed = new ArrayList<String>();
    private static final UserInformation userinfo = new UserInformation();

    public UserInformation() {
    }

    public UserInformation(ArrayList<String> CF, ArrayList<String> NF) {

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

    public static UserInformation getInstance() {
        return userinfo;
    }
}
