package utilities;

import ast.statement.CallNode;
import ast.statement.FunDeclarationNode;
import ast.IdNode;
import ast.Node;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

import static utilities.Utils.*;

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
             Node node = checkIdNode(map.keySet(), ((IdNode) n).getId());
             if(node != null){
                 return map.get(node);
             }
        }
        String c;

            String result = "";

            if (hasMethod(n,"getId")) {
                try {
                    // Ottieni il metodo "getId"
                    Method getIdMethod = n.getClass().getMethod("getId");
                    // Chiamare il metodo e ottenere il risultato
                    result = (String) getIdMethod.invoke(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                c = getCharUnivoqueKey(map,result);
                map.put(n,c);
                return  c;
            }else {
                do {
                    c = randomChar();

                } while (map.containsValue(c));
                map.put(n, c);
                return c;
            }
    }



    public String add(Node n,String s){
        s = s.toUpperCase();
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
        if (node instanceof CallNode)
            return map.get(getFunDecNode(((CallNode)node).getId()));
        else
            return map.get(node);
    }

    public void remove(Node n){
        map.remove(n);
    }

    public Node checkIdNode(Set<Node> set, String id){
        for(Node n : set){
            if(n instanceof IdNode){
                if(((IdNode) n).getId().equals(id)){
                    return n;
                }
            }
        }
        return null;
    }

    public Node getFunDecNode(String id){
        for(Node n : map.keySet()){
            if(n instanceof FunDeclarationNode){
               if(((FunDeclarationNode) n).getId().equals(id)){
                        return n;
                    }
            }
        }
        return null;
    }

    public Set<Node> getSet(){
        return map.keySet();
    }

    //make a function that given a value(string) return a node

}
