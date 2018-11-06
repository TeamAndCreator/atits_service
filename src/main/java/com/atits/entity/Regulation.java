package com.atits.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 规章制度
 * @author zys
 */
@Entity
@Table(name = "t_regulation")
public class Regulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;


    @Column(length = 100000)
    private String content;

    private String time;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Files> files=new HashSet<Files>();

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(nullable = false)
    @ManyToOne
    private System system;

    private String date;

    public Set<Files> getFiles() {
        return files;
    }

    public void setFiles(Set<Files> files) {
        this.files = files;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Regulation(){}

    public Regulation(Integer id , String title,String date){
        this.id=id;
        this.title=title;
        this.date=date;
    }

    public Regulation(Integer id,Integer systemId,String systemName,String title,String name,String date,int state){
        this.id=id;
        this.system=new System();
        this.system.setId(systemId);
        this.system.setSystemName(systemName);
        this.title=title;
        this.user=new User();
        this.user.setProfile(new Profile(name));
        this.date=date;
        this.state=state;
    }

    @Override
    public String toString() {
        return "Regulation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", files=" + files +
                ", user=" + user +
                ", system=" + system +
                ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }

}
