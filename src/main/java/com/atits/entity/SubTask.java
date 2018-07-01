package com.atits.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 子任务
 * @author zys
 */
@Entity
@Table(name = "t_subtask")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User bearer;//承担人

    @Column( length = 100000)
    private String content;

//    private int state;

    private String title;//子任务名称

    private String time;//发布时间

    private String date;

    private String startTime;

    private String endTime;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TaskProgress> taskProgresses =new HashSet<TaskProgress>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Files> files=new HashSet<Files>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBearer() {
        return bearer;
    }

    public void setBearer(User bearer) {
        this.bearer = bearer;
    }

    public Set<TaskProgress> getTaskProgresses() {
        return taskProgresses;
    }

    public void setTaskProgresses(Set<TaskProgress> taskProgresses) {
        this.taskProgresses = taskProgresses;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Set<Files> getFiles() {
        return files;
    }

    public void setFiles(Set<Files> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", bearer=" + bearer +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", taskProgresses=" + taskProgresses +
                ", files=" + files +
                '}';
    }
}
