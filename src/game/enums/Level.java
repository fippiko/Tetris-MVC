package game.enums;

public enum Level {
   Level1(5),
   Level2(10),
   Level3(25),
   Level4(50),
   Level5(75),
   Level6(100),
   Level7(125),
   Level8(150),
   Level9(175),
   Level10(200),
   Level11(250),
   Level12(300),
   Level13(350),
   Level14(400),
   Level15(500),
   Level16(650),
   Level17(800),
   Level18(950),
   Level19(1100),
   Level20(1250),
   Level21(1400),
   Level22(1550),
   Level23(1700),
   Level24(1850),
   Level25(2000);

   private int neededRows = 0;

   Level(int neededRows) {
      this.neededRows = neededRows;
   }

   public int getNeededRows() {
      return this.neededRows;
   }
}
