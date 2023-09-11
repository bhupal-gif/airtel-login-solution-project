package com.dgliger.model.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DeleteResponse {
    private String id;
    private String status;
}
