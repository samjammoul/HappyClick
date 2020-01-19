package CommunicatorClient;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class test{
    static int s =0;
    static int a =0;
    static int v=0;
    static int g =0;
    private int time;
    public static void main(String[] args) {
        for (int i =1 ; i<= 5;i++){
            System.out.println(i);
        }
    }
}

/*
        int b =0;

        List<String> var = new ArrayList<>();
        var.add("Hey");
        a= var.size()+1;
        var.add("Bye");
        var.add("Hey");
        var.add("Bye");
        b= var.size()+1;
        Timer timer = new Timer();
       TimerTask task;
        int finalA = a;
        //for (int r = 0 ;r<=var.size();r++) {


        timer.schedule(task= new TimerTask() {



            @Override
            public void run() {
                v++;
                //     System.out.println("Timer ran " + ++v);

                if(s < var.size() ){
                    System.out.println(var.get(s));
                    System.out.println("Timer ran " + ++s);
                }
                if(v == var.size()+1){
                    timer.cancel();
                    System.out.println("Done");
                }

            }

        }, var.size(),2*1000); // 2  second


        }

        public void print(){
            System.out.println("print");
        }
        /*
    Timer timer2 = new Timer();
    TimerTask task;




                    timer2.schedule(task= new TimerTask() {

        int time =0;

        @Override
        public void run() {
            time++;
            System.out.println(time);

            if(time == 2){
                timer2.cancel();

                System.out.println("Game is finish");
                print();
            }



        }

    }, 0,1*1000);

         */

