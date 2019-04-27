package javawebclass.Bean;

public class User {
    private Integer ID;
    private String userName;
    private String passwd;
    private String email;
    private Integer level;

    public User() {
        super();
    }

    public User(Integer ID){
        super();
        this.ID=ID;
    }

    public User(Integer ID, String userName, String passwd, String email) {
        super();
        this.ID = ID;
        this.userName = userName;
        this.passwd = passwd;
        this.email = email;
    }

    public User(Integer ID, String userName, String passwd) {
        super();
        this.ID = ID;
        this.userName = userName;
        this.passwd = passwd;
    }

    public User(String userName, String passwd) {
        super();
        this.userName = userName;
        this.passwd = passwd;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
