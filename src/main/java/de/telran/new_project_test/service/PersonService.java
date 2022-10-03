package de.telran.new_project_test.service;

import de.telran.new_project_test.dto.request.PersonRequestDTO;
import de.telran.new_project_test.dto.response.PersonResponseDTO;

import java.util.List;

public interface PersonService {

    void add(PersonRequestDTO dto);
    void delete(Integer id);
    PersonResponseDTO getById(Integer id);
    PersonResponseDTO getByName(String firstName, String lastName);
    List<PersonResponseDTO> getAll();
}
