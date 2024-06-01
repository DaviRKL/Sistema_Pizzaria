import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SistemaPedidos sistema = new SistemaPedidos(10, 10);
        Pilha cardapio = new Pilha(20);
        ImageIcon iconeOriginal = new ImageIcon("./imagens/PizzaLogo.png");
        Image imagemOriginal = iconeOriginal.getImage();
        Image iconeRedimencionado = imagemOriginal.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
        boolean continuar = true;
        ImageIcon icone = new ImageIcon(iconeRedimencionado);
        UIManager.put("OptionPane.background", new Color(255, 165, 0 ));
        UIManager.put("Panel.background", new Color(255,165,0));
        UIManager.put("OptionPane.errorIcon", icone);
        UIManager.put("OptionPane.informationIcon", icone);
        UIManager.put("OptionPane.warningIcon", icone);
        UIManager.put("OptionPane.questionIcon", icone);
        UIManager.put("OptionPane.messageDialog", "PIZZARIA JAVA");

        while (continuar) {
            String escolha = JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                    "1. Adicionar sabor de pizza no cardápio\n" +
                    "2. Retirar sabor de pizza do cardápio\n" +
                    "3. Adicionar pedido\n" +
                    "4. Cancelar pedido\n" +
                    "5. Listar todos os pedidos\n" +
                    "6. Incluir pedidos para preparo\n" +
                    "7. Incluir pedidos para entrega\n" +
                    "8. Gerar relatório para entrega\n" +
                    "9. Informar entrega realizada\n" +
                    "10. Sair"
                    );

            switch (escolha) {
                case "1":
                String resposta = JOptionPane.showInputDialog("Digite o nome do sabor de Pizza:");
                cardapio.empilhar(resposta);
                break;
                case "2":
                JOptionPane.showMessageDialog(null, cardapio.desempilhar() + " foi retirado do cardápio com sucesso!");
                break;
                case "3":
                    String sabor = JOptionPane.showInputDialog("Escolha um sabor de pizza do nosso cardápio! \n" + cardapio.exibirSabores());
                    String tamanho = JOptionPane.showInputDialog("Digite o tamanho da pizza:");
                    String endereco = JOptionPane.showInputDialog("Digite o endereço de entrega:");
                    double distancia = Double.parseDouble(JOptionPane.showInputDialog("Digite a distância (em km):"));
                    sistema.adicionarPedido(sabor, tamanho, endereco, distancia);
                    break;
                case "4":
                    int numeroPedidoCancelar = Integer
                            .parseInt(JOptionPane.showInputDialog("Digite o número do pedido a ser cancelado:"));
                    sistema.cancelarPedido(numeroPedidoCancelar);
                    break;
                case "5":
                    sistema.listarTodosPedidos();
                    break;
                case "6":
                    sistema.incluirPedidosPreparo();
                    break;
                case "7":
                    sistema.incluirPedidosEntrega();
                    break;
                case "8":
                    sistema.gerarRelatorioEntrega();
                    break;
                case "9":
                    sistema.informarEntregaRealizada();
                    break;
                case "10":
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }

        JOptionPane.showMessageDialog(null, "Programa encerrado.");
    }
}
