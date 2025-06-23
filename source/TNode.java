package source;

import java.awt.List;
import java.util.ArrayList;
import java.util.Enumeration;


public class TNode{
    public String[]data;
    private int score;
    private TNode parent;
    private ArrayList<TNode> children;

    public TNode(String[] data){
        this.data = data;
        this.parent = null;
    }

    public TNode getParent(){
        return parent;
    }

    public void addChild(TNode child){
        children.add(child);
    }

    public ArrayList<TNode> getChildren(){
        return children;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int num){
        score = num;
    }

    
}
