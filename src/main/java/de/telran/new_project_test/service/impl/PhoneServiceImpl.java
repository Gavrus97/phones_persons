package de.telran.new_project_test.service.impl;

import de.telran.new_project_test.dto.request.PhoneRequestDTO;
import de.telran.new_project_test.dto.response.PhoneResponseDTO;
import de.telran.new_project_test.entity.Phone;
import de.telran.new_project_test.repository.PhoneRepository;
import de.telran.new_project_test.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository repository;

    @Override
    public void add(PhoneRequestDTO dto) {
        if (repository.existsByNumber(dto.getNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Phone [%s] already exists", dto.getNumber())
            );
        }

        repository.save(Phone
                .builder()
                .number(dto.getNumber())
                .person(null)
                .build()
        );
    }

    @Override
    public void delete(Integer id) {
        var phone = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Phone with id %s doesn't exist", id)
                        )
                );
        repository.delete(phone);
    }

    @Override
    public PhoneResponseDTO getById(Integer id) {
        var phone = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Phone with id %s doesn't exist", id)
                        )
                );

        return PhoneResponseDTO
                .builder()
                .number(phone.getNumber())
                .ownerName(phone.getPerson() == null ?
                        null : phone.getPerson().getFirstName() + " " + phone.getPerson().getLastName())
                .build();
    }

    @Override
    public PhoneResponseDTO getByNumber(String number) {
        var phone = repository.findByNumber(number)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Phone with number %s doesn't exist", number)
                        )
                );

        return PhoneResponseDTO
                .builder()
                .number(phone.getNumber())
                .ownerName(phone.getPerson() == null ?
                        null : phone.getPerson().getFirstName() + " " + phone.getPerson().getLastName())
                .build();
    }

    @Override
    public List<PhoneResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(x -> PhoneResponseDTO
                        .builder()
                        .number(x.getNumber())
                        .ownerName(x.getPerson() == null ?
                                null : x.getPerson().getFirstName() + " " + x.getPerson().getLastName())
                        .build())
                .collect(Collectors.toList());
    }
}
