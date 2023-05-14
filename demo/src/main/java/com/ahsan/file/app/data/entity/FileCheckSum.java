package com.ahsan.file.app.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "file_checksum")
public class FileCheckSum {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String checkksum;

    @Column(nullable = false, unique = true)
    private String downloadlink;

    @Column(nullable = false)
    private String downloaduuid;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isBlocked;

}
