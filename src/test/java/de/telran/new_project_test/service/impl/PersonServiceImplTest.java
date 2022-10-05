package de.telran.new_project_test.service.impl;

import de.telran.new_project_test.dto.request.PersonRequestDTO;
import de.telran.new_project_test.repository.PersonRepository;
import de.telran.new_project_test.repository.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PersonServiceImpl service;

    @Nested
    @DisplayName("Tests of add method")
    class AddMethodTest {

        @Test
        public void shouldThrow409IfSuchPersonExist() {
            var firstName = "Vasja";
            var lastName = "Pupkin";
            var dto = PersonRequestDTO
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            var errorMessage = String.format("%s %s already exists", firstName, lastName);
            var expectedStatus = HttpStatus.CONFLICT;

            Mockito
                    .when(personRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName))
                    .thenReturn(true);
            var exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> service.add(dto)
            );

            Assertions.assertEquals(errorMessage, exception.getReason());
            Assertions.assertEquals(expectedStatus, exception.getStatus());
        }

    }

}
