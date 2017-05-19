/**
 * Created by Hasan MEN on 22.02.2016.
 *
 * This file include UserInterface Interface
 * This interface will use for users.
 * Every user has common spesifications. We will enforce users to override this methods
 */
public interface UserInterface {
    /**
     * Get user name
     * @return user name
     */
    String getName();

    /**
     * Get surname
     * @return user surname
     */
    String getSurName();

    /**
     * gets User email
     * @return user email
     */
    String geteMail();

    /**
     * gets user username
     * @return userName
     */
    String getUserName();

    /**
     * Gets usr password
     * @return password
     */
    String getPassWord();

    /**
     * get user id
     * @return userId
     */
    int getUserID();
}
