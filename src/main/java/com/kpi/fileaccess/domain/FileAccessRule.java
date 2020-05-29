package com.kpi.fileaccess.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FileAccessRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private boolean create;

    private boolean read;

    private boolean update;

    private boolean delete;

    @OneToMany(mappedBy = "fileAccessRule")
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

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public List<FileAccess> getFileAccesses() {
        return fileAccesses;
    }

    public void setFileAccesses(List<FileAccess> fileAccesses) {
        this.fileAccesses = fileAccesses;
    }
}
