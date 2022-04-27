package newaot;

public class NewAoT {

    public static void main(String[] args) {
        // display Ground
        Ground g = new Ground();
        System.out.println(g.toString());
        
        // idk what u use for this variable
        // btw testing nia, can delete this de
        // 6 - test put ATitian, CTitan
        // 25 - test put CTitan, Shield
        int hour = 6;
        
        // enemy turn
        // order: moveTitan, generateNewTitan, generatenewShield
        ArmouredTitan[] a = new ArmouredTitan[123456];
        ColossusTitan[] c = new ColossusTitan[123456];
        Shield[] s = new Shield[123456];
        // game will crash if player kills 123456 objects, but impossible. XD
        
        // moveArmouredTitan
        if (ArmouredTitan.getArmouredTitanSpawnCounter() > 0) {
//            int closestWeaponColumn;
//            a[i].setClosestWeaponColumn(closestWeaponColumn);
            for (int i=0 ; i<ArmouredTitan.getArmouredTitanSpawnCounter() ; i++) {
                if ( (a[i].getArmouredTitanHP() > 0) && (a[i].getArmouredTitanCurrentRow() != 10) ) {
                    a[i].MoveArmouredTitan();
                    if (g.checkCoordinateForTitanIsEmpty(a[i])) {
                        g.removeArmouredTitan(a[i]);
                        a[i].setPermitToMoveArmouredTitan();
                        g.putArmouredTitan(a[i]);
                        int space = g.getSpaceAssignedToTitan();
                        a[i].setArmouredTitanCurrentSpace(space);
                    }
                    else if (!g.checkCoordinateForTitanIsEmpty(a[i])) {
                        while (a[i].getTestMoveCounter() < 4) {
                            a[i].MoveIsRestrictedArmouredTitan();
                            if (g.checkCoordinateForTitanIsEmpty(a[i])) {
                                g.removeArmouredTitan(a[i]);
                                a[i].setPermitToMoveArmouredTitan();
                                g.putArmouredTitan(a[i]);
                                int space = g.getSpaceAssignedToTitan();
                                a[i].setArmouredTitanCurrentSpace(space);
                                break;  // break while loop
                            }
                        }
                    }
                }
            }
        }
        // moveColossusTitan
        if (ColossusTitan.getColossusTitanSpawnCounter() > 0) {
            for (int i=0 ; i<ColossusTitan.getColossusTitanSpawnCounter() ; i++) {
                if ( (c[i].getColossusTitanHP() > 0) && (c[i].getColossusTitanCurrentRow() != 10) ) {
                    c[i].MoveColossusTitan();
                    if (g.checkCoordinateForTitanIsEmpty(c[i])) {
                        g.removeColossusTitan(c[i]);
                        c[i].setPermitToMoveColossusTitan();
                        g.putColossusTitan(c[i]);
                        int space = g.getSpaceAssignedToTitan();
                        c[i].setColossusTitanCurrentSpace(space);
                    }
                    else if (!g.checkCoordinateForTitanIsEmpty(c[i])) {
                        while (c[i].getTestMoveCounter() < 3) {
                            c[i].MoveIsRestrictedColossusTitan();
                            if (g.checkCoordinateForTitanIsEmpty(c[i])) {
                                g.removeColossusTitan(c[i]);
                                c[i].setPermitToMoveColossusTitan();
                                g.putColossusTitan(c[i]);
                                int space = g.getSpaceAssignedToTitan();
                                c[i].setColossusTitanCurrentSpace(space);
                                break;  // break while loop
                            }
                        }
                    }
                }
            }
        }
        
        // sample only (start)
        // can implement this in player turn, for killed Titan or Shield
        // after weapon activated
        for (int i=0 ; i<ArmouredTitan.getArmouredTitanSpawnCounter() ; i++ ) {
            if (a[i].getArmouredTitanHP() <= 0) {
                g.removeArmouredTitan(a[i]);
                a[i].setIsKilledArmouredTitan();
            }
        }
        for (int i=0 ; i<ColossusTitan.getColossusTitanSpawnCounter() ; i++ ) {
            if (c[i].getColossusTitanHP() <= 0) {
                g.removeColossusTitan(c[i]);
                c[i].setIsKilledColossusTitan();
            }
        }
        for (int i=0 ; i<Shield.getShieldSpawnCounter() ; i++ ) {
            if (s[i].getShieldHP() <= 0) {
                g.removeShield(s[i]);
                s[i].setIsKilledShield();
            }
        }
        // sample only (end)
        
        // generate
        int MaxTitanSummon = 0;
        int groundMaxTitanSummon = 0;
        int MaxShieldSummon = 0;
        int groundMaxShieldSummon = 0;
        // generate ArmouredTitan
        // hour 06-20 : 1 per 4 hour (for 15 hours)
        // hour 21-40 : 1 per 3 hour
        // hour 41-60 : 2 per 4 hour
        // hour 61-80 : 2 per 3 hour
        // hour > 80 (endless) : 2 per 2 hour
        // hour > 100 (hardcore endless) : 2 per 1 hour
        MaxTitanSummon = 0;
        groundMaxTitanSummon = g.checkMaxArmouredTitanCanBeSummoned();
        if ( (hour >= 6) && (hour <= 20) && ((hour-2)%4 == 0) ) {
            MaxTitanSummon = 1;
        }
        else if ( (hour >= 21) && (hour <= 40) && (hour%3 == 0) ) {
            MaxTitanSummon = 1;
        }
        else if ( (hour >= 41) && (hour <= 60) && ((hour-1)%4 == 0) ) {
            MaxTitanSummon = 2;
        }
        else if ( (hour >= 61) && (hour <= 80) && ((hour-1)%4 == 0) ) {
            MaxTitanSummon = 2;
        }
        else if ( (hour >= 81) && (hour <= 100) && ((hour-1)%2 == 0) ) {
            MaxTitanSummon = 2;
        }
        else if (hour >= 101) {
            MaxTitanSummon = 2;
        }
        else {
            MaxTitanSummon = 0;
        }
        for (int i=0 ; i<MaxTitanSummon && i<groundMaxTitanSummon ; i++) {
            int index = ArmouredTitan.getArmouredTitanSpawnCounter();
            a[index] = new ArmouredTitan();
            while (!g.checkCoordinateForTitanIsEmpty(a[index])) {
                a[index].GenerateAgainArmouredTitan();
            }
            if (g.checkCoordinateForTitanIsEmpty(a[index])) {
                g.putArmouredTitan(a[index]);
                int space = g.getSpaceAssignedToTitan();
                a[index].setArmouredTitanCurrentSpace(space);
            }
        }
        
        // generate ColossusTitan
        // hour 06-20 : 1 per 5 hour (for 15 hours)
        // hour 21-40 : 1 per 4 hour
        // hour 41-60 : 2 per 5 hour
        // hour 61-80 : 2 per 4 hour
        // hour > 80 (endless) : 2 per 3 hour
        // hour > 100 (hardcore endless) : 2 per 2 hour
        MaxTitanSummon = 0;
        groundMaxTitanSummon = g.checkMaxArmouredTitanCanBeSummoned();
        if ( (hour >= 6) && (hour <= 20) && ((hour-1)%5 == 0) ) {
            MaxTitanSummon = 1;
        }
        else if ( (hour >= 21) && (hour <= 40) && ((hour-1)%4 == 0) ) {
            MaxTitanSummon = 1;
        }
        else if ( (hour >= 41) && (hour <= 60) && ((hour-1)%5 == 0) ) {
            MaxTitanSummon = 2;
        }
        else if ( (hour >= 61) && (hour <= 80) && ((hour-1)%4 == 0) ) {
            MaxTitanSummon = 2;
        }
        else if ( (hour >= 81) && (hour <= 100) && (hour%3 == 0) ) {
            MaxTitanSummon = 2;
        }
        else if (hour >= 101 && ((hour-1)%2 == 0)) {
            MaxTitanSummon = 2;
        }
        else {
            MaxTitanSummon = 0;
        }
        for (int i=0 ; i<MaxTitanSummon && i<groundMaxTitanSummon ; i++) {
            int index = ColossusTitan.getColossusTitanSpawnCounter();
            c[index] = new ColossusTitan();
            while (!g.checkCoordinateForTitanIsEmpty(c[index])) {
                c[index].GenerateAgainColossusTitan();
            }
            if (g.checkCoordinateForTitanIsEmpty(c[index])) {
                g.putColossusTitan(c[index]);
                int space = g.getSpaceAssignedToTitan();
                c[index].setColossusTitanCurrentSpace(space);
            }
        }
        
        // generate Shield (if hour%5==0 && hour%10!=0)
        // hour 06-20 : 0
        // hour 25 && 35 : max 5 shield
        // hour 45 && 55 : max 10 shield
        // hour 65 && 75 : max 15 shield
        // hour 85 && 95 (endless) : max 20 shield
        // hour >= 105 (hardcore endless) : max 25 shield
        MaxShieldSummon = 0;
        groundMaxShieldSummon = g.checkMaxShieldCanBeSummoned();
        if ( (hour == 25) || (hour == 35) ) {
            MaxShieldSummon = 5;
        }
        else if ( (hour == 45) || (hour == 55) ) {
            MaxShieldSummon = 10;
        }
        else if ( (hour == 65) || (hour == 75) ) {
            MaxShieldSummon = 15;
        }
        else if ( (hour == 85) || (hour == 95) ) {
            MaxShieldSummon = 20;
        }
        else if ( (hour >= 105) && ((hour%5 == 0) || (hour%10 != 0)) ) {
            MaxShieldSummon = 25;
        }
        else {
            MaxShieldSummon = 0;
        }
        for (int i=0 ; i<MaxShieldSummon && i<groundMaxShieldSummon ; i++) {
            int index = Shield.getShieldSpawnCounter();
            s[index] = new Shield();
            while (!g.checkCoordinateForShieldIsEmpty(s[index])) {
                s[index].GenerateAgainShield();
            }
            if (g.checkCoordinateForShieldIsEmpty(s[index])) {
                g.putShield(s[index]);
            }
        }
        
        System.out.println(g.toString());
        
        System.out.println("----------Inspect----------");
        if (hour == 6) {
            System.out.print("A0 row " + a[0].getArmouredTitanCurrentRow());
            System.out.print(" column " + a[0].getArmouredTitanCurrentColumn());
            System.out.println(" space " + a[0].getArmouredTitanCurrentSpace());
        }
        if (hour == 6 || hour == 25) {
            System.out.print("C0 row " + c[0].getColossusTitanCurrentRow());
            System.out.print(" column " + c[0].getColossusTitanCurrentColumn());
            System.out.println(" space " + c[0].getColossusTitanCurrentSpace());
        }
        if (hour == 25) {
            System.out.print("S0 row " + s[0].getShieldCurrentRow());
            System.out.println(" column " + s[0].getShieldCurrentColumn());
        }
        System.out.println("--------Inspect End--------\n");
    }
    
}
