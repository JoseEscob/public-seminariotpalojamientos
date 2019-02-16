package views;

public class PaginacionView {
	private int cantidadElementos;
	private int paginaActual;
	private int paginas;
	private int totalElementos;
	
	public PaginacionView() {
		this.cantidadElementos = 5;
		this.paginaActual = 1;
		this.totalElementos = 1;
	}

	public int getCantidadElementos() {
		return cantidadElementos;
	}

	public void setCantidadElementos(int cantidadElementos) {
		this.cantidadElementos = cantidadElementos;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getTotalElementos() {
		return totalElementos;
	}

	public void setTotalElementos(int totalElementos) {
		this.totalElementos = totalElementos;
		this.calcularCantPaginas();
	}
	public void calcularCantPaginas() {
		this.paginas = ((int)Math.ceil(this.totalElementos/this.cantidadElementos)+1);
	}
	public static PaginacionView crearPaginacion(String parameter, int countElements){
		PaginacionView pagination = new PaginacionView();
		pagination.setPaginaActual(parameter != null? Integer.parseInt(parameter) : 1);
		pagination.setTotalElementos(countElements);
		return pagination;
	}
}
