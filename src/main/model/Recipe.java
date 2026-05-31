package model;

import java.util.ArrayList;
import java.util.List;

// Represents a recipe with a name, category, list of ingredients, and a favourite status.
public class Recipe {

    // fields
    private String name;
    private String category;
    private List<String> ingredients;
    private boolean favourite;

    // Constructor 
    public Recipe(String name, String category, List<String> ingredients) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Recipe name cannot be empty");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        this.name = name;
        this.category = category;
        this.ingredients = ingredients != null ? new ArrayList<>(ingredients) : new ArrayList<>();
        this.favourite = false;
    }

    // Getters and setters 
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    // Checks if the recipe is marked as favourite
    public boolean isFavourite() {
        return favourite;
    }

    // Sets the favourite status 
    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Recipe{name='" + name + "', category='" + category +
               "', favourite=" + favourite + ", ingredients=" + ingredients + "}";
    }
}
