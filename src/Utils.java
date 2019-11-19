/**
 * Clase que contiene metodos utiles
 *
 * @project IlernaPACDesaPSPServer
 * @author: jonan on 18/11/2019
 */
public class Utils {

    /**
     * Metodo que genera un numero random entre dos valores
     * @param min valor minimo
     * @param max vaslor maximo
     * @return Devuelve el valor random integer
     */
    public static int getRandom(int min, int max){
        return (int) Math.floor(Math.random() * (max - min + 1) + (min));
    }
}
