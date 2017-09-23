package app.oneapp.eddy.myapp.com.oneapp;


public class Adaptador {

    public static final String tabla_empresa = "Empresa";
    public static final String nombre = "nombre";
    public static final String mechardising = "mechardising";
    public static final String codigoEmpresa = "codigoEmpresa";

    public static final String tabla_empresa_2 = "Partido";
    public static final String codigoPartido = "codigoPartido";
    public static final String equipoA = "equipoA";
    public static final String equipoB = "equipoB";
    public static final String evento = "evento";
    public static final String fecha = "fecha";
    public static final String horario = "horario";
    public static final String emisora = "emisora";

    public static final String tabla_empresa_3 = "Transmision";
    public static final String codigoTransmision = "codigoTransmision";
    public static final String horaExacta = "horaExacta";

    public static final String tabla_empresa_4 = "HorasRegistradas";


    public static final String sqlCreateEmpresa = "CREATE TABLE " + tabla_empresa + " ("
            + nombre + " TEXT, " + mechardising + " TEXT, "
            + codigoEmpresa + " INTEGER PRIMARY KEY NOT NULL)";


    public static final String sqlCreatePartido = "CREATE TABLE " + tabla_empresa_2 + " ("
            + equipoA + " TEXT, " + equipoB + " TEXT, " + evento + " TEXT, " + fecha + " TEXT, " + horario + " TEXT, " + emisora + " TEXT, "
            + codigoPartido + " INTEGER PRIMARY KEY NOT NULL)";

    public static final String getSqlCreateTransmision = "CREATE TABLE " + tabla_empresa_3 + " ("
            + codigoTransmision + " INTEGER NOT NULL, " + codigoPartido + " INTEGER NOT NULL, " + codigoEmpresa + " INTEGER NOT NULL, "
            + "PRIMARY KEY(codigoPartido, codigoEmpresa), "
            + "FOREIGN KEY(codigoPartido) REFERENCES Partido(codigoPartido), "
            + "FOREIGN KEY(codigoEmpresa) REFERENCES Empresa(codigoEmpresa))";

    public static final String getSqlCreateHorasRegistradas = "CREATE TABLE " + tabla_empresa_4 + " ("
            + horaExacta + " TEXT NOT NULL, "
            + codigoTransmision + " INTEGER PRIMARY KEY NOT NULL, "
            + "FOREIGN KEY(codigoTransmision) REFERENCES Transmision(codigoTransmision))";

    public static String insertarEmpresa(String pro, String mercha, String cod) {

        String insert = "INSERT INTO " + Adaptador.tabla_empresa + " (" + Adaptador.nombre + ", "
                + Adaptador.mechardising + ", " + Adaptador.codigoEmpresa + ") VALUES ( '"
                + pro + "', '" + mercha + "', '" + cod + "')";

        return insert;
    }
}
