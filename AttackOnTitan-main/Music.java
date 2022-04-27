package aot;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/* IMPORTANT!
 * AOT_PIANO.wav (Playing Game)
 * AOT_GAME_OVER.wav (Game Over)
 * Please download the .rar from Github and unzip to AoT project directory
 */
public class Music extends Thread {
    AudioInputStream audioStream;
    Clip clip;
    File file;
    
    public Music(String filename) {
        // check if audio file exists or not
        file = new File("./BGM/" + filename);
        if (!file.exists()) {
            System.out.println("Audio file was missing!");
            System.out.println("Remember to download the .rar from Github and unzip to AoT project directory");
        }
        
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file format not supported!");
        } catch (LineUnavailableException e) {
            System.out.println("Line is unavailable at this moment!");
        } catch (IOException e) {
            System.out.println("Problem with file input");
        }
    }
    
    // when thread.start(), it will automatically call the run() method
    public void run() {
        play();
    }
    
    // play the music continuously
    public void play()  {
        while (true) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    // remember to close the music clip before exit the game
    public void close() {
        clip.drain();
        clip.close();
    }
}
