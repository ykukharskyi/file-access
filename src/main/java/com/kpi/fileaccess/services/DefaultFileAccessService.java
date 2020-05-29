package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.FileAccess;
import com.kpi.fileaccess.repositories.FileAccessRepository;
import com.kpi.fileaccess.repositories.FileAccessRuleRepository;
import com.kpi.fileaccess.repositories.FileRepository;
import com.kpi.fileaccess.repositories.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultFileAccessService implements FileAccessService {

    @Autowired
    private FileAccessRepository fileAccessRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileAccessRuleRepository fileAccessRuleRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public FileAccess create(FileAccess fileAccess) {
        populateDependedEntities(fileAccess);
        return fileAccessRepository.save(fileAccess);
    }

    @Override
    public FileAccess edit(Long id, FileAccess newFileAccess) {
        Optional<FileAccess> existingFileAccessOptional = fileAccessRepository.findById(id);
        existingFileAccessOptional.ifPresent(fileAccess -> {
            fileAccess.setTitle(newFileAccess.getTitle());
            populateDependedEntities(newFileAccess, fileAccess);
            fileAccessRepository.save(fileAccess);
        });
        return existingFileAccessOptional.orElse(null);
    }

    @Override
    public FileAccess delete(Long id) {
        Optional<FileAccess> fileAccessOptional = fileAccessRepository.findById(id);
        fileAccessOptional.ifPresent(fileAccess -> fileAccessRepository.delete(fileAccess));
        return fileAccessOptional.orElse(null);
    }

    @Override
    public FileAccess read(Long id) {
        return fileAccessRepository.findById(id).orElse(null);
    }

    @Override
    public List<FileAccess> readAll() {
        List<FileAccess> result = new ArrayList<>();
        fileAccessRepository.findAll().forEach(result::add);
        return result;
    }

    private void populateDependedEntities(FileAccess fileAccess) {
        populateDependedEntities(fileAccess, fileAccess);
    }

    private void populateDependedEntities(FileAccess source, FileAccess target) {
        if (source.getFile() != null) {
            target.setFile(fileRepository.findById(source.getFile().getId()).orElse(null));
        }
        if (source.getFileAccessRule() != null) {
            target.setFileAccessRule(fileAccessRuleRepository.findById(source.getFileAccessRule().getId()).orElse(null));
        }
        if (source.getUserGroup() != null) {
            target.setUserGroup(userGroupRepository.findById(source.getUserGroup().getId()).orElse(null));
        }
    }
}
