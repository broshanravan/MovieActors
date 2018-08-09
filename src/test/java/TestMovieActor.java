import org.junit.Test;

/**
 * Created by Behrooz on 19/07/2018.
 */
public class TestMovieActor {



    @Test
    public void testSetName() {
        MovieActor movieActor = new MovieActor();
        movieActor.setName("Brucee");
        assert("Brucee".equalsIgnoreCase(movieActor.getName()));
    }

    @Test
    public void testSetActorId() {
        MovieActor movieActor = new MovieActor();
        movieActor.setActorId(666);
        assert(movieActor.getActorId() ==666);
    }


    @Test
    public void testSetHeight() {
        MovieActor movieActor = new MovieActor();
        movieActor.setHeight(4.51);
        assert(movieActor.getHeight() ==4.51);
    }


    @Test
    public void testSetDateOfBirth() {
        MovieActor movieActor = new MovieActor();
        movieActor.setDateOfBirth("10 may 1968");
        assert("10 may 1968".equalsIgnoreCase(movieActor.getDateOfBirth()));

    }
}
