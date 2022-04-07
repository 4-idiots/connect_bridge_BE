package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import javax.persistence.*;

@Entity
@Table(name ="total")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "total_id")
    private Long totalID;

    @Column(name = "total_main")
    private String totalMain;

    @Column(name = "total_sub")
    private String totalSub;

    @Column(name = "total_need")
    private int totalNeed;

}
