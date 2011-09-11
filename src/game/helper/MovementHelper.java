package game.helper;

import game.enums.Level;

import java.awt.event.KeyEvent;

public class MovementHelper extends Helper {

   public static int getVerticalDelta() {
      return 1;
   }

   public static int getHorizontalDelta() {
      int delta = 0;

      if (InputHelper.isKeyPressed(KeyEvent.VK_LEFT)) {
         delta = -1;
      }
      if (InputHelper.isKeyPressed(KeyEvent.VK_RIGHT)) {
         delta = 1;
      }

      return delta;
   }

   public static long getHorizontalSpeedInterval() {
      int horizontalInterval = ConfigurationHelper.getConfiguration().getHorizontalSpeed();

      return horizontalInterval;
   }

   public static long getVerticalSpeedInterval(final Level level) {
      int verticalInterval = 500 - level.getPosition() * 18;

      if (InputHelper.isKeyPressed(KeyEvent.VK_DOWN)) {
         verticalInterval = 50;
      }

      return verticalInterval;
   }
}
