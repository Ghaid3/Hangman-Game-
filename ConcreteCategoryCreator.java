/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Access;


public class ConcreteCategoryCreator implements CategoryCreator {
    @Override
    public Category createCategory(String type) {
        
        switch (type.toLowerCase()) {
            case "foods":
                return new FoodCategory();
            case "plants":
                return new PlantsCategory();
            case "names":
                return new NamesCategory();
            default:
                throw new IllegalArgumentException("Unknown category: " + type);
        }
    }
}

