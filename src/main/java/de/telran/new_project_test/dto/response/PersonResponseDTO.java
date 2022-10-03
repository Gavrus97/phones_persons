package de.telran.new_project_test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonResponseDTO {

    private String firstName;
    private String lastName;
    private List<String> phoneNumbers;
}
