package com.example.recipe_app.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe_app.model.Recipe;
import com.example.recipe_app.repository.CategoryRepository;
import com.example.recipe_app.repository.RecipeRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class RecipeService {
	@Autowired
	private RecipeRepository rr;
	@Autowired
	private CategoryRepository cr;
	
	public void saveRecipe( Recipe recipe) {
		//List<In>
		 rr.save(recipe);
		//rr.saveAndFlush(recipe);
	}
	
	public Recipe getRecipe(Long id) {
		Recipe recipe = rr.findById(id).orElse(null);
        System.out.println("Recipe: " + recipe);
        return recipe;
    }
	
	public void deleteRecipe(Long id) {
		Recipe recipe = rr.findById(id).orElse(null);
		rr.delete(recipe);
	}
	public List<Recipe> getAllRecipes(){
		return rr.findAll();
	}
	
}
