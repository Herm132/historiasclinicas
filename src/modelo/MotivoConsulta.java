package modelo;

/**
 *
 * @author Harold
 */
public class MotivoConsulta {
    
    
    private int idMConsulta;
    private String motivoConsulta;

    public MotivoConsulta(int idMConsulta, String motivoConsulta) {
        this.idMConsulta = idMConsulta;
        this.motivoConsulta = motivoConsulta;
    }

    public int getIdMConsulta() {
        return idMConsulta;
    }

    public void setIdMConsulta(int idMConsulta) {
        this.idMConsulta = idMConsulta;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
    
    
    
}
