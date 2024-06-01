import javax.swing.JOptionPane;

public class FilaEntregas {
    private int tamanho;
    private int inicio;
    private int fim;
    private int total;
    private Pedido[] vetor;

    public FilaEntregas(int tam) {
        inicio = 0;
        fim = 0;
        total = 0;
        vetor = new Pedido[tam];
        tamanho = tam;
    }

    public boolean vazia() {
        return total == 0;
    }

    public boolean cheia() {
        return total == tamanho;
    }

    public void enfileirar(Pedido pedido) {
        if (!cheia()) {
            vetor[fim] = pedido;
            fim = (fim + 1) % tamanho;
            total++;
        } else {
            JOptionPane.showMessageDialog(null, "Fila Cheia!");
        }
    }

    public Pedido desenfileirar() {
        Pedido pedido = null;
        if (!vazia()) {
            pedido = vetor[inicio];
            inicio = (inicio + 1) % tamanho;
            total--;
        }
        return pedido;
    }

    public void exibirFila() {
        if (vazia()) {
            JOptionPane.showMessageDialog(null, "Fila vazia!");
        } else {
            StringBuilder lista = new StringBuilder("Fila de entregas:\n");
            for (int i = 0; i < total; i++) {
                int index = (inicio + i) % tamanho;
                lista.append(vetor[index]).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }

    public void exibirPessoas() {
        if (vazia()) {
            JOptionPane.showMessageDialog(null, "Fila vazia!");
        } else {
            for (int i = inicio; i < fim; i++) {
                Pedido pedido = vetor[i];
                JOptionPane.showMessageDialog(null,
                        "Sabor: " + pedido.getSabor() + "\n" +
                        "Tamanho: " + pedido.getTamanho() + "\n" +
                        "Endereço: " + pedido.getEndereco() + "\n" +
                        "Distância: " + pedido.getDistancia() + " km\n");
            }
        }
    }
}
