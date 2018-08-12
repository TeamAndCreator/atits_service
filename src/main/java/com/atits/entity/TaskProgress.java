package com.atits.entity;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * 任务进展
 * @author zys
 */
@Entity
@Table(name = "t_taskprogress")
public class TaskProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SubTask subTask;//所属子任务

    private String title;

    @Column( length = 100000)
    private String content;

    private String time;

    private String date;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Files> files;

    private int state;//1-通过  2-不通过  0-审核中

    public TaskProgress(){}

    public TaskProgress(int id,int subTaskId,String subTaskName,int bearerId,String bearerName,String title,String content,String date,int state){
        this.id=id;
        this.subTask=new SubTask(subTaskId,subTaskName);
        this.subTask.setBearer(new User(bearerId,bearerName));
        this.title=title;
        this.content=content;
        this.date=date;
        this.state=state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public SubTask getSubTask() {
        return subTask;
    }

    public void setSubTask(SubTask subTask) {
        this.subTask = subTask;
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

    public Set<Files> getFiles() {
        return files;
    }

    public void setFiles(Set<Files> files) {
        this.files = files;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TaskProgress{" +
                "id=" + id +
                ", subTask=" + subTask +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", files=" + files +
                ", state=" + state +
                '}';
    }
}
