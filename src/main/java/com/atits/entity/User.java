package com.atits.entity;


import io.swagger.annotations.ApiModel;


import javax.persistence.*;
//import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")
@ApiModel(value="user对象",description="用户对象user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable = false,unique = true)
    private String userName;

    private String password;//密码限制？？？

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToOne
    private Laboratory laboratory;
    @ManyToOne
    private Station station;
    @ManyToOne
    @JoinColumn
    private System system;
    private String createTime;
    private int state; //1--已激活   2---未激活
    @ManyToMany
    private Set<Role> roles =new HashSet<>();

    public User() {
        roles.add(new Role());
    }

    public User(Set<Role> roles){
        this.roles=roles;
    }

    public User(int id,String userName,String systemName,int state){
        this.id=id;
        this.profile=new Profile();
        this.profile.setName(userName);
        this.system=new System();
        this.system.setSystemName(systemName);
        this.state=state;
    }

    public User(int id,String userName,String sysName,Set<Role> roles) {
        this.system=new System();
        this.id=id;
        this.userName = userName;
        this.system.setSystemName(sysName);
        this.roles=roles;
    }
    public User(int id,String name) {
        this.id=id;
        this.profile=new Profile();
        profile.setName(name);
    }
    public User(int id,String name,int systemId) {
        this.id=id;
        this.profile=new Profile();
        profile.setName(name);
        this.system=new System();
        this.system.setId(systemId);
    }
    public User(int id,int profileId,String name,int state) {
        this.id=id;
        this.profile=new Profile();
        this.profile.setName(name);
        this.profile.setId(profileId);
        this.state=state;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

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
                ", profile=" + profile +
                ", laboratory=" + laboratory +
                ", station=" + station +
                ", system=" + system +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                ", roles=" + roles +
                '}';
    }
}
