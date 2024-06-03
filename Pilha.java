import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Pilha {
    int topo;
    int tamanho;
    int total;

    Object vetor[];
    
    Pilha(int tam){
        topo = -1; 
        tamanho = tam;
        vetor = new Object[tam];
        total = 0;
    }

    public boolean vazia(){
        return topo == -1;
    }

    public boolean cheia(){
        return topo == tamanho -1;
    }

    public void empilhar(Object elem){
        if (!cheia()) {
            topo++;
            vetor[topo] = elem;
            total++;
        } else {
            JOptionPane.showMessageDialog(null, "PILHA CHEIA!");
        }
    }

    public Object desempilhar(){
        if (vazia()) {
            return "Pilha Vazia";
        } else {
            Object valorDesempilhado = vetor[topo];
            topo--;
            total--;
            return valorDesempilhado;
        }
    }

    public void desempilharPessoas(Object elem) {
        if (vazia()) {
            elem = "Pilha Vazia";
        } else {
            elem = vetor[topo];
            topo--;
            total--;
        }
    }

    public void exibirPessoas() {
        if (vazia()) {
            JOptionPane.showMessageDialog(null, "Pilha vazia!");
        } else {
            for (int i = topo; i >= 0; i--) {
                Object elem = vetor[i];
                if (elem instanceof Pedido) {
                    Pedido pedido = (Pedido) elem;
                    JOptionPane.showMessageDialog(null,
                            "Pedido #" + pedido.getNumero() + "\n" +
                            "Sabor: " + pedido.getSabor() + "\n" +
                            "Tamanho: " + pedido.getTamanho() + "\n" +
                            "Endereço: " + pedido.getEndereco() + "\n" +
                            "Distância: " + pedido.getDistancia() + " km\n");
                }
            }
        }
    }

        public ArrayList<String> exibirSabores() {
            ArrayList <String> cardapio = new ArrayList<>();
            if (!vazia()) {
                for (int i = topo; i >= 0; i--) {
                    String sabor = vetor[i].toString();
                    cardapio.add(sabor);
                }
                
            }
            return cardapio;
        }

        public String exibirListaSabores() {
            StringBuilder cardapio = new StringBuilder("Lista de sabores 🍕:\n");
            if (vazia()) {
                return "Sem sabores!";
            } else {
                for (int i = topo; i >= 0; i--) {
                    cardapio.append("\n-" + vetor[i]);
                }
                return cardapio.toString();
            }
        }

    public int tamanho() {
        return total;
    }
}
