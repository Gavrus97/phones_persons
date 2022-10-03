package de.telran.new_project_test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhonePersonConnectionDTO {

    @Positive(message = "personId cannot be negative or zero")
    private Integer personId;

    @Positive(message = "phoneId cannot be negative or zero")
    private Integer phoneId;
}
