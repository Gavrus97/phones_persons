package de.telran.new_project_test.service.impl;

import de.telran.new_project_test.dto.request.PhonePersonConnectionDTO;
import de.telran.new_project_test.repository.PersonRepository;
import de.telran.new_project_test.repository.PhoneRepository;
import de.telran.new_project_test.service.PhonePersonConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class PhonePersonConnectionServiceImpl implements PhonePersonConnectionService {

    private final PhoneRepository phoneRepository;
    private final PersonRepository personRepository;

    @Override
    public void addPhoneToPerson(PhonePersonConnectionDTO dto) {

        var phoneId = dto.getPhoneId();
        var personId = dto.getPersonId();

        var person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Person not found"
                        )
                );

        var phone = phoneRepository.findById(phoneId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Phone not found"
                        )
                );

        if (phone.getPerson() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone belongs to other Person"
            );
        }

        phone.setPerson(person);
        phoneRepository.save(phone);
    }
}
