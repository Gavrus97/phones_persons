package de.telran.new_project_test.service;

import de.telran.new_project_test.dto.request.PhoneRequestDTO;
import de.telran.new_project_test.dto.response.PhoneResponseDTO;

import java.util.List;

public interface PhoneService {

    void add(PhoneRequestDTO dto);
    void delete(Integer id);
    PhoneResponseDTO getById(Integer id);
    PhoneResponseDTO getByNumber(String number);
    List<PhoneResponseDTO> getAll();

}
