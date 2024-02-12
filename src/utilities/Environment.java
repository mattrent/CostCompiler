package utilities;

import ast.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
    private ArrayList<HashMap<String, Node>> symTable;
    private int level;

    public Environment() {
        symTable = new ArrayList<>();
        level = 0;
    }

    public Environment(ArrayList<HashMap<String, Node>> symTable, int level) {
        this.symTable = symTable;
        this.level = level;
    }
    public void openScope() {
        symTable.add(new HashMap<>());
        level++;
    }

    public void closeScope() {
        symTable.remove(level-1);
        level--;
    }

    public void addDeclaration(String id, Node node) {
        symTable.get(level-1).put(id, node);
    }

    public Node getDeclaration(String id) {
        for (int i = level; i > 0; i--) {
            if (symTable.get(i-1).containsKey(id)) {
                return symTable.get(i-1).get(id);
            }
        }
        return null;
    }

    public boolean containsDeclaration(String id) {
        for (int i = level; i > 0; i--) {
            if (symTable.get(i-1).containsKey(id)) {
                return true;
            }
        }
        return false;
    }

    public int getLevel() {
        return level;
    }



    public Environment clone(){
        ArrayList<HashMap<String, Node>> symTableClone = new ArrayList<>();
        for(int i = 0; i < symTable.size(); i++){
            symTableClone.add(new HashMap<>());
            for(String key : symTable.get(i).keySet()){
                symTableClone.get(i).put(key, symTable.get(i).get(key));
            }
        }
        return new Environment(symTableClone, level);
    }

    public boolean checkHeadDeclaration(String id){
        return symTable.get(level-1).containsKey(id);
    }

    public HashMap<String, Node> getSymTable(int level){
        return symTable.get(level);
    }
}
