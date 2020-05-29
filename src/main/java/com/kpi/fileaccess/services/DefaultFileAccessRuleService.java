package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.FileAccessRule;
import com.kpi.fileaccess.repositories.FileAccessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultFileAccessRuleService implements FileAccessRuleService {

    @Autowired
    private FileAccessRuleRepository fileAccessRuleRepository;

    @Override
    public FileAccessRule create(FileAccessRule fileAccessRule) {
        return fileAccessRuleRepository.save(fileAccessRule);
    }

    @Override
    public FileAccessRule edit(Long id, FileAccessRule newFileAccessRule) {
        Optional<FileAccessRule> existingFileAccessRuleOptional = fileAccessRuleRepository.findById(id);
        existingFileAccessRuleOptional.ifPresent(fileAccessRule -> {
            fileAccessRule.setTitle(newFileAccessRule.getTitle());
            fileAccessRule.setCreate(newFileAccessRule.isCreate());
            fileAccessRule.setRead(newFileAccessRule.isRead());
            fileAccessRule.setUpdate(newFileAccessRule.isUpdate());
            fileAccessRule.setDelete(newFileAccessRule.isDelete());
            fileAccessRuleRepository.save(fileAccessRule);
        });
        return existingFileAccessRuleOptional.orElse(null);
    }

    @Override
    public FileAccessRule delete(Long id) {
        Optional<FileAccessRule> fileAccessRuleOptional = fileAccessRuleRepository.findById(id);
        fileAccessRuleOptional.ifPresent(fileAccessRule -> fileAccessRuleRepository.delete(fileAccessRule));
        return fileAccessRuleOptional.orElse(null);
    }

    @Override
    public FileAccessRule read(Long id) {
        return fileAccessRuleRepository.findById(id).orElse(null);
    }

    @Override
    public List<FileAccessRule> readAll() {
        List<FileAccessRule> result = new ArrayList<>();
        fileAccessRuleRepository.findAll().forEach(result::add);
        return result;
    }
}
