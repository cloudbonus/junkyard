package com.github.poi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Raman Haurylau
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    private String month;
    private String amount;
}
