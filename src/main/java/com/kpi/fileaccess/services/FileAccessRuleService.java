package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.FileAccessRule;

import java.util.List;

public interface FileAccessRuleService {
    FileAccessRule create(FileAccessRule fileAccessRule);
    FileAccessRule edit(Long id, FileAccessRule fileAccessRule);
    FileAccessRule delete(Long id);
    FileAccessRule read(Long id);
    List<FileAccessRule> readAll();
}
