package service;

import java.util.ArrayList;
import java.util.List;
import model.Recipe;

// Manages a collection of recipes, allowing addition, removal, searching, and managing favourites.
public class RecipeManager {

    private final List<Recipe> recipes = new ArrayList<>();

    // Adds a new recipe to the collection, also ensuring no duplicate names exist.
    public void addRecipe(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null");
        }
        boolean alreadyExists = recipes.stream()
                .anyMatch(r -> r.getName().equalsIgnoreCase(recipe.getName()));
        if (alreadyExists) {
            throw new IllegalArgumentException("A recipe named '" + recipe.getName() + "' already exists");
        }
        recipes.add(recipe);
    }
    
    // Removes a recipe by name
    public boolean removeRecipe(String name) {
        return recipes.removeIf(r -> r.getName().equalsIgnoreCase(name));
    }

    // Searches for recipes that contain the ingredient 
    public List<Recipe> searchByIngredient(String ingredient) {
        if (ingredient == null || ingredient.isBlank()) {
            return new ArrayList<>();
        }
        String query = ingredient.toLowerCase();
        return recipes.stream()
                .filter(r -> r.getIngredients().stream()
                        .anyMatch(i -> i.toLowerCase().contains(query)))
                .toList();
    }

    // Searches for recipes by category
    public List<Recipe> searchByCategory(String category) {
        if (category == null || category.isBlank()) {
            return new ArrayList<>();
        }
        return recipes.stream()
                .filter(r -> r.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // Marks a recipe as favourite by name
    public boolean addToFavourites(String name) {
        return recipes.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(r -> { r.setFavourite(true); return true; })
                .orElse(false);
    }

    // Removes a recipe from favourites by name
    public boolean removeFromFavourites(String name) {
        return recipes.stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(r -> { r.setFavourite(false); return true; })
                .orElse(false);
    }

    // Returns a list of all favourite recipes
    public List<Recipe> getFavourites() {
        return recipes.stream().filter(Recipe::isFavourite).toList();
    }

    // Returns a list of all recipes
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }
}
