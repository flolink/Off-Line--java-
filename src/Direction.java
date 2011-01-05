public enum Direction {
   NORD(0,-1),
   SUD(0,1),
   EST(1,0),
   OUEST(-1,0);
   
   private int x,y;
   private Direction(int x, int y) {
   this.x = x;
   this.y = y;
   }
   
   public final int getX() {
      return x;
   }
   
   public final int getY() {
      return y;
   }
}