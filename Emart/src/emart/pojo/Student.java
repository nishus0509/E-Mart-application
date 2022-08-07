/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.pojo;

/**
 *
 * @author nishu
 */
public class Student {
    private int rollno;
    private String name;
    private String CollegeName;
    private int cntNo;
    int series;

    public Student(int rollno, String name, String CollegeName, int cntNo, int series) {
        this.rollno = rollno;
        this.name = name;
        this.CollegeName = CollegeName;
        this.cntNo = cntNo;
        this.series = series;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String CollegeName) {
        this.CollegeName = CollegeName;
    }

    public int getCntNo() {
        return cntNo;
    }

    public void setCntNo(int cntNo) {
        this.cntNo = cntNo;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
