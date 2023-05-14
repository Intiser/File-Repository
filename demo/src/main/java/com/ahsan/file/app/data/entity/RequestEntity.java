package com.ahsan.file.app.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RequestEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", referencedColumnName = "id")
    private FileEntity fileEntity;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isResolved;


}
