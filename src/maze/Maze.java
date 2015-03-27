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

        void Wanderer(int x, int y , Maze maze){
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


    }

    String maze[] = {
            "######################################",
            "#                                    #",
            "#                                    #",
            "#                                    #",
            "######################################",

    };
    boolean isWall(int x , int y){

        return maze[y].charAt(x) == '#';

    }

    public void Maze(){

    }




    void print(){
        for (String s : maze){
            System.out.println(s);
        }
    }




    public static void  main(String args[]){

        System.out.println("main");
        Maze m = new Maze();
        m.print();

        System.out.println("Done");

    }


}
