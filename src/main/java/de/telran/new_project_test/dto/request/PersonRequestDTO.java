package de.telran.new_project_test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonRequestDTO {

    @NotBlank(message = "firstname cannot be blank")
    @Size(min = 0, max = 20, message = "name size must be 3-20 characters")
    private String firstName;

    @NotBlank(message = "lastname cannot be blank")
    @Size(min = 0, max = 20, message = "lastname size must be 3-20 characters")
    private String lastName;
}
