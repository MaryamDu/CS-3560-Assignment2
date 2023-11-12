import java.util.ArrayList;

public class UserInformation {
    private ArrayList<String> currentFollowing = new ArrayList<String>();
    private ArrayList<String> newsFeed = new ArrayList<String>();

    public void addFollowing(String text) {
        currentFollowing.add(text);
    }

    public void addNewsFeed(String text) {
        newsFeed.add(text);
    }

    public ArrayList<String> getFollowing() {
        return currentFollowing;
    }

    public ArrayList<String> getNewsFeed() {
        return newsFeed;
    }
}
