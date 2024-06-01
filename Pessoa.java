
public class Pessoa {
    private String CPF;
    private String Nome;
    private char Sexo;
    private int Idade;

    public Pessoa(String CPF, String Nome, char Sexo, int Idade) {
        this.CPF = CPF;
        this.Nome = Nome;
        this.Sexo = Sexo;
        this.Idade = Idade;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getNome() {
        return Nome;
    }

    public void setIdade(int Idade) {
        this.Idade = Idade;
    }

    public int getIdade() {
        return Idade;
    }

    public void setSexo(char Sexo) {
        this.Sexo = Sexo;
    }

    public char getSexo() {
        return Sexo;
    }

    public String Imprimir() {
        String pessoa = "Nome: "+ this.Nome+ "\n" + 
                        "CPF: "+this.CPF + "\n" +
                        "Sexo: "+this.Sexo + "\n" +
                        "Idade: "+this.Idade;
        return pessoa;
    }


}
