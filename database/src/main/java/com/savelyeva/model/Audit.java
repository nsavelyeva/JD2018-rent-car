package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Embeddable
public class Audit {

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "updated_date")
    private Instant updatedDate;

    public Audit(Instant createdDate) {
        this.createdDate = createdDate;
    }
}
