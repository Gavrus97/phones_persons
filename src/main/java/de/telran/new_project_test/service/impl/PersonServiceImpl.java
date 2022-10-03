package de.telran.new_project_test.service.impl;

import de.telran.new_project_test.dto.request.PersonRequestDTO;
import de.telran.new_project_test.dto.response.PersonResponseDTO;
import de.telran.new_project_test.entity.Person;
import de.telran.new_project_test.entity.Phone;
import de.telran.new_project_test.repository.PersonRepository;
import de.telran.new_project_test.repository.PhoneRepository;
import de.telran.new_project_test.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PhoneRepository phoneRepository;

    @Override
    public void add(PersonRequestDTO dto) {
        if (repository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(dto.getFirstName(), dto.getLastName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("%s %s already exists", dto.getFirstName(), dto.getLastName())
            );
        }

        repository.save(Person
                .builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build()
        );
    }

    @Override
    public void delete(Integer id) {
        var person = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Person with id [%s] doesn't exist", id)
                        )
                );
        repository.delete(person);
    }

    @Override
    public PersonResponseDTO getById(Integer id) {
        var person = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Person with id %s doesn't exist", id)
                )
        );

        return PersonResponseDTO
                .builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .phoneNumbers(getPhonesOfPerson(id))
                .build();
    }

    @Override
    public PersonResponseDTO getByName(String firstName, String lastName) {
        var person = repository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Person [%s %s] doesn't exist", firstName, lastName)
                        )
                );

        return PersonResponseDTO
                .builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .phoneNumbers(getPhonesOfPerson(person.getId()))
                .build();
    }

    @Override
    public List<PersonResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(x -> PersonResponseDTO
                        .builder()
                        .firstName(x.getFirstName())
                        .lastName(x.getLastName())
                        .phoneNumbers(getPhonesOfPerson(x.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<String> getPhonesOfPerson(Integer id) {
        return phoneRepository.findAllByPersonId(id)
                .stream()
                .map(Phone::getNumber)
                .collect(Collectors.toList());
    }
}
