import { Component } from '@angular/core';

@Component({
  selector: 'app-alertas',
  templateUrl: './alertas.component.html',
  styleUrl: './alertas.component.css'
})
export class AlertasComponent {

  mensagem : string =  '';
  bootstrapClasse : string = '';
  aparecer : boolean = false;

  mostrarAlerta(mensagem : string, tipo : string) : void {
    this.mensagem = mensagem;
    this.bootstrapClasse = `alert-${tipo}`;
    this.aparecer = true;

    setTimeout(() => {
      this.aparecer = false;
    }, 5000)

  }

}
