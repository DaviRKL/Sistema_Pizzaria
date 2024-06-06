import javax.swing.JOptionPane;

public class SistemaPedidos {
    //Define como atributos a fila de pedidos, a fila de pedidos em preparo, pilha de entrega e o historico de pedidos usando as classes fila, pilha e lista encadeada
    private FilaEntregas filaPedidos;
    private FilaEntregas filaPedidosEmPreparo;
    private Pilha pilhaEntregas;
    private ListaEncadeada historicoPedidos;

    //Armazena o numero de entregas feitas
    private int entregasRealizadas = 0;

    //Metodo construtor do Sistema Pedidos,(quando criar um novo SistemaPedidos ele criará a fila de pedidos, pilha de entrega e o historico de pedidos)
    public SistemaPedidos(int tamanhoFila, int tamanhoPilha) {
        filaPedidos = new FilaEntregas(tamanhoFila);
        filaPedidosEmPreparo = new FilaEntregas(tamanhoFila);
        pilhaEntregas = new Pilha(tamanhoPilha);
        historicoPedidos = new ListaEncadeada();
    }

    public void adicionarPedido(String sabor, String tamanho, String endereco, double distancia) {
        //cria um novo pedido usando os valores recebidos
        Pedido novoPedido = new Pedido(sabor, tamanho, endereco, distancia);

        //coloca o pedido na fila de pedidos
        filaPedidos.enfileirar(novoPedido);

        //Insere o pedido no historico de pedidos
        historicoPedidos.insereNo_fim(new IntNoSimples(novoPedido));
        JOptionPane.showMessageDialog(null, "Pedido adicionado com sucesso!");
    }

    public void cancelarPedido(int numeroPedido) {
        //tenta encontrar o pedido no historico, pelo numero fornecido
        IntNoSimples noPedido = historicoPedidos.buscaNo(numeroPedido);
        if (noPedido != null) {
            //caso encontre o pedido e excluido do historico
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
        //tenta incluir ate 3 pedidos na pilha de entrega
        for (int i = 0; i < 3; i++) {
            //Tenta retirar um pedido da fila de pedidos
            Pedido pedido = filaPedidos.desenfileirar();
            if (pedido != null) {
                //caso encontre um pedido ele insere o pedido na fila de preparo
                filaPedidosEmPreparo.enfileirar(pedido);
                JOptionPane.showMessageDialog(null, "Pedido #" + pedido.getNumero() + " incluído para preparo.");
            } else {
                JOptionPane.showMessageDialog(null, "Não há mais pedidos na fila para preparo.");
                break;
            }
        }
    }

    public void incluirPedidosEntrega() {
        //Cria duas listas, uma de pedidos e uma de distancias
        Pedido[] pedidos = new Pedido[3];
        double[] distancias = new double[3];
        int pedidosCount = 0;

        //Tenta pegar ate 3 pedidos da fila de pedidos em preparo
        for (int i = 0; i < 3; i++) {
            //Tenta pegar um pedido da fila de pedidos em preparo
            Pedido pedido = filaPedidosEmPreparo.desenfileirar();

            //caso encontre, armazena na lista de pedidos o pedido e a distancia na lista de distancias
            if (pedido != null) {
                pedidos[i] = pedido;
                distancias[i] = pedido.getDistancia();
                pedidosCount++;
            } else {
                break;
            }
        }
       //Ordena os pedidos com os mais proximos no topo
        for (int i = 0; i < pedidosCount - 1; i++) { //Loop para percorer do primeiro ate o penultimo pedido
            //For para comparar o pedido atual com os proximos
            for (int j = i + 1; j < pedidosCount; j++) {
                //caso a distancia do pedido atual seja menor que a do proximo, executa as trocas
                if (distancias[i] < distancias[j]) {
                    //armazena a distancia do pedido atual na variavel temporaria
                    double tempDistancia = distancias[i];
                    //Substitui a distancia do pedido atual pela do proximo pedido
                    distancias[i] = distancias[j];
                    //Substitui a distancia do proximo pedido pela distancia do pedido atual
                    distancias[j] = tempDistancia;

                    //armazena o pedido atual na variavel temporaria
                    Pedido temPedido = pedidos[i];
                    //Substitui o pedido atual pelo proximo pedido
                    pedidos[i] = pedidos[j];
                    //Substitui o proximo pedido pelo pedido atual
                    pedidos[j] = temPedido;
                }

            }
        }
        //Adiciona os pedidos ordenados na pilha de entregas
        for(int i =0; i<pedidosCount; i++){
            pilhaEntregas.empilhar(pedidos[i]);
        }
        JOptionPane.showMessageDialog(null, "Pedidos incluidos com sucesso!");
    }



    public void gerarRelatorioEntrega() {
        //Cria o texto incial do relatorio
        StringBuilder relatorio = new StringBuilder("Pedidos para entrega:\n");
        //Caso não tenha pedidos para entrega exibe esta mensagem
        if (pilhaEntregas.vazia()) {
            relatorio.append("Não há nenhuma entrega pendente.\n");
        } else {
            //Verifica quantos pedidos ha para entrega
            int tamanhoPilha = pilhaEntregas.tamanho();
            //Adiciona a informacao de todos os pedidos ao relatorio
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
        //verifica se ha pedidos na pilha de entregas
        if (pilhaEntregas.vazia()) {
            JOptionPane.showMessageDialog(null, "Não há entregas pendentes para realizar.");
        } else {
            entregasRealizadas++;
            // Verifica se foram realizadas duas entregas
            if (entregasRealizadas == 2) {
                 // Executa a remoção de até três pedidos da pilha de entregas
                for (int i = 0; i < 3; i++) {
                     // Desempilha um pedido da pilha de entregas
                    Pedido pedido = (Pedido) pilhaEntregas.desempilhar();
                    // Remove o pedido do histórico de pedidos
                    historicoPedidos.excluiNo(pedido.getNumero());
                }
                entregasRealizadas = 0;
            }
            JOptionPane.showMessageDialog(null, "Entrega realizada com sucesso!");
        }
    }
}
