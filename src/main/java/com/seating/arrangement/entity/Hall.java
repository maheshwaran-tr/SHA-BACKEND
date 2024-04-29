package com.seating.arrangement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hall_number")
    private String hallNumber;

    @Column(name = "block_id")
    private Integer blockId;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "`rows`")
    private Integer rows;

    @Column(name = "cols")
    private Integer columns;

    @Column(name = "seat_per_bench")
    private Integer seatPerBench;

    @Column(name = "extra_seats")
    private Integer extraSeats;

    @Transient
    private Seat[][] hallMatrix;

    @PostLoad
    public void initHallMatrix() {
        this.hallMatrix = generateStructure();
    }

    public Seat[][] generateStructure() {
        char colName = 'A';
        Seat[][] matrix = new Seat[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Seat seat = new Seat();
                seat.seatName = (char) (colName + j) + "" + (i + 1);
                matrix[i][j] = seat; // Store the seat in the matrix
            }
        }
        return matrix;
    }
}
