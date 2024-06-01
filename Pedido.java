public class Pedido {
    private static int contador = 0;
    private int numero;
    private String sabor;
    private String tamanho;
    private String endereco;
    private double distancia;

    public Pedido(String sabor, String tamanho, String endereco, double distancia) {
        this.numero = ++contador;
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.endereco = endereco;
        this.distancia = distancia;
    }

    public static int getContador() {
        return contador;
    }
    public double getDistancia() {
        return distancia;
    }
    public String getEndereco() {
        return endereco;
    }
    public int getNumero() {
        return numero;
    }
    public String getSabor() {
        return sabor;
    }
    public String getTamanho() {
        return tamanho;
    }
    public static void setContador(int contador) {
        Pedido.contador = contador;
    }
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String toString() {
        return "Pedido #" + numero +
               "\nSabor: " + sabor +
               "\nTamanho: " + tamanho +
               "\nEndereço: " + endereco +
               "\nDistância: " + distancia + " km\n\n";
    }
    
}
