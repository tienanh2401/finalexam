class FootballPlayer extends Athlete {
    private String name;
    private int shirtNo;
    private String position;

    // Constructor
    public FootballPlayer(String name, int shirtNo, String position, double height, double weight, int age) {
        super(height, weight, age, 2, 2);
        this.name = name;
        this.shirtNo = shirtNo;
        this.position = position;
    }

    // Method to shoot
    public void shoot() {
        System.out.println(name + " is shooting a ball.");
    }

    // Method to pass
    public void pass(String teammate) {
        System.out.println(name + " is passing to " + teammate + ".");
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Method to display football player info
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Shirt No: " + shirtNo);
        System.out.println("Position: " + position);
        System.out.println("Height: " + getHeight() + " cm");
        System.out.println("Weight: " + getWeight() + " kg");
        System.out.println("Age: " + getAge() + " years");
    }

    // Accessors for height, weight, and age

}
