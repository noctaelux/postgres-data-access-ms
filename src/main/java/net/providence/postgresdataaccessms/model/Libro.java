package net.providence.postgresdataaccessms.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Libro {
    private String nombre;
    private String autor;
    private String isbn;
}
