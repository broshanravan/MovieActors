/**
 * Created by Behrooz on 19/07/2018.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MaintainActor implements IMaintainActor {

    private String moveActorsFileName = "movieActorsList.txt";

    final static Logger logger = LogManager.getLogger(MaintainActor.class);


    /**
     * reading  the Actor detaild by actorId from a flatfile
     *
     * @param movieActorIdIn the Id on movie actor
     * @return movieActor
     */
    public MovieActor getMoviActorById(int movieActorIdIn) {

        MovieActor movieActor = null;
        FileReader fileReader = null;

        BufferedReader bufferedReader = null;
        String line = null;
        try {
            fileReader =
                    new FileReader(moveActorsFileName);

            bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                movieActor = consructMovieActorFromDetailsDetailsLine(line);
                if (movieActor.getActorId() == movieActorIdIn) {
                    break;
                }
            }

        } catch (FileNotFoundException ex) {
            logger.error("error reading the file", ex);
        } catch (IOException ex) {

        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ioe) {
                logger.error("error closing connection", ioe);

            } catch (NullPointerException noe) {
                logger.error("error Null connection Object", noe);
            }
        }


        return movieActor;

    }

    /**
     * reading the Actor details by actor name from a flatfile
     *
     * @param movieActorNameIn the Id on movie actor
     * @return movieActor
     */
    public List<MovieActor> getMovieActorsByName(String movieActorNameIn) {
        List<MovieActor> movieActorsList = new ArrayList<MovieActor>();

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            fileReader =
                    new FileReader(moveActorsFileName);

            bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                MovieActor movieActor = consructMovieActorFromDetailsDetailsLine(line);
                if (movieActorNameIn.equalsIgnoreCase(movieActor.getName())) {
                    movieActorsList.add(movieActor);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            moveActorsFileName + "'");
        } catch (IOException ex) {

        } finally {

            try {
                bufferedReader.close();
            } catch (IOException ioe) {
                logger.error("error closing connection", ioe);
            } catch (NullPointerException noe) {
                logger.error("error Null connection Object", noe);
            }
        }

        return movieActorsList;

    }

    /**
     * updating the Actor details within
     *
     * @param movieActorIn the Id on movie actor
     */
    public void updateMovieActorDetails(MovieActor movieActorIn) {
        deleteMovieActorDetails(movieActorIn.getActorId());
        addMovieActorToFile(movieActorIn);
    }


    /**
     * Delete the Actor details from the file
     * using the ID
     * @param movieActorIdIn the Id on movie actor
     */
    private void deleteMovieActorDetails(int movieActorIdIn) {

        Charset charset = StandardCharsets.UTF_8;
        MovieActor movieActor = getMoviActorById(movieActorIdIn);
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            File inputFile = new File(moveActorsFileName);
            File tempFile = new File("myTempFile.txt");
            Path path = Paths.get(moveActorsFileName);

            reader = new BufferedReader(new FileReader(inputFile));
            String currentLine;
            StringBuffer contentBuffer = new StringBuffer();

            while ((currentLine = reader.readLine()) != null) {
                MovieActor currentMovieActor = consructMovieActorFromDetailsDetailsLine(currentLine);
                if (movieActor.getActorId() == currentMovieActor.getActorId()) continue;
                contentBuffer.append(currentLine);
                contentBuffer.append("\n");
            }

            String content = contentBuffer.toString().trim();
            Files.write(path, content.getBytes(charset));

        } catch (IOException ioe) {

        } finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException ioe) {
                logger.error("erroe closing resources" + ioe);
            } catch (NullPointerException noe) {
                logger.error("Null resource", noe);
            }
        }

    }


    /**
     * adding the Actor details to the file
     *
     * @param movieActor the Id on movie actor
     */
    public void addMovieActorToFile(MovieActor movieActor) {
        BufferedWriter bufferedWriter = null;

        if (!isMovieAcorIdinUse(movieActor.getActorId())) {

            try {
                bufferedWriter = new BufferedWriter(new FileWriter("movieActorsList.txt", true));
                String movieActorStr = creatMovieActorDetailsLine(movieActor);
                bufferedWriter.append("\n");
                bufferedWriter.append(movieActorStr);

            } catch (IOException ioe) {
                //logger
            } finally {
                try {
                    bufferedWriter.close();
                } catch (IOException ioe) {
                    logger.error("error closing bufferWriter", ioe);
                } catch (NullPointerException npe) {
                    logger.error("Null BufferWriter", npe);
                }
            }
        } else {
            logger.error("The actor ID is already in use");
            System.err.println("The actor ID is already in use");
        }

    }

    /**
     * REtrieving the actor details by
     * Createing an object of type actor from
     * the line containing actor details
     *
     * @param line actor details line from the file
     * @return object of type MovieActor
     */
    public MovieActor consructMovieActorFromDetailsDetailsLine(String line) {
        List<String> actorDetails = Arrays.asList(line.split(","));

        String actorName = actorDetails.get(0);
        String actorIdStr = actorDetails.get(1);
        int actorId = (Integer.parseInt(actorIdStr));
        double actorHeight = Double.parseDouble(actorDetails.get(2));
        String dateOfBirthIn = actorDetails.get(3);
        MovieActor movieActor = new MovieActor(
                actorName,
                actorId,
                actorHeight,
                dateOfBirthIn);
        return movieActor;

    }


    /**
     * creates a string of actor details
     * that will be inserted into the inventory file
     *
     * @param movieActor actor datails object
     * @return actor details String
     */
    public String creatMovieActorDetailsLine(MovieActor movieActor) {

        String movieActorName = movieActor.getName();
        String movieActorHeight = String.valueOf(movieActor.getHeight());
        String movieActorId = Integer.toString(movieActor.getActorId());
        String movieActorDOB = movieActor.getDateOfBirth();

        String movieActorStr = movieActorName + "," + movieActorId + "," + movieActorHeight + "," + movieActorDOB;

        return movieActorStr;
    }


    /**
     * finding out if the user Id
     *is already in use
     * @param movieActorIdIn the Id of movie actor
     *                       retrun isInUse
     */

    public boolean isMovieAcorIdinUse(int movieActorIdIn) {

        boolean isInUse = false;

        MovieActor movieActor = null;

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            fileReader =
                    new FileReader(moveActorsFileName);
            bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                movieActor = consructMovieActorFromDetailsDetailsLine(line);
                if (movieActor.getActorId() == movieActorIdIn) {
                    isInUse = true;
                    break;
                }
            }

        } catch (FileNotFoundException ex) {
            logger.error("error reading the file", ex);
        } catch (IOException ex) {

        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ioe) {
                logger.error("error closing connection", ioe);
            } catch (NullPointerException noe) {
                logger.error("error closing connection", noe);
                logger.error("error Null connection Object", noe);

            }
        }

        return isInUse;

    }

    /**
     * To make sure if the operation entered by user is valid
     *
     * @param operation
     * @return
     */
    public String validateOperation(String operation ) {
        boolean isValid = false;

        while (isValid == false) {

            if (operation != null && ("ADD".equalsIgnoreCase(operation.trim()) ||
                    "DEL".equalsIgnoreCase(operation.trim()) ||
                    "UPD".equalsIgnoreCase(operation.trim()) ||
                    "GETBYNAME".equalsIgnoreCase(operation.trim()) ||
                    "GETBYID".equalsIgnoreCase(operation.trim())
            )) {
                isValid = true;

            } else{
                System.out.println("Invalid selection. Please enter a valid operation code from the above list");
                Scanner scanner = new Scanner(System.in);
                operation = scanner.nextLine();
            }
        }

        return operation;
    }


    /**
     * to operate on the details entered via
     * the command line
     * @param operation the operation  selected by the user
     * @param scanner the scanner that collects user inputs
     */
    public void processActorDetails(String operation, Scanner scanner) {
        operation = validateOperation(operation);
            if ("ADD".equalsIgnoreCase(operation.trim())) {
                System.out.println("Please enter your Movie Actor Details");
                String movieActorDetails = scanner.nextLine();
                MovieActor movieActor = consructMovieActorFromDetailsDetailsLine(movieActorDetails);
                addMovieActorToFile(movieActor);


            } else if ("UPD".equalsIgnoreCase(operation.trim())) {
                System.out.println("Please enter your Movie Actor Details");
                String movieActorDetails = scanner.nextLine();
                MovieActor movieActor = consructMovieActorFromDetailsDetailsLine(movieActorDetails);
                updateMovieActorDetails(movieActor);

            } else if ("GETBYID:".equalsIgnoreCase(operation.trim())) {
                System.out.println("Please enter your Movie Actor ID");
                String movieActorId = scanner.nextLine();
                MovieActor movieActor = getMoviActorById(Integer.parseInt(movieActorId));
                movieActor.displayActorDetails();

            } else if ("GETBYNAME:".equalsIgnoreCase(operation.trim())) {
                String movieActorId = scanner.nextLine();
                String movieActorName = scanner.nextLine();
                System.out.println("Please enter your Movie Actor NAME");
                List<MovieActor> movieActorsList = getMovieActorsByName(movieActorName);

                for (MovieActor movieActor : movieActorsList) {
                    movieActor.displayActorDetails();
                }
            }
    }
}







