package com.atits.entity;

import javax.persistence.*;

/**
 * 体系
 * @author zys
 */
@Entity(name = "t_system")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String systemName;

    private String content;

    private String overView;

    public void setId(int id){this.id=id;}

    public int getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }


    @Override
    public String toString() {
        return "System{" +
                "id=" + id +
                ", systemName='" + systemName + '\'' +
                ", content='" + content + '\'' +
                ", overView='" + overView + '\'' +
                '}';
    }
}
