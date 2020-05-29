package com.kpi.fileaccess.controllers;

import com.kpi.fileaccess.domain.File;
import com.kpi.fileaccess.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public String renderFilesList(Model model) {
        model.addAttribute("files", fileService.readAll());
        return "files_list";
    }

    @GetMapping("/create")
    public String renderNewFileForm(Model model) {
        model.addAttribute("file", new File());
        return "file_form";
    }

    @GetMapping("/edit/{id}")
    public String renderEditFileForm(@PathVariable Long id, Model model) {
        model.addAttribute("file", fileService.read(id));
        return "file_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        fileService.delete(id);
        return "redirect:/files";
    }

    @PostMapping("/create")
    public String createFile(File file) {
        fileService.create(file);
        return "redirect:/files";
    }

    @PostMapping("/edit/{id}")
    public String createFile(@PathVariable Long id, File file) {
        fileService.edit(id, file);
        return "redirect:/files";
    }
}
