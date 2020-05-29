package com.kpi.fileaccess.controllers;

import com.kpi.fileaccess.domain.FileAccess;
import com.kpi.fileaccess.services.FileAccessRuleService;
import com.kpi.fileaccess.services.FileAccessService;
import com.kpi.fileaccess.services.FileService;
import com.kpi.fileaccess.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file-accesses")
public class FileAccessController {

    @Autowired
    private FileAccessService fileAccessService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private FileAccessRuleService fileAccessRuleService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public String renderFileAccessesList(Model model) {
        model.addAttribute("fileAccesses", fileAccessService.readAll());
        return "file_accesses_list";
    }

    @GetMapping("/create")
    public String renderNewFileAccessForm(Model model) {
        model.addAttribute("userGroups", userGroupService.readAll());
        model.addAttribute("fileAccessRules", fileAccessRuleService.readAll());
        model.addAttribute("files", fileService.readAll());
        model.addAttribute("fileAccess", new FileAccess());
        return "file_access_form";
    }

    @GetMapping("/edit/{id}")
    public String renderEditFileAccessForm(@PathVariable Long id, Model model) {
        model.addAttribute("userGroups", userGroupService.readAll());
        model.addAttribute("fileAccessRules", fileAccessRuleService.readAll());
        model.addAttribute("files", fileService.readAll());
        model.addAttribute("fileAccess", fileAccessService.read(id));
        return "file_access_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFileAccess(@PathVariable Long id) {
        fileAccessService.delete(id);
        return "redirect:/file-accesses";
    }

    @PostMapping("/create")
    public String createFileAccess(FileAccess fileAccess) {
        fileAccessService.create(fileAccess);
        return "redirect:/file-accesses";
    }

    @PostMapping("/edit/{id}")
    public String createFileAccess(@PathVariable Long id, FileAccess fileAccess) {
        fileAccessService.edit(id, fileAccess);
        return "redirect:/file-accesses";
    }
}
