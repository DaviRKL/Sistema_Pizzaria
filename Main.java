import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        String[] tamanhos = new String[3];
        tamanhos[0] = "Pequena";
        tamanhos[1] = "Média";
        tamanhos[2] = "Grande";
        Color buttonBackground = new Color(220, 20, 60);
        Color buttonForeground = new Color(255, 255, 255);
        Color buttonSelect = new Color(255, 255, 255);
        SistemaPedidos sistema = new SistemaPedidos(10, 10);
        Pilha cardapio = new Pilha(20);
        ImageIcon iconeOriginal = new ImageIcon("./imagens/PizzaLogo.png");
        Image imagemOriginal = iconeOriginal.getImage();
        Image iconeRedimencionado = imagemOriginal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        boolean continuar = true;
        ImageIcon icone = new ImageIcon(iconeRedimencionado);
        UIManager.put("OptionPane.background", new Color(255, 165, 0));
        UIManager.put("Panel.background", new Color(255, 165, 0));
        UIManager.put("OptionPane.errorIcon", icone);
        UIManager.put("OptionPane.informationIcon", icone);
        UIManager.put("OptionPane.warningIcon", icone);
        UIManager.put("OptionPane.questionIcon", icone);
        UIManager.put("OptionPane.messageDialog", "PIZZARIA JAVA");
        UIManager.put("Button.background", buttonBackground);
        UIManager.put("Button.foreground", buttonForeground);
        UIManager.put("Button.select", buttonSelect);

        while (continuar) {
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

            switch (escolha) {
                case "1":
                    String resposta = JOptionPane.showInputDialog("Digite o nome do sabor de Pizza:");
                    cardapio.empilhar(resposta);
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null,
                            cardapio.desempilhar() + " foi retirado do cardápio com sucesso!");
                    break;
                case "3":
                        JOptionPane.showMessageDialog(null, cardapio.exibirListaSabores());
                    break;
                case "4":
                    try {
                        String[] opcoes = cardapio.exibirSabores().toArray(new String[0]);
                        Integer numeroSabor = JOptionPane.showOptionDialog(null, "Escolha uma sabor de Pizza:",
                                "Escolha um sabor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icone,
                                opcoes,
                                opcoes[0]);
                        String sabor = opcoes[numeroSabor];
                        Integer numeroTamanho = JOptionPane.showOptionDialog(null, "Escolha o tamanho da pizza:",
                                "Escolha um tamanho", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, icone,
                                tamanhos, tamanhos[0]);
                        String tamanho = tamanhos[numeroTamanho];
                        String endereco = JOptionPane.showInputDialog("Digite o endereço de entrega:");
                        double distancia = Double
                                .parseDouble(JOptionPane.showInputDialog("Digite a distância (em km):"));
                        sistema.adicionarPedido(sabor, tamanho, endereco, distancia);
                        break;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha a lista de sabores!");
                        break;
                    }

                case "5":
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
