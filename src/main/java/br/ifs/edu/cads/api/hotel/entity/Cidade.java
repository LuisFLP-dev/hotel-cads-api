package br.ifs.edu.cads.api.hotel.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;

    @Column(name = "nomeCidade", nullable = false, length = 255)
    private String nomeCidade;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "idEstado",nullable = false)
    private Estado estado;

    public Cidade(Long idCidade, String nomeCidade, Estado estado) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    public Cidade() {
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((idCidade == null) ? 0 : idCidade.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;
        Cidade other = (Cidade) obj;

        if (idCidade == null) {
            if (other.idCidade != null)
                return false;
        } else if (!idCidade.equals(other.idCidade))
            return false;
        return true;
    }
}
