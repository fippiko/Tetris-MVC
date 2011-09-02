package game.enums;

public enum Level {
   Level1(5),
   Level2(10),
   Level3(20),
   Level4(30),
   Level5(40),
   Level6(50),
   Level7(60),
   Level8(70),
   Level9(80),
   Level10(90),
   Level11(100),
   Level12(115),
   Level13(130),
   Level14(145),
   Level15(160),
   Level16(175),
   Level17(190),
   Level18(205),
   Level19(225),
   Level20(245),
   Level21(265),
   Level22(285),
   Level23(305),
   Level24(330),
   Level25(350);

   private int neededRows = 0;

   Level(int neededRows) {
      this.neededRows = neededRows;
   }

   public int getNeededRows() {
      return this.neededRows;
   }
   
   public int getPosition(){
      int levelcount = 0;
      
      for (Level level : Level.values()) {
         levelcount++;
         
         if(level == this){
            break;
         }
      }
      
      return levelcount;
   }
}
