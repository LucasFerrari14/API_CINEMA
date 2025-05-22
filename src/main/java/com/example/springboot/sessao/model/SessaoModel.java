package com.example.springboot.sessao.model;

import com.example.springboot.filme.model.FilmeModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CEH_Sessao")
@Getter
@Setter
public class SessaoModel implements Serializable  {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "CD_SESSAO", updatable = false, nullable = false)
    private UUID cdSessao;

    @Column(name = "DE_SESSAO", length = 255)
    private Integer nuSessao;

    @Column(name = "HR_INICIO")
    private Time hrInicio;

    @Column(name = "HR_FIM")
    private Time hrFim;

    @ManyToOne()
    @JoinColumn(name = "CD_FILME")
    private FilmeModel cdFilme;
}

