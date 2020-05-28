package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.File;

import java.util.List;

public interface FileService {
    File create(File file);
    File edit(Long id, File file);
    File delete(Long id);
    File read(Long id);
    List<File> readAll();
}
