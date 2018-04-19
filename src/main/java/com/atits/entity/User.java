package com.atits.entity;


import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "t_user")
@ApiModel(value="user对象",description="用户对象user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(nullable = false,unique = true)
    private String userName;
    private String password;
    @ManyToOne
    private Laboratory laboratory;
    @ManyToOne
    private Station station;
    @ManyToOne
    @JoinColumn(nullable = false)
    private System system;
    private String createTime;
    private int state; //1--已激活   2---未激活
    @ManyToMany
    private Set<Role> roles;


    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", laboratory=" + laboratory +
                ", station=" + station +
                ", system=" + system+
                ", time='" + createTime + '\'' +
                ", state=" + state +
                ", roles=" + roles +
                '}';
    }
}
