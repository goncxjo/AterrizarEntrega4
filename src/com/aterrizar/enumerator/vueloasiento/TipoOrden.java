package com.aterrizar.enumerator.vueloasiento;

import com.aterrizar.model.vueloasiento.VueloAsiento;

public enum TipoOrden {
    superOferta
    , precioAscendente {

        @Override
        public int sort(VueloAsiento asientoIzquierda, VueloAsiento asientoDerecha) {
            return super.ordenarPorTipoOPorSuperOferta(
                    super.sort(asientoIzquierda, asientoDerecha)
                    , asientoIzquierda.getAsiento().getPrecio()
                    , asientoDerecha.getAsiento().getPrecio()
            );
        }
    }
    , precioDescendente {
        @Override
        public int sort(VueloAsiento asientoIzquierda, VueloAsiento asientoDerecha) {
            return super.ordenarPorTipoOPorSuperOferta(
                    super.sort(asientoIzquierda, asientoDerecha)
                    , asientoDerecha.getAsiento().getPrecio()
                    , asientoIzquierda.getAsiento().getPrecio()
            );
        }
    }
    , tiempoVuelo {
    	@Override
    	public int sort(VueloAsiento asientoIzquierda, VueloAsiento asientoDerecha) {
            return super.ordenarPorTipoOPorSuperOferta(
                    super.sort(asientoIzquierda, asientoDerecha)
                    , asientoIzquierda.getVuelo().getTiempoVuelo()
                    , asientoDerecha.getVuelo().getTiempoVuelo()
            );
    	}
    }
    , popularidad {
    	@Override
    	public int sort(VueloAsiento asientoIzquierda, VueloAsiento asientoDerecha) {
            return super.ordenarPorTipoOPorSuperOferta(
                    super.sort(asientoIzquierda, asientoDerecha)
                    , asientoDerecha.getVuelo().getPopularidad()
                    , asientoIzquierda.getVuelo().getPopularidad()
            );
    	}
    };

    public int sort(VueloAsiento asientoIzq, VueloAsiento asientoDer) {
        if(asientoIzq.getAsiento().esSuperOferta()) {
            return -1;
        } else if (asientoDer.getAsiento().esSuperOferta()) {
            return 1;
        } else {
            return 0;
        }
    }

    private int ordenarPorTipoOPorSuperOferta(int resultadoComparacionSuperOferta, Double a, Double b) {
        if(resultadoComparacionSuperOferta == 0) {
            return a.compareTo(b);
        }
        return resultadoComparacionSuperOferta;
    }

	private int ordenarPorTipoOPorSuperOferta(int resultadoComparacionSuperOferta, Integer a, Integer b) {
        if(resultadoComparacionSuperOferta == 0) {
            return a.compareTo(b);
        }
        return resultadoComparacionSuperOferta;
    }
	
	
}
