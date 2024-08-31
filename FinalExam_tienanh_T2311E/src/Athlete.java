class Athlete extends HomoSapien {
    public Athlete(double height, double weight, int age, int feet, int hands) {
        super(height, weight, age, feet, hands);
    }

    // Method to be overridden by subclasses
    public void run() {
        System.out.println("Athlete is running.");
    }

    public void jump() {
        System.out.println("Athlete is jumping.");
    }
}
