public class Player {
    String name;
    Character chr;
    boolean isturn;
    public Player(){
        
    }
    public Player(String n, Character c){
        name = n;
        chr = c;
        isturn = true;
    }
    public String toString(){
        return name + " " + chr.toString();
    }
    public Character getCharacter(){
        return chr;
    }
    public void setCharacterPoint(int x, int y){
        chr.setPoint(x, y);
    }
    
            /** 
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(OOPHarkka.class.getResource("epic.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        while(!clip.isRunning()) clip.start();
        */
}
