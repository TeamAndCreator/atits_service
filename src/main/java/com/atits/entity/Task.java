package com.atits.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 父任务
 * @author zys
 */
@Entity
@Table(name = "t_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;//负责人

    @ManyToOne
    @JoinColumn(nullable = false)
    private System system;//任务所属体系-负责人所属体系

    private String title;//任务名称

    private String time;//发布时间

    private String date;

    //任务起止时间
    private String stratTime;

    private String endTime;

    @Column(length = 100000)
    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Files> files=new HashSet<Files>();

    public Task(){}

    public Task(Integer id,String title){
        this.id=id;
        this.title=title;
    }

    public Task(Integer id,Integer userId,String userName,Integer systemId,String systemName,String tile,String date,String stratTime,String endTime){
        this.id=id;
        this.user=new User(userId,userName);
        this.system=new System(systemId,systemName);
        this.title=tile;
        this.date=date;
        this.stratTime=stratTime;
        this.endTime=endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStratTime() {
        return stratTime;
    }

    public void setStratTime(String stratTime) {
        this.stratTime = stratTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", system=" + system +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", stratTime='" + stratTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", content='" + content + '\'' +
                ", files=" + files +
                '}';
    }
}
