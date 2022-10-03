package de.telran.new_project_test.controller;

import de.telran.new_project_test.dto.request.PhoneRequestDTO;
import de.telran.new_project_test.dto.response.PhoneResponseDTO;
import de.telran.new_project_test.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/phones")
@Validated
public class PhoneController {

    @Autowired
    private PhoneService service;

    @PostMapping("/add")
    public void add(@RequestBody @Valid PhoneRequestDTO dto) {
        service.add(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
    }

    @GetMapping("/get/{id}")
    public PhoneResponseDTO getById(@PathVariable(name = "id") Integer id) {
        return service.getById(id);
    }

    @GetMapping("/get")
    public PhoneResponseDTO getByNumber(@NotBlank(message = "number cannot be blank")
                                        @Size(min = 14, max = 14, message = "number must be like +1122233333333")
                                        @RequestParam("number") String number) {
        return service.getByNumber(number);
    }

    @GetMapping("/getAll")
    public List<PhoneResponseDTO> getAll() {
        return service.getAll();
    }
}
