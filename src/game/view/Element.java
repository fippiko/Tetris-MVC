package game.view;

public abstract class Element {

   private int   x;
   private int   y;

   protected int height;
   protected int width;

   public Element(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getHeight() {
      return height;
   }

   public int getWidth() {
      return width;
   }

}
