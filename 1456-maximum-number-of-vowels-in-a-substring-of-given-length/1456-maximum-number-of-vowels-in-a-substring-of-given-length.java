class Solution {
    public int maxVowels(String s, int k) {
        int max=0;
        int c=0;
        for(int i=0;i<k;i++){
            if(isVowel(s.charAt(i))) c++;
        }
        max=c;
        for(int i=k;i<s.length();i++){
            if(isVowel(s.charAt(i-k))) c--;
            if(isVowel(s.charAt(i))) c++;
            max=Math.max(max,c);

        }
        return max;
    }
    public boolean isVowel(char ch){
        return ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u';
    }
}