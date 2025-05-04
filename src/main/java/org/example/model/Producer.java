package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Producer {

    @Id
    private String producer;

    private Integer previousWin;
    private Integer followingWin;

}
