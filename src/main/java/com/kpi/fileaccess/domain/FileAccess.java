package com.kpi.fileaccess.domain;

import javax.persistence.*;

@Entity
public class FileAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne
    @JoinColumn(name = "file_access_rule_id")
    private FileAccessRule fileAccessRule;

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

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileAccessRule getFileAccessRule() {
        return fileAccessRule;
    }

    public void setFileAccessRule(FileAccessRule fileAccessRule) {
        this.fileAccessRule = fileAccessRule;
    }
}
