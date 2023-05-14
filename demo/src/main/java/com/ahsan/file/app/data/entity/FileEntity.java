package com.ahsan.file.app.data.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@Entity(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isBlocked;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name="checksum_id", referencedColumnName = "id")
    private FileCheckSum fileCheckSum;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "datas", nullable = false, length = 16777215, columnDefinition = "LONGBLOB")
    private byte[] data;


    @Column(name = "data", nullable = true)
    private byte[] old;

}
