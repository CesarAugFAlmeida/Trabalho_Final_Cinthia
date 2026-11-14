package trabalhoFinal;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

	static Scanner scan = new Scanner(System.in);
	static ArrayList<AnimalSelvagem> lista = new ArrayList<AnimalSelvagem>();
	static int opcao = -1;
	
	public static void main(String[] args) {
		
		while (opcao != 0) {
			menu();
			lerOpcao(scan);
			switch (opcao) {
			case 1:
				cadastrarAnimal();
				break;
			case 2:
				consultarAnimais();
				break;
			case 3:
				atualizarAnimal();
				break;
			case 4:
				removerAnimal();
				break;
			case 5:
				gerenciarAnimais(1);  // 1 para Dieta (Polimorfismo)
				break;
			case 6:
				gerenciarAnimais(2); // 2 para Vacina
				break;
			case 7:
				gerenciarAnimais(3); // 3 para Ferimentos
				break;
			case 8:
				gerenciarAnimais(4); // 4 para Realocação
				break;
			case 0:
				System.out.println("Encerrando o sistema...");
				break;
			case -1:
				// Erro tratado no ValidadorEntrada, apenas pula o ciclo
				break;
			default:
				System.out.println("Opção inválida! Digite um número entre 0 e 8.");
			}
			
			if (opcao != 0 && opcao != -1) {
				System.out.println("\nPressione ENTER para continuar...");
				scan.nextLine();
			}
		}
	}

	public static void menu() {
		System.out.println("\n============= MENU PRINCIPAL ============="
				+ "\n1. Cadastrar novo animal"
				+ "\n2. Consultar animais"
				+ "\n3. Atualizar cadastro do animal"
				+ "\n4. Remover animal"
				+ "\n5. Mostrar dieta do animal"
				+ "\n6. Mostrar situação vacinal do animal"
				+ "\n7. Mostrar situação dos ferimentos do animal"
				+ "\n8. Mostrar situação da realocação do animal"
				+ "\n0. Sair"
				+ "\nResp: ");
	}

	// ==================== MÉTODOS DOS CASES ====================

	public static void cadastrarAnimal() {
		System.out.println("\n==== CADASTRAR NOVO ANIMAL ====");


			System.out.print("Digite o ID do animal: ");
			int id = scan.nextInt();
			scan.nextLine();

			System.out.print("Digite o nome do animal: ");
			String nome = scan.nextLine();

			System.out.print("Digite o peso do animal (kg): ");
			double peso = scan.nextDouble();
			scan.nextLine();

			System.out.print("Escolha a situação dos ferimentos:\n"
					+ "1. Grave\n"
					+ "2. Moderado\n"
					+ "3. Leve\n"
					+ "4. Cicatrizado\n"
					+ "Resp:");
			lerOpcao(scan);
			String ferimentos = null;
			switch(opcao) {
			case 1:
				ferimentos = "Grave";
				break;
			case 2:
				ferimentos = "Moderado";
				break;
			case 3:
				ferimentos = "Leve";
				break;
			case 4:
				ferimentos = "Cicatrizado";
				break;
			default:
				System.out.println("Opção inválida. A operação foi cancelada");
				return;
			}

			System.out.print("Digite a raça do animal: ");
			String raca = scan.nextLine();

			System.out.println("Escolha qual o estágio de desenvolvimento da espécime:\n"
					+ "1. Filhote\n"
					+ "2. Subadulto\n"
					+ "3. Adulto\n"
					+ "4. Geriátrico\n"
					+ "Resp:");
			String estagioDesenvolvimento = null;
			lerOpcao(scan);
			switch (opcao) {
				case 1:
					estagioDesenvolvimento = "Filhote";
					break;
				case 2:
					estagioDesenvolvimento = "Subadulto";
					break;
				case 3:
					estagioDesenvolvimento = "Adulto";
					break;
				case 4:
					estagioDesenvolvimento = "Geriátrico";
					break;
				default:
					System.out.println("Opção inválida. A operação foi cancelada");
					return;
			}
			AnimalSelvagem novoAnimal;
			System.out.println("Escolha o tipo de animal:");
			System.out.println("1. Ave");
			System.out.println("2. Mamífero");
			System.out.println("3. Réptil");
			System.out.print("Resp: ");
			lerOpcao(scan);
			
			switch (opcao) {
				case 1:
					System.out.println("Escolha o porte da ave resgatada\n"
							+ "1. Muito pequeno\n"
							+ "2. Pequeno\n"
							+ "3. Médio\n"
							+ "4. Grande\n"
							+ "5. Gigante\n"
							+ "6. Ave de Rapina (Escolha majoritariamente está opção se a ave se enquandra nas características dessa espécie)\n"
							+ "Resp:");
					lerOpcao(scan);
					String porte = null;
					switch (opcao) {
					case 1:
						porte = "Muito pequeno";
						break;
					case 2:
						porte = "Pequeno";
						break;
					case 3:
						porte = "Médio";
						break;
					case 4:
						porte = "Grande";
						break;
					case 5:
						porte = "Gigante";
						break;
					case 6:
						porte = "Ave de Rapina";
					default:
						System.out.println("Opção inválida. A operação foi cancelada");
						return;
					}
					novoAnimal = new Ave(id, nome, peso, ferimentos, raca, estagioDesenvolvimento, porte);
					break;
				case 2:
					System.out.println("Escolha a alimentação do animal:\n"
							+ "1. Herbívoro\n"
							+ "2. Carnívoro\n"
							+ "3. Onívoro\n"
							+ "Resp: ");
					lerOpcao(scan);
					String alimentacao;
					switch (opcao) {
					case 1:
						alimentacao = "Herbívoro";
						break;
					case 2:
						alimentacao = "Carnívoro";
						break;
					case 3:
						alimentacao = "Onívoro";
						break;
					default:
						System.out.println("Opção inválida. A operação foi cancelada");
						return;
					}
					novoAnimal = new Mamifero(id, nome, peso, ferimentos, raca, estagioDesenvolvimento, alimentacao);
					break;
				case 3:
					System.out.println("Escolha a alimentação do animal:\n"
							+ "1. Herbívoro\n"
							+ "2. Carnívoro\n"
							+ "3. Onívoro\n"
							+ "Resp: ");
					lerOpcao(scan);
					String alimentacaoReptil = null;
					switch (opcao) {
					case 1:
						alimentacaoReptil = "Herbívoro";
						break;
					case 2:
						alimentacaoReptil = "Carnívoro";
						break;
					case 3:
						alimentacaoReptil = "Onívoro";
						break;
					case 4:
						alimentacaoReptil = "Insetívoro";
						break;
					default:
						System.out.println("Opção inválida. A operação foi cancelada");
						break;
					}
					novoAnimal = new Reptil(id, nome, peso, ferimentos, raca, estagioDesenvolvimento, alimentacaoReptil);
					break;
				default:
					System.out.println("Tipo inválido! Cadastro cancelado.");
					return;
			}

			lista.add(novoAnimal);
			System.out.println("Animal cadastrado com sucesso!");
	}

	public static void consultarAnimais() {
		System.out.println("\n--- LISTA DE ANIMAIS CADASTRADOS ---");
		if (lista.isEmpty()) {
			System.out.println("Nenhum animal cadastrado no momento.");
			return;
		}
		for (AnimalSelvagem a : lista) {
			System.out.println(a.toString() + " | Classe: " + a.getClass().getSimpleName());
		}
	}

	public static void atualizarAnimal() {
		System.out.println("\n--- ATUALIZAR CADASTRO ---");
		try {
			System.out.print("Digite o ID do animal que deseja atualizar: ");
			int id = scan.nextInt();
			scan.nextLine();
			AnimalSelvagem animal = buscarPorId(id);
			if (animal != null) {
				System.out.println("O que deseja atualizar?\n"
						+ "1. Peso\n"
						+ "2. Estado dos ferimentos\n"
						+ "3. Estágio de desenvolvimento\n"
						+ "Resp: ");
				lerOpcao(scan);
				switch (opcao) {
				case 1:
					System.out.print("Peso atual (" + animal.getPeso() + ")");
					animal.setPeso(scan.nextDouble());
					scan.nextLine();
					break;
				case 2:
					System.out.print("Estado dos ferimentos atual (" + animal.getFerimentos() + ")");
					System.out.print("Escolha a situação dos ferimentos:\n"
							+ "1. Grave\n"
							+ "2. Moderado\n"
							+ "3. Leve\n"
							+ "4. Cicatrizado\n"
							+ "Resp:");
					lerOpcao(scan);
					switch(opcao) {
					case 1:
						animal.setFerimentos("Grave");
						break;
					case 2:
						animal.setFerimentos("Moderado");
						break;
					case 3:
						animal.setFerimentos("Leve");
						break;
					case 4:
						animal.setFerimentos("Cicatrizado");
						break;
					default:
						System.out.println("Opção inválida. A operação foi cancelada");
						return;
					}
					
					break;
				case 3:
					System.out.print("Estágio de desenvolvimento atual (" + animal.getEstagioDesenvolvimento() + ")");
					System.out.println("Escolha qual o estágio de desenvolvimento da espécime:\n"
							+ "1. Filhote\n"
							+ "2. Subadulto\n"
							+ "3. Adulto\n"
							+ "4. Geriátrico\n"
							+ "Resp:");
					lerOpcao(scan);
					switch (opcao) {
						case 1:
							animal.setEstagioDesenvolvimento("Filhote");
							break;
						case 2:
							animal.setEstagioDesenvolvimento("Subadulto");
							break;
						case 3:
							animal.setEstagioDesenvolvimento("Adulto");
							break;
						case 4:
							animal.setEstagioDesenvolvimento("Geriátrico");
							break;
						default:
							System.out.println("Opção inválida. A operação foi cancelada");
							return;
					}
					break;
				default:
					System.out.println("Opção inválida. A operação foi cancelada");
					return;
				}
				
				System.out.println("Cadastro atualizado com sucesso!");
			} else {
				System.out.println("Animal com ID " + id + " não encontrado.");
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("Formato numérico inválido.");
			scan.nextLine();
		}
	}

	public static void removerAnimal() {
		System.out.println("\n--- REMOVER ANIMAL ---");
		try {
			System.out.print("Digite o ID do animal que deseja remover: ");
			int id = scan.nextInt();
			scan.nextLine();

			AnimalSelvagem animal = buscarPorId(id);
			if (animal != null) {
				lista.remove(animal);
				System.out.println("Animal removido com sucesso!");
			} else {
				System.out.println("Animal não encontrado.");
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("ID inválido.");
			scan.nextLine();
		}
	}

	public static void gerenciarAnimais(int acao) {
		if (lista.isEmpty()) {
			System.out.println("Nenhum animal cadastrado.");
			return;
		}
		
		try {
			System.out.print("Digite o ID do animal: ");
			int id = scan.nextInt();
			scan.nextLine();

			AnimalSelvagem animal = buscarPorId(id);
			if (animal != null) {
				System.out.println("----------------------------------------");
				System.out.println("Animal: " + animal.getNome() + " (ID: " + animal.getId() + ")");
				switch (acao) {
					case 1:
						System.out.println("Dieta Específica: " + animal.getDietaEspecifica());
						break;
					case 2:
						System.out.println("Situação Vacinal: " + animal.situacaoVacinas());
						break;
					case 3:
						System.out.println("Situação dos Ferimentos: " + animal.situacaoFerimentos());
						break;
					case 4:
						System.out.println("Situação da Realocação: " + animal.situacaoRealocacao());
						break;
				}
				System.out.println("----------------------------------------");
			} else {
				System.out.println("Animal não encontrado.");
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("[ERRO] ID inválido.");
			scan.nextLine();
		}
	}

	public static AnimalSelvagem buscarPorId(int id) {
		for (AnimalSelvagem a : lista) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}

	    public static int lerOpcao(Scanner scan) {
	        try {
	            opcao = scan.nextShort();
	            scan.nextLine(); // Consome a quebra de linha pendente
	            return opcao;
	        } catch (InputMismatchException e) {
	            System.out.println("\n[ERRO] Entrada inválida! Por favor, digite apenas números inteiros.");
	            scan.nextLine(); // Limpa o buffer do scanner para evitar loop infinito
	            return -1; // Retorna um valor inválido para o menu tratar
	        }
	    }
	}