import { Component, OnInit } from '@angular/core';
import { Balancete } from '../model/Balancete';
import { BalanceteService } from '../services/balancete.service';

@Component({
  selector: 'app-balancete',
  templateUrl: './balancete.component.html',
  styleUrl: './balancete.component.css'
})
export class BalanceteComponent implements OnInit {

  balancete : Balancete[] =[];

  constructor( private balanceteService : BalanceteService ){}

  ngOnInit() : void {
    this.balanceteService.getBalancete().subscribe(data => {
      this.balancete = data;
    })
  }
}
