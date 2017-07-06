
import java.io.Serializable;

public class Weapon implements Serializable{
    int dmg;
    private Type type;
    
    public enum Type{
        STAFF,SWORD,BOTTLE
    }
    public Weapon(){
        
    }
    public Weapon(Type t, int i){
        type = t;
        dmg = i;
    }
    public Weapon(String w){
        if(w.equals("Staff")) type = Type.STAFF;
        if(w.equals("Sword")) type = Type.SWORD;
        if(w.equals("Bottle")) type = Type.BOTTLE;
    }
    public Weapon(String w, int d){
        if(w.equals("Staff")) type = Type.STAFF;
        if(w.equals("Sword")) type = Type.SWORD;
        if(w.equals("Bottle")) type = Type.BOTTLE;
        dmg = d;
    }
    public int getDmg(){
        return dmg;
    }
    public Type getType(){
        return type;
    }
    public void addDmg(int d){
        dmg+=d;
    }
    public void setDmg(int d){
        dmg = d;
    }

}
