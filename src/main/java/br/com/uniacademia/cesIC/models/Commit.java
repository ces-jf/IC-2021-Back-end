package br.com.uniacademia.cesIC.models;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commit implements Serializable {

    private static final long serialVersionUID = 2975939668465056463L;

    private LocalDate date;
}