public class HomoSapien {
    double height;
    double weight;
    int age;
    int feet;
    int hands;

    public HomoSapien (double height, double weight, int age, int feet, int hands) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.feet = feet;
        this.hands = hands;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getHands() {
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }
}
