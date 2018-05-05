package com.atits.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_test_start")
public class TestStart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;// 考评年度
    private String date;//考评日期
    private String address;//考评地点


    @ManyToMany
    private Set<User> users=new HashSet<>();//考评人员

    private int state;//考评状态 ：1、启动考评，2、考评开始，3、考评结束


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TestStart{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", users=" + users +
                ", state=" + state +
                '}';
    }
}
