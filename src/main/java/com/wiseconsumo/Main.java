package com.wiseconsumo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.wiseconsumo.model.Abastecimento;
import com.wiseconsumo.service.CalculadoraEstatisticas;
import com.wiseconsumo.util.GeradorDeDados;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main (String[] args){

        Scanner leitor = new Scanner(System.in);
        List<Abastecimento> historico = new ArrayList<>();

        GeradorDeDados.preencherHistorico(historico);

        System.out.println(">>> WiseConsumo: Histórico carregado (Março e Abril).");
        while(true){
            System.out.println("------ MENU WiseConsumo ------");
            System.out.println("OPÇÃO 1: Regristrar Abastecimento");
            System.out.println("OPÇÃO 2: Historico de Abastecimento ");
            System.out.println("OPÇÃO 3: Relatório de Abastecimentos ");
            System.out.println("OPÇÃO 0: Sair do menu");
            System.out.println("Digite a opção desejada: ");

            int opcao = leitor.nextInt();

            if(opcao==1){
                System.out.println("------Insira as informações solicitadas------");

                System.out.println("Digite a kilometragem atual: ");
                double km = leitor.nextDouble();

                System.out.println("Digite a quantidade de litros abastecidos: ");
                double litros = leitor.nextDouble();

                System.out.println("Digite o valor total gasto: ");
                BigDecimal valor = leitor.nextBigDecimal();

                LocalDate data = LocalDate.now();

                //Validação dos dados de KM - Deve ser maior do que o anterior
                boolean kmValido = true;


                if (!historico.isEmpty()) {
                    // Ultimo carro cadastrado
                    double ultimoKm = historico.get(historico.size() - 1).getQuilometragemAtual();

                    if (km <= ultimoKm) {
                        System.out.println("❌ ERRO: A quilometragem digitada (" + km + ") deve ser MAIOR que a do último registro (" + ultimoKm + ").");
                        System.out.println("Abastecimento cancelado. Tente novamente.");
                        kmValido = false;
                    }
                }

                   // Caso km válido
                if (kmValido) {
                    Abastecimento abastecimento_1 = new Abastecimento(km, litros, valor, data);
                    historico.add(abastecimento_1);
                    System.out.println("\n✅ Abastecimento registrado com sucesso!");
                }

                System.out.println("\n");
            } else if (opcao == 2) {
                System.out.println("\n--- HISTÓRICO DE ABASTECIMENTOS ---");
                System.out.println("Escolha a opção de histórico geral ou por período específico");
                System.out.println("OPÇÃO 1: Histórico Geral (Todos os abastecimentos)");
                System.out.println("OPÇÃO 2: Histórico por período específico");
                System.out.print("Digite a opção desejada: ");

                int opcaoHistorico = leitor.nextInt();

                if (opcaoHistorico == 1) {

                    System.out.println("\n--- HISTÓRICO GERAL ---");
                    if (historico.isEmpty()) {
                        System.out.println("Nenhum abastecimento registrado ainda.");
                    } else {
                        // O seu for original
                        for (Abastecimento a : historico) {
                            a.mostrarInfo();
                            System.out.println("\n");
                        }
                    }

                } else if (opcaoHistorico == 2) {

                    // 1. LIMPANDO O TECLADO
                    leitor.nextLine();

                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    try {
                        // 2. LENDO AS DATAS
                        System.out.print("Digite a Data Inicial (dd/mm/aaaa): ");
                        LocalDate dataInicio = LocalDate.parse(leitor.nextLine(), formatador);

                        System.out.print("Digite a Data Final (dd/mm/aaaa): ");
                        LocalDate dataFim = LocalDate.parse(leitor.nextLine(), formatador);

                        // 3. FILTRANDO A LISTA
                        CalculadoraEstatisticas calculadora = new CalculadoraEstatisticas();
                        List<Abastecimento> historicoFiltrado = calculadora.filtrarPorDatas(historico, dataInicio, dataFim);

                        // 4. VERIFICANDO E IMPRIMINDO
                        if (historicoFiltrado.isEmpty()) {
                            System.out.println("\n❌ Nenhum abastecimento encontrado entre " + dataInicio + " e " + dataFim + "\n");
                        } else {
                            System.out.println("\n--- HISTÓRICO DO PERÍODO (" + historicoFiltrado.size() + " registros) ---");

                            // O mesmo for, mas agora usando a lista "historicoFiltrado"
                            for (Abastecimento a : historicoFiltrado) {
                                a.mostrarInfo();
                                System.out.println("\n");
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("\n❌ Formato de data inválido. Cancelando operação...\n");
                    }

                } else {
                    System.out.println("Opção Inválida, digite uma opção válida");
                }
            }else if(opcao ==3){
                System.out.println("\n--- RELATORIO DOS ABASTECIMENTOS ---");
                System.out.println("Escolha a opção de relatorio geral ou por perido especifico");
                System.out.println("OPÇÃO 1: Relatório Geral (Todos os abastecimentos)");
                System.out.println("OPÇÃO 2: Relatório por périodo específico");
                System.out.println("Digite a opção desejada: ");

               int opcaoRelatorio = leitor.nextInt();

               if(opcaoRelatorio==1){
                   CalculadoraEstatisticas calculadora = new CalculadoraEstatisticas();
                   //Totais
                   System.out.println("Total de Litros Abastecidos: "+calculadora.calcularTotalLitros(historico)+"L");
                   System.out.println("Total Gasto: R$"+calculadora.calcularTotalGasto(historico));
                   BigDecimal precoMedio = calculadora.calcularPrecoMedioCombustivel(historico);
                   System.out.println("Preço Médio Pago no Combustível: R$ " + precoMedio + " / Litro");

                   //Media de Km/l
                   double mediaGeral = calculadora.calcularMediaGeralKML(historico);
                   if (mediaGeral > 0) {
                       System.out.printf("Média geral do veiculo: %.1f km/l %n", mediaGeral);
                   }
                   System.out.println("\n");

                   //calculadora.calcularEExibirKML(historico);

               }else if(opcaoRelatorio == 2){
                   leitor.nextLine();

                   DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                   try {
                       // 2. LENDO AS DATAS
                       System.out.print("Digite a Data Inicial (dd/mm/aaaa): ");
                       LocalDate dataInicio = LocalDate.parse(leitor.nextLine(), formatador);

                       System.out.print("Digite a Data Final (dd/mm/aaaa): ");
                       LocalDate dataFim = LocalDate.parse(leitor.nextLine(), formatador);

                       // 3. FILTRANDO A LISTA
                       CalculadoraEstatisticas calculadora = new CalculadoraEstatisticas();
                       List<Abastecimento> historicoFiltrado = calculadora.filtrarPorDatas(historico, dataInicio, dataFim);

                       // 4. VERIFICANDO SE ENCONTROU ALGO E IMPRIMINDO
                       if (historicoFiltrado.isEmpty()) {
                           System.out.println("\n❌ Nenhum abastecimento encontrado entre " + dataInicio + " e " + dataFim + "\n");
                       } else {
                           System.out.println("\n--- RELATÓRIO DO PERÍODO (" + historicoFiltrado.size() + " registros) ---");

                           // Usamos a lista nova "historicoFiltrado" em vez do "historico" original
                           System.out.println("Total de Litros Abastecidos: " + calculadora.calcularTotalLitros(historicoFiltrado) + "L");
                           System.out.println("Total Gasto: R$" + calculadora.calcularTotalGasto(historicoFiltrado));

                           BigDecimal precoMedio = calculadora.calcularPrecoMedioCombustivel(historicoFiltrado);
                           System.out.println("Preço Médio Pago no Combustível: R$ " + precoMedio + " / Litro");

                           double mediaGeral = calculadora.calcularMediaGeralKML(historicoFiltrado);
                           if (mediaGeral > 0) {
                               System.out.printf("Média geral do período: %.1f km/l %n", mediaGeral);
                           }
                           System.out.println("\n");
                       }

                   } catch (Exception e) {
                       // Formato invalido
                       System.out.println("\n❌ Formato de data inválido. Cancelando operação...\n");
                   }
                }else{
                    System.out.println("Opção Inválida, digite uma opção válida");
                }

            }else if(opcao==0){
                System.out.println("Saiu do menu");
                break;
            }else{
                System.out.println("Opção Inválida, digite uma opção válida");

            }

        }

    }
}


        /*
        Abastecimento abastecimento_1 = new Abastecimento(10500,5,new BigDecimal(30), LocalDate.now());

        System.out.println("=== TESTE DE ABASTECIMENTO ===");
        System.out.println("KM do Carro: " + abastecimento_1.getQuilometragemAtual());
        System.out.println("Litros Colocados: " + abastecimento_1.getLitrosAbastecidos());
        System.out.println("Valor Pago: R$ " + abastecimento_1.getValorGasto());
        System.out.println("Data do Registro: " + abastecimento_1.getData());

        abastecimento_1.setLitrosAbastecidos(8);

        System.out.println("Litros Colocados: " + abastecimento_1.getLitrosAbastecidos());

         */