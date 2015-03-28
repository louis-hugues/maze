package maze;

import java.util.Stack;
/**
 * Created by huguesl on 27/03/15.
 */


public class Maze
{
     public enum Direction { up,down,left,right};

     protected class Wanderer {
        int x,y;
        Direction dir;
        Maze maze;

         Wanderer(int x, int y ){
            moveTo(x,y);
            turnTo(Direction.up);
            this.maze = Maze.this;
        }

        void turnLeft(){
            switch (dir){
                case up: dir = Direction.left;break;
                case left: dir = Direction.down;break;
                case right: dir = Direction.up;break;
                case down: dir = Direction.right;break;
            }
        }

         void moveTo(int x, int y){
             this.x = x;
             this.y = y;
         }
         void turnTo(Direction dir){
             this.dir = dir;
         }
        void turnRight(){
            switch (dir){
                case up: dir = Direction.right;break;
                case left: dir = Direction.up;break;
                case right: dir = Direction.down;break;
                case down: dir = Direction.left;break;
            }
        }

        void forward(){
            switch (dir){
                case up:  y--;break;
                case left: x--;break;
                case right: x++;break;
                case down: y++;break;
            }
        }
         void backward(){
             switch (dir){
                 case up:  y++;break;
                 case left: x++;break;
                 case right: x--;break;
                 case down: y--;break;
             }
         }

        boolean faceWall(){
            switch (dir){
                case up:  return maze.isWall(x,y-1);
                case left: return maze.isWall(x-1,y);
                case right: return maze.isWall(x+1,y);
                case down: return maze.isWall(x,y+1);
                default : return false;
            }
        }

        void mark(){
            maze.setCharAt(x,y,'*');
        }
         boolean isMarked(){
             return maze.isCharAt(x,y,'*');
         }
         boolean isMarkedWith(char c){
             return maze.isCharAt(x,y,c);
         }

        void wander(int steps, boolean atRandom){
            while (steps-- >0) {
                if (atRandom){
                    while (Math.random() < 0.25)
                        turnRight();
                    }
                while (faceWall())
                    turnRight();
                forward();
                mark();
            }
        }


         boolean search(char c){
             if (isMarkedWith(c)) return true;
             if (isMarked()) return false;
             mark();
              for (Maze.Direction d : Maze.Direction.values() ){
                 turnTo(d);
                 if (!faceWall()){
                     forward();
                     if(search(c)) return true;
                     backward();
                 }
             }
             return false;
         }
    }


    private String maze[] = {
            "######################################",
            "#     #                              #",
            "#                                    #",
            "#                                    #",
            "#                                    #",
            "#                                    #",
            "#    ##                              #",
            "######################################",
    };

    private char grid[][];
    private int width,height;


    protected boolean isWall(int x , int y){
        return grid[y][x] == '#';
    }


    protected boolean  isCharAt(int x ,int y , char c) {
         return grid[y][x] == c;
    }


    protected void setCharAt(int x ,int y , char c) {
        //System.out.format("(%d %d )",x,y);
        grid[y][x] = c;
    }

    public void clear(){
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                grid[y][x] = maze[y].charAt(x);
    }

    public Maze(){
        width = maze[0].length();
        height = maze.length;
        grid = new char[height][width];
        clear();
    }

    public void print(){
        for (int y = 0; y < height; y++){
            System.out.println(grid[y]);
        }
    }


    public static void  main(String args[]){
        System.out.println("Main");
        Maze m = new Maze();

        Wanderer w = m.new Wanderer(2,3);
        w.wander(10,false);
        m.print();

        m.clear();
        w = m.new Wanderer(2,3);
        w.wander(30, true);
        m.print();

        m.clear();
        m.setCharAt(4, 5, 'F');
        w = m.new Wanderer(2,3);
        w.search('F');
        m.print();

        System.out.println("Done");
    }


}
