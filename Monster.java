
import java.awt.Point;
import java.io.Serializable;

public class Monster implements Serializable{
    private Type type;
    int defaulthealth;
    int health;
    int dmg;
    Point p;
    boolean isturn;
    boolean isalive;
    public enum Type{
        LIZARD,GHOST,TROLL
    }
    public Monster(){
        
    }
    public Monster(int rnd, int h, int d){
        if(rnd==1){
            type = Type.LIZARD;
        }
        if(rnd==2){
            type = Type.GHOST;
        }
        if(rnd==3){
            type = Type.TROLL;
        }
        defaulthealth = h;
        health = h;
        dmg = d;
        p = new Point(0,0);
        isalive = true;
    }
    public Monster(int rnd, int h, int d, Point po){
        if(rnd==1){
            type = Type.LIZARD;
        }
        if(rnd==2){
            type = Type.GHOST;
        }
        if(rnd==3){
            type = Type.TROLL;
        }
        defaulthealth = h;
        health = h;
        dmg = d;
        p = po;
        isalive = true;
    }
    public Point getPoint(){
        return p;
    }
    public void setPoint(Point po){
        p = po;
    }
    public void takeDmg(int d,Character.Type t){
        if(t.equals(Character.Type.MAGE) && type.equals(Type.GHOST)){
            health -= d;
        }
        if(t.equals(Character.Type.BARBARIAN) && type.equals(Type.TROLL)){
            health -= d;
        }
        if(t.equals(Character.Type.WHISPERER)){
            health -= d;
        }
        health -= d;
        if(health<=0) isalive = false;
    }
    public int getDmg(){
        return dmg;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int h){
        health = h;
    }
    public void setDefaultHealth(int h){
        defaulthealth = h;
    }
    public void setType(int rnd){
        if(rnd==1){
            type = Type.LIZARD;
        }
        if(rnd==2){
            type = Type.GHOST;
        }
        if(rnd==3){
            type = Type.TROLL;
        }
    }
    public int getDefaultHealth(){
        return defaulthealth;
    }
    public void setNewHealth(int h){
        defaulthealth = h;
        health = h;
    }
    public void setDmg(int d){
        dmg = d;
    }
    public boolean getTurn(){
        return isturn;
    }
    public Type getType(){
        return type;
    }
    public boolean isAlive(){
        return isalive;
    }
    public void respawn(){
        isalive = true;
    }
    

}
