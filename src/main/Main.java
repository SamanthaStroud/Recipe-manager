import java.util.List;
import java.util.Scanner;
import model.Recipe;
import service.RecipeManager;

public class Main {

    // Simple console application
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();
        Scanner scanner = new Scanner(System.in);

        // some recipes
        manager.addRecipe(new Recipe("Spaghetti Bolognese", "Dinner", List.of("pasta", "beef mince", "tomato", "onion")));
        manager.addRecipe(new Recipe("Pancakes", "Breakfast", List.of("flour", "egg", "milk", "butter")));
        manager.addRecipe(new Recipe("Caesar Salad", "Lunch", List.of("romaine lettuce", "parmesan", "croutons", "egg")));

        System.out.println("=== Recipe Manager ===");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listAllRecipes(manager);
                case "2" -> searchIngredient(manager, scanner);
                case "3" -> searchCategory(manager, scanner);
                case "4" -> addFavourite(manager, scanner);
                case "5" -> showFavourites(manager);
                case "6" -> running = false;
                default  -> System.out.println("Invalid option, try again.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    // Helper methods for menu actions
    private static void printMenu() {
        System.out.println("\n1. List all recipes");
        System.out.println("2. Search by ingredient");
        System.out.println("3. Search by category");
        System.out.println("4. Add recipe to favourites");
        System.out.println("5. View favourites");
        System.out.println("6. Exit");
        System.out.print("Choose: ");
    }

    // List all recipes
    private static void listAllRecipes(RecipeManager manager) {
        manager.getAllRecipes().forEach(r -> System.out.println("  - " + r));
    }

    // Search by ingredient
    private static void searchIngredient(RecipeManager manager, Scanner scanner) {
        System.out.print("Enter ingredient: ");
        List<Recipe> results = manager.searchByIngredient(scanner.nextLine());
        results.forEach(r -> System.out.println("  - " + r.getName()));
        if (results.isEmpty()) System.out.println("  No matches found.");
    }

    // Search by category
    private static void searchCategory(RecipeManager manager, Scanner scanner) {
        System.out.print("Enter category: ");
        List<Recipe> results = manager.searchByCategory(scanner.nextLine());
        results.forEach(r -> System.out.println("  - " + r.getName()));
        if (results.isEmpty()) System.out.println("  No matches found.");
    }

    // Add to favourites
    private static void addFavourite(RecipeManager manager, Scanner scanner) {
        System.out.print("Enter recipe name: ");
        boolean success = manager.addToFavourites(scanner.nextLine());
        System.out.println(success ? "  Added to favourites!" : "  Recipe not found.");
    }

    // Show favourites
    private static void showFavourites(RecipeManager manager) {
        List<Recipe> favs = manager.getFavourites();
        favs.forEach(r -> System.out.println("  - " + r.getName()));
        if (favs.isEmpty()) System.out.println("  No favourites saved yet.");
    }
}
