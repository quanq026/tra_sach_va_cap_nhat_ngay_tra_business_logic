package com.rikkei.course141.ss1;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BookUpdateStockDTO {
    @Min(0)
    private Integer stock;
}
