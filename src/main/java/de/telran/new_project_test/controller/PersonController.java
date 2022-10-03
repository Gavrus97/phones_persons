package de.telran.new_project_test.controller;

import de.telran.new_project_test.dto.request.PersonRequestDTO;
import de.telran.new_project_test.dto.response.PersonResponseDTO;
import de.telran.new_project_test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/add")
    public void add(@RequestBody @Valid PersonRequestDTO dto) {
        service.add(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
    }

    @GetMapping("/get/{id}")
    public PersonResponseDTO getById(@PathVariable(name = "id") Integer id) {
        return service.getById(id);
    }

    @GetMapping("/get")
    public PersonResponseDTO getByName(@NotBlank(message = "firstName cannot be blank")
                                       @RequestParam(name = "firstName") String firstName,
                                       @NotBlank(message = "lastName cannot be blank")
                                       @RequestParam(name = "lastName") String lastName) {
        return service.getByName(firstName, lastName);
    }

    @GetMapping("/getAll")
    public List<PersonResponseDTO> getAll() {
        return service.getAll();
    }
}
