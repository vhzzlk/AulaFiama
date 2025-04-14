package Aula8;

import java.util.Scanner;

public class SituaçãoDesafiadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o salário bruto: R$ ");
        double salarioBruto = scanner.nextDouble();

        System.out.print("Digite o número de dependentes: ");x
        int dependentes = scanner.nextInt();

        System.out.print("Digite o tipo do plano de saúde (Básico/Avançado/Nenhum): ");
        String planoSaude = scanner.next().toUpperCase();

        System.out.print("Vale Transporte (SIM/NÃO): ");
        String valeTransporte = scanner.next().toUpperCase();

        System.out.print("Vale Alimentação (SIM/NÃO): ");
        String valeAlimentacao = scanner.next().toUpperCase();

        System.out.print("Vale Refeição (SIM/NÃO): ");
        String valeRefeicao = scanner.next().toUpperCase();

        // Cálculo do INSS
        double descontoINSS = calcularINSS(salarioBruto);

        // Cálculo do IRRF
        double descontoIRRF = calcularIRRF(salarioBruto - descontoINSS, dependentes);

        // Cálculo do plano de saúde
        double descontoPlanoSaude = planoSaude.equals("BÁSICO") ? 150 : planoSaude.equals("AVANÇADO") ? 300 : 0;

        // Cálculo do vale transporte
        double descontoValeTransporte = valeTransporte.equals("SIM") ? Math.min(salarioBruto * 0.06, 140) : 0;

        // Cálculo do vale alimentação
        double descontoValeAlimentacao = valeAlimentacao.equals("SIM") ? 200 : 0;

        // Cálculo do vale refeição
        double descontoValeRefeicao = valeRefeicao.equals("SIM") ? 250 : 0;

        // Total de descontos
        double totalDescontos = descontoINSS + descontoIRRF + descontoPlanoSaude + descontoValeTransporte + descontoValeAlimentacao + descontoValeRefeicao;

        // Salário líquido
        double salarioLiquido = salarioBruto - totalDescontos;

        // Percentual de descontos
        double percentualDescontos = (totalDescontos / salarioBruto) * 100;

        System.out.printf("Salário Líquido: R$ %.2f%n", salarioLiquido);
        System.out.printf("Percentual de Descontos: %.2f%%%n", percentualDescontos);
    }

    // Método para calcular o INSS
    private static double calcularINSS(double salarioBruto) {
        if (salarioBruto <= 1302) {
            return salarioBruto * 0.08;
        } else if (salarioBruto <= 2571) {
            return salarioBruto * 0.09;
        } else if (salarioBruto <= 3856) {
            return salarioBruto * 0.11;
        } else {
            return salarioBruto * 0.14;
        }
    }

    // Método para calcular o IRRF
    private static double calcularIRRF(double salarioBaseIRRF, int dependentes) {
        double deducaoDependentes = dependentes * 189.59;

        if (salarioBaseIRRF <= 1903.98) {
            return 0;
        } else if (salarioBaseIRRF <= 2826.65) {
            return (salarioBaseIRRF - 142.80 - deducaoDependentes) * 0.075;
        } else if (salarioBaseIRRF <= 3751.05) {
            return (salarioBaseIRRF - 354.80 - deducaoDependentes) * 0.15;
        } else if (salarioBaseIRRF <= 4664.68) {
            return (salarioBaseIRRF - 636.13 - deducaoDependentes) * 0.225;
        } else {
            return (salarioBaseIRRF - 869.36 - deducaoDependentes) * 0.275;
        }
    }
}