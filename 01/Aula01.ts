class ContaBancaria{
  public saldo: number;
  public titular: string;

  constructor(saldoInicial: number, titular: string){
    this.saldo = saldoInicial;
    this.titular = titular;
  }

 public depositar(valor: number): void{
    if(valor <= 0){
        console.log("Valor de déposito inválido.")
        return;
    }
    this.saldo += valor;
 }
   public sacar(valor: number): void{
    if(this.saldo == 0){
       console.log("Valor de saque inválido.")
       return;
    }
     this.saldo - valor;
   }
   public verSaldo(): number{
    return this.saldo;
   }
   public liberarCartao(answer: boolean): void{
    if(this.saldo < 5000){
      console.log("Cartão não aprovado.")
      return;
    }
    this.saldo > 5000
    console.log("Cartão aprovado.")
    return;
   }
}

 const conta1 = new ContaBancaria(1000, "Joe");
 console.log(`Titular da Conta: ${conta1.titular}`)
 console.log(`Saldo: ${conta1.saldo}`)
 conta1.depositar(500)
 console.log(`Saldo após déposito: ${conta1.saldo}`)
 console.log(conta1.sacar(200))
 console.log(`Saldo atual: ${conta1.verSaldo()}`)
 console.log(conta1.liberarCartao(false))