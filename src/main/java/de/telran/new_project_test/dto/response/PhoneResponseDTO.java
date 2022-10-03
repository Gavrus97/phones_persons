package de.telran.new_project_test.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhoneResponseDTO {

    private String number;
    private String ownerName;
}
