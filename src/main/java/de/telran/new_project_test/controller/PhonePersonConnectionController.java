package de.telran.new_project_test.controller;

import de.telran.new_project_test.dto.request.PhonePersonConnectionDTO;
import de.telran.new_project_test.service.PhonePersonConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/connections")
@Validated
public class PhonePersonConnectionController {

    @Autowired
    private PhonePersonConnectionService service;

    @PutMapping
    public void connectPersonAndPhone(@RequestBody @Valid PhonePersonConnectionDTO dto) {
        service.addPhoneToPerson(dto);
    }
}
