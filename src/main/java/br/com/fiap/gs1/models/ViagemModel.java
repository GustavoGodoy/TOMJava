package br.com.fiap.gs1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "viagem")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="viagemsequence", sequenceName="viagemsequence")
    @Column(nullable = false)
    private Long id;

    @Column(name = "data_decolagem")
    private LocalDate data_decolagem;

    @Column(name = "qtd_dias")
    private int qtd_dias;

    @Column(name = "qtd_assentos")
    private int qtd_assentos;

    @Column(name = "modelo_nave")
    private String modelo_nave;

    @Column(name = "primeiro_comandante")
    private String primeiro_comandante;

    @Column(name = "matricula_prim_comandante")
    private String matricula_prim_comandante;

    @Column(name = "segundo_comandante")
    private String segundo_comandante;

    @Column(name = "matricula_seg_comandante")
    private String matricula_segn_comandante;

}
