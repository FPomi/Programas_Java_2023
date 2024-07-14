package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    
    // Agregar atributos privados del Conjunto
    int cardinal;
    Nodo primer_Nodo;
    List<T> valores;

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo padre;
        Nodo izq;
        Nodo der;

        Nodo (T v){
            valor = v;
            padre = null;
            izq = null;
            der = null;
        }

        Nodo (T v, Nodo p){
            valor = v;
            padre = p;
            izq = null;
            der = null;
        }

    }

    //-----------------------------------------------------------------------------------------------------------------//

    public ABB() {
        cardinal = 0;
        primer_Nodo = null;
        valores = new ArrayList<>();
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public int cardinal() {
        return cardinal;
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public T minimo(){
        
        Nodo nodo_Actual = primer_Nodo;

        while (nodo_Actual.izq != null){
            nodo_Actual = nodo_Actual.izq;
        }

        return nodo_Actual.valor;

    }

    //-----------------------------------------------------------------------------------------------------------------//

    public T maximo(){
        Nodo nodo_Actual = primer_Nodo;

        while (nodo_Actual.der != null){
            nodo_Actual = nodo_Actual.der;
        }

        return nodo_Actual.valor;
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public void insertar(T elem){
        

        if (cardinal == 0) {
        
            primer_Nodo = new Nodo(elem);
            cardinal ++;

        }else if (pertenece(elem) == false){

            Nodo nodo_Actual = primer_Nodo;

            while (((elem.compareTo(nodo_Actual.valor) < 0) && (nodo_Actual.izq != null)) || ((elem.compareTo(nodo_Actual.valor) > 0) && (nodo_Actual.der != null))){
                
                if (elem.compareTo(nodo_Actual.valor) < 0){
                    nodo_Actual = nodo_Actual.izq;
                }else if (elem.compareTo(nodo_Actual.valor) > 0){
                    nodo_Actual = nodo_Actual.der;
                }

            }

            if (elem.compareTo(nodo_Actual.valor) < 0){
                nodo_Actual.izq = new Nodo(elem, nodo_Actual);
            }else if (elem.compareTo(nodo_Actual.valor) > 0) {
                nodo_Actual.der = new Nodo(elem, nodo_Actual);
            }
            
            valores.add(elem);
            cardinal ++;
        }

    }

    //-----------------------------------------------------------------------------------------------------------------//

    public boolean pertenece(T elem){
        
        boolean respuesta = true;

        if (cardinal == 0) {
            
            respuesta = false;
        
        }else{

            Nodo nodo_Actual = primer_Nodo;

            while (( elem.compareTo(nodo_Actual.valor) != 0) && (respuesta == true)){

                if (elem.compareTo(nodo_Actual.valor) < 0) {
                
                    if (nodo_Actual.izq == null){
                        respuesta = false;
                    }else{
                        nodo_Actual = nodo_Actual.izq;
                    }

                }else{
                
                    if (nodo_Actual.der == null){
                        respuesta = false;
                    }else{
                        nodo_Actual = nodo_Actual.der;
                    }
                }

            }
        }

        return respuesta;
    
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public void eliminar(T elem){

        if (pertenece(elem)){
            
            if(cardinal == 1){
                
                primer_Nodo = null;
                cardinal --;

            }else{
                
                Nodo nodo_Actual = primer_Nodo;

                while (elem.compareTo(nodo_Actual.valor) != 0){
                
                    if (elem.compareTo(nodo_Actual.valor) < 0){
                        nodo_Actual = nodo_Actual.izq;
                    }else if (elem.compareTo(nodo_Actual.valor) > 0){
                        nodo_Actual = nodo_Actual.der;
                    }

                }

                if ((nodo_Actual.izq == null) && (nodo_Actual.der == null)){ // SIN HIJOS

                    if (nodo_Actual.padre.izq == nodo_Actual) {
                        nodo_Actual.padre.izq = null;
                    }else{
                        nodo_Actual.padre.der = null;
                    }

                    valores.remove(elem);
                    cardinal --;
                
                }else if ((nodo_Actual.izq != null) && (nodo_Actual.der != null)){ // CON 2 HIJOS

                    nodo_Actual = nodo_Actual.izq;

                    while (nodo_Actual.der != null){
                        nodo_Actual = nodo_Actual.der;
                    }

                    T valor_Reemplazo = nodo_Actual.valor;

                    eliminar(valor_Reemplazo);

                    nodo_Actual = primer_Nodo;

                    while (elem.compareTo(nodo_Actual.valor) != 0){
                
                        if (elem.compareTo(nodo_Actual.valor) < 0){
                            nodo_Actual = nodo_Actual.izq;
                        }else if (elem.compareTo(nodo_Actual.valor) > 0){
                            nodo_Actual = nodo_Actual.der;
                        }

                    }

                    nodo_Actual.valor = valor_Reemplazo;

                }else{

                    if (nodo_Actual == primer_Nodo){  // CON UN HIJO
                        
                        if (nodo_Actual.izq == null){
                        
                            primer_Nodo = nodo_Actual.der;
                            nodo_Actual.der.padre = null; 
                        
                        }else{

                            primer_Nodo = nodo_Actual.izq;
                            nodo_Actual.izq.padre = null;
                        
                        }

                    }else{
                        
                        if (nodo_Actual.padre.izq == nodo_Actual) {
                        
                            if (nodo_Actual.der == null){
                                nodo_Actual.padre.izq = nodo_Actual.izq;
                                nodo_Actual.izq.padre = nodo_Actual.padre;
                            }else{
                                nodo_Actual.padre.izq = nodo_Actual.der;
                                nodo_Actual.der.padre = nodo_Actual.padre;
                            }
                        
                        }else{

                            if (nodo_Actual.der == null){
                                nodo_Actual.padre.der = nodo_Actual.izq;
                                nodo_Actual.izq.padre = nodo_Actual.padre;
                            }else{
                                nodo_Actual.padre.der = nodo_Actual.der;
                                nodo_Actual.der.padre = nodo_Actual.padre;
                            }
                    
                        }
                    }

                    valores.remove(elem);
                    cardinal --;

                }

            }

        }
        
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public String toString(){
        
        StringBuilder resultado = new StringBuilder();
        obtenerValoresOrdenadosAux(primer_Nodo, resultado);
        return "{" + resultado.toString() + "}";
        
    }

    private void obtenerValoresOrdenadosAux(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            
            obtenerValoresOrdenadosAux(nodo.izq, resultado);
            resultado.append(nodo.valor);
            
            if (nodo.valor != maximo()) {
                resultado.append(",");
            }

            obtenerValoresOrdenadosAux(nodo.der, resultado);
        
        }
    }

    //-----------------------------------------------------------------------------------------------------------------//

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            
            boolean respuesta = true;
            
            if (_actual.valor == maximo()){
                respuesta = false;
            }

            return respuesta;

        }
    
        public T siguiente() {

            if (_actual == null) {
                
                _actual = primer_Nodo;
                
                while (_actual.valor != minimo()){
                    _actual = _actual.izq;
                }

            }else{

                if (haySiguiente()) {

                    if (_actual.der == null){
                        
                       
                        while (_actual.padre.der == _actual) {
                            _actual = _actual.padre;
                        }

                        _actual = _actual.padre;
                            
                           
                    }else{

                        _actual = _actual.der;

                        while(_actual.izq != null){
                            _actual = _actual.izq;
                        }


                    }

                }

            }

            return _actual.valor;

        }
    }

    //-----------------------------------------------------------------------------------------------------------------//

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}

