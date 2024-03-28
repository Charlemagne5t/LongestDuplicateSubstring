import java.util.HashSet;
import java.util.Set;

public class Solution {
    public String longestDupSubstring(String s) {
        String res = "";
        int l = 0;
        int r = s.length() - 1;
        int mid = l + (r - l) / 2;

        while(l <= r){
            mid = l + (r - l) / 2;
            String str = findDuplicate(s, mid + 1);
            if(str == null){
                r = mid - 1;
            }
            if(str != null){
                res = str;
                l = mid + 1;
            }
        }
        return res;
    }

    private String findDuplicate(String s, int length){
        if(length == 0){
            return null;
        }
        Set<Long> set = new HashSet<>();
        long mod = (long)(Math.pow(2,45) - 1);
        long RM = 1;
        for(int i = 1; i < length; i++){
            RM  = (26 * RM) % mod;
        }
        long h = hash(s, 0, length - 1);
        set.add(h);
        for(int i = length; i < s.length(); i++){
            h = (h + mod - RM * s.charAt(i - length) % mod) % mod;
            h = (h * 26 + s.charAt(i)) % mod;
            if(set.contains(h)){
                return s.substring(i - length + 1, i + 1);
            }
            set.add(h);
        }
        return null;
    }
    private long hash(String key, int start, int end) {
        long h = 0;
        for (int j = start; j <= end; j++){
            h = (26 * h + key.charAt(j)) % (long)(Math.pow(2,45) - 1);
        }
        return h;
    }
}


