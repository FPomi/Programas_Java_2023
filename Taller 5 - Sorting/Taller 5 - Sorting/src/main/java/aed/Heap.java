package aed;

public class Heap {

    private Router[] heap;
    private int size;

    public Heap(int R) { // constructor
        heap = new Router[R];
        size = 0;
    }

    public Router desencolarRaiz (){

        Router raizOriginal = heap[0];

        intercambiar(0, size-1);
        size --;
        bajar(0);
        
        return raizOriginal;

    }

    public void encolar(Router nodo) {
        heap[size] = nodo;
        size++;
        subir();
    }

    public void subir() {
        
        int indice = size - 1;

        while (indice > 0 && heap[indice].compareTo(heap[posPadre(indice)]) > 0 ) {
            intercambiar(indice, posPadre(indice));
            indice = posPadre(indice);
        }

    }

    public void bajar(int indice) {

        while (!esHoja(indice)) {
            int indiceHijoIzquierdo = posHijoIzquierdo(indice);
            int indiceHijoDerecho = posHijoDerecho(indice);
            int indiceMayor = indiceHijoIzquierdo;

            if (posHijoDerecho(indice) < size && heap[indiceHijoDerecho].compareTo(heap[indiceHijoIzquierdo]) > 0) {
                indiceMayor = indiceHijoDerecho;
            }

            if (heap[indice].compareTo(heap[indiceMayor]) > 0) {
                break;
            } else {
                intercambiar(indice, indiceMayor);
            }

            indice = indiceMayor;
        }
    }

    public void intercambiar(int indice1, int indice2) {
        Router temp = this.heap[indice1];
        heap[indice1] = heap[indice2];
        heap[indice2] = temp;
    }

    public boolean esHoja(int indice) {return posHijoIzquierdo(indice) >= size;}

    public int posPadre(int indice) {return (indice - 1) / 2;}

    public int posHijoDerecho(int indice) {return 2*indice + 2;}

    public int posHijoIzquierdo(int indice) {return 2*indice + 1;}

    public boolean isEmpty() {return (size == 0);}
}
