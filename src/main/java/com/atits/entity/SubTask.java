package com.atits.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * 子任务
 * @author zys
 */
@Entity(name = "t_subtask")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(nullable = false)
    private User bearer;

    @Column( length = 100000)
    private String content;

    private int state;

    private String title;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Task fatherTask;

    private String time;

    private String date;

    private String startDate;

    private String endDate;

    @OneToMany
    private Set<Files> files;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
                ", state=" + state +
                ", title='" + title + '\'' +
                ", fatherTask=" + fatherTask +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", files=" + files +
                '}';
    }
}
