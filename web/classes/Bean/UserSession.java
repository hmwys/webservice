package classes.Bean;

public class UserSession {
    private String sid;
    private Integer userID;

    public UserSession(String sid) {
        super();
        this.sid = sid;
    }

    public UserSession(String sid, Integer userID) {
        super();
        this.sid = sid;
        this.userID = userID;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sid='" + sid + '\'' +
                ", userID=" + userID +
                '}';
    }
}
