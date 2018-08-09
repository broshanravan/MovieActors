import java.util.Scanner;

public class MovieActorWorld {

    public static void main(String[] args){
        IMaintainActor maintainActor = new MaintainActor();

        System.out.println("Please enter your operation");

        System.out.println("ADD : to add Movie Actor");
        System.out.println("UPD : to update Movie Actor Detaild");
        System.out.println("DEL : to remove Movie Actor");
        System.out.println("GETBYID: to Show Movie Actor details using the Name");
        System.out.println("GETBYNAME : to Show Movie Actor details using ID");

        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        maintainActor.processActorDetails(operation,scanner);
    }
}
