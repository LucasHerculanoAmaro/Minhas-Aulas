import { Conta } from "./Conta";
import { Diario } from "./Diario";

export class Razao {
    public id!: number;
    public conta!: Conta;
    public debito!: number;
    public credito!: number;
    public data!: Date;
    public historico!: String;
    public diario!: Diario;
}