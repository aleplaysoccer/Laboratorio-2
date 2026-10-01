import java.util.ArrayList;

public class Caso {
    private String nombre;
    private String codigo;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo, String detectiveResponsable) {
        if (nombre == null || nombre.trim().isEmpty() || codigo == null || codigo.trim().isEmpty()
                || detectiveResponsable == null || detectiveResponsable.trim().isEmpty()) {
            throw new IllegalArgumentException("Los datos del caso no pueden estar vacios.");
        }
        this.nombre = nombre.trim();
        this.codigo = codigo.trim();
        this.detectiveResponsable = detectiveResponsable.trim();
        ubicaciones = new Ubicacion[5];
        pistas = new ArrayList<Pista>();
    }

    public void registrarUbicacion(int posicion, Ubicacion ubicacion) {
        if (buscarUbicacion(posicion) != null) {
            throw new IllegalArgumentException("La posicion ya esta ocupada.");
        }
        if (ubicacion == null) {
            throw new IllegalArgumentException("La ubicacion no puede ser null.");
        }
        ubicaciones[posicion] = ubicacion;
    }

    public String consultarUbicaciones() {
        String resultado = "";
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                resultado += "Posicion: " + i + "\n" + ubicaciones[i].toString() + "\n\n";
            }
        }
        if (resultado.isEmpty()) {
            return "No hay ubicaciones registradas.";
        }
        return resultado;
    }

    public Ubicacion buscarUbicacion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IllegalArgumentException("La posicion debe estar entre 0 y 4.");
        }
        return ubicaciones[posicion];
    }

    public void modificarUbicacion(int posicion, int nivelRiesgo, String estado) {
        Ubicacion ubicacion = buscarUbicacion(posicion);
        if (ubicacion == null) {
            throw new IllegalArgumentException("La posicion esta vacia.");
        }
        ubicacion.actualizarDatos(nivelRiesgo, estado);
    }

    public void descartarUbicacion(int posicion) {
        if (buscarUbicacion(posicion) == null) {
            throw new IllegalArgumentException("La posicion esta vacia.");
        }
        ubicaciones[posicion] = null;
    }

    public void registrarPista(Pista pista) {
        if (pista == null) {
            throw new IllegalArgumentException("La pista no puede ser null.");
        }
        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una pista con ese codigo.");
        }
        pistas.add(pista);
    }

    public String consultarPistas() {
        if (pistas.isEmpty()) {
            return "No hay pistas registradas.";
        }
        String resultado = "";
        for (int i = 0; i < pistas.size(); i++) {
            resultado += pistas.get(i).toString() + "\n\n";
        }
        return resultado;
    }

    public Pista buscarPista(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio.");
        }
        for (int i = 0; i < pistas.size(); i++) {
            if (pistas.get(i).getCodigo().equals(codigo.trim())) {
                return pistas.get(i);
            }
        }
        return null;
    }

    public void modificarPista(String codigo, String descripcion, String tipoEvidencia,
            int nivelImportancia, int nivelConfiabilidad) {
        Pista pista = buscarPista(codigo);
        if (pista == null) {
            throw new IllegalArgumentException("No existe una pista con ese codigo.");
        }
        pista.actualizarDatos(descripcion, tipoEvidencia, nivelImportancia, nivelConfiabilidad);
    }

    public void eliminarPista(String codigo) {
        if (buscarPista(codigo) == null) {
            throw new IllegalArgumentException("No existe una pista con ese codigo.");
        }
        for (int i = 0; i < pistas.size(); i++) {
            if (pistas.get(i).getCodigo().equals(codigo.trim())) {
                pistas.remove(i);
                return;
            }
        }
    }

    public String generarReporte() {
        int cantidadUbicaciones = 0;
        Ubicacion mayorRiesgo = null;
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                cantidadUbicaciones++;
                if (mayorRiesgo == null || ubicaciones[i].getNivelRiesgo() > mayorRiesgo.getNivelRiesgo()) {
                    mayorRiesgo = ubicaciones[i];
                }
            }
        }

        String reporte = "Caso: " + nombre + "\nCodigo: " + codigo
                + "\nDetective: " + detectiveResponsable
                + "\nUbicaciones registradas: " + cantidadUbicaciones
                + "\nEspacios disponibles: " + (ubicaciones.length - cantidadUbicaciones);

        if (mayorRiesgo == null) {
            reporte += "\nNo hay ubicaciones para determinar el mayor riesgo.";
        } else {
            reporte += "\nUbicacion con mayor riesgo:\n" + mayorRiesgo.toString();
        }

        reporte += "\nPistas registradas: " + pistas.size();
        if (pistas.isEmpty()) {
            reporte += "\nNo hay pistas para determinar los niveles mayores ni el promedio.";
        } else {
            Pista mayorImportancia = pistas.get(0);
            Pista mayorConfiabilidad = pistas.get(0);
            double sumaImportancia = 0;

            for (int i = 0; i < pistas.size(); i++) {
                Pista pista = pistas.get(i);
                sumaImportancia += pista.getNivelImportancia();
                if (pista.getNivelImportancia() > mayorImportancia.getNivelImportancia()) {
                    mayorImportancia = pista;
                }
                if (pista.getNivelConfiabilidad() > mayorConfiabilidad.getNivelConfiabilidad()) {
                    mayorConfiabilidad = pista;
                }
            }
            double promedio = sumaImportancia / pistas.size();
            reporte += "\nPista con mayor importancia:\n" + mayorImportancia.toString();
            reporte += "\nPista con mayor confiabilidad:\n" + mayorConfiabilidad.toString();
            reporte += "\nPromedio de importancia: " + promedio;
        }
        return reporte;
    }
}
