/**
 * Created by Hasan MEN on 20.02.2016.
 *
 *
 * This is an ABSTRUCT class. All users will extended from USER CLASS.
 * This class implements UserInterface for common properties
 * Class has some user information like name,username,passord ...
 *
 */
public abstract class User implements  UserInterface{

    protected static int TOTAL_USER_ID = 0;

    private String name = null;
    private String surName = null;
    private String userName = null;
    private String eMail = null;
    private String passWord = null;
    private int userID;

    /**
     * User constructor
     * @param name name of user
     * @param surName surname of user
     * @param userName username of user
     * @param passWord password of user
     * @param eMail email address of user
     */
    public User(String name, String surName, String userName, String passWord, String eMail) {
        this.name = name;
        this.surName = surName;
        this.userName = userName;
        this.eMail = eMail;
        this.passWord = passWord;
        this.userID = TOTAL_USER_ID;
        ++TOTAL_USER_ID;
    }

    /**
     * COPY CONSTRUCTOR
     * Takes use reference and copy it.
     * Created independent copy
     * @param user User for copy information
     */
    public User(User user){
        this.name = user.getName();
        this.surName = user.getSurName();
        this.userName =user.getUserName();
        this.eMail = user.geteMail();
        this.passWord = user.getPassWord();
        this.userID =user.getUserID();
    }

    /**
     * This methods gets user name
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * This methods gets user surname
     * @return user surname
     */
    public String getSurName() {
        return surName;
    }

    /**
     * This methods gets user emaill address
     * @return user email address
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * This methods gets user name
     * @return user userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method gets user passWord
     * @return user password
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * This method gets user ID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This methods sets user name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method set user surname
     * @param surName new user surname
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * This method sets userName
     * @param userName new userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method sets user email address
     * @param eMail new email address
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * This method sets user password
     * @param passWord new password
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * This methods compares two user objects.
     *
     * @param o Compare object
     * @return result of comparation
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (surName != null && surName.equals(user.getSurName()) &&
                name != null && name.equals(user.getName())) return true;
        else return false;
    }

    @Override
    public String toString() {
        return String.format(" Name :" + getName() + " " + getSurName() +
                " Username : " + getUserName() + " eMail : " + geteMail());
    }

    // WILL BE OVERRIDE
    public abstract String showCourseItems(Course course);

}
