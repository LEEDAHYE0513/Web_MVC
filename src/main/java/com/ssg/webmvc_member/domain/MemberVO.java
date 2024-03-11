package com.ssg.webmvc_member.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String id;
    private String pw;
    private String name;
    private String email;
    private LocalDate date;
}
