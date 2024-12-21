import { Component, OnInit, ViewChild } from '@angular/core';
import { DiarioService } from '../services/diario.service';
import { Diario } from '../model/Diario';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AlertasComponent } from '../alertas/alertas.component';

@Component({
  selector: 'app-diario',
  templateUrl: './diario.component.html',
  styleUrl: './diario.component.css'
})
export class DiarioComponent implements OnInit {

  // Objeto
  lancamento: Diario[] = [];

  // Objeto para Filtros
  lancamentoFiltrado: Diario[] = [];
  filtro = {
    id: '',
    historico: '',
    transacao: '',
    data: '',
    valor: ''
  }

  // Variáveis de controle de Alertas
  alertaMensagem : string = '';
  alertaTipo : string = '';
  alertaMostrar : boolean = false;

  // Variável para expandir as linhas
  expandedRowIndex: number | null = null;

  @ViewChild(AlertasComponent) alertas! : AlertasComponent;

  constructor(
    private diarioService: DiarioService,
    private router: Router
  ) { }

  ngOnInit(): void {

    // Chamando o método GET Lançamento
    this.diarioService.getLancamentos().subscribe({
      next: (data) => {
        this.lancamento = data;
        this.lancamentoFiltrado = data;
      },
    error: error => console.error(error)
    });
  }

/* MÉTODO READ, UPDATE e DELETE */

  // Método GET para os Lançamentos 
  getLancamentos() {
    this.diarioService.getLancamentos().subscribe(data => {
      this.lancamento = data;
    })
  }

  // Método PUT para o Lançamento
  updateLancamento(id: number) {
    this.router.navigate(['atualizar', id]);
  }

  // Método DELETE para o Lançamento
  deleteLancamento(id: number) {
    this.diarioService.deleteLancamento(id).subscribe({
      next: data => {

        console.log(data);
        
        this.alertas.mostrarAlerta("Lançamento deletado com sucesso!", 'success'),

        // this.getLancamentos();

        setTimeout(() => {
          location.reload();
        }, 3000);

      },
      error: error => {

        console.log(error);

        this.alertas.mostrarAlerta('Erro ao deletar o lançamento.', 'danger');

      }
    })
  }
/* Fim do READ, UPDATE e DELETE */

  // Filtros 
  filtros(): void {
    this.lancamentoFiltrado =this.lancamento.filter((lancamento => {

      const matchId = this.filtro.id 
      //? lancamento.id.toString().includes(this.filtro.id.toString())
        ? lancamento.id === Number(this.filtro.id)
        : true;

      const matchHistorico = this.filtro.historico
        ? lancamento.historico.toLowerCase().includes(this.filtro.historico.toLowerCase())
        : true;

      const matchTransacao = this.filtro.transacao
        ? lancamento.transacao === this.filtro.transacao
        : true;

      const matchData = this.filtro.data
        ? lancamento.data.includes(this.filtro.data) //=== this.filtro.data
        : true;

      const matchValor = this.filtro.valor
        ? lancamento.valor.toString().includes(this.filtro.valor) // === Number(this.filtro.valor)
        : true;

        return matchId && matchHistorico && matchTransacao && matchData && matchValor;
      
      }))
  }

// MÉTODOS PARA OCULTAR OPÇÕES 

  // Definindo a expansão da linha com o ID
  toggleRow(index: number) {
    this.expandedRowIndex = this.expandedRowIndex === index ? null : index;
  }

// MÉTODO PARA DEFINIR LANÇAMENTO DE "DÉBITO" E "CRÉDITO"

  // Método para Débito
  isDebito(transacao: string): boolean {
    return transacao.toLowerCase() === "débito";
  }

  // Método para Crédito
  isCredito(transacao: string): boolean {
    return transacao.toLowerCase() === "crédito";
  }

// MÉTODO PARA SOMAR DE "DÉBITO", "CREDITO" E "TOTAL"

  // Soma do Crédito
  get totalCredito() : number {
    return this.lancamento
      .filter((item) => item.transacao === 'CRÉDITO')
      .reduce((total, item) => total + item.valor, 0);
  }

  // Soma do Débito
  get totalDebito() : number {
    return this.lancamento
      .filter((item) => item.transacao === 'DÉBITO')
      .reduce((total, item) => total + item.valor, 0);
  }

  // Total Débito e Crédito
  get saldoGeral() : number {
    return this.totalCredito - this.totalDebito;
  }

// DEFININDO CLASSE BOOTSTRAP PARA VALORES POSITIVOS E NEGATIVOS 
  
  // Atribuindo valores
  getBootstrapClass() {
    return (this.totalCredito - this.totalDebito > 0 ) ? 'text-primary' : 'text-danger';
  }

}