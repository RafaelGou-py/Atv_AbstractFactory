package org.example;

interface PassagemFactory {
    PassagemPreco criarPassagemAerea(double valorPassagem);
}

class PassagemAereaNacionalFactory implements PassagemFactory {
    @Override
    public PassagemPreco criarPassagemAerea(double valorPassagem) {
        return new PassagemAereaNacional(valorPassagem);
    }
}

class PassagemAereaInternacionalFactory implements PassagemFactory {
    @Override
    public PassagemPreco criarPassagemAerea(double valorPassagem) {
        return new PassagemAereaInternacional(valorPassagem);
    }
}

interface PassagemPreco {
    double calcularPrecoTotal();
}

class PassagemAereaNacional implements PassagemPreco {
    private double valorDaPassagem;

    public PassagemAereaNacional(double valorPassagem) {
        this.valorDaPassagem = valorPassagem;
    }

    @Override
    public double calcularPrecoTotal() {
        double taxaEmbarque = 0.05 * valorDaPassagem;
        double taxaBagagem = 0.03 * valorDaPassagem;
        return valorDaPassagem + taxaEmbarque + taxaBagagem;
    }
}

class PassagemAereaInternacional implements PassagemPreco {
    private double valorDaPassagem;

    public PassagemAereaInternacional(double valorPassagem) {
        this.valorDaPassagem = valorPassagem;
    }

    @Override
    public double calcularPrecoTotal() {
        double taxaDeEmbarque = 0.10 * valorDaPassagem;
        double taxaDeBagagem = 0.05 * valorDaPassagem;
        return valorDaPassagem + taxaDeEmbarque + taxaDeBagagem;
    }
}

class SistemaReservaPassagens {
    public static void main(String[] args) {
        //É necessario trocar o valor e o tipo de passagem aq abaixo para dar valores variados
        double valorPassagem = 670.77;
        String tipoEmbarque = "Nacional";

        PassagemFactory factory;
        if (tipoEmbarque.equalsIgnoreCase("Nacional")){
            factory = new PassagemAereaNacionalFactory();
        }

        else if (tipoEmbarque.equalsIgnoreCase("Internacional")){
            factory = new PassagemAereaInternacionalFactory();
        }

        else{
            System.out.println("Tipo de embarque inválido.");
            return;
        }

        PassagemPreco passagemAerea = factory.criarPassagemAerea(valorPassagem);

        double precoTotal = passagemAerea.calcularPrecoTotal();
        System.out.println("Preço total da passagem: R$" + precoTotal);

    }

}