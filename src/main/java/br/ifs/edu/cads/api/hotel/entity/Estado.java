package br.ifs.edu.cads.api.hotel.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uf", nullable = false, length = 255)
    private String uf;


    public Estado(long id, String uf) {
        this.id = id;
        this.uf = uf;
    }

    public long getIdEstado() {
        return id;
    }

    public void setId(long idEstado) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


}
