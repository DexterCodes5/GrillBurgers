import java.util.ArrayList;

// Encapsulation, Composition, Inheritance
public class Meal {
    private Burger burger;
    private Item drink;
    private Item side;

    public Meal() {
        this("Hamburger", "Sprite", "Fries");
    }

    public Meal(String burger, String drink, String side) {
        if (burger.equalsIgnoreCase("DELUXE")) {
            this.burger = new Burger(burger, 8.5);
        }
        else {
            this.burger = new Burger(burger, 4);
        }
        this.drink = new Item("DRINK", drink, 1);
        this.side = new Item("SIDE", side, 1.5);
    }

    public double getTotalPrice() {
        if (burger.isDeluxe()) {
            return burger.getAdjustedPrice();
        }
        return burger.getAdjustedPrice() + drink.getAdjustedPrice() + side.getAdjustedPrice();
    }

    public void addTopping(String topping) {
        burger.addTopping(topping);
    }

    public void setDrinkSize(String size) {
        drink.setSize(size);
    }

    public void printItemizedList() {
        burger.printItemizedList();
        if (burger.isDeluxe()) {
            Item.printItem(drink.getName(), 0, false);
            Item.printItem(side.getName(), 0, false);
        }
        else {
            Item.printItem(drink.getName(), drink.getAdjustedPrice(), false);
            Item.printItem(side.getName(), side.getAdjustedPrice(), false);
        }
        System.out.println("-".repeat(30));
        Item.printItem("TOTAL PRICE", getTotalPrice(), false);
        System.out.println();
    }
}

class Item {
    private String type;
    private String name;
    private double price;
    private String size = "MEDIUM";

    public Item(String type, String name, double price) {
        type = type.toUpperCase();
        if (!type.equals("BURGER") && !type.equals("SIDE") && !type.equals("DRINK") && !type.equals("TOPPING")) {
            this.type = "INVALID";
        }
        else {
            this.type = type.toUpperCase();
        }
        this.name = name.toUpperCase();
        this.price = price;
    }

    public static void printItem(String name, double price, boolean topping) {
        if (topping) {
            System.out.printf("%20s:%7.2f%n", name, price);
            return;
        }
        System.out.printf("%20s:%6.2f%n", name, price);
    }

    // Getters
    public String getName() {
        if (type.equals("SIDE") || type.equals("DRINK")) {
            return size + " " + name;
        }
        return name;
    }

    public double getBasePrice() {
        return price;
    }

    public double getAdjustedPrice() {
        return switch (size) {
            case "SMALL" -> getBasePrice() - 0.5;
            case "LARGE" -> getBasePrice() + 1;
            default -> getBasePrice();
        };
    }

    // Setters
    public void setSize(String size) {
        this.size = size;
    }
}

class Burger extends Item {
    private ArrayList<Item> toppings;
    private boolean deluxe;

    public Burger(String name, double price) {
        super("Burger", name, price);
        if (getName().equals("DELUXE")) deluxe = true;
        toppings = new ArrayList<>();
    }

    public void addTopping(String topping) {
        if (!deluxe && toppings.size() == 3) {
            System.out.println("Burger -> addToppings Error: Can't add more toppings(3)");
            return;
        }
        else if (deluxe && toppings.size() == 5) {
            System.out.println("Burger -> addToppings Error: Can't add more toppings(Deluxe 5)");
            return;
        }
        toppings.add(new Item("Topping", topping, getExtraPrice(topping)));
    }

    public double getExtraPrice(String topping) {
        if (deluxe) return 0;
        return switch (topping) {
            case "AVOCADO", "CHEESE" -> 1.0;
            case "BACON", "HAM", "SALAMI" -> 1.5;
            default -> 0.0;
        };
    }

    public void printItemizedList() {
        printItem(getName(), getBasePrice(), true);
        for (Item i: toppings) {
            printItem(i.getName(), i.getAdjustedPrice(), true);
        }
        System.out.printf("%27.2f%n", getAdjustedPrice());
    }

    @Override
    public double getAdjustedPrice() {
        double res = getBasePrice();
        for (Item i: toppings) {
            res += i.getAdjustedPrice();
        }
        return res;
    }

    // Getters
    public boolean isDeluxe() {
        return deluxe;
    }

}
