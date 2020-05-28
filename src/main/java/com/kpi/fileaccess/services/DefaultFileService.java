package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.File;
import com.kpi.fileaccess.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileService implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File create(File file) {
        return fileRepository.save(file);
    }
}
