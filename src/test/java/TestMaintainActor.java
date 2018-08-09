
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Behrooz on 19/07/2018.
 */
public class TestMaintainActor {

    @Test
    public void testGetActorById() {

        MaintainActor maintainActor = new MaintainActor();

        MovieActor movieActor = maintainActor.getMoviActorById(1001);
        assert("Sandra Bullock".equalsIgnoreCase(movieActor.getName()));
        assert (1.71 == movieActor.getHeight());
        assert("26 July 1964".equalsIgnoreCase(movieActor.getDateOfBirth()));
    }

    @Test
    public void testGetActorByName(){
        MaintainActor maintainActor = new MaintainActor();
        List<MovieActor> movieActorList = maintainActor.getMovieActorsByName("Sandra Bullock");

        assert(1 == movieActorList.size());
        MovieActor movieActor = movieActorList.get(0);

        assert(1001 == movieActor.getActorId());
        assert (1.71 == movieActor.getHeight());
        assert("26 July 1964".equalsIgnoreCase(movieActor.getDateOfBirth()));

    }

    @Test
    public void testConsructMovieActorFromDetailsDetailsLine() {
        MaintainActor maintainActor = new MaintainActor();
        MovieActor movieActor = maintainActor.consructMovieActorFromDetailsDetailsLine("Sandra Bullock,1001,1.71,26 July 1964");
        assert (1001 == movieActor.getActorId());
        assert ("26 July 1964".equalsIgnoreCase(movieActor.getDateOfBirth()));
        assert ("Sandra Bullock".equalsIgnoreCase(movieActor.getName()));
        assert (1.71 == movieActor.getHeight());
    }

    @Test
    public void testAddMovieActorToFile() {
        MaintainActor maintainActor = new MaintainActor();
        MovieActor bruce = new MovieActor("Bruce Roshanravan", 666 , 1.62, "10 May 1968");
        maintainActor.addMovieActorToFile(bruce);

        MovieActor movieActor = maintainActor.getMoviActorById(666);

        if(!maintainActor.isMovieAcorIdinUse(bruce.getActorId())){
            assertNotNull(movieActor);
            assert (666 == movieActor.getActorId());
            assert ("10 May 1968".equalsIgnoreCase(movieActor.getDateOfBirth()));
            assert ("Bruce Roshanravan".equalsIgnoreCase(movieActor.getName()));
            assert (1.62 == movieActor.getHeight());
        }

    }

    @Test
    public void testUpdatedMovieActorToFile() {
        MaintainActor maintainActor = new MaintainActor();

        MovieActor bruce = new MovieActor("Bruce Roshanravan_1", 666 , 1.62, "20 May 1968");
        if(maintainActor.isMovieAcorIdinUse(bruce.getActorId())){
            maintainActor.updateMovieActorDetails(bruce);
            MovieActor movieActor = maintainActor.getMoviActorById(666);
            assert("Bruce Roshanravan_1".equalsIgnoreCase(movieActor.getName()));
            assert (1.62 == movieActor.getHeight());
            assert("20 May 1968".equalsIgnoreCase(movieActor.getDateOfBirth()));
        }

        ;

    }

    @Test
    public void testCreatMovieActorDetailsLine() {
        MaintainActor maintainActor = new MaintainActor();
        MovieActor movieActo = new MovieActor("Sandra Bullock",1001,1.71,"26 July 1964");
        String movieActorStr = maintainActor.creatMovieActorDetailsLine(movieActo);
        assertEquals("Sandra Bullock,1001,1.71,26 July 1964",movieActorStr);


    }
}
