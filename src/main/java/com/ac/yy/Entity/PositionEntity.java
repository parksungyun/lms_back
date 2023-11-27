package com.ac.yy.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "positions")
public class PositionEntity {
    @Id
    @Column(name = "position_id")
    private int positionId;

    @Column(name = "position_name")
    private String positionName;
}
