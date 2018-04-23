package com.atits.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_user")
@ApiModel(value="user对象",description="用户对象user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false,unique = true)
    @ApiModelProperty(value = "用户登录账号", required = true)
    private String userName;

    @ApiModelProperty(value = "用户登录密码", required = true)
    private String password;
    @ManyToOne
    private Laboratory laboratory;
    @ManyToOne
    private Station station;
    @ManyToOne
    @JoinColumn(nullable = false)
    private System system;
    private String time;
    private int state;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
                ", time='" + time + '\'' +
                ", state=" + state +
                ", roles=" + roles +
                '}';
    }
}
