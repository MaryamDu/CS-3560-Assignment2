public class AddAdmin {

    public int userTotal = 0;
    public int groupTotal = 0;
    public int msgTotal = 0;
    public int percentage = 0;

    public void addGroupNum() {
        groupTotal++;
    }

    public void addUserNum() {
        userTotal++;
    }

    public void addMsgs() {
        msgTotal++;
    }

    public void updatePercentage() {
        percentage++;
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

}
