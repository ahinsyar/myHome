package com.myhome.server.domain;

import com.myhome.server.domain.enumvalue.Repeat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_idx")
    private Long idx;

    private String name;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User user;

    private Repeat repeat;

    private Integer repeatNumber;

    @Builder
}
