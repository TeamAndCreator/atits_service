package com.atits.entity;


import javax.persistence.*;

//@Entity(name = "t_station")
public class Station {

   // id、sta_name、systemID、content、company、time、state
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String staName;
    private String content;
    private String company;
    private String time;
    private int state;

    @ManyToOne
    private System system;
}
