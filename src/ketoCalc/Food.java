/*********************************************
 * This Program is designed to help calculate*
 * the net calories, fat, protein, and carbs *
 * in a meal to help with the keto diet      *
 * *******************************************/

package ketoCalc;

import java.util.Scanner;

public class Food {
//All food items have a number of calories, fat, carbs, and protein (in grams)
// Default value is 0
	private double mServingSize;
	private double mCalories = 0;
	private double mFat = 0;
	private double mCarbs = 0;
	private double mProtein = 0;
	private String mName;
	private static double mTotalCal = 0;
	private static double mTotalFat = 0;
	private static double mTotalProtein = 0;
	private static double mTotalCarbs = 0;
	
//Constructor for new food items	
	Food(double servingSize, double calories, double fat, double carbs, double protein, String name) {
		mServingSize = servingSize;
		mCalories = calories;
		mFat = fat;
		mCarbs = carbs;
		mProtein = protein;
		mName = name;
	}
	
//Method to take a food's nutrition info and multiply it for the number of servings used
	public void setServings(double numberOfServings) {
		mCalories = mCalories*numberOfServings;
		mFat = mFat*numberOfServings;
		mCarbs = mCarbs*numberOfServings;
		mProtein = mProtein*numberOfServings;
	}

//Method to convert object to string
	public String toString() {
		return String.format("Calories: %f \nFat: %f g\nCarbs: %f g\nProtein %f g" ,
							this.mCalories,
							this.mFat,
							this.mCarbs,
							this.mProtein);		
	}
//Method to get name of current food in loop
	public String getName() {
		return mName;
	}
	
//Method to calculate number of servings
	public double numberOfServings(double amountUsed) {
		return amountUsed/mServingSize;
	}
	
//Method to print food nutrition info
	public void printNutrition(){
		System.out.println( this );
	}
	
//Method to calculate total values
	public void calculateTotal() {
		mTotalCal = mTotalCal + mCalories;
		mTotalFat = mTotalFat + mFat;
		mTotalCarbs = mTotalCarbs + mCarbs;
		mTotalProtein = mTotalProtein + mProtein;
	}
	
//Method to print recipe totals
	public void printTotals() {
		System.out.println(String.format("Your recipe totals:\nCalories: %f \nFat: %f g\nCarbs: %f g\nProtein %f g",
											Food.mTotalCal,
											Food.mTotalFat,
											Food.mTotalCarbs,
											Food.mTotalProtein));
	}
	
//Main method takes input for number of servings then calculates nutrition info
	public static void main(String[] args) {
		double amountUsed = 0;
		int length = 0;
		String foodName;
		 //Food objects and allow use of Scanner
		Food whiteCorn = new Food(1, 606, 8, 123, 16, "White Corn");
		Food spinach = new Food(1, 7, 0.1, 1.1, 0.9, "Spinach");
		Food gratedParmesan = new Food(0.0417, 20, 1.5, 0, 2, "Grated Parmesan");
		Food shreddedParmesan = new Food(0.236, 110, 7, 0, 10, "Shredded Parmesan");
		Food eggs = new Food(1, 70, 5, 0, 6, "Eggs");
		Food[] foodList = 
				{whiteCorn, 
				spinach,
				gratedParmesan,
				shreddedParmesan,
				eggs};
		Scanner input = new Scanner( System.in );
		//Determine size of array
		length = foodList.length;
		/*LOOP ITERATIONS ASK HOW MUCH IS USED. IF MORE THAN 0, THEN CALCULATES
		 *SERVINGS AND PRINTS NUTRITION INFO. ANYTHING OTHER THAN 0, ASK AGAIN.
		 *INCREMENTS ARRAY INDEX BY 1*/
		for (int i = 0; i<length; i++) {
			foodName = foodList[i].getName();
			System.out.println( String.format("How much %s did you use?", foodName.toLowerCase()));
			amountUsed = input.nextDouble();
			while (amountUsed<0) {
				System.out.println("Invalid entry. Enter new value.");
				amountUsed = input.nextDouble();
				}
			((Food) foodList[i]).setServings(((Food) foodList[i]).numberOfServings(amountUsed));
			((Food) foodList[i]).calculateTotal();
			}
		foodList[(length-1)].printTotals();
		input.close();
	}
}
//Add a choice for items -- drop down?
