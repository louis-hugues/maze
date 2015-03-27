package maze;

/**
 * Created by huguesl on 27/03/15.
 */
public class Maze

{

    class Wanderer {
        int x,y;

        void Wanderer(int x, int y ){
            this.x = x;
            this.y = y;
        }

    }

    String maze[] = {
            "######################################",
            "#                                    #",
            "#                                    #",
            "#                                    #",
            "######################################",

    };


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
