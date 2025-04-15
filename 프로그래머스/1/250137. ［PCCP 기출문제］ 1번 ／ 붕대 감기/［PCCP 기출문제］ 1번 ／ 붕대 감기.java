class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int initHealth = health;
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        health -= attacks[0][1];
        if (health <= 0) {
            return -1;
        }
        System.out.printf("start heatlh: %d%n", health);
        
        for (int i = 1; i < attacks.length; i++) {
            int attackTime = attacks[i][0];
            int attackDamage = attacks[i][1];
            
            int timeDiff = attackTime - attacks[i-1][0] -1;
            int cure = (timeDiff / t) * y + timeDiff * x;
            health += cure;
            if (health > initHealth) {
                health = initHealth;
            }
            System.out.printf("timeDiff: %d - cure: %d + %d%n", timeDiff, (timeDiff / t) * y, timeDiff);
            
            health -= attackDamage;
            System.out.printf("damage: %d%n", health);
            if (health <= 0) {
                return -1;
            }
        }
        return health;
    }
}