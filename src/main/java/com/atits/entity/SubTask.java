package com.atits.entity;

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

    @OneToOne
    @JoinColumn(nullable = false)
    private User bearer;//承担人

    @Column( length = 100000)
    private String content;

//    private int state;

    private String title;//子任务名称

    @ManyToOne
    @JoinColumn(nullable = false)
    private Task fatherTask;//所属任务

    private String time;//发布时间

//    private String date;

    private String startTime;

    private String endTime;

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

    public Task getFatherTask() {
        return fatherTask;
    }

    public void setFatherTask(Task fatherTask) {
        this.fatherTask = fatherTask;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
                ", fatherTask=" + fatherTask +
                ", time='" + time + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", files=" + files +
                '}';
    }
}
