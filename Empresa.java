package app.oneapp.eddy.myapp.com.oneapp;


public class Empresa {

    private String nombre;
    private String mechardising;
    //private String emisora;
    private Integer codigo;
    private int foto;

    public Empresa(String nombre, String mechardising, int codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.mechardising = mechardising;
       // this.emisora = emisora;
    }

    public Empresa(){

    }

    //Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMechardising() {
        return mechardising;
    }

   // public String getEmisora() {
   //     return emisora;
   // }

    public int getFoto() {
       return foto;
    }

    //Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMechardising(String mechardising) {
        this.mechardising = mechardising;
    }

    //public void setEmisora(String emisora) {
   //     this.emisora = emisora;
    //}

    public void setFoto(int foto) {
        this.foto = foto;
    }

}
