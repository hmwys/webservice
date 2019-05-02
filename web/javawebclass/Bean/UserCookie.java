package javawebclass.Bean;

public class UserCookie {
    private String cname;
    private String cvalue;
    private Integer userID;

    public UserCookie() {
        super();
    }

    public UserCookie(String cname, String cvalue) {
        super();
        this.cname = cname;
        this.cvalue = cvalue;
    }

    public UserCookie(String cname, String cvalue, Integer userID) {
        this.cname = cname;
        this.cvalue = cvalue;
        this.userID = userID;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "UserCookie{" +
                "cname='" + cname + '\'' +
                ", cvalue='" + cvalue + '\'' +
                ", userID=" + userID +
                '}';
    }
}
