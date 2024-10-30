package com.example.demo.DTO;

public class MascotasDTO {
    private Long total;
    private Long activas;

    public MascotasDTO(Long total, Long activas) {
        this.total = total;
        this.activas = activas;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getActivas() {
        return activas;
    }

    public void setActivas(Long activas) {
        this.activas = activas;
    }
}
