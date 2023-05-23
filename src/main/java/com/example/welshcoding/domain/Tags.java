package com.example.welshcoding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(
    name = "SEQ_TAGS_GENERATOR",
    sequenceName = "SEQ_TAGS",
    allocationSize = 1
)
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TAGS_GENERATOR")
    private Long tagsId;
    private String tagsName;
    private Integer tagsNum;

    @ManyToOne
    @JoinColumn(name = "BOARDID")
    private Board board;

    @ManyToOne
	@JoinColumn(name = "MEMBERID")
	private Member member;

    @Getter @Setter
    public static class Form {
        private long userEmail;
        private String userPwd;
        private String userName;
        private String userBirthyy;
        private String userBirthmm;
        private String userBirthdd;
        private String userGender;
        private String userPhone;
    }
}
