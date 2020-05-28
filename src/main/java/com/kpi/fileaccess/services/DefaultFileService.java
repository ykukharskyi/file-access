package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.File;
import com.kpi.fileaccess.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultFileService implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File create(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File edit(Long id, File newFile) {
        Optional<File> existingFileOptional = fileRepository.findById(id);
        existingFileOptional.ifPresent(file -> {
            file.setTitle(newFile.getTitle());
            file.setPath(newFile.getPath());
            fileRepository.save(file);
        });
        return existingFileOptional.orElse(null);
    }

    @Override
    public File delete(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        fileOptional.ifPresent(file -> fileRepository.delete(file));
        return fileOptional.orElse(null);
    }

    @Override
    public File read(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    @Override
    public List<File> readAll() {
        List<File> result = new ArrayList<>();
        fileRepository.findAll().forEach(result::add);
        return result;
    }
}
