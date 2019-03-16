package tool;

import java.util.Set;
import java.util.TreeSet;

public class CommonFriend {

    public static Set<Integer> intersection(Set<Integer> userOneFriend,Set<Integer> userTwoFriend){
        if (userOneFriend == null || userOneFriend.size() ==0 ) {
            return null;
        }
        if(userTwoFriend == null || userTwoFriend.size() == 0) {
            return null;
        }
        if (userOneFriend.size() < userTwoFriend.size()){
            return  intersect(userOneFriend,userTwoFriend);
        }else{
            return  intersect(userTwoFriend,userOneFriend);
        }
    }

    public static  Set<Integer> intersect(Set<Integer> smallSet,Set<Integer> largeSet){
        Set<Integer> result = new TreeSet<Integer>();
        for (Integer v : smallSet) {
            if(largeSet.contains(v)) {
                result.add(v);
            }
        }
        return  result;
    }
}
