package trabalhoFinal;

public class Mamifero extends AnimalSelvagem {

	private String alimentacao;
	
	public Mamifero(int id, String nome, double peso, String ferimentos, String raca, String estagioDesenvolvimento, String alimentacao) {
		super(id, nome, peso, ferimentos, raca, estagioDesenvolvimento);
		this.alimentacao = alimentacao;
	}

	public String getAlimentacao() {
		return alimentacao;
	}

	@Override
	public String getDietaEspecifica() {
		if (this.getEstagioDesenvolvimento().equalsIgnoreCase("Filhote")) {
			return "Amamentação frequente";
		}
		if (this.getAlimentacao().equalsIgnoreCase("Herbívoro")) {
			return "Plantas, frutas e legumes";
		}
		if (this.getAlimentacao().equalsIgnoreCase("Carnívoro")) {
			return "Carne crua";
		}
		if (this.getAlimentacao().equalsIgnoreCase("Onívoro")) {
			return "Frutas, legumes e carne";
		}
		else {
			return "Alimentação não informada";
		}
	}

	@Override
	public String situacaoVacinas() {
		 if (super.isVacinado() == false) {
			 return this.getNome() + " com o ID: " + this.getId() + " ainda não foi vacinado";
		 }else {
			 return this.getNome() + " com o ID: " + this.getId() + " foi vacinado";
		 }
	}

	@Override
	public String situacaoFerimentos() {
		if (this.getFerimentos().equalsIgnoreCase("Grave")) {
			return this.getNome() + " com o ID: " + this.getId() + " precisa de acompanhamento constante";
		}
		if (this.getFerimentos().equalsIgnoreCase("Moderado")) {
			return this.getNome() + " com o ID: " + this.getId() + " precisa ser monitorado com frequência";
		}
		if (this.getFerimentos().equalsIgnoreCase("Leve")) {
			return this.getNome() + " com o ID: " + this.getId() + " precisa ser monitorado com baixa urgência";
		}
		if (this.getFerimentos().equalsIgnoreCase("Cicatrizado")) {
			return this.getNome() + " com o ID: " + this.getId() + " não precisa de monitoramento";
		}
		else {
			return this.getNome() + " com o ID: " + this.getId() + " está em um estado desconhecido";
		}
	}

	@Override
	public String situacaoRealocacao() {
		if (this.getFerimentos().equalsIgnoreCase("Grave") && this.isVacinado() == false) {
			return this.getNome() + " com o ID: " + this.getId() + " não pode ser realocado e\n O animal não foi vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Grave") && this.isVacinado() == true) {
			return this.getNome() + " com o ID: " + this.getId() + " não pode ser realocado e\n O animal foi vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Moderado") && this.isVacinado() == false) {
			return this.getNome() + " com o ID: " + this.getId() + " pode ser realocado internamente e\n O animal não foi vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Moderado") && this.isVacinado() == true) {
			return this.getNome() + " com o ID: " + this.getId() + " pode ser realocado internamente e\n O animal foi vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Leve") && this.isVacinado() == false) {
			return this.getNome() + " com o ID: " + this.getId() + " pode ser realocado em um grande grupo internamente e\n O animal não foi vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Leve") && this.isVacinado() == true) {
			return this.getNome() + " com o ID: " + this.getId() + " pode ser realocado em um grande grupo internamente e\n O animal foi vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Cicatrizado") && this.isVacinado() == false) {
			return this.getNome() + " com o ID: " + this.getId() + " não pode ser realocado externamente por não estar vacinado";
		}
		if (this.getFerimentos().equalsIgnoreCase("Cicatrizado") && this.isVacinado() == true) {
			if (this.getEstagioDesenvolvimento().equalsIgnoreCase("Filhote") || this.getEstagioDesenvolvimento().equalsIgnoreCase("Subadulto")) {
				return this.getNome() + " com o ID: " + this.getId() + " não pode ser realocado externamente por não ter atingido a maioridade ainda";
			} else {
				return this.getNome() + " com o ID: " + this.getId() + " pode ser realocado externamente";
			}
		}
		else {
			return this.getNome() + " com o ID: " + this.getId() + " está em um estado desconhecido";
		}
	}

	
}
