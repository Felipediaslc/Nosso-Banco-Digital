package zup.bootcamp.banco.digital.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="nome", nullable = false, length = 50)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;


    @Column(name="sobrenome", nullable = false, length = 200)
    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    private String sobrenome;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    @Email(message = "{campo.email.invalido}")
    private String email;

    @Column(name="cpf", nullable = false, length = 11, unique = true)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;
    


}
