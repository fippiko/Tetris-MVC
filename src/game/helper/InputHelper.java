package game.helper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHelper extends Helper implements KeyListener {
   private static ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
   private static ArrayList<Integer> handledKeys = new ArrayList<Integer>();

   private static InputHelper        instance;

   public static InputHelper getInstance() {
      if (instance == null) {
         instance = new InputHelper();
      }

      return instance;
   }

   public static boolean isKeyPressed(int keyCode) {
      return pressedKeys.contains(keyCode);
   }

   public static boolean isKeyPressed(final int keyCode, boolean handleOnce) {
      boolean pressed = isKeyPressed(keyCode);

      if (pressed && handleOnce) {
         setKeyHandled(keyCode);
      }

      return pressed;
   }

   private static void addPressedKey(final int keyCode) {
      if (!pressedKeys.contains(keyCode) && !handledKeys.contains(keyCode)) {
         pressedKeys.add(keyCode);
      }
   }

   private static void removePressedKey(final int keyCode) {
      pressedKeys.remove((Object) keyCode);
   }

   private static void removeHandledKey(final int keyCode) {
      handledKeys.remove((Object) keyCode);
   }

   public static ArrayList<Integer> getPressedKeys() {
      return pressedKeys;
   }

   private static void setKeyHandled(int keyCode) {
      handledKeys.add(keyCode);
      removePressedKey(keyCode);
   }

   @Override
   public void keyPressed(KeyEvent e) {
      InputHelper.addPressedKey(e.getKeyCode());
   }

   @Override
   public void keyReleased(KeyEvent e) {
      InputHelper.removePressedKey(e.getKeyCode());
      InputHelper.removeHandledKey(e.getKeyCode());
   }

   @Override
   public void keyTyped(KeyEvent arg0) {
      // unused
   }
}
