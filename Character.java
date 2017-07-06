
import java.awt.Point;
import java.io.Serializable;

public class Character implements Serializable{
    private Type type;
    int lvl = 1;
    int exp = 0;
    int exptolvl = 20;
    String perks;
    int intelligence;
    int agility;
    int strength;
    Weapon weapon;
    int defaulthealth = 100;
    int health = 100;
    Point p;
    static int hpinc = 40;
    public enum Type{
        MAGE,BARBARIAN,WHISPERER
    }
    public Character(Type t){
        type = t;
        if(t == Type.MAGE){
            intelligence = 10;
            agility = 5;
            strength = 3;
            weapon = new Weapon(Weapon.Type.STAFF, intelligence);
        }
        if(t == Type.BARBARIAN){
            intelligence = 1;
            agility = 7;
            strength = 10;
            weapon = new Weapon(Weapon.Type.SWORD, strength);
        }
        if(t == Type.WHISPERER){
            intelligence = 5;
            agility = 5;
            strength = 5;
            weapon = new Weapon(Weapon.Type.BOTTLE, agility);
        }
        p = new Point(0,0);
        health = strength*hpinc;
        defaulthealth = strength*hpinc;
    }
    public Character(Type t, String tw){
        type = t;
        if(t == Type.MAGE){
            intelligence = 10;
            agility = 5;
            strength = 3;
            if(tw.equals("Staff")) {
                weapon = new Weapon(tw,intelligence);
            }
            else{
            weapon = new Weapon(tw,5);
            }
        }
        if(t == Type.BARBARIAN){
            intelligence = 1;
            agility = 7;
            strength = 10;
            if(tw.equals("Sword")) {
                weapon = new Weapon(tw,strength);
            }
            else{
            weapon = new Weapon(tw,5);
            }
        }
        if(t == Type.WHISPERER){
            intelligence = 5;
            agility = 5;
            strength = 5;
            if(tw.equals("Bottle")) {
                weapon = new Weapon(tw,agility);
            }
            else{
            weapon = new Weapon(tw,5);
            }
        }
        p = new Point(0,0);
        health = strength*hpinc;
        defaulthealth = strength*hpinc;
    }

    public String toString(){
        return type + " " + lvl + " " + exp + " " + exptolvl + " " + perks + " " + intelligence + " " + agility + " " + strength + " " + health;
    }
    public Point getPoint(){
        return p;
    }
    public void setPoint(int x, int y){
        p = new Point(x,y);
    }
    public Type getType(){
        return type;
    }
    public int getWeaponDmg(){
        return weapon.getDmg();
    }
    public int getHealth(){
        return health;
    }
    public int getDefaultHealth(){
        return defaulthealth;
    }
    public void addExp(int e){
        exp += e;
        if(exp>exptolvl){
            lvlUp();
        }
    }
    public boolean isAlive(){
        if(health>0) return true;
        return false;
    }
    public int takeDmg(int d){
        health -= d;
        return health;
    }
    public void setHealth(int h){
        health = h;
    }
    public void addHealth(int h){
        health += h;
    }
    public void setDefaultHealth(int h){
        defaulthealth = h;
    }
    
    public void lvlUp(){
        lvl++;
        exp = 0;
        exptolvl = 50*lvl;
        switch(type){
            case MAGE:
                intelligence+=3;
                agility++;
                strength++;
                switch(weapon.getType()){
                    case STAFF: weapon.addDmg(intelligence*2); break;
                    case SWORD: weapon.addDmg(5); break;
                    case BOTTLE: weapon.addDmg(5); break;
                    default: break;
                }
                break;
            case BARBARIAN:
                intelligence++;
                agility+=2;
                strength+=3;
                switch(weapon.getType()){
                    case STAFF: weapon.addDmg(5); break;
                    case SWORD: weapon.addDmg(strength*2); break;
                    case BOTTLE: weapon.addDmg(5); break;
                    default: break;
                }
                break;
            case WHISPERER: 
                intelligence+=2;
                agility+=2;
                strength+=2;
                switch(weapon.getType()){
                    case STAFF: weapon.addDmg(5); break;
                    case SWORD: weapon.addDmg(5); break;
                    case BOTTLE: weapon.addDmg(agility*2); break;
                    default: break;
                }
                break;
            default: break;
        }
        defaulthealth = strength*hpinc;
        health = defaulthealth;
    }
    public String getStats(){
        return intelligence + " " + agility + " " + strength;
    }
    public String getIntelligence(){
        return intelligence+"";
    }
    public int getAgility(){
        return agility;
    }
    public int getStrength(){
        return strength;
    }
    public int getExp(){
        return exp;
    }
    public int getLvl(){
        return lvl;
    }

}
