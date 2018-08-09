import java.util.Date;

/**
 * Created by Behrooz on 19/107/2018.
 */
public class MovieActor {
    private String name;
    private int actorId;
    private double height;

    private String dateOfBirth;

    /**
     * This method displays all the
     */
    public void displayActorDetails() {
        System.out.println("Actor name = " + name);
        System.out.println("Actor Id = " + actorId);
        System.out.println("Actor height = " + height);
        System.out.println("Actor dateOfBirth = " +dateOfBirth);
    }

    public MovieActor(String nameIn, int actorIdIn, double heightIn, String dateOfBirthIn){
        name = nameIn;
        actorId = actorIdIn;
        height = heightIn;
        dateOfBirth = dateOfBirthIn;
    }

    public MovieActor() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
