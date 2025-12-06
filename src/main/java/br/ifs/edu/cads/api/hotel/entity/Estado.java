package br.ifs.edu.cads.api.hotel.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;


    public Estado(String uf) {
        this.uf = uf;
    }

    public Estado(){}

    public long getidEstado() {
        return idEstado;
    }

    public void setidEstado(long idEstadoEstado) {
        this.idEstado = idEstado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((idEstado == null) ? 0 : idEstado.hashCode());
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
        Estado other = (Estado) obj;

        if (idEstado == null) {
            if (other.idEstado != null)
                return false;
        } else if (!idEstado.equals(other.idEstado))
            return false;
        return true;
    }
}
