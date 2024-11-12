import { Diario } from "./Diario";

export class Historico {
    public id!: number;
    public diario!: Diario;
    public usuario!: String;
    public campos!: String;
    public data!: Date;
}