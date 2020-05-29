package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.FileAccess;

import java.util.List;

public interface FileAccessService {
    FileAccess create(FileAccess fileAccess);
    FileAccess edit(Long id, FileAccess fileAccess);
    FileAccess delete(Long id);
    FileAccess read(Long id);
    List<FileAccess> readAll();
}
