//handles all of the admins totals
public class AddAdmin {

    public int userTotal = 0;
    public int groupTotal = 1;
    public static int msgTotal = 0;
    public static int percentage = 0;
    public static float average = 0;
    public static String lastUser = "";

    public void addGroupNum() {
        groupTotal++;
    }

    public void addUserNum() {
        userTotal++;
    }

    public static void addMsgs() {
        msgTotal++;
    }

    public static void updatePercentage() {
        percentage++;
        calculateAverage();
    }

    public static void calculateAverage() {
        average = (float) percentage / (float) msgTotal;
    }

    public static void setlastUser(String name) {
        lastUser = name;
    }

    public int getUserT() {
        return userTotal;
    }

    public int getGroupT() {
        return groupTotal;
    }

    public int getMsgs() {
        return msgTotal;
    }

    public int getPercent() {
        return percentage;
    }

    public float getAvg() {
        return average;
    }

    public String getLastUser() {
        return lastUser;
    }

}
