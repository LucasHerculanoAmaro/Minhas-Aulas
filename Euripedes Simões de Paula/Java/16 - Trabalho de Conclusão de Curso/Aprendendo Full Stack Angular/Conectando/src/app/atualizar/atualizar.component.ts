import { Component, ViewChild } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertasComponent } from '../alertas/alertas.component';

@Component({
  selector: 'app-atualizar',
  templateUrl: './atualizar.component.html',
  styleUrl: './atualizar.component.css'
})
export class AtualizarComponent {

  // Objeto
  lancamento : Diario = new Diario();

  // ID do Lançamento
  id!: number;

  // Variáveis de controle de Alertas
  alertaMensagem : string = '';
  alertaTipo : string = '';
  alertaMostrar : boolean = false;

  @ViewChild(AlertasComponent) alertas! : AlertasComponent;

  constructor(
    private diarioService: DiarioService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit () {

    // Chamando o método GET para determinado Lançamento
    this.id = this.route.snapshot.params['id'];
    this.diarioService.getLancamentoById(this.id).subscribe({
      next : data => {
        this.lancamento = data;
      },
      error : error => console.log(error)
    })

  }

  onSubmit() {

    // Lógica de transformação de Caixa Alta
    if (this.lancamento.transacao === "credito") {
      this.lancamento.transacao = "CRÉDITO"
    } else if (this.lancamento.transacao === "debito") {
      this.lancamento.transacao = "DÉBITO"
    } else {
      this.lancamento.transacao = "Não Definido"
    }

    // Lógica para Transação e Histórico sem registros
    if (!this.lancamento.historico || this.lancamento.historico.trim() === "") {
      this.lancamento.historico = "Sem Registro";
    }

    // Método UPDATE
    this.diarioService.updateLancamento( this.id, this.lancamento ).subscribe({
      next : () => {

        console.log("Lançamento atualizado com sucesso.", 'success');

        this.alertas.mostrarAlerta("Lançamento atualizado com sucesso!", 'success');

        setTimeout(() => {
          this.goToLista();
        }, 3000);

      },
      error : error => {

        console.log("Erro ao criar o lançamento",error);

        this.alertas.mostrarAlerta("Erro ao atualizar o Lançamento", 'danger');

      }
    })
  }

  // Método para direcionamento de Rota
  goToLista() {
    this.router.navigate(['/diario']);
  }

}
