package service;

import java.util.List;
import model.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class RecipeManagerTest {

    private RecipeManager manager;

    // Sets up a RecipeManager instance with some initial recipes before each test.
    @BeforeEach
    void setUp() {
        manager = new RecipeManager();
        manager.addRecipe(new Recipe("Spaghetti Bolognese", "Dinner", List.of("pasta", "beef", "tomato")));
        manager.addRecipe(new Recipe("Pancakes", "Breakfast", List.of("flour", "egg", "milk")));
        manager.addRecipe(new Recipe("Caesar Salad", "Lunch", List.of("lettuce", "egg", "parmesan")));
    }

    // Tests for adding recipes
    @Test
    void addRecipe_increasesTotalCount() {
        int before = manager.getAllRecipes().size();
        manager.addRecipe(new Recipe("Omelette", "Breakfast", List.of("egg", "cheese")));

        assertEquals(before + 1, manager.getAllRecipes().size());
    }

    // Tests for adding duplicate recipes 
    @Test
    void addRecipe_duplicate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.addRecipe(new Recipe("Pancakes", "Breakfast", List.of("flour"))));
    }

    // Tests for adding a null recipe
    @Test
    void addRecipe_null_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                manager.addRecipe(null));
    }

    @Test
    void removeRecipe_existingName_returnsTrue() {
        boolean result = manager.removeRecipe("Pancakes");

        assertTrue(result);
        assertEquals(2, manager.getAllRecipes().size());
    }

    @Test
    void removeRecipe_nonExistentName_returnsFalse() {
        boolean result = manager.removeRecipe("Burger");

        assertFalse(result);
        assertEquals(3, manager.getAllRecipes().size());
    }

    @Test
    void searchByIngredient_matchingIngredient_returnsCorrectRecipes() {
        List<Recipe> results = manager.searchByIngredient("egg");

        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(r -> r.getName().equals("Pancakes")));
        assertTrue(results.stream().anyMatch(r -> r.getName().equals("Caesar Salad")));
    }

    @Test
    void searchByIngredient_noMatch_returnsEmptyList() {
        List<Recipe> results = manager.searchByIngredient("dragon fruit");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchByIngredient_blankQuery_returnsEmptyList() {
        List<Recipe> results = manager.searchByIngredient("   ");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchByIngredient_isCaseInsensitive() {
        List<Recipe> results = manager.searchByIngredient("EGG");

        assertEquals(2, results.size());
    }

    @Test
    void searchByCategory_matchingCategory_returnsCorrectRecipes() {
        List<Recipe> results = manager.searchByCategory("Breakfast");

        assertEquals(1, results.size());
        assertEquals("Pancakes", results.get(0).getName());
    }

    @Test
    void searchByCategory_noMatch_returnsEmptyList() {
        List<Recipe> results = manager.searchByCategory("Dessert");

        assertTrue(results.isEmpty());
    }

    @Test
    void addToFavourites_existingRecipe_marksAsFavourite() {
        manager.addToFavourites("Pancakes");

        List<Recipe> favs = manager.getFavourites();
        assertEquals(1, favs.size());
        assertEquals("Pancakes", favs.get(0).getName());
    }

    @Test
    void addToFavourites_nonExistentRecipe_returnsFalse() {
        boolean result = manager.addToFavourites("Pizza");

        assertFalse(result);
        assertTrue(manager.getFavourites().isEmpty());
    }

    @Test
    void removeFromFavourites_existingFavourite_removesIt() {
        manager.addToFavourites("Pancakes");
        manager.removeFromFavourites("Pancakes");

        assertTrue(manager.getFavourites().isEmpty());
    }
    
    @Test
    void getFavourites_noFavourites_returnsEmptyList() {
        assertTrue(manager.getFavourites().isEmpty());
    }
}
