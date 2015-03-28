package maze;

/**
 * Created by huguesl on 27/03/15.
 */


public class Maze

{
    public enum Direction { up,down,left,right};

     class Wanderer {
        int x,y;
        Direction dir;
        Maze maze;

         Wanderer(int x, int y , Maze maze){
            this.x = x;
            this.y = y;
            dir = Direction.up;
            this.maze = maze;
        }

        public void setDir(Direction dir) {
            this.dir = dir;
        }

        void turnLeft(){

            switch (dir){
                case up: dir = Direction.left;break;
                case left: dir = Direction.down;break;
                case right: dir = Direction.up;break;
                case down: dir = Direction.right;break;
            }
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

        void wander(int steps){
            while (steps-- >0) {
                while (faceWall())
                    turnRight();
                forward();
                mark();

            }
        }


    }



    String maze[] = {
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

    protected void setCharAt(int x ,int y , char c) {
        //System.out.format("(%d %d )",x,y);
        grid[y][x] = c;
    }

    private void clear(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                grid[y][x] = maze[y].charAt(x);

        }
    }
    public Maze(){

        width = maze[0].length();
        height = maze.length;
        grid = new char[height][width];

        clear();

    }




    void print(){
        for (int y = 0; y < height; y++){
            System.out.println(grid[y]);
        }
    }




    public static void  main(String args[]){

        System.out.println("Main");
        Maze m = new Maze();

        Wanderer w = m.new Wanderer(2,3,m);
        w.wander(10);
        m.print();
        System.out.println("Done");

    }


}
