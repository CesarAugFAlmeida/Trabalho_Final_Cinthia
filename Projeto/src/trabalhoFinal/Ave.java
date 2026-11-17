package trabalhoFinal;

public class Ave extends AnimalSelvagem {
	private String porte;
	
	 public Ave(int id, String nome, double peso, String ferimentos, String raca, String estagioDesenvolvimento,String porte) {
	     super(id, nome, peso, ferimentos, raca, estagioDesenvolvimento);
	     this.porte = porte;
	 }
	 
	 public String getPorte() {
		return porte;
	}
	
	 public String getDietaEspecifica() {
		 if (this.getPorte().equalsIgnoreCase("Muito pequeno porte")) {
			 return "Sementes e frutas pequenas";
		 }
		 if (this.getPorte().equalsIgnoreCase("Pequeno porte")) {
			 return "Sementes e frutas";
		 }
		 if (this.getPorte().equalsIgnoreCase("Médio porte")) {
			 return "Ração extrudada, sementes, frutas, nozes e insetos";
		 }
		 if (this.getPorte().equalsIgnoreCase("Grande porte")) {
			 return "Ração extrudada, frutas e insetos";
		 }
		 if (this.getPorte().equalsIgnoreCase("Porte gigante")) {
			 return "Ração à base de milho, folhas e raízes";
		 }
		 if (this.getPorte().equalsIgnoreCase("Ave de Rapina") || this.getRaca().equalsIgnoreCase("Ave de Rapina")) {
			 return "Peixes e pequenos roedores";
		 }
		 else {
			 return "Porte não definido";
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
				return this.getNome() + " com o ID: " + this.getId() + " pode ser realocado externamente";
			}
			else {
				return this.getNome() + " com o ID: " + this.getId() + " está em um estado desconhecido";
			}
			
	
	 }
	}