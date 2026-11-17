package trabalhoFinal;

public abstract class AnimalSelvagem {
    private int id;
    private String nome;
    private double peso;
    private boolean vacinado;
    private String ferimentos;
    private String raca;
	private String estagioDesenvolvimento;

    public AnimalSelvagem(int id, String nome, double peso, String ferimentos, String raca, String estagioDesenvolvimento) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.vacinado = false;
        this.ferimentos = ferimentos;
        this.raca = raca;
        this.estagioDesenvolvimento = estagioDesenvolvimento;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Raça: " + raca + " | Peso: " + peso + " kg"
                + " | Estágio: " + estagioDesenvolvimento + " | Ferimentos: " + ferimentos
                + " | Vacinado: " + (vacinado ? "Sim" : "Não");
    }

    public abstract String getDietaEspecifica();
    public abstract String situacaoVacinas();
    public abstract String situacaoFerimentos();
    public abstract String situacaoRealocacao();
    
    public int getId() {
		return id;
	}
    
    public String getNome() { 
    	return nome; 
    }

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public boolean isVacinado() {
		return vacinado;
	}

	public void setVacinado() {
		this.vacinado = true;
	}

	public String getFerimentos() {
		return ferimentos;
	}

	public void setFerimentos(String ferimentos) {
		this.ferimentos = ferimentos;
	}

	public String getRaca() {
		return raca;
	}

	public String getEstagioDesenvolvimento() {
		return estagioDesenvolvimento;
	}

	public void setEstagioDesenvolvimento(String estagioDesenvolvimento) {
		this.estagioDesenvolvimento = estagioDesenvolvimento;
	}

	
    
}