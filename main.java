//Inspired by https://youtu.be/EpB9u4ItOYU

import java.util.Random;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        //System Objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
       

        //Game Variables
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        int maxEnemyHealth = 10;
        int enemyAttackDamage = 7;
        int highScore =11;

        //Player Variables
        int health = 10;
        int amountOfDefeatedEnemies = 0;
        int attackDamage = 8;
        int numHealthPotions = 1;
        int healthPotionHealAmount = 5;
        int healthPotionDropChance = 24; //Percent

        boolean running = true;

        System.out.println("\n\nWelcome to the Dungeon!");
        
        GAME:
        while(running){
            try {
                Thread.sleep(1500);
                System.out.println("You start exploring the dungeon more...");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("---------------------------------------------");
            

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            if (enemyHealth==0){
                enemyHealth =1;
                    }
            String enemy = enemies[rand.nextInt(3)];
            System.out.println("\t# "+ enemy + " has appeared! #\n");

            while(enemyHealth>0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t"+ enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\t---What would you like to do?---");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                if(input.equals("1")){
                    int myAttack = rand.nextInt(attackDamage);
                    System.out.println("\n\t>>> Your strike does " + myAttack + " points of damage to the enemy!");
                    enemyHealth -= myAttack;
                    if(enemyHealth<1){
                        amountOfDefeatedEnemies +=1;
                        System.out.println("\n\t***********************");
                        System.out.println("\tYou defeated the "+enemy+"! You have defeated "+ amountOfDefeatedEnemies + " enemie(s)!\n");
                        if(rand.nextInt(100) < healthPotionDropChance){
                            numHealthPotions++;
                            System.out.println("\t$ Congrats! You found a Health Potion, you now have "+ numHealthPotions+"!\n");
                        }
                        System.out.println("\t***********************\n");
                        

                        continue GAME;
                    }
                    
                    int enemyAttack = rand.nextInt(enemyAttackDamage);
                    System.out.println("\t>>> The enemy does " + enemyAttack + " damage!\n");
                    health -= enemyAttack;
                    if(health<1){
                        System.out.println("\t> You've taken too much damage, you are too weak to go on!");
                        break;
                    }
                    

                }
                else if(input.equals("2")) {
                    if(numHealthPotions>0){
                        health+=healthPotionHealAmount;
                        numHealthPotions--;
                        System.out.println("\n\t- The potion restored " + healthPotionHealAmount + " HP points\n" 
                                            +"\t- You now have " + health + " HP" 
                                            +"\n\t- You have "+ numHealthPotions + " Health Potion(s)\n");
                        
                    }else{
                        System.out.println("\t& You have no more potions, defeat enemies for a chance to gain one!\n");
                    }
                }
                else if(input.equals("3")){
                    System.out.println("\tYou ran away! \n");
                    break;
                }
                else{
                    //Repeat Menu
                }
            }
            if(health<1){
                System.out.println("\n---------------------------------------------");
                System.out.println("You limp out of the dungeon...");
                if(amountOfDefeatedEnemies>11){
                    int dif = amountOfDefeatedEnemies-highScore;
                System.out.println("Congrats, you defeated "+ amountOfDefeatedEnemies + " enimies, " + dif +" more than the high score!\n");
                }else{
                    System.out.println("You defeated "+ amountOfDefeatedEnemies+" enemies. Try again to bet the high score of "+ highScore+ "\n");
                };
                System.out.println("---------------------------------------------\n\n");
                break;
            }


        }
        in.close();
    }
}
