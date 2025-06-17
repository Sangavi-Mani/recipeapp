package com.example.recipe_app.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import com.example.recipe_app.model.Category;
import com.example.recipe_app.model.Ingredient;
import com.example.recipe_app.model.Recipe;
import com.example.recipe_app.service.CategoryService;
import com.example.recipe_app.service.RecipeService;

@Controller
public class RecipeController {
	
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/recipes/add")
	public String showHomePage(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("recipe", new Recipe());
		return "home";
	}

	
	@GetMapping("/categories/add")
	public String showCategoryPage(Model model) {
		//List<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("category", new Category());
		return "add-category";
	}
	
	@PostMapping("/addcategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		
		categoryService.saveCategory(category);
		return "redirect:/home";
	}
	
	@PostMapping("/saveRecipe")
	public String saveRecipe(@ModelAttribute("recipe") Recipe recipe) {
		if (recipe.getIngredients() != null) {
	        for (Ingredient ingredient : recipe.getIngredients()) {
	            ingredient.setRecipe(recipe);
	        }
	    }
		
		

		recipeService.saveRecipe(recipe);
		
		return "redirect:/recipes";
		
	}
	
	@GetMapping("/recipes")
    public String viewAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipe-list"; // This should be the name of your Thymeleaf template
    }
	@GetMapping("/recipes/edit/{id}")
    public String editRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipe(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-recipe";
    }
	// Delete a recipe
    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes";
    }



}
