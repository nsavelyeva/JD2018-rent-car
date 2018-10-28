package com.savelyeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Embeddable
public class Audit {

    @Column(name = "created_date", nullable = false)
    private Instant created_date;

    @Column(name = "updated_date")
    private Instant updated_date;

    public Audit(Instant created_date) {
        this.created_date = created_date;
    }
}
