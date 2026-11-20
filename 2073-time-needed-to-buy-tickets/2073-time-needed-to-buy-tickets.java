class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {

        int t = 0;
        int targettickets = tickets[k];

        for(int i=0 ;i<tickets.length; i++){
            if(i<=k){
                t += Math.min(tickets[i], targettickets);
            }else{
                t += Math.min(tickets[i], targettickets-1);
            }
        }
        return t;


        
    }
}