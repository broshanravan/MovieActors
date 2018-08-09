import java.util.List;
import java.util.Scanner;

/**
 * Created by Behrooz on 19/07/2018.
 */
public interface IMaintainActor {
    public MovieActor getMoviActorById(int movieActorId);
    public List<MovieActor> getMovieActorsByName(String movieActorNameIn);
    public void addMovieActorToFile(MovieActor movieActor);
    public void updateMovieActorDetails (MovieActor movieActor);
    public MovieActor consructMovieActorFromDetailsDetailsLine(String line);
    public String validateOperation(String operation);
    public void processActorDetails(String operation, Scanner scanner);
}
