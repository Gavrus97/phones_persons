package de.telran.new_project_test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhoneRequestDTO {

    @NotBlank(message = "number cannot be blank")
    @Size(min = 14, max = 14, message = "number must be 14 digits lang")
    @Pattern(regexp = "^(\\+49)\\d{3}\\d{8}", message = "number must be like +4911122222222")
    private String number;
}
