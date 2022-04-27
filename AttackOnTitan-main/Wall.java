package aot;

public class Wall {
    private int hp = 50;
    private static int upgradeCost = 0;
    
    public static void main(String[] args) {
        Wall[] w = new Wall[10];
        Wall.display(w);
    }
    
    // constructor, default hp is 50
    public Wall() {
        hp = 50;
    }
    
    public Wall(int hp) {
        this.hp = hp;
    }
    
    public int getHP() {
        return hp;
    }
    
    public int getUpgradeCost() {
        int cost = upgradeCost;
        upgradeCost = 0;
        return cost;
    }
    
    public void setHP(int hp) {
        this.hp = hp;
    }
    
    public void upgrade(int addHP) {
        hp += addHP;
        upgradeCost += addHP;
    } 
    
    // display wall edge --- --- ---
    public static void printEdge(Wall[] w) {
        System.out.print("  ");
        for (int i = 0; i < w.length; i++) {
            String pattern = "---";
            if (w[i].getHP() == 0)
                pattern = "";
            System.out.printf("%4s", pattern);
        }
    }
    
    // display the wall index 
    public static void printIndex(Wall[] w) {
        System.out.print("  ");
        for (int i = 0; i < w.length; i++) { 
            System.out.printf("%4d", i);
        }
        System.out.printf("%5s %-10s\n", "", "Index");
    }
    
    // display the wall HP
    public static void printHP(Wall[] w) {
        System.out.print("  ");
        for (int i = 0; i < w.length; i++) {
            System.out.printf("%4d", w[i].getHP());
        }
        System.out.printf("%5s %-10s\n", "", "HP");
    }
    
    // display the wall pattern in front of ground
    public static void display(Wall[] w) {    
        printEdge(w);
        System.out.printf("%5s %-10s\n", "", "The Wall");  // display label
        printIndex(w);
        printHP(w);
        printEdge(w);
        System.out.println("\n");
    }    
}
