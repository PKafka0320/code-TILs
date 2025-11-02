class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int[] serverCount = new int[24];
        for (int i = 0; i < players.length; i++) {
            int needServerCount = players[i] / m;
            if (serverCount[i] < needServerCount) {
                int amount = needServerCount - serverCount[i];
                answer += amount;
                addServerCount(i, serverCount, k, amount);
            }
        }
        return answer;
    }
    
    public void addServerCount(int idx, int[] serverCount, int k, int amount) {
        for (int i = idx; i < idx + k; i++) {
            if (i >= serverCount.length) break;
            serverCount[i] += amount;
        }
    }
}