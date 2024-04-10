package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class NamingACompany {

    public static void main(String[] args) {
        NamingACompany obj = new NamingACompany();
        System.out.println(obj.distinctNames(new String[]{"coffee", "donuts", "time", "toffee"}));
    }

    public long distinctNames(String[] ideas) {
        // Set<String> set = new HashSet<>();
        // for(String idea: ideas){
        //     set.add(idea);
        // }
        // long result = 0;
        // for(int i=0; i<ideas.length; i++){
        //     String ideaASuffix =  ideas[i].substring(1);
        //     for(int j=i+1; j<ideas.length;j++){
        //         if(ideas[i].charAt(0)!=ideas[j].charAt(0)){
        //             String ideaA = ideas[j].charAt(0) + ideaASuffix;
        //             String ideaB = ideas[i].charAt(0) + ideas[j].substring(1);
        //             if(!set.contains(ideaA) && !set.contains(ideaB)){
        //                 result+=2;
        //             }
        //         }
        //     }
        // }
        // return result;
        Map<String, Set<Character>> map = new HashMap();
        long result =0;
        for(String idea: ideas){
            String ideaSuffix =  idea.substring(1);
            if(map.containsKey(ideaSuffix)){
                map.get(ideaSuffix).add(idea.charAt(0));
            } else {
                Set<Character> set = new HashSet<>();
                set.add(idea.charAt(0));
                map.put(ideaSuffix, set);
            }
        }
        for(int i=0; i<ideas.length; i++){
            String ideaSuffix =  ideas[i].substring(1);
            Set<Character> set = map.get(ideaSuffix);
            for (int j = i+1; j < ideas.length; j++) {
                if(!set.contains(ideas[j].charAt(0))){
                    result+=2;
                }
            }
        }
        return result;
    }
}