package game.helper;

import game.enums.Sound;

import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.jlp;

public class SoundHelper extends Helper implements Runnable {

   private static ArrayList<SoundHelper> instances = new ArrayList<SoundHelper>();

   private boolean                       loop      = false;
   private Sound                         sound     = null;

   public SoundHelper(Sound sound, boolean loop) {
      this.sound = sound;
      this.loop = loop;

      Thread t = new Thread(this);
      t.start();
   }

   public static void playSound(Sound sound, boolean loop) {
      SoundHelper newInstance = new SoundHelper(sound, loop);

      instances.add(newInstance);
   }

   @Override
   public void run() {

      if (sound.getPath().endsWith(".mp3")) {
         this.playMp3(sound);
      }
      else if (sound.getPath().endsWith(".wav")) {

      }
   }

   private void playMp3(Sound sound) {
      String[] args = new String[1];
      args[0] = this.sound.getPath();
      jlp player = jlp.createInstance(args);

      try {
         player.play();

         instances.remove(this);
      } catch (JavaLayerException e) {
         e.printStackTrace();
      }
   }
}
