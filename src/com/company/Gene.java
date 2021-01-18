package com.company;
import java.util.*;
import java.io.*;
//Each gene represents a block in table...
public class Gene {
    public Vector courses;

    public Gene() {
        courses=new Vector();

    }
    public void CreateGene(int a){
        courses.add(a);
    }
    public void print(){
        for(int i=0;i<courses.size();i++){
            System.out.print(courses.get(i) + "/");

        }

    }

    public Vector getCourses() {
        return courses;
    }

    public void setCourses(Vector courses) {
        this.courses = courses;
    }
}
