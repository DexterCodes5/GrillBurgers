public class Main {

    public static void main(String[] args) {
//        Meal meal = new Meal();
//        meal.printItemizedList();
//        meal.addTopping("BACON");
//        meal.setDrinkSize("LARGE");
//        meal.printItemizedList();

//        Meal meal2 = new Meal("Cheeseburger", "Mirinda", "chili");
//        meal2.addTopping("BACON");
//        meal2.addTopping("CHEESE");
//        meal2.addTopping("MAYO");
//        meal2.addTopping("MAYO");
//        meal2.setDrinkSize("SMALL");
//        meal2.printItemizedList();

        Meal deluxe = new Meal("Deluxe", "sprite", "fries");
        deluxe.addTopping("AVOCADO");
        deluxe.addTopping("BACON");
        deluxe.addTopping("LETTUCE");
        deluxe.addTopping("CHEESE");
        deluxe.addTopping("MAYO");
        deluxe.addTopping("MAYO");
        deluxe.setDrinkSize("SMALL");
        deluxe.printItemizedList();

    }
}
