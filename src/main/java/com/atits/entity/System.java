package com.atits.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity(name = "t_system")
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String systemName;

    private String overview;

    private String picPath;

    private String time;

    private int state;


}
