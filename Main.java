import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Criando uma instancia de Sistema pedidos 
        SistemaPedidos sistema = new SistemaPedidos(10, 10);

        //Criando uma pilha cardapio, que vai conter as opcoes de pizza
        Pilha cardapio = new Pilha(20);

        //Criando a lista de tamanho de pizzas e preenchendo
        String[] tamanhos = new String[3];
        tamanhos[0] = "Pequena";
        tamanhos[1] = "Média";
        tamanhos[2] = "Grande";

        boolean continuar = true;
        
        //pegando a imagem que vai ser o icone, redimencionando, e definindo como icone no JOptionPane
        ImageIcon iconeOriginal = new ImageIcon("./imagens/PizzaLogo.png");
        Image imagemOriginal = iconeOriginal.getImage();
        Image iconeRedimencionado = imagemOriginal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon icone = new ImageIcon(iconeRedimencionado);
        UIManager.put("OptionPane.errorIcon", icone);
        UIManager.put("OptionPane.informationIcon", icone);
        UIManager.put("OptionPane.warningIcon", icone);
        UIManager.put("OptionPane.questionIcon", icone);

        //Definindo as cores dos elementos da tela
        UIManager.put("OptionPane.background", new Color(255, 165, 0));
        UIManager.put("Panel.background", new Color(255, 165, 0));
        UIManager.put("Button.background", new Color(220, 20, 60));
        UIManager.put("Button.foreground", new Color(255, 255, 255));
        UIManager.put("Button.select", new Color(255, 255, 255));

        //iniciando repetição
        while (continuar) {
            //Exibindo as açoes que o usuario pode fazer e salvando a sua escolha na String escolha
            String escolha = JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                            "1. Adicionar sabor de pizza no cardápio\n" +
                            "2. Retirar sabor de pizza do cardápio\n" +
                            "3. Exibir Cardápio \n" +
                            "4. Adicionar pedido\n" +
                            "5. Cancelar pedido\n" +
                            "6. Listar todos os pedidos\n" +
                            "7. Incluir pedidos para preparo\n" +
                            "8. Incluir pedidos para entrega\n" +
                            "9. Gerar relatório para entrega\n" +
                            "10. Informar entrega realizada\n" +
                            "11. Sair");
            
            //verificando qual opcao ele escolheu e executando a acao correspondente
            switch (escolha) {
                case "1":
                    // Adiciona um sabor de pizza no cardápio
                    String resposta = JOptionPane.showInputDialog("Digite o nome do sabor de Pizza:");
                    cardapio.empilhar(resposta);
                    break;
                case "2":
                    //Retira um sabor de pizza do cardápio
                    JOptionPane.showMessageDialog(null,
                            cardapio.desempilhar() + " foi retirado do cardápio com sucesso!");
                    break;
                case "3":
                    //Exibi Cardápio
                        JOptionPane.showMessageDialog(null, cardapio.exibirListaSabores());
                    break;
                case "4":
                    //tenta executar as acoes
                    try {
                        //Transforma o ArrayList de opçoes em um Array comum, para que ele possa ser usado no showOptionDialog 
                        String[] opcoes = cardapio.exibirSabores().toArray(new String[0]);

                        //Escolhe um sabor
                        Integer numeroSabor = JOptionPane.showOptionDialog(null, "Escolha uma sabor de Pizza:",
                                "Escolha um sabor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icone,
                                opcoes,
                                opcoes[0]);
                        //pega o nome do sabor de acordo com o que ele escolheu nas opcoes, pois la ele retorna apenas um numero e nao o nome
                        String sabor = opcoes[numeroSabor];

                        //Escolhe um tamanho
                        Integer numeroTamanho = JOptionPane.showOptionDialog(null, "Escolha o tamanho da pizza:",
                                "Escolha um tamanho", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icone,
                                tamanhos, tamanhos[0]);
                        //pega o nome do tamanho de acordo com o que ele escolheu nas opcoes, pois la ele retorna apenas um numero e nao o nome        
                        String tamanho = tamanhos[numeroTamanho];
                        
                        
                        String endereco = JOptionPane.showInputDialog("Digite o endereço de entrega:");
                        double distancia = Double
                                .parseDouble(JOptionPane.showInputDialog("Digite a distância (em km):"));

                        //Adiciona um pedido no sistema usando os valores escolhas dele
                        sistema.adicionarPedido(sabor, tamanho, endereco, distancia);
                        break;
                    } 
                    //caso falhe, ele exibe esta mensagem
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha a lista de sabores!");
                        break;
                    }

                case "5":
                    //Cancela um pedido
                    int numeroPedidoCancelar = Integer
                            .parseInt(JOptionPane.showInputDialog("Digite o número do pedido a ser cancelado:"));
                    sistema.cancelarPedido(numeroPedidoCancelar);
                    break;
                case "6":
                    sistema.listarTodosPedidos();
                    break;
                case "7":
                    sistema.incluirPedidosPreparo();
                    break;
                case "8":
                    sistema.incluirPedidosEntrega();
                    break;
                case "9":
                    sistema.gerarRelatorioEntrega();
                    break;
                case "10":
                    sistema.informarEntregaRealizada();
                    break;

                case "11":
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }

        JOptionPane.showMessageDialog(null, "Programa encerrado.");
    }
}
