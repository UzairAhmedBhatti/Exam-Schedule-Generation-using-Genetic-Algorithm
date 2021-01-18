package com.company;
import java.util.*;
import java.io.*;
//Each chromosome represent a complete table solution...
public class Chromosome {
    int noofdays;
    int slots;
    int noofstudents;
    int noofcourses;

    Gene[][] ch;
    int[][] r;
    int[][] sc;
    int[] co;
    int rc;
    int fitness;

    public Chromosome(int a, int b, int c, int d, int[][] e, int f) {
        noofdays = a;
        slots = b;
        noofstudents = c;
        noofcourses = d;
        ch = new Gene[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                ch[i][j] = new Gene();
            }
        }
        r = e;
        rc = f;
        sc = new int[noofstudents][slots];
        for (int i = 0; i < noofstudents; i++) {
            for (int j = 0; j < slots; j++) {
                sc[i][j] = 0;
            }
        }
        co = new int[noofcourses];
        for (int i = 0; i < noofcourses; i++) {
            co[i] = 0;
        }

    }

    public void CreateChromosome() {
        for (int i = 1; i <= noofcourses; i++) {
            //System.out.print(RandomNum(1,noofdays));
            ch[RandomNum(1, noofdays) - 1][RandomNum(1, slots) - 1].CreateGene(i);
        }
        for (int i = 0; i < noofdays; i++) {
            for (int j = 0; j < slots; j++) {
                for (int z = 0; z < ch[i][j].getCourses().size(); z++) {
                    co[((int) ch[i][j].getCourses().get(z)) - 1]++;
                }
            }
        }

    }

    public int RandomNum(int start, int end) {
        Random rand = new Random();
        int range = (end - start) + 1;
        int random = start + (rand.nextInt(range));
        return random;
    }


  //  /*
    public void CalculateFitness()
    {
        int points=0;

        points++; //all exams are scheduled within given number of days
        int c=0;
        int totalstudentinslot=0;
        int totalcourseinslotforstudent=0;
        int totalstudentwithcourse2=0;
        int totalslotforstudent=0;
        int studentwith2slot=0;
        int flag=1;
        int flag1=1;
        outer:for(int i=0;i<noofdays;i++){
            for(int j=0;j<slots;j++){
                c=0;
                totalstudentinslot=0;
                totalcourseinslotforstudent=0;
                totalstudentwithcourse2=0;
                for(int z=0;z<ch[i][j].getCourses().size();z++){//condition to check that total number of students in one given slot is less than total room capacity
                    c= (int) ch[i][j].getCourses().get(z);
                    for(int l =0;l<noofstudents;l++){
                        if(r[c-1][l] == 1){
                            totalstudentinslot++;
                            sc[l][j] = 1;
                        }
                    }

                }
                if(totalstudentinslot < rc){
                    points=points+1;
                }
                outer1:for(int l=0;l<noofstudents;l++){//condition to check that student cannot have more than 2 exam in one slot and 2 exam must be minimum
                    for(int z=0;z<ch[i][j].getCourses().size();z++){
                        c= (int) ch[i][j].getCourses().get(z);
                        if(r[c-1][l]==1){
                            totalcourseinslotforstudent++;
                        }
                    }

                    if(totalcourseinslotforstudent==2){
                        totalstudentwithcourse2++;
                    }
                    else if(totalcourseinslotforstudent>2){
                        flag=0;
                        break outer1;
                    }
                }
                if(totalstudentwithcourse2 == 0 && flag==1){
                    points = points+(noofstudents-totalstudentwithcourse2);
                    //points = points+2;
                }
                else if(totalstudentwithcourse2 > 0 && flag==1){
                    points=points+(noofstudents);
                    //points=points+1;
                }


            }
            outer2:for(int z=0;z<noofstudents;z++){//condition to check that student cannot have  exam in in more than 2 slots and 2 slots must be minimum
                for(int l=0;l<slots;l++){
                    if(sc[z][l]==1){
                        totalslotforstudent++;
                    }
                }
                if(totalslotforstudent==2){
                    studentwith2slot++;
                }
                else if(totalslotforstudent>2){
                    flag1=0;
                    break outer2;
                }
            }
            if(studentwith2slot ==0 && flag1==1){
                points=points + (noofstudents-studentwith2slot);
                //points=points+2;
            }
            else if(studentwith2slot >0 && flag1==1){
                points=points+(noofstudents);
                //points=points+1;
            }


        }
        int totalcourse=0;
        for(int i=0;i<noofdays;i++){
            for(int j=0;j<slots;j++){
                totalcourse=totalcourse+ch[i][j].getCourses().size();
            }
        }
        if(totalcourse != noofcourses){
            //points=0;
            points=points+totalcourse;
        }
        else{
            points=points+noofcourses;
        }


        fitness= points;
    }
//    */


/*
    public void CalculateFitness() {
        int points = 0;

        //points++; //all exams are scheduled within given number of days
        int c = 0;
        int totalstudentinslot = 0;
        int totalcourseinslotforstudent = 0;
        int totalstudentwithcourse2 = 0;
        int totalslotforstudent = 0;
        int studentwith2slot = 0;
        int flag = 1;
        int flag1 = 1;
        int totalcourse = 0;
        int nocourserepeat = 1;
        for (int i = 0; i < noofdays; i++) {
            for (int j = 0; j < slots; j++) {
                totalcourse = totalcourse + ch[i][j].getCourses().size();
            }
        }
        if (totalcourse == noofcourses) {
            //points=0;
            //points=points+totalcourse;
            points = points + 50;
        }
        outer:for (int i = 0; i < noofcourses; i++) {
            if (co[i] != 1) {
                nocourserepeat = 0;
                break outer;
            }
        }
        if (nocourserepeat == 1) {
            //points = points + noofcourses;
            points = points + 50;
        }

            outer0:
            for (int i = 0; i < noofdays; i++) {
                for (int j = 0; j < slots; j++) {
                    c = 0;
                    totalstudentinslot = 0;
                    for (int z = 0; z < ch[i][j].getCourses().size(); z++) {//condition to check that total number of students in one given slot is less than total room capacity
                        c = (int) ch[i][j].getCourses().get(z);
                        for (int l = 0; l < noofstudents; l++) {
                            if (r[c - 1][l] == 1) {
                                totalstudentinslot++;
                                sc[l][j] = 1;
                            }
                        }

                    }
                    if (totalstudentinslot < rc) {
                        points = points + 1;
                    }
                }
            }

            outer1:
            for (int l = 0; l < noofstudents; l++) {
                for (int i = 0; i < noofdays; i++) {
                    for (int j = 0; j < slots; j++) {
                        totalcourseinslotforstudent = 0;
                        for (int z = 0; z < ch[i][j].getCourses().size(); z++) {
                            c = (int) ch[i][j].getCourses().get(z);
                            if (r[c - 1][l] == 1) {
                                totalcourseinslotforstudent++;
                                sc[l][j] = 1;
                            }
                        }
                        if ( totalcourseinslotforstudent==0 || totalcourseinslotforstudent == 1) {
                            points =points+2;
                       } else if (totalcourseinslotforstudent == 2) {
                            points=points+1;
                        }

                    }
                }
            }
    fitness=points;
}
 */

    public Gene mutation(){
        Gene g=new Gene();
        g.CreateGene(RandomNum(1,noofcourses));
        return g;

    }

    public void crossover(Chromosome p1,Chromosome p2){
        int z=0;
        int c=0;

        for(int i=0;i<noofdays;i++){
            for(int j=0;j<slots;j++){
                float p=RandomNum(0,100)/100; // probability
                if(p<0.45){
                    if(!p1.ch[i][j].getCourses().isEmpty()){
                        ch[i][j]=p1.ch[i][j];
                    }
                    else{
                        ch[i][j]=mutation();
                    }


                }
                else if(p<0.9){
                    if(!p2.ch[i][j].getCourses().isEmpty()){
                        ch[i][j]=p2.ch[i][j];
                    }
                    else{
                        ch[i][j]=mutation();
                    }

                }

                //else{
                   // ch[i][j]=mutation();
              // }

            }

        }

    }

    public void print(){
      for(int i=0;i<noofdays;i++){
           for(int j=0;j<slots;j++){
               ch[i][j].print();
               System.out.print("  ");
            }
           System.out.println();
       }
      // System.out.println();
       System.out.println(fitness);
    }

}
