package com.company;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        inputdata a = new inputdata(223,4,46);
        a.readfromregistrationfile();
        a.readfromroomcapacity();
        a.readfromgeneral();
        //a.print();

        //Initialise population
        Vector<Chromosome> population=new Vector();
        int populationsize=100;
        int generation=0;

        for(int i=0;i<populationsize;i++){
            Chromosome c = new Chromosome(a.getExamdays(),a.getSlots(),a.getN(),a.getM(),a.getRegistration(),a.getR());
            c.CreateChromosome();
            c.CalculateFitness();
            population.add(c);
        }


       // for(int i=0;i<populationsize;i++){
           // population.get(i).print();
       // }
       //System.out.println();


        Boolean found =false;
        Comparator<Chromosome> c = new ChromosomeComparator();
       // Collections.sort(population,c);
        //for(int i=0;i<populationsize;i++){
        //population.get(i).print();
        //}
       // population.get(0).print();
       // System.out.println();

        //int target=(2+(a.getExamdays()*a.getSlots())+(2*(a.getExamdays()*a.getSlots()))+2*(a.getExamdays()));
        int target=((a.getExamdays()*a.getSlots())+(a.getN()*(a.getExamdays()*a.getSlots()))+a.getN()*(a.getExamdays())+(2*a.getM()));
        //int target=(50+50+(a.getN()*(a.getSlots()*a.getExamdays()*2)+(a.getSlots()*a.getExamdays())));
        System.out.println(target);
        int previousfitness=0;
        int samefitnesscount=0;
        while(!found && samefitnesscount!= 100){ //Genetic Algorithm

            Collections.sort(population,c); //sort population according to fitness score
            if(population.get(0).fitness == target ){//maximum fitness that can be achieved following all conditions of exam schedule is (totalslots(slots*noofdays)+2*totalslots(slots*noofdays)+2*noofdays
                //we have reached target
                found =true;
                break;
            }
            if(population.get(0).fitness==previousfitness){
                samefitnesscount++;
            }
            else{
                samefitnesscount=0;
            }
            previousfitness=population.get(0).fitness;

            Vector<Chromosome> newgeneration=new Vector();
            int z=(10*population.size())/100; //10% of fittest goes into new generation selection
            for(int i=0;i<z;i++){
               newgeneration.add(population.get(i));
            }
            z=(90*population.size())/100 ; //rest of generation is filled by crossover and mutation
            int mutationcount=0;
            for(int i=0;i<z;i++){
                if(mutationcount==5){//Mutation
                    Chromosome o=new Chromosome(a.getExamdays(),a.getSlots(),a.getN(),a.getM(),a.getRegistration(),a.getR());
                    o.CreateChromosome();
                    newgeneration.add(o);
                    //System.out.println(mutationcount);
                    mutationcount=0;
                }
                else{//Crossover
                    int l=population.size();
                    int r = RandomNum(0,50);
                    Chromosome p1 = population.get(r);
                    r=RandomNum(0,50);
                    Chromosome p2=population.get(r);
                    Chromosome o=new Chromosome(a.getExamdays(),a.getSlots(),a.getN(),a.getM(),a.getRegistration(),a.getR());
                    o.crossover(p1,p2);
                    newgeneration.add(o);
                    mutationcount++;
                }


            }
            population=newgeneration;
            System.out.print("Generation: " + generation + " ");
            System.out.print("Fitness: " + population.get(0).fitness + " ");
            System.out.println();
            generation++;
            for(int i=0;i<population.size();i++){
               population.get(i).CalculateFitness();
               //population.get(i).print();
            }



        }
        System.out.print("Generation: " + generation + " ");
        System.out.print("Fitness: " + population.get(0).fitness + " ");
        System.out.print("Exam Schedule is: ");
        population.get(0).print();

        //local search
        found=false;
        previousfitness=0;
        samefitnesscount=0;
        Chromosome GAsol=population.get(0);
        int r1;
        int r2;
        int r3;
        int r4;
        while(!found && samefitnesscount!=100){
            Vector<Chromosome> newgeneration = new Vector();
            Vector ca=new Vector();
            Chromosome o=new Chromosome(a.getExamdays(),a.getSlots(),a.getN(),a.getM(),a.getRegistration(),a.getR());
            for(int q=0;q<a.getExamdays()*a.getSlots();q++) {


                for (int i = 0; i < a.getExamdays() * a.getSlots(); i++) {
                    r1 = RandomNum(0, a.getExamdays() - 1);
                    r2 = RandomNum(0, a.slots - 1);
                    r3 = RandomNum(0, a.getExamdays() - 1);
                    r4 = RandomNum(0, a.slots - 1);
                    ca = GAsol.ch[r1][r2].getCourses();
                    GAsol.ch[r1][r2].setCourses(GAsol.ch[r3][r4].getCourses());
                    GAsol.ch[r3][r4].setCourses(ca);

                }
                newgeneration.add(GAsol);
            }
            for(int i=0;i<a.getExamdays()*a.getSlots();i++){
                newgeneration.get(i).CalculateFitness();
            }
            if(newgeneration.get(0).fitness==target){
                found=true;
                GAsol=newgeneration.get(0);
                break;
            }
            if(newgeneration.get(0).fitness==previousfitness){
                samefitnesscount++;
            }
            else{
                samefitnesscount=0;
            }
            previousfitness=population.get(0).fitness;
            GAsol=newgeneration.get(0);



        }
       // System.out.print("Generation: " + generation + " ");
        System.out.print("Fitness: " + GAsol.fitness + " ");
        System.out.print("Exam Schedule is: ");
        GAsol.print();




        /*
        for(int i=0;i<populationsize;i++){
            population.get(i).print();
        }

         */


    }
    public static int RandomNum(int start, int end)
    {
        Random rand=new Random();
        int range=(end-start)+1;
        int random=start+(rand.nextInt(range));
        return random;
    }
}
