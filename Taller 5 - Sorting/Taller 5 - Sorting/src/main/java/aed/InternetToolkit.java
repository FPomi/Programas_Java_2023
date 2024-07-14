package aed;

public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {

        for (int fragmento = 1; fragmento < fragments.length ; fragmento++ ){

            int posicion = fragmento;
            int anterior = fragmento - 1;

            while (fragments[posicion].compareTo(fragments[anterior]) <= 0){
                
                Fragment aux = fragments[posicion];
                fragments[posicion] = fragments[anterior];
                fragments[anterior] = aux;
                
                if (anterior > 0){
                    posicion --;
                    anterior --;
                }
                    
            }

        }

        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        
        Heap heap = new Heap(routers.length);
        Router[] routersOrdenados = new Router[k];

        for(int pos = 0; pos < routers.length; pos++){
            
            if(routers[pos].getTrafico() > umbral){
                heap.encolar(routers[pos]);
            }
        }

        for (int contador = 0; contador < k && !heap.isEmpty(); contador ++){
            routersOrdenados[contador] = heap.desencolarRaiz();
        }

        return routersOrdenados;
    }

    public IPv4Address[] sortIPv4(String[] ipv4) {
        
        int n = ipv4.length;
        IPv4Address[] direcciones = new IPv4Address[n];

        for (int i = 0; i < n; i++) {
            direcciones[i] = new IPv4Address(ipv4[i]);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareIPv4(direcciones[j], direcciones[j + 1]) > 0) {
                    IPv4Address temp = direcciones[j];
                    direcciones[j] = direcciones[j + 1];
                    direcciones[j + 1] = temp;
                }
            }
        }

        return direcciones;
    }

    private static int compareIPv4(IPv4Address ip1, IPv4Address ip2) {
        for (int i = 0; i < 4; i++) {
            int resultado = Integer.compare(ip1.getOctet(i), ip2.getOctet(i));
            if (resultado != 0) {
                return resultado;
            }
        }
        return 0;
    }

}
