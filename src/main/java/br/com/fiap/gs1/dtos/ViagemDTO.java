package br.com.fiap.gs1.dtos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Valid
@Data
public class ViagemDTO {

        private Long id;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
        private LocalDate data_decolagem;

        @Positive
        private int qtd_dias;

        @Positive
        private int qtd_assentos;

        @Pattern(regexp = "([a-z]{4}-{1}[0-9]{4})")
        private String modelo_nave;

        @NotNull
        @NotBlank
        private String primeiro_comandante;

        @Pattern(regexp = "([a-zA-Z]{3}-{1}[0-9]{4}-[a-zA-Z]{1}[0-9]{1}[a-zA-Z]{1})")
        private String matricula_prim_comandante;

        @NotBlank
        @NotNull
        private String segundo_comandante;

        @Pattern(regexp = "([a-zA-Z]{3}-{1}[0-9]{4}-[a-zA-Z]{1}[0-9]{1}[a-zA-Z]{1})")
        private String matricula_segn_comandante;

        private LocalDate data_retorno;


        public boolean multiplo3() {return this.getQtd_dias() % 3 == 0;}
        public void calculaDia(){
                this.data_retorno = this.data_decolagem.plusDays(this.qtd_dias);
        }
}



