
package group.assignment;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        //Create ground 
        Ground g = new Ground();
//        System.out.println(g.toString());
        int gameHour = 0;
        int armouredtitancounter = 0;
        ArmouredTitan[] a = new ArmouredTitan[15];
        
        while (gameHour < 80){
            System.out.println("Game Hour " + gameHour);
            
            //armouredtitan movement 
            if (gameHour > 5) {
                moveArmouredTitan(a, g);
            }
            
            //every five hours, generate new A titan
            if (gameHour % 5 == 0 && gameHour != 0) {
                a[armouredtitancounter] = new ArmouredTitan();
                int currentrow = a[armouredtitancounter].getArmouredTitanCurrentRow();
                int currentcolumn = a[armouredtitancounter].getArmouredTitanCurrentColumn();
                while (!g.checkCoordinateForTitanIsEmpty(currentrow, currentcolumn)) {
                    a[armouredtitancounter].GenerateAgainArmouredTitan();
                }
                if (g.checkCoordinateForTitanIsEmpty(currentrow, currentcolumn)) {
                    g.putArmouredTitan(currentrow, currentcolumn);
                    int Aspace = g.getSpaceAssignedToTitan();
                    a[armouredtitancounter].setArmouredTitanCurrentSpace(Aspace);
                }
                armouredtitancounter++; //used for declaring new A titan
            }
            
            System.out.println(g.toString());
            System.out.print("Press enter to continue ");
            s.nextLine();
            gameHour++;
        }
    }
    
    //call nuke, ignore first
    public static boolean getinputforNuke(){
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to use nuke? (1 nuke = 100 coins)");
        System.out.println("Type Y to call nuke, else press enter");
        String userinput = s.nextLine();
        if (userinput.equalsIgnoreCase("Y")) {
            return true; 
        }
        else 
            return false;
    }
    
    public static void moveArmouredTitan(ArmouredTitan[] a, Ground g){
        for (int i = 0; i < a.length - 1; i++) { //loop through the entire titan array
            if(a[i] != null){
                a[i].setClosestWeaponColumn(9); //set the weapon to be always on column 9 
                g.removeArmouredTitan(a[i].getArmouredTitanCurrentRow(), a[i].getArmouredTitanCurrentColumn(), a[i].getArmouredTitanCurrentSpace()); //remove
                a[i].MoveArmouredTitan(); //move
                int currentrowmove = a[i].getArmouredTitanCurrentRow();
                int currentcolumnmove = a[i].getArmouredTitanCurrentColumn();
                boolean Acheck = g.checkCoordinateForTitanIsEmpty(currentrowmove, currentcolumnmove);
                if (Acheck) {
                    a[i].checkPermitToMoveArmouredTitan(Acheck);
                    a[i].setArmouredTitanCurrentSpace(g.getSpaceAssignedToTitan());
                }
                else {
                    for (int j=0 ; j<5 ; j++) {
                        a[i].MoveIsRestrictedArmouredTitan();
                        int currentrowmove1 = a[i].getArmouredTitanCurrentRow();
                        int currentcolumnmove1 = a[i].getArmouredTitanCurrentColumn();
                        Acheck = g.checkCoordinateForTitanIsEmpty(currentrowmove1, currentcolumnmove1);
                        if (Acheck) {
                            a[i].checkPermitToMoveArmouredTitan(Acheck);
                            a[i].setArmouredTitanCurrentSpace(g.getSpaceAssignedToTitan());
                            break;
                        }
                    }
                }
                g.putArmouredTitan(a[i].getArmouredTitanCurrentRow(), a[i].getArmouredTitanCurrentColumn()); //put titan
            }
        }
    }
}
