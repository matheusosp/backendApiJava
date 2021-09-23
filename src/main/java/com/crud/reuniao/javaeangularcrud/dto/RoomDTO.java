package com.crud.reuniao.javaeangularcrud.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String date;

    @NotNull
    private String startHour;

    @NotNull
    private String endHour;

}
