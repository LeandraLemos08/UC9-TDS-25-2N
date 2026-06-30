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
}

 const conta1 = new ContaBancaria(1000, "Joe");
 console.log(conta1.titular)
 console.log(conta1.saldo)
 conta1.depositar(500)
 console.log(conta1.saldo)
