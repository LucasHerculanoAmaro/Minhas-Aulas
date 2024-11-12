import { Conta } from "./Conta";
import { Historico } from "./Historico";
import { Razao } from "./Razao";

export class Diario {
    public id!: number;
    public data!: Date;
    public historico!: Historico;
    // public razoes!: Razao;
    public valor!: number;
    public credito!: { id: number } //Conta;
    public debito!: { id: number } //Conta;
}