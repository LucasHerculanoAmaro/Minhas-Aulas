import { Component } from '@angular/core';
import { Diario } from '../model/Diario';
import { DiarioService } from '../services/diario.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-atualizar',
  templateUrl: './atualizar.component.html',
  styleUrl: './atualizar.component.css'
})
export class AtualizarComponent {

  lancamento : Diario = new Diario();

  id!: number;

  constructor(
    private diarioService: DiarioService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit () {
    this.id = this.route.snapshot.params['id'];
    this.diarioService.getLancamentoById(this.id).subscribe({
      next : data => {
        this.lancamento = data;
      },
      error : error => console.log(error)
    })
  }

  onSubmit() {
    this.diarioService.updateLancamento( this.id, this.lancamento ).subscribe({
      next : data => {
        this.goToLista();
      },
      error : error => console.log(error)
    })
  }

  goToLista() {
    this.router.navigate(['\diario']);
  }

}
