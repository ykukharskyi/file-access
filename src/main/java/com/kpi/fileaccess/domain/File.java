package com.kpi.fileaccess.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String path;

    @OneToMany(mappedBy = "file")
    private List<FileAccess> fileAccesses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FileAccess> getFileAccesses() {
        return fileAccesses;
    }

    public void setFileAccesses(List<FileAccess> fileAccesses) {
        this.fileAccesses = fileAccesses;
    }
}
