

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * OOP Harkkatyö. 2017
 * @author MarkoLoponen
 */
public class OOPHarkkaUI extends javax.swing.JFrame implements ActionListener, ItemListener {
    JLabel character;
    JLabel monster;
    Character chr;
    Monster mon;
    JFrame character_creation;
    JTextArea character_info_field;
    Random rnd = new Random();
    JTextArea preview;
    String previewclass;
    String previewweapon;
    /**
     * Creating the UI and show it.
     */
    public void createAndShowGUI(Character ch, Monster mo) throws IOException {
        chr = ch;
        mon = mo;

        
        // Creating frame
        JFrame frame = new JFrame("Starlord Megathunder v0.1");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300,300, 500,420);
        frame.setPreferredSize(new Dimension(500,420));
        frame.setLayout(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        
        // Creating menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(200, 20));
        // Creating menu and needed items
        JMenu menu = new JMenu("Start");
        JMenuItem menuItem1 = new JMenuItem("New");
        menu.add(menuItem1);
        JMenuItem menuItem2 = new JMenuItem("Save");
        menu.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Load");
        menu.add(menuItem3);
        JMenuItem menuItem4 = new JMenuItem("Quit");
        menu.add(menuItem4);
        
        // Adding menu to menubar
        menuBar.add(menu);
        
        // Creating listeners for menuitems
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);
        
        // Setting menubar
        frame.setJMenuBar(menuBar);
        
        // Creating background
        JPanel paneeli = new JPanel();
        paneeli.setLayout(null);
        paneeli.setBorder(BorderFactory.createLineBorder(Color.black));
        paneeli.setBounds(50, 50, 400, 200);
        JLabel background = new JLabel();
        background.setBounds(0, 0, 400, 200);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background.png")));
        background.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if(chr.isAlive()){
                    move(e.getPoint());
                    }
                    if(!mon.isAlive()){
                        respawn();
                    }
                }
        });
        
        // Creating character panel
        JPanel character_info = new JPanel();
        character_info.setLayout(null);
        character_info.setBorder(BorderFactory.createLineBorder(Color.black));
        character_info.setBounds(50, 250, 400, 150);
        
        // Creating character icon
        character = new JLabel();
        character.setBounds(0,0, 50,50);
        character.setLocation(chr.getPoint());
        character.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mage.jpg")));
        // Luodaan monsteri ikoni
        monster = new JLabel();
        monster.setBounds(0,0,50,50);
        monster.setLocation(mon.getPoint());
        monster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/troll.jpg")));


        // Info paneeli
        character_info_field = new JTextArea();
        setCharacterInfo();
        character_info_field.setBounds(5,5,390,140);

        paneeli.add(character);
        paneeli.add(monster);

        character_info.add(character_info_field);
        
        paneeli.add(background);
        
        frame.getContentPane().add(paneeli);
        frame.getContentPane().add(character_info);
        
        character_creation = new JFrame("Character creation");
        character_creation.setResizable(false);
        character_creation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        character_creation.setBounds(300,300, 500,420);
        character_creation.setPreferredSize(new Dimension(500,420));
        character_creation.setLayout(null);
        character_creation.setUndecorated(false);
        
        JPanel character_panel = new JPanel();
        character_panel.setLayout(null);
        character_panel.setBorder(BorderFactory.createLineBorder(Color.black));
        character_panel.setBounds(50, 50, 400, 200);
        
        preview = new JTextArea();
        preview.setBounds(230, 5, 165, 190);
        character_panel.add(preview);
        
        // Pick hero class
        
        JLabel pickclass = new JLabel("Pick character");
        pickclass.setBounds(25, 5, 120, 30);
        character_panel.add(pickclass);
        
        JButton mage = new JButton("Mage");
        mage.addActionListener(this);
        mage.setBounds(20, 30, 100, 30);
        mage.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                previewclass = "Mage";
                if(previewweapon!=null) setPreviewInfo(previewclass,previewweapon);
            } 
        } );
        character_panel.add(mage);
        
        JButton barbarian = new JButton("Barbarian");
        barbarian.addActionListener(this);
        barbarian.setBounds(20, 60, 100, 30);
        barbarian.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                previewclass = "Barbarian";
                if(previewweapon!=null) setPreviewInfo(previewclass,previewweapon);
            } 
        } );
        character_panel.add(barbarian);
        
        JButton whisperer = new JButton("Whisperer");
        whisperer.addActionListener(this);
        whisperer.setBounds(20, 90, 100, 30);
        whisperer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                previewclass = "Whisperer";
                if(previewweapon!=null) setPreviewInfo(previewclass,previewweapon);
            } 
        } );
        character_panel.add(whisperer);
        
        // Pick a weapon
        
        JLabel pickweapon = new JLabel("Pick a weapon");
        pickweapon.setBounds(130, 5, 120, 30);
        character_panel.add(pickweapon);
        
        JButton staff = new JButton("Staff");
        staff.addActionListener(this);
        staff.setBounds(120, 30, 100, 30);
        staff.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                previewweapon = "Staff";
                if(previewclass!=null) setPreviewInfo(previewclass,previewweapon);
            } 
        } );
        character_panel.add(staff);
        
        JButton sword = new JButton("Sword");
        sword.addActionListener(this);
        sword.setBounds(120, 60, 100, 30);
        sword.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                previewweapon = "Sword";
                if(previewclass!=null) setPreviewInfo(previewclass,previewweapon);
            } 
        } );
        character_panel.add(sword);
        
        JButton bottle = new JButton("Bottle");
        bottle.addActionListener(this);
        bottle.setBounds(120, 90, 100, 30);
        bottle.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                previewweapon = "Bottle";
                if(previewclass!=null) setPreviewInfo(previewclass,previewweapon);
            } 
        } );
        character_panel.add(bottle);
        
        // Done creating char
        
        JButton donecharacter = new JButton("Create");
        donecharacter.addActionListener(this);
        donecharacter.setBounds(230, 250, 100, 30);
        donecharacter.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                if(previewclass.equals("Mage")){
                    chr = new Character(Character.Type.MAGE,previewweapon);
                }
                if(previewclass.equals("Barbarian")){
                    chr = new Character(Character.Type.BARBARIAN,previewweapon);
                }
                if(previewclass.equals("Whisperer")){
                    chr = new Character(Character.Type.WHISPERER,previewweapon);
                }
                
                character.setLocation(chr.getPoint());
                character.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mage.jpg")));
                mon = new Monster(1,5,2, new Point(200,100));
                monster.setLocation(mon.getPoint());
                
                setCharacterInfo();
                character_creation.setVisible(false);
            } 
        } );
        character_creation.add(donecharacter);
        
        // Exit character creation
        
        JButton exitcharacter = new JButton("Exit");
        exitcharacter.addActionListener(this);
        exitcharacter.setBounds(350, 250, 100, 30);
        exitcharacter.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
              character_creation.setVisible(false);
            } 
        } );
        character_creation.add(exitcharacter);
        character_creation.add(character_panel);
        character_creation.pack();
        
        // Näytetään ikkuna.
        frame.pack();
        frame.setVisible(true);
  
    }

    /**
     * Action listener. Menu clicks. Shows new hero create panel, load & save and exit.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("New")) character_creation.setVisible(true);
        if(s.equals("Save")){
             try {
                 save();
             } catch (IOException ex) {
                 Logger.getLogger(OOPHarkkaUI.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        if(s.equals("Load")){
             try {
                 load();
             } catch (IOException ex) {
                 Logger.getLogger(OOPHarkkaUI.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(OOPHarkkaUI.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        if(s.equals("Quit")) System.exit(0);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Moving the player. Moves hero one tile to the direction which player clicked. If monster is there, attacks the monster.
     * After hero move, monster moves.
     * @param p 
     */
    public void move(Point p){
        int characterX = character.getLocation().x;
        int characterY = character.getLocation().y;
        int monsterX = monster.getLocation().x;
        int monsterY = monster.getLocation().y;
        int mouseX = p.x;
        int mouseY = p.y;
        
        if(mouseX < 0 && mouseX > 400 && mouseY < 0 && mouseY > 200){}
        else{
        // Righty
        if(characterX+50 <= mouseX && characterY <= mouseY && characterY+50>=mouseY){
            if(characterX+50==monsterX && characterY==monsterY){
                battle();
            }
            else{
                character.setLocation(characterX+50, characterY);
                chr.setPoint(characterX+50, characterY);
            }
        }
        // Lefty
        if(characterX >= mouseX && characterY <= mouseY && characterY+50>=mouseY){
            if(characterX-50==monsterX && characterY==monsterY){
                battle();
            }
            else{
                character.setLocation(characterX-50, characterY);
                chr.setPoint(characterX-50, characterY);
            }
        }
        // Uppy
        if(characterX <= mouseX && characterX+50 >= mouseX && characterY >= mouseY){
            if(characterY-50==monsterY && characterX==monsterX){
                battle();
            }
            else{
                character.setLocation(characterX, characterY-50);
                chr.setPoint(characterX, characterY-50);
            }
        }
        // Downy
        if(characterX <= mouseX && characterX+50 >= mouseX && characterY+50 <= mouseY){
            if(characterY+50==monsterY && characterX==monsterX){
                battle();
            }
            else{
            character.setLocation(characterX, characterY+50);
            chr.setPoint(characterX, characterY+50);
            }
        }
        }
        if(chr.getPoint().x==0 && chr.getPoint().y==0) chr.setHealth(chr.getDefaultHealth());
        moveMonster();
        setCharacterInfo();
    }
    /**
     * Respawn monster. When monster is dead. Awards player with exp which is equal to monster health.
     */
    public void respawn(){
                    chr.addExp(mon.getDefaultHealth());
                        mon.respawn();
                        Point freeslot = freeRandomTile();
                        monster.setLocation(freeslot);
                        mon.setPoint(freeslot);
                        mon.setNewHealth(mon.getDefaultHealth()*2);
                        mon.setDmg(mon.getDmg()*2);
                        int random = rnd.nextInt(3)+1;
                        mon.setType(random);
    }
    /**
     * Randomly selects free tile where player is not currently.
     * @return 
     */
    public Point freeRandomTile(){
        int x = (rnd.nextInt(8))*50;
        int y = (rnd.nextInt(4))*50;
        Point p = new Point(x,y);
        if(chr.getPoint()==p){
            return freeRandomTile();
        }
        else{
            return p;
        }
       
    }
    /**
     * Hero deals damage to monster and monster deals damage to hero. 
     */
    public void battle(){
        mon.takeDmg(chr.getWeaponDmg(),chr.getType());
        if(chr.getAgility()>=rnd.nextInt(100)){ }
        else{
        if(chr.takeDmg(mon.getDmg())<=0){
            character.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demon.jpg")));
        }
        }
        setCharacterInfo();
    }
    /**
     * Moves the monster closer to player.
     */
    public void moveMonster(){
        int characterX = chr.getPoint().x;
        int characterY = chr.getPoint().y;
        int monsterX = mon.getPoint().x;
        int monsterY = mon.getPoint().y;
        
        // Left
        if(characterX+50 < monsterX && characterY == monsterY){
            monsterX -= 50;
            monster.setLocation(monsterX, monsterY);
            mon.setPoint(new Point(monsterX, monsterY));
            return;
        }
        // Right
        if(characterX > monsterX+50 && characterY == monsterY){
            monsterX += 50;
            monster.setLocation(monsterX, monsterY);
            mon.setPoint(new Point(monsterX, monsterY));
            return;
        }
        // Ylös
        if(characterY+50 < monsterY && characterX == monsterX){
            monsterY -= 50;
            monster.setLocation(monsterX, monsterY);
            mon.setPoint(new Point(monsterX, monsterY));
            return;
        }
        // Alas
        if(characterY > monsterY+50 && characterX == monsterX){
            monsterY += 50;
            monster.setLocation(monsterX, monsterY);
            mon.setPoint(new Point(monsterX, monsterY));
            return;
        }
        // Ebin tekoälyns
        if(characterX < monsterX && !isClose()){
            monsterX -= 50;
            monster.setLocation(monsterX, monsterY);
            mon.setPoint(new Point(monsterX, monsterY));
            return;
        }
        if(characterX > monsterX && !isClose()){
            monsterX += 50;
            monster.setLocation(monsterX, monsterY);
            mon.setPoint(new Point(monsterX, monsterY));
            return;
        }
    }
    /**
     * Checks if hero is close. If it is close - do nothing.
     * @return 
     */
    public boolean isClose(){
        int characterX = chr.getPoint().x;
        int characterY = chr.getPoint().y;
        int monsterX = mon.getPoint().x;
        int monsterY = mon.getPoint().y;
        if(monsterX-50 == characterX && monsterY == characterY) return true;
        if(monsterX+50 == characterX && monsterY == characterY) return true;
        if(monsterX == characterX && monsterY-50 == characterY) return true;
        if(monsterX == characterX && monsterY+50 == characterY) return true;
        return false;
    }
    /**
     * Sets preview textfield to show starting stats of the hero.
     * @param clss
     * @param weapon 
     */
    public void setPreviewInfo(String clss,String weapon){
        int health = 0;
        int intelligence = 0;
        int agility = 0;
        int strength = 0;
        int dmg = 0;
        String extradmg = "Nothing";
        if(clss.equals("Mage")){
            intelligence = 10;
            agility = 5;
            strength = 3;
            health = strength*Character.hpinc;
            if(weapon.equals("Staff")){
                dmg = intelligence;
            }
            else{
                dmg = 5;
            }
            extradmg = "Ghost";
        }
        if(clss.equals("Barbarian")){
            intelligence = 1;
            agility = 7;
            strength = 10;
            health = strength*Character.hpinc;
            if(weapon.equals("Sword")){
                dmg = strength;
            }
            else{
                dmg = 5;
            }
            extradmg = "Troll";
        }
        if(clss.equals("Whisperer")){
            intelligence = 5;
            agility = 5;
            strength = 5;
            health = strength*Character.hpinc;
            if(weapon.equals("Bottle")){
                dmg = agility;
            }
            else{
                dmg = 5;
            }
            extradmg = "\nEverything";
        }
        
        preview.setText("Character stats : \n\n"+"Intelligence: " + intelligence + "\n" + "Agility: " + agility + "\n" + "Strength: " + strength + "\n" + "Health: " + health + "\n" + "Weapon dmg: " + dmg + "\n" + "Double dmg on : " + extradmg);
        
    }
    /**
     * Updates character info textfield.
     */
    public void setCharacterInfo(){
        character_info_field.setText(chr.getType().toString() + "\nHealth:"+ chr.getHealth() + "\nIntelligence :" + chr.getIntelligence() + "\nAgility :" + chr.getAgility() + "\nStrenght :" + chr.getStrength() + "\nEXP: " + chr.getExp() + "\nLevel: " + chr.getLvl());
        if(!chr.isAlive()) character_info_field.setText("You became a demon, so you kinda lost.");
    }
    /**
     * Saves the game.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void save() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("save.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println("Tallennetaan.");
        oos.writeObject(chr);
        oos.writeObject(mon);
        oos.close();   
    }
    /**
     * Loads the game.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        if(new File("save.tmp").isFile()){
        FileInputStream fis = new FileInputStream("save.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        System.out.println("Ladataan..");
        chr = ((Character) ois.readObject());
        mon = (Monster) ois.readObject();
        ois.close();
        character.setLocation(chr.getPoint());
        character.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mage.jpg")));
        monster.setLocation(mon.getPoint());
        setCharacterInfo();
        }
    }
}
