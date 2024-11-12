import { Component } from '@angular/core';
import { Razao } from '../model/Razao';
import { RazaoService } from '../services/razao.service';

@Component({
  selector: 'app-razao',
  templateUrl: './razao.component.html',
  styleUrl: './razao.component.css'
})
export class RazaoComponent {

  razao : Razao[] = [];

  constructor( private razaoService : RazaoService ){}

  ngOnInit() : void {
    this.razaoService.getRazao().subscribe(data => {
      this.razao = data
    });
  }

}
