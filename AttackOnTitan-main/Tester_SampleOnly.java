package assignemnt;
import java.util.Scanner;        
import java.util.Arrays;

public class Tester {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int gameHour = 0;
        // create a coin object with balance = 50
        Coin coin = new Coin();
        
        // initialize the wall objects with initial HP = 50
        Wall[] walls = new Wall[10];
        Arrays.fill(walls, new Wall(50));
        
        // initialize the weapon objects with level = 0 and damage = 0
        Weapon[] weapons = new Weapon[10];
        Arrays.fill(weapons, new Weapon());
         
        // loading screen

        // welcome screen

        // start to play BGM

        // start game
        System.out.println("The Game Starts\n\n");
        // load game
        // view leaderboard
        // exit
        
        // select mode(endless mode or 33hour to end)
        System.out.print("Please select the game mode(80 hours to end,1"
        + " or endless mode,2 : ");
        int mode = s.nextInt();
        
        //game mode 1 
        if(mode==1){
            while (gameHour<81){ 
                while(Wall.testHP(walls)){ //test the HP of wall
                    //method to prompt user to upgrade weapon/wall and display balance
                    playerTurn();
                   
                    // display walls in front of ground
                    Wall.display(walls);
                    // display weapons on wall
                    Weapon.display(weapons);
                    
                    //method for the computer turn (Enter to continue)
                    enermyTurn();
                }
                System.out.println("Player loses as one of the walls is "
                        + "destroyed by the Titans at hour "+gameHour);
            }    
            System.out.println("Player wins as the Titans unable to"
                    + " destroy the wall within 80 game hours. ");
        }
        //game mode 2
        else if (mode==2){
            while(Wall.testHP(walls)){
                playerTurn();
                
                    
                // display walls in front of ground
                Wall.display(walls);
                // display weapons on wall
                Weapon.display(weapons); 
                
                enermyTurn();
            }
            System.out.println("Player loses as one of the walls is "
                        + "destroyed by the Titans at hour "+gameHour);
        }
        else{
            System.out.println("Please enter 1 or 2 to choose the game mode.");
        }
        

        
        
    }
        //method of playerTurn to upgrade weapon or wall
        public static void playerTurn(){
            Scanner s = new Scanner(System.in);
            Coin c = new Coin();
            Wall wll = new Wall();
            System.out.println("Player's turn");
            System.out.print("Choose the weapon(s) you would like to upgrade (Type a string of"
            + "integer or hit Enter to skip) : ");//First Question
            String upweapon = s.nextLine();
            if (upweapon.equals("")){//check if the player want to skip
                System.out.println("");//skip
            }
            else{
                
            }
            
            //Second question
            System.out.println("Do you want to upgrade all walls? (press 1 if yes, press Enter if" 
            + "no) Current coin number: "+ c.getBalance());
            String upwall = s.nextLine();
            if (upwall.equals("")){
                System.out.println("");//skip
            }
            else if (upwall.equals("1")){//check if the player wants to upgrade the wall
                
                //ask for number of walls to upgrade
                System.out.println("How many wall(s) you want to upgrade? "
                + "(e.g. the player type 1234 indicating that he/she want to add the "
                + "HP of wall units 1, 2, 3 and 4.) Current coin number: "+ c.getBalance());
                String upwallunit = s.nextLine();
                
                //question to get HP for uograding the wall(a)
                System.out.println("How many HP do you want to add up to the wall(s)? "
                        + "Current coin number: " + c.getBalance());
                int upwallHP = s.nextInt();//HP to upgrade wall(s)
                wll.upgrade(upwallHP);//call the upgrade method in class Wall
                c.sumUpCost(upwallHP);//Sum up the upgrade cost
                c.pay();//Pay the upgrade cost
            }
        }
        
        //method of enermyTurn for the titan to attack
        public static void enermyTurn(){
            Scanner s = new Scanner(System.in);
            System.out.print("Enermies' turn (Press Enter to proceed)");
            s.nextLine();
        }
       
       
}

