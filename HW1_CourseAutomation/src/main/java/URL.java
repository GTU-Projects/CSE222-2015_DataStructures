/**
 * Created by Hasan MEN on 24.02.2016.
 *
 * This class created for store url adresses
 *
 */
public class URL extends CourseItem{
    private String urlAddress;

    /**
     * Constructor
     * Will create an url object and initialize it
     * @param name name of url
     * @param urlAddress specific adress of url
     */
    public URL(String name,String urlAddress) {
        super(name);
        this.urlAddress = urlAddress;
    }

    /**
     * This method gets url address
     * @return url adress as a string
     */
    public String getUrlAddress() {
        return urlAddress;
    }

    /**
     * This methods taken an url adress as String and sets old url.
     * @param urlAddress new url adress
     */
    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public boolean updateCourseItem(CourseAddable newCourseItem) {
        if (newCourseItem instanceof URL) {
            URL newURL = (URL) newCourseItem;
            setName(newURL.getName()); // isimlerini ata
            setUrlAddress(newURL.getUrlAddress());
            return true;
        } else return false;
    }

    /**
     * Creates url information and return it
     * @return url information
     */
    @Override
    public String toString() {
        return String.format("URL -> Name : "+getName() +" Address : "+getUrlAddress());
    }
}
