/*class FoodRatings {

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        
    }
    
    public void changeRating(String food, int newRating) {
        
    }
    
    public String highestRated(String cuisine) {
        
    }
}*/

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

 class FoodRatings {

    // Map food -> cuisine
    private Map<String, String> foodToCuisine;
    // Map food -> rating
    private Map<String, Integer> foodToRating;
    // Map cuisine -> foods (sorted by rating desc, name asc)
    private Map<String, TreeSet<String>> cuisineToFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToFoods.putIfAbsent(cuisine, new TreeSet<>((a, b) -> {
                int cmp = Integer.compare(foodToRating.get(b), foodToRating.get(a));
                if (cmp == 0) return a.compareTo(b); // lexicographically smaller
                return cmp;
            }));

            cuisineToFoods.get(cuisine).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> foodsSet = cuisineToFoods.get(cuisine);

        // Remove old entry
        foodsSet.remove(food);

        // Update rating
        foodToRating.put(food, newRating);

        // Add updated entry
        foodsSet.add(food);
    }

    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first(); // top element
    }
}
