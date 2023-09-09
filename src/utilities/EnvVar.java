package utilities;

import ast.IdNode;
import ast.Node;

import java.util.HashMap;
import java.util.Set;

import static utilities.Utils.randomChar;

public class EnvVar {
//TODO: Guardare se invertire String,Node è più efficiente

    HashMap<Node, String> map;
    int counter;

    public EnvVar(){
        map = new HashMap<>();
        counter = 0;
    }

    public boolean containsValue(String s){
        return map.containsValue(s);
    }

    public String add( Node n) {
        //choose a marker univoque to identify the node
        if(map.containsKey(n) ){
            return map.get(n);
        }else if(n instanceof IdNode){
             Node node = checkEqualsIdNode(map.keySet(), ((IdNode) n).getId());
             if(node != null){
                 return map.get(node);
             }
        }
        char c;
        do {
            c = randomChar();
        }while(map.containsValue(c));
        map.put(n, String.valueOf(c));
        return  String.valueOf(c);
    }

    public String add(Node n,String s){
        if(!map.containsValue(s)){
            map.put(n,s);
            return s;
        }
        else{
            do{
                s = s+counter++;
            }while(map.containsValue(s));
            map.put(n,s);
        }
        return null;
    }
    public String get(Node node){
        return map.get(node);
    }

    public void remove(Node n){
        map.remove(n);
    }

    Node checkEqualsIdNode(Set<Node> set, String id){
        for(Node n : set){
            if(n instanceof IdNode){
                if(((IdNode) n).getId().equals(id)){
                    return n;
                }
            }
        }
        return null;
    }

    public Set<Node> getSet(){
        return map.keySet();
    }
}
