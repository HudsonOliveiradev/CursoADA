public class Main {
    public static double calculoSalario( double[] input) {

        return novoSalario(1, 5.7, 4800);
    }
    public static double novoSalario(double tempo, double inflacao, double salario){
        double salarioAtualizado = 0;
        double novaInflacao = 0.0;
        if((tempo > 1) && (tempo <= 5)){
            novaInflacao = (inflacao/100) + 0.01;
            salarioAtualizado = salario * (1 + novaInflacao);
            return salarioAtualizado;

        } else if((tempo>5) && (tempo <= 10)){
            novaInflacao = (inflacao/100) + 0.015;
            salarioAtualizado = salario * (1 + novaInflacao);
            return salarioAtualizado;
        } else if(tempo > 10){
            novaInflacao = (inflacao/100) + 0.02;
            salarioAtualizado = salario * (1 + novaInflacao);
            return salarioAtualizado;
        } else {
            novaInflacao = (inflacao/100);
            salarioAtualizado = salario * (1 + novaInflacao);
            return salarioAtualizado;
        }
    }
}