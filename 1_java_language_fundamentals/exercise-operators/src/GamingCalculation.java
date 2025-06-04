public class GamingCalculation {
    public static void main(String[] args) {
        int currentXP = 1200;
        int level = 5;
        int xpToNextLevel = 1500;
        int level7Requirement = 5000;
        boolean hasDoubleXPBoost = true;

        // 1. Arithmetic Operations
        currentXP += 300;
        if (hasDoubleXPBoost) {
            currentXP *= 2;
            //System.out.println("Double XP activated!");
        }
        xpToNextLevel -= 300;
        if (level == 5 && currentXP >= 1500) {
            level = 6;
            xpToNextLevel = level7Requirement - currentXP;
        }

        // 2. Comparison Checks (store results)
        boolean xpComparison = currentXP >= xpToNextLevel;
        boolean isMaxLevel = level >= 10;

        // 3. Logical Operations (store results)
        boolean didLevelUp = xpComparison && !isMaxLevel;
        boolean isProPlayer = (level > 7) || (currentXP > 5000);

        // 4. FINAL PRINT (all output at the end)
        System.out.println("=== Final Player Status ===");
        System.out.println("Current XP: " + currentXP);
        System.out.println("Current level: " + level);
        System.out.println("XP to Next Level: " + xpToNextLevel);

        if (xpComparison) {
            System.out.println("Current XP >= XP to next level: " + xpComparison);
        } else {
            System.out.println("Current XP >= XP to next level: " + !xpComparison);
        }
        if (isMaxLevel) {
            System.out.println("Player at max level: " + !isMaxLevel);
        } else {
            System.out.println("Player at max level: " + isMaxLevel);
        }

        if (didLevelUp) {
            System.out.println("Leveled Up: " + didLevelUp);
        } else {
            System.out.println("Leveled Up: " + !didLevelUp);
        }
        if (isProPlayer) {
            System.out.println("Pro Status: " + !isProPlayer);
        } else {
            System.out.println("Pro Status: " + isProPlayer);
        }
    }
}
