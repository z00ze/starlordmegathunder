
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class OOPHarkka {
    
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        OOPHarkkaUI game = new OOPHarkkaUI();
        game.createAndShowGUI(new Character(Character.Type.MAGE),new Monster(1,5,2, new Point(200,100)));
    }
}
