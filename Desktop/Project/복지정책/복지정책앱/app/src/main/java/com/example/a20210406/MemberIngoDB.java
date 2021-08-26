package com.example.a20210406;

import android.widget.EditText;

public class MemberIngoDB {

    public String name;
    public String age;
    public String choice;
    public String obstKiArray;
    public String obstAbtArray;
    public String trgterIndvdlArray;
    public String desireArray;

    public MemberIngoDB(String name, String age,String choice, String obstKiArray,String obstAbtArray, String trgterIndvdlArray,String desireArray){
        this.name = name;
        this.age = age;
        this.choice = choice;
        this.obstKiArray = obstKiArray;
        this.obstAbtArray = obstAbtArray;
        this.trgterIndvdlArray = trgterIndvdlArray;
        this.desireArray = desireArray;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name =name;
    }
    public String getAge(){
        return this.age;
    }
    public void setAge(String age){
        this.age =age;
    }
    public String getChoice(){
        return this.choice;
    }
    public void setChoice(String choice){
        this.choice =choice;
    }
    //
    public String setobstKiArray(){
        return this.obstKiArray;
    }
    public void setobstKiArray(String obstKiArray){
        this.obstKiArray =obstKiArray;
    }
    public String getobstAbtArray(){
        return this.obstAbtArray;
    }
    public void getobstAbtArray(String obstAbtArray){
        this.obstAbtArray =obstAbtArray;
    }

    public String gettrgterIndvdlArray(){
        return this.trgterIndvdlArray;
    }
    public void gettrgterIndvdlArray(String trgterIndvdlArray){
        this.trgterIndvdlArray =trgterIndvdlArray;
    }
    public String getdesireArray(){
        return this.desireArray;
    }
    public void getdesireArray(String desireArray) {
        this.desireArray =desireArray;
    }
}
