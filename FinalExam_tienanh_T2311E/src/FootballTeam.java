import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FootballTeam {
    private static Scanner scanner = new Scanner(System.in);



    // Method to create a team of 11 players
    public static List<FootballPlayer> CreatePlayer() {
        List<FootballPlayer> team = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            System.out.println("Enter details for Player " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Shirt No: ");
            int shirtNo = Integer.parseInt(scanner.nextLine());
            System.out.print("Position: ");
            String position = scanner.nextLine();
            System.out.print("Height (in cm): ");
            double height = Double.parseDouble(scanner.nextLine());
            System.out.print("Weight (in kg): ");
            double weight = Double.parseDouble(scanner.nextLine());
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            FootballPlayer player = new FootballPlayer(name, shirtNo, position, height, weight, age);
            team.add(player);
        }
        return team;
    }

    // Method to display all players
    public static void Display(List<FootballPlayer> team) {
        System.out.println("Team Players:");
        for (FootballPlayer player : team) {
            player.displayInfo();
            System.out.println(); // Print a blank line between players
        }
    }
}
