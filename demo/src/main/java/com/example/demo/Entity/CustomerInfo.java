package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name = "CUSTOMER_INFO")
public class CustomerInfo {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "CUSTOMER_INFO_SEQ")
    @SequenceGenerator(name = "CUSTOMER_INFO_SEQ", sequenceName = "CUSTOMER_INFO_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CIF")
    private String cif;

    @Column(name = "CARD_ID")
    private String cardId;

    @Column(name = "CARD_TYPE")
    private Long cardType;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "BIRTH_DAY")
    private String birthDay;

    @Column(name = "BRANCH_CIF")
    private String branchCif;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "DATE_REGISTER")
    private Date dateRegister;

    @Column(name = "ADDRESS_REGISTER")
    private String addressRegister;

    @Column(name = "FLAG_JOB")
    private Long flagJob;

    @Column(name = "CONFIRM_STATUS")
    private Boolean confirmStatus;

}