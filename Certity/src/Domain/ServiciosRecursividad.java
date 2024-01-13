package Domain;

import java.util.List;

public class ServiciosRecursividad {
	
    public static int maximoServiciosConPresupuesto(List<Anuncio> anuncios, float presupuesto) {
        return calcularMaximoServicios(anuncios, 0, presupuesto, 0);
    }

    private static int calcularMaximoServicios(List<Anuncio> anuncios, int indice, float presupuestoRestante, int contador) {
    	
    	if (indice == anuncios.size()) {
            return contador;
        }

        Anuncio anuncioActual = anuncios.get(indice);

        // caso en que se contrata el servicio actual, si el presupuesto lo permite
        int contarConServicioActual = 0;
        if (presupuestoRestante >= anuncioActual.getPrecio()) {
            contarConServicioActual = calcularMaximoServicios(anuncios, indice + 1, presupuestoRestante - anuncioActual.getPrecio(), contador + 1);
        }

        // caso si no se contrata 
        int contarSinServicioActual = calcularMaximoServicios(anuncios, indice + 1, presupuestoRestante, contador);

        // devuelve mayor
        return Math.max(contarConServicioActual, contarSinServicioActual);
    }

}
