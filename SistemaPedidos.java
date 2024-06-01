import javax.swing.JOptionPane;

public class SistemaPedidos {
    private FilaEntregas filaPedidos;
    private Pilha pilhaEntregas;
    private ListaEncadeada historicoPedidos;

    private int entregasRealizadas = 0;

    public SistemaPedidos(int tamanhoFila, int tamanhoPilha) {
        filaPedidos = new FilaEntregas(tamanhoFila);
        pilhaEntregas = new Pilha(tamanhoPilha);
        historicoPedidos = new ListaEncadeada();
    }

    public void adicionarPedido(String sabor, String tamanho, String endereco, double distancia) {
        Pedido novoPedido = new Pedido(sabor, tamanho, endereco, distancia);
        filaPedidos.enfileirar(novoPedido);
        historicoPedidos.insereNo_fim(new IntNoSimples(novoPedido));
        JOptionPane.showMessageDialog(null, "Pedido adicionado com sucesso!");
    }

    public void cancelarPedido(int numeroPedido) {
        IntNoSimples noPedido = historicoPedidos.buscaNo(numeroPedido);
        if (noPedido != null) {
            historicoPedidos.excluiNo(numeroPedido);
            JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
        }
    }

    public void listarTodosPedidos() {
        historicoPedidos.exibeLista();
    }

    public void incluirPedidosPreparo() {
        for (int i = 0; i < 3; i++) {
            Pedido pedido = filaPedidos.desenfileirar();
            if (pedido != null) {
                pilhaEntregas.empilhar(pedido);
                JOptionPane.showMessageDialog(null, "Pedido #" + pedido.getNumero() + " incluído para preparo.");
            } else {
                JOptionPane.showMessageDialog(null, "Não há mais pedidos na fila para preparo.");
                break;
            }
        }
    }

    public void incluirPedidosEntrega() {
        int tamanhoFila = pilhaEntregas.tamanho();
        for (int i = 0; i < tamanhoFila; i++) {
            Pedido pedido = (Pedido) pilhaEntregas.desempilhar();
            pilhaEntregas.empilhar(pedido); 
        }


        for (int i = 0; i < 3; i++) {
            Pedido pedido = filaPedidos.desenfileirar();
            if (pedido != null) {
                pilhaEntregas.empilhar(pedido);
            }
        }
    }

    public void gerarRelatorioEntrega() {
        StringBuilder relatorio = new StringBuilder("Pedidos para entrega:\n");

        if (pilhaEntregas.vazia()) {
            relatorio.append("Não há nenhuma entrega pendente.\n");
        } else {
            int tamanhoPilha = pilhaEntregas.tamanho();
            for (int i = 0; i < tamanhoPilha; i++) {
                Pedido pedido = (Pedido) pilhaEntregas.desempilhar();
                relatorio.append("Pedido #").append(pedido.getNumero()).append("\n");
                relatorio.append("Endereço: ").append(pedido.getEndereco()).append("\n");
                relatorio.append("Distância: ").append(pedido.getDistancia()).append(" km\n\n");
            }
        }

        JOptionPane.showMessageDialog(null, relatorio.toString());
    }

    public void informarEntregaRealizada() {
        if (pilhaEntregas.vazia()) {
            JOptionPane.showMessageDialog(null, "Não há entregas pendentes para realizar.");
        } else {
            entregasRealizadas++;
            if (entregasRealizadas == 2) {
                for (int i = 0; i < 3; i++) {
                    Pedido pedido = (Pedido) pilhaEntregas.desempilhar();
                    historicoPedidos.excluiNo(pedido.getNumero());
                }
                entregasRealizadas = 0;
            }
            JOptionPane.showMessageDialog(null, "Entrega realizada com sucesso!");
        }
    }
}
