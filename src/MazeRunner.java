
import java.util.*;
public class MazeRunner {
    public static Scanner input = new Scanner(System.in);
    public static Maze myMap = new Maze();


    public static void main(String[] args){
        String direction = "";
        int moveCount = 0;
        intro();
        while(!myMap.didIWin()) {
            direction = userMove(); /*prompting and getting user direction input*/
            takeMove(direction); /*checking and making move if it is possible*/
            ++moveCount;
            movesMessage(moveCount);
            if (moveCount>100) {
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                break;
            }
        }
        //displaying closing message
        System.out.println("Congratulations, you made it out alive! And you did it in " + moveCount + " moves");



    }

    public static void intro(){

        // Welcome the user into the program and print the new map.

        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        myMap.printMap();
    }


    public static String userMove(){
        String userChoice;
        do{
            System.out.print("Where would you like to move? (R, L, U, D) ");
            userChoice = input.next();
        } while(!userChoice.equals("R") && !userChoice.equals("L") && !userChoice.equals("U") && !userChoice.equals("D"));
        return userChoice;
    }
    public static void takeMove(String userChoice){

        if(userChoice.equals("U") && myMap.canIMoveUp()) {
            myMap.moveUp();
        } else if (userChoice.equals("D") && myMap.canIMoveDown()) {
            myMap.moveDown();
        } else if (userChoice.equals("L") && myMap.canIMoveLeft()) {
            myMap.moveLeft();
        } else if (userChoice.equals("R") && myMap.canIMoveRight()){
            myMap.moveRight();
        } else if(myMap.isThereAPit(userChoice)) {
            navigatePit(userChoice);
        } else {
            System.out.println("Sorry, you’ve hit a wall.");
        }

        myMap.printMap();
    }
    public static void navigatePit(String userChoice){
        System.out.print("Watch out! There's a pit ahead, jump it? (y/n) ");
        String option = input.next();
        if(option.equalsIgnoreCase("y")) {
            myMap.jumpOverPit(userChoice);
        }
    }


    public static void movesMessage(int moves){
        switch (moves){
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
                break;

            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                break;

            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;

            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                System.out.println("Sorry, but you didn't escape in time- you lose!");
        }
    }
}


