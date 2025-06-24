package modelo.entidad;
/**
 *
 * @author constantino.ramirez
 */
public class Producto {
   private int idProducto;
    private String nombre;
    private String codigo;
    private Categoria categoria;  // relación con la tabla categoria
    private double precio;
    private int stock;
    private int stockMinimo;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String codigo, Categoria categoria, double precio, int stock, int stockMinimo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigo = codigo;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", codigo=" + codigo + ", categoria=" + categoria + ", precio=" + precio + ", stock=" + stock + ", stockMinimo=" + stockMinimo + '}';
    }
    
}
