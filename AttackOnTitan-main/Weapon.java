package aot;

public class Weapon {
    private int level;
    private int damage;
    private String weaponPattern;
    private static int[] eachLevelDamage = {0, 2, 5, 10};
    
    /* default weapon pattern is "***"
     * important!!! pattern length should be 3 
     * e.g. "###", "***"
     */ 
    public Weapon(String pattern) {
        level = 0;
        damage = 0;
        this.weaponPattern = pattern; 
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public String getWeaponPattern() {
        return weaponPattern;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;    
    }
    
    // each weapon has different pattern
    public void setPattern(String pattern) {
        this.weaponPattern = pattern;
    }
    
    // if not normal mode, set weapon damage at different levels
    private void setEachLevelDamage(int[] damage) {
        eachLevelDamage = damage;
    }
    
    // get weapon upgrade cost for next level which is equal to next level damage
    public int getUpgradeCost() {
        int nextLevel = level + 1;
        return eachLevelDamage[nextLevel];
    }
    
    // print the weapon on walls based on the weapon level
    public static void display(Weapon[] weapons) { 
        
        // find out maximum rows(levels) to be allocated to visualize weapon above the wall
        int maxRowToDisplay = 0;
        for (Weapon weapon : weapons) {
            if (weapon.getLevel() > maxRowToDisplay) {
                maxRowToDisplay = weapon.getLevel();
            }
        }
        
        // print first row pattern, then second row, thhird row
        // **       **        (Level 3) Row 1
        // ** ** ** **    **  (Level 2) Row 2
        // ** ** ** ** ** **  (Level 1) Row 3
        for (int i = maxRowToDisplay; i > 0; i--) {
            System.out.print("  ");
            for (Weapon weapon : weapons) {
                // no pattern by default for level 0 weapon
                String pattern = "";
                // check weapon pattern to display based on level
                if (weapon.getLevel() >= i) {
                    pattern = weapon.getWeaponPattern();
                } 
                System.out.printf("%4s", pattern);
            }
            System.out.println();
        }
    }
    
    // upgrade the weapon on wall (add level, add damage)
    public void upgrade() {
        level += 1;     
        damage = eachLevelDamage[level];
    }
}
