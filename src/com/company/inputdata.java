package com.company;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class inputdata  {
    int n;//students
    int m;//courses
    int noofrooms;
    int examdays;
    int slots;
    int[][] registration;
    int[] roomcapacity;
    int r;



    public inputdata(int a, int b, int c) throws FileNotFoundException {
        n=b;
        m=a;
        noofrooms=c;
        File file=new File ("registration.data");
        Scanner s=new Scanner(file);
        int j=0;
        while(s.hasNextInt()){
            s.nextInt();
            j++;

        }
        n=j/a;
        s.close();
        System.out.println(n);
        registration = new int[m][n];
        roomcapacity = new int[noofrooms];
        examdays=0;
        slots=0;
        r=0;

    }
    public void readfromregistrationfile() throws FileNotFoundException {
        File file=new File ("registration.data");
        Scanner s=new Scanner(file);

        for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
                 registration[i][j]=s.nextInt();
             }
        }
        s.close();
    }
    public void readfromroomcapacity() throws FileNotFoundException{
        File file=new File ("capacity.room");
        Scanner s=new Scanner(file);
        for(int i=0;i<noofrooms;i++){
            roomcapacity[i]=s.nextInt();
            r=r+roomcapacity[i];
        }
    }
    public void readfromgeneral() throws FileNotFoundException{
        File file=new File ("general.info");
        Scanner s=new Scanner(file);
        examdays=s.nextInt();
        slots=s.nextInt();
    }
    public void print(){
        for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               System.out.print(registration[i][j] + " ");

           }
            System.out.println();

        }
        for(int z=0;z<noofrooms;z++){
            System.out.println("room " + z +" "+ "=" +" "+ roomcapacity[z] + " ");
        }
        System.out.println("examdays " + examdays);
        System.out.println("slots " + slots);
        System.out.println("roomcapacity " + r);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getExamdays() {
        return examdays;
    }

    public void setExamdays(int examdays) {
        this.examdays = examdays;
    }

    public int[][] getRegistration() {
        return registration;
    }

    public void setRegistration(int[][] registration) {
        this.registration = registration;
    }

    public int[] getRoomcapacity() {
        return roomcapacity;
    }

    public void setRoomcapacity(int[] roomcapacity) {
        this.roomcapacity = roomcapacity;
    }

    public int getNoofrooms() {
        return noofrooms;
    }

    public void setNoofrooms(int noofrooms) {
        this.noofrooms = noofrooms;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
